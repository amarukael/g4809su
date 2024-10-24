package validator.ppob.plnpost.payment;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import database.PpobDataHelper;
import helper.CompareField;
import model.ppob.database.PpobTbLogpay;
import model.ppob.inquiry.additionaldata2.plnpost.PlnpostPayAdd2;
import model.ppob.payment.ResPayment;

public abstract class PpobPlnpostPayBaseValidator {
    protected List<String> tmpMessage = new ArrayList<>();
    protected List<String> tmpList = new ArrayList<>();
    protected List<String> message = new ArrayList<>();
    protected ObjectMapper objectMapper = new ObjectMapper();
    protected PpobDataHelper dbHelper = new PpobDataHelper();

    protected Boolean additionalDataValidator(ResPayment resPayment, PpobTbLogpay tbLogpay) {
        String typeAdditional = dbHelper.getAdditionalDataTypeByTrackingRef(resPayment.getTrackingref(), "inquiry");
        tmpMessage.clear();
        tmpMessage.add("• Note For Additional Data : ");

        if (typeAdditional.equals("0") && dbHelper
                .get_categoryname_by_switchid(resPayment.getProductid(), resPayment.getPartnerid()).equals("PLNPOST")) {
            String additionalData = resPayment.getAdditionaldatanew();

            if (additionalData != null && !additionalData.isEmpty()) {
                JSONArray arrayValue = new JSONObject(additionalData).getJSONArray("Ls_Resadd");

                for (int i = 0; i < arrayValue.length(); i++) {
                    JSONObject obj = arrayValue.getJSONObject(i);
                    if (i == 4 || i == 5 || i == 6)
                        obj.put("value", obj.getString("value").replace(".", "").replace("Rp", ""));

                    String expectedValue = switch (i) {
                        case 0 -> resPayment.getCustomerid();
                        case 4 -> String.valueOf(tbLogpay.getNominal().intValue());
                        case 5 -> String.valueOf(tbLogpay.getAdminFee().intValue());
                        case 6 -> resPayment.getTotalamount();
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
                .get_categoryname_by_switchid(resPayment.getProductid(), resPayment.getPartnerid())
                .equals("PLNPOST")) {
            String additionalData = resPayment.getAdditionaldatanew();
            ObjectMapper objectMapper = new ObjectMapper();

            try {
                PlnpostPayAdd2 plnPost = objectMapper.readValue(additionalData, PlnpostPayAdd2.class);
                tmpList = Arrays.asList(tbLogpay.getCustomerId().toString(),
                        resPayment.getCustomerid().toString(), plnPost.getIdPelanggan().toString());
                tmpMessage.add(CompareField.compare_value(tmpList, "Customer ID"));
                tmpList = Arrays.asList(tbLogpay.getAmount().toString(), resPayment.getTotalamount().toString(),
                        plnPost.getTotalBayar().toString().replace(".", "").replace("Rp", ""));
                tmpMessage.add(CompareField.compare_value(tmpList, "Total Amount"));
                tmpList = Arrays.asList(tbLogpay.getReceiptNo().toString(),
                        resPayment.getReceiptcode().toString(), plnPost.getReff().toString());
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

    protected Boolean databaseValidator(Object entity, Set<String> skipFields) {
        tmpMessage.clear();
        tmpMessage.add("• Note For Database : ");
        for (Field field : entity.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                Object value = field.get(entity);
                if (skipFields.contains(field.getName().toLowerCase())) {
                    continue;
                } else if (value == null || value.toString().isEmpty()) {
                    tmpMessage.add(" - " + field.getName() + " is null or empty");
                } else if (value.toString().equals("0")) {
                    tmpMessage.add(" - " + field.getName() + " is 0");
                } else {
                    tmpMessage.add(" - " + field.getName() + " is filled");
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        message.addAll(tmpMessage);
        return true;
    }

    protected Boolean dbResponseValidator(Object resPayment, Object tbEntity, List<String> excludedFields) {
        tmpMessage.clear();
        tmpMessage.add("• Validation Database Response : ");
        try {
            String jsonString = objectMapper.writeValueAsString(resPayment);
            ObjectNode resJson = (ObjectNode) objectMapper.readTree(jsonString);
            resJson.set("switchid", resJson.remove("productid"));
            resJson.set("amount", resJson.remove("totalamount"));
            resJson.set("additionaldata", resJson.remove("additionaldatanew"));
            resJson.set("receiptno", resJson.remove("receiptcode"));
            tmpMessage.addAll(CompareField.compareJson(resJson, objectMapper.convertValue(tbEntity, ObjectNode.class),
                    excludedFields));
        } catch (Exception e) {
            tmpMessage.add("Error: " + e.getMessage());
            e.printStackTrace();
        }
        message.addAll(tmpMessage);
        if (tmpMessage.toString().contains("different.")) {
            return false;
        }
        return true;
    }

    protected Boolean requestResponseValidator(Object reqPayment, Object resPayment, List<String> excludedFields) {
        tmpMessage.clear();
        tmpMessage.add("• Validation Request Response : ");
        try {
            tmpMessage.addAll(CompareField.compareObjects(reqPayment, resPayment, excludedFields));
        } catch (Exception e) {
            e.printStackTrace();
        }
        message.addAll(tmpMessage);
        if (tmpMessage.toString().contains("different.")) {
            return false;
        }

        return true;
    }

    public List<String> getMessage() {
        return message;
    }
}