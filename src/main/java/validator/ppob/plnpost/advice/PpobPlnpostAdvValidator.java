package validator.ppob.plnpost.advice;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.Gson;

import database.PpobDataHelper;
import helper.CompareField;
import model.ppob.advice.ReqAdvice;
import model.ppob.advice.ResAdvice;
import model.ppob.database.PpobTbLogpay;
import model.ppob.inquiry.additionaldata2.plnpost.PlnpostPayAdd2;

public class PpobPlnpostAdvValidator {
    private List<String> tmpMessage = new ArrayList<>();
    private List<String> tmpList = new ArrayList<>();
    private List<String> message = new ArrayList<>();

    Gson gson = new Gson();
    PpobDataHelper dbHelper = new PpobDataHelper();
    PpobTbLogpay tbLogpay = new PpobTbLogpay();
    CompareField compareField;

    public List<String> getMessage() {
        return message;
    }

    public Boolean valid(ReqAdvice reqAdvice, ResAdvice resAdvice) {
        tbLogpay = dbHelper.get_data_logpay_by_trackingref(resAdvice.getTrackingref().toString());
        return additionalDataValidator(resAdvice) & databaseValidator(tbLogpay) & dbResponseValidator(resAdvice,
                tbLogpay) & requestResponseValidator(reqAdvice, resAdvice);
    }

    private Boolean additionalDataValidator(ResAdvice resAdvice) {
        String typeAdditional = dbHelper.getAdditionalDataTypeByTrackingRef(resAdvice.getTrackingref(), "inquiry");
        tmpMessage.clear();
        tmpMessage.add("• Note For Additional Data : ");

        if (typeAdditional.equals("0") && dbHelper
                .get_categoryname_by_switchid(resAdvice.getProductid(), resAdvice.getPartnerid()).equals("PLNPOST")) {
            String additionalData = resAdvice.getAdditionaldatanew();

            if (additionalData != null && !additionalData.isEmpty()) {
                JSONArray arrayValue = new JSONObject(additionalData).getJSONArray("Ls_Resadd");

                for (int i = 0; i < arrayValue.length(); i++) {
                    JSONObject obj = arrayValue.getJSONObject(i);
                    if (i == 4 || i == 5 || i == 6)
                        obj.put("value", obj.getString("value").replace(".", "").replace("Rp", ""));

                    String expectedValue = switch (i) {
                        case 0 -> resAdvice.getCustomerid();
                        case 4 -> String.valueOf(tbLogpay.getNominal().intValue());
                        case 5 -> String.valueOf(tbLogpay.getAdminFee().intValue());
                        case 6 -> resAdvice.getAmount();
                        default -> null;
                    };

                    if (expectedValue != null && !obj.getString("value").equals(expectedValue)) {
                        tmpMessage.add("- Additional "
                                + (i == 0 ? "Customer ID"
                                        : i == 1 ? "Customer Name"
                                                : i == 4 ? "Nominal" : i == 5 ? "Admin Fee" : "Total Amount")
                                + " is incorrect");
                        message.addAll(tmpMessage);
                        return false;
                    }
                }
            }
        } else if (typeAdditional.equals("2") && dbHelper
                .get_categoryname_by_switchid(resAdvice.getProductid(), resAdvice.getPartnerid())
                .equals("PLNPOST")) {
            String additionalData = resAdvice.getAdditionaldatanew();
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                PlnpostPayAdd2 plnPost = objectMapper.readValue(additionalData, PlnpostPayAdd2.class);
                tmpList = Arrays.asList(tbLogpay.getCustomerId().toString(),
                        resAdvice.getCustomerid().toString(), plnPost.getIdPelanggan().toString());
                tmpMessage.add(CompareField.compare_value(tmpList, "Customer ID"));
                tmpList = Arrays.asList(tbLogpay.getAmount().toString(), resAdvice.getAmount().toString(),
                        plnPost.getTotalBayar().toString().replace(".", "").replace("Rp", ""));
                tmpMessage.add(CompareField.compare_value(tmpList, "Total Amount"));
                tmpList = Arrays.asList(tbLogpay.getReceiptNo().toString(),
                        resAdvice.getReceiptcode().toString(), plnPost.getReff().toString());
                tmpMessage.add(CompareField.compare_value(tmpList, "Receipt No"));
                tmpList = Arrays.asList(tbLogpay.getNominal().toString(), plnPost.getNominal().toString()
                        .replace(".", "").replace("Rp", ""));
                tmpMessage.add(CompareField.compare_value(tmpList, "Nominal"));
                tmpList = Arrays.asList(tbLogpay.getAdminFee().toString(), plnPost.getBiayaAdmin().toString()
                        .replace(".", "").replace("Rp", ""));
                tmpMessage.add(CompareField.compare_value(tmpList, "Admin Fee"));
            } catch (Exception e) {
                e.printStackTrace();
                message.addAll(tmpMessage);
                return false;
            }
        } else {
            tmpMessage.add("- Additional Data is empty");
            message.addAll(tmpMessage);
            return false;
        }
        message.addAll(tmpMessage);
        return true;
    }

    private Boolean databaseValidator(PpobTbLogpay tbLogpay) {
        tmpMessage.clear();
        boolean valid = true;
        tmpMessage.add("• Note For Database : ");
        Set<String> skipFields = new HashSet<>(Arrays.asList(
                "stan", "extendinfo", "finishedBy", "prevRc", "prevSupplierRc", "prevSupplierRcDesc",
                "adminCharge", "orderIdIsNull", "supplierId"));
        for (Field field : tbLogpay.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                Object value = field.get(tbLogpay);
                if (skipFields.toString().toLowerCase().contains(field.getName().toString().toLowerCase())) {
                    continue;
                } else if (value == null || value.toString().isEmpty() || value.toString().equals("")) {
                    tmpMessage.add(" - " + field.getName() + " is null or empty");
                    valid = false;
                } else if (value.toString().equals("0")) {
                    tmpMessage.add(" - " + field.getName() + " is 0");
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return valid;

    }

    private boolean dbResponseValidator(ResAdvice resAdvice, PpobTbLogpay tbLogpay) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<String> excludedFields = Arrays.asList("trxid", "trxdate", "signature");
        tmpMessage.clear();
        tmpMessage.add("• Validation Database Response : ");
        try {
            String jsonString = objectMapper.writeValueAsString(resAdvice);
            ObjectNode jsonNode = (ObjectNode) objectMapper.readTree(jsonString);
            jsonNode.set("switchid", jsonNode.remove("productid"));
            jsonNode.set("additionaldata", jsonNode.remove("additionaldatanew"));
            jsonNode.set("receiptno", jsonNode.remove("receiptcode"));

            ObjectNode tbLogpayNode = objectMapper.convertValue(tbLogpay, ObjectNode.class);

            tmpMessage.addAll(CompareField.compareJson(jsonNode, tbLogpayNode, excludedFields));
        } catch (Exception e) {
            e.printStackTrace();
        }
        message.addAll(tmpMessage);
        if (tmpMessage.toString().contains("different.")) {
            return false;
        }

        return true;
    }

    private boolean requestResponseValidator(ReqAdvice reqAdvice, ResAdvice resAdvice) {
        List<String> excludedFields = Arrays.asList("trxdate", "signature");
        tmpMessage.clear();
        tmpMessage.add("• Validation Request Response : ");
        try {
            tmpMessage.addAll(CompareField.compareObjects(reqAdvice, resAdvice, excludedFields));
        } catch (Exception e) {
            e.printStackTrace();
        }
        message.addAll(tmpMessage);
        if (tmpMessage.toString().contains("different.")) {
            return false;
        }
        return true;
    }

}
