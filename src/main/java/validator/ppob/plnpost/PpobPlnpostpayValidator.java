package validator.ppob.plnpost;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import database.PpobDataHelper;
import helper.CompareField;
import model.ppob.database.PpobTbLogpay;
import model.ppob.inquiry.additionaldata2.plnpost.PlnpostPayAdd2;
import model.ppob.payment.ReqPayment;
import model.ppob.payment.ResPayment;

public class PpobPlnpostpayValidator {
    private List<String> tmpMessage = new ArrayList<>();
    private List<String> message = new ArrayList<>();
    
    Gson gson = new Gson();
    PpobDataHelper dbHelper = new PpobDataHelper();
    PpobTbLogpay tbLogpay = new PpobTbLogpay();
    ResPayment resPayment;
    ReqPayment reqPayment;
    CompareField compareField;

    public List<String> getMessage() {
        return message;
    }

    public Boolean valid(ReqPayment reqPayment, ResPayment resPayment) {
        tbLogpay = dbHelper.get_data_logpay_by_trackingref(resPayment.getTrackingref().toString());
        return additionalDataValidator(resPayment);
        // return additionalDataValidator(resInquiry) & databaseValidator(tbLogpay)
        // & dbResponseValidator(resInquiry, tbLogpay)
        // & requestResponseValidator(reqInquiry, resInquiry);
    }

    private Boolean additionalDataValidator(ResPayment resPayment) {
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
                int i = 0;
                List<String> cutomerIdList = Arrays.asList(tbLogpay.getCustomerId().toString(),
                        resPayment.getCustomerid().toString(), plnPost.getIdPelanggan().toString());
                tmpMessage.add(CompareField.compare_value(cutomerIdList, "Customer ID"));
                List<String> totalAmountList = Arrays.asList(
                        tbLogpay.getAmount().toString(), resPayment.getTotalamount().toString(), 
                        plnPost.getTotalBayar().toString().replace(".", "").replace("Rp", ""));
                tmpMessage.add(CompareField.compare_value(totalAmountList, "Total Amount"));
                List<String> receiptNoList = Arrays.asList(tbLogpay.getReceiptNo().toString(),
                        resPayment.getReceiptcode().toString(), plnPost.getReff().toString());
                tmpMessage.add(CompareField.compare_value(receiptNoList, "Receipt No"));

                for (Field field : plnPost.getClass().getDeclaredFields()) {
                    field.setAccessible(true);
                    try {
                        String value = field.get(plnPost).toString();
                        if (i == 3 || i == 4 || i == 6) {
                            value = value.replace(".", "").replace("Rp", "");
                        }
                        String dbValue = switch (i) {
                            case 0 -> tbLogpay.getCustomerId().toString();
                            case 3 -> String.valueOf(tbLogpay.getNominal().intValue());
                            case 4 -> String.valueOf(tbLogpay.getAdminFee().intValue());
                            case 6 -> String.valueOf(tbLogpay.getAmount().intValue());
                            case 10 -> tbLogpay.getReceiptNo().toString();
                            default -> null;
                        };
                        // String responseValue = switch (i) {
                        // case 0 -> resPayment.getCustomerid().toString();
                        // case 6 -> resPayment.getTotalamount().toString();
                        // default -> null;
                        // };
                        // if (responseValue != null && !value.equals(responseValue)) {
                        // tmpMessage.add("- Additional "
                        // + (i == 0 ? "Customer ID"
                        // : i == 2 ? "Nominal"
                        // : i == 3 ? "Admin Fee"
                        // : "Total Amount")
                        // + " is incorrect");
                        // message.addAll(tmpMessage);
                        // return false;
                        // }
                        if (dbValue != null && !value.equals(dbValue)) {
                            System.out.println("Value: " + value + " | DB Value: " + dbValue);
                            tmpMessage.add("- Additional "
                                    + (i == 0 ? "Customer ID"
                                            : i == 3 ? "Nominal"
                                                    : i == 4 ? "Admin Fee"
                                                            : i == 6 ? "Total Amount"
                                                                    : "Receipt No")
                                    + " is incorrect");
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
        } else

        {
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

}
