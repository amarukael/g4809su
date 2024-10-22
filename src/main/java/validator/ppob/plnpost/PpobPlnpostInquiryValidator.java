package validator.ppob.plnpost;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import database.PpobDataHelper;
import helper.CompareField;
import model.ppob.database.PpobTbInquiry;
import model.ppob.inquiry.ReqInquiry;
import model.ppob.inquiry.ResInquiry;
import model.ppob.inquiry.additionaldata2.plnpost.PlnpostInqAdd2;
import validator.ppob.PpobInquiryValidator;

public class PpobPlnpostInquiryValidator {
    private List<String> tmpMessage = new ArrayList<>();
    private List<String> message = new ArrayList<>();
    Gson gson = new Gson();
    PpobDataHelper dbHelper = new PpobDataHelper();
    ResInquiry resInquiry;
    ReqInquiry reqInquiry;
    PpobTbInquiry tbInquiry = new PpobTbInquiry();

    PpobInquiryValidator ppobInquiryValidator;

    public List<String> getMessage() {
        return message;
    }

    public Boolean valid(ReqInquiry reqInquiry, ResInquiry resInquiry) {
        tbInquiry = dbHelper.get_data_inquiry_by_trackingref(resInquiry.getTrackingref().toString());
        return additionalDataValidator(resInquiry) & databaseValidator(tbInquiry)
                & dbResponseValidator(resInquiry, tbInquiry)
                & requestResponseValidator(reqInquiry, resInquiry);
    }

    private Boolean additionalDataValidator(ResInquiry resInquiry) {
        String typeAdditional = dbHelper.getAdditionalDataTypeByTrackingRef(resInquiry.getTrackingref(), "inquiry");
        tmpMessage.clear();
        tmpMessage.add("• Note For Additional Data : ");

        if (typeAdditional.equals("0") && dbHelper
                .get_categoryname_by_switchid(resInquiry.getProductid(), resInquiry.getPartnerid()).equals("PLNPOST")) {
            String additionalData = resInquiry.getAdditionaldatanew();

            if (additionalData != null && !additionalData.isEmpty()) {
                JSONArray arrayValue = new JSONObject(additionalData).getJSONArray("Ls_Resadd");

                for (int i = 0; i < arrayValue.length(); i++) {
                    JSONObject obj = arrayValue.getJSONObject(i);
                    if (i == 4 || i == 5 || i == 6)
                        obj.put("value", obj.getString("value").replace(".", "").replace("Rp", ""));

                    String expectedValue = switch (i) {
                        case 0 -> resInquiry.getCustomerid();
                        case 1 -> resInquiry.getCustomername();
                        case 4 -> String.valueOf(tbInquiry.getNominal().intValue());
                        case 5 -> String.valueOf(tbInquiry.getAdminFee().intValue());
                        case 6 -> resInquiry.getTotalamount();
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
                .get_categoryname_by_switchid(resInquiry.getProductid(), resInquiry.getPartnerid())
                .equals("PLNPOST")) {
            String additionalData = resInquiry.getAdditionaldatanew();
            ObjectMapper objectMapper = new ObjectMapper();

            try {
                PlnpostInqAdd2 plnpostInqAdd2 = objectMapper.readValue(additionalData, PlnpostInqAdd2.class);
                int i = 0;
                for (Field field : plnpostInqAdd2.getClass().getDeclaredFields()) {
                    field.setAccessible(true);

                    try {
                        String value = field.get(plnpostInqAdd2).toString();
                        if (i == 2 || i == 3 || i == 6) {
                            value = value.replace(".", "").replace("Rp", "");
                        }
                        String expectedValue = switch (i) {
                            case 0 -> resInquiry.getCustomerid().toString();
                            case 1 -> resInquiry.getCustomername().toString();
                            case 2 -> String.valueOf(tbInquiry.getNominal().intValue());
                            case 3 -> String.valueOf(tbInquiry.getAdminFee().intValue());
                            case 6 -> resInquiry.getTotalamount().toString();
                            default -> null;
                        };
                        if (expectedValue != null && !value.equals(expectedValue)) {
                            tmpMessage.add("- Additional "
                                    + (i == 0 ? "Customer ID"
                                            : i == 1 ? "Customer Name"
                                                    : i == 2 ? "Nominal"
                                                            : i == 3 ? "Admin Fee"
                                                                    : "Total Amount")
                                    + " is incorrect");
                            message.addAll(tmpMessage);
                            return false;
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                        message.addAll(tmpMessage);
                        return false;
                    }
                    i++;
                }
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
        if (tmpMessage.size() == 1) {
            tmpMessage.add(" - All fields are the same.");
            message.addAll(tmpMessage);
            return true;
        }
        message.addAll(tmpMessage);
        return true;
    }

    private Boolean databaseValidator(PpobTbInquiry tbInquiry) {
        tmpMessage.clear();
        tmpMessage.add("• Note For Database : ");
        for (Field field : tbInquiry.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            Set<String> skipFields = new HashSet<>(Arrays.asList(
                    "issync", "extendinfo", "hpno", "additionaldatapay",
                    "email", "oritransdate", "customerid2", "customerid3",
                    "reffno", "reffno2", "reffno3", "customerid1"));
            try {
                Object value = field.get(tbInquiry);
                if (skipFields.contains(field.getName().toLowerCase())) {
                    continue;
                } else if (value == null || (value instanceof String && ((String) value).isEmpty())) {
                    tmpMessage.add(" - " + field.getName() + " is null or empty");
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        if (tmpMessage.size() == 1) {
            tmpMessage.add(" - All fields are the same.");
            message.addAll(tmpMessage);
            return true;
        } else {
            message.addAll(tmpMessage);
            return false;
        }
    }

    private boolean dbResponseValidator(ResInquiry resInquiry, PpobTbInquiry tbInquiry) {
        List<String> excludedFields = Arrays.asList("trxid", "trxdate", "signature");
        tmpMessage.clear();
        try {
            tmpMessage.addAll(CompareField.compareObjects(resInquiry, tbInquiry, excludedFields,
                    "Database Response"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        message.addAll(tmpMessage);
        return true;
    }

    private boolean requestResponseValidator(ReqInquiry reqInquiry, ResInquiry resInquiry) {
        List<String> excludedFields = Arrays.asList("trxdate", "signature");
        tmpMessage.clear();
        try {
            tmpMessage.addAll(CompareField.compareObjects(reqInquiry, resInquiry, excludedFields,
                    "Request Response"));

        } catch (Exception e) {
            e.printStackTrace();
        }
        message.addAll(tmpMessage);
        return true;
    }
}
