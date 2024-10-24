package newsteplist.ppob;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import constant.ConstantPpob;
import helper.Helper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.FormatReport;
import services.ppob.AdvicePpob;
import services.ppob.InquiryPpob;
import services.ppob.PaymentPpob;
import services.ppob.Ppob;
import utility.CSVDataReader;
import utility.ExcelDataWriter;
import validator.ppob.validator.PpobAdviceValidator;
import validator.ppob.validator.PpobInquiryValidator;
import validator.ppob.validator.PpobPaymentValidator;

public class StepPpob {
    long StartTime;
    public List<Map<String, String>> csvData;
    Map<String, List<String>> validationNoteData = new HashMap<>();
    Map<String, String> statusData = new HashMap<>();
    Ppob ppob = new Ppob();
    int countTC = 0;
    int countPass = 0;
    int countNtPass = 0;
    String status = "Not Pass";
    String projectNm = "PPOB";

    InquiryPpob inquiry;
    PaymentPpob payment;
    AdvicePpob advice;
    Helper helper = new Helper();

    @Given("I have CSV data file for testing PPOB {string}")
    public void i_have_csv_data_file_for_testing_ppob(String excelNm) {
        StartTime = System.currentTimeMillis();
        try {
            csvData = CSVDataReader.readCSVData(excelNm);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @When("I perform testing prefer from CSV data for PPOB")
    public void i_perform_testing_prefer_from_csv_data_for_ppob() {
        for (Map<String, String> data : csvData) {
            String tmpTC = data.get("TC");
            if (data.get("IsInq").equalsIgnoreCase("TRUE")) {
                Helper.hitDelayTime();
                ppob.postInquiry(
                        tmpTC,
                        data.get("partnerId"),
                        data.get("productId"),
                        data.get("customerId"),
                        data.get("extendInfo"),
                        data.get("terminalId"));
                countTC++;
            }
            if (data.get("IsPay").equalsIgnoreCase("TRUE")) {
                Helper.hitDelayTime();
                ppob.postPayment(
                        tmpTC,
                        data.get("partnerId"),
                        data.get("productId"),
                        data.get("customerId"),
                        data.get("extendInfo"),
                        data.get("terminalId"),
                        data.get("totalAmount"));
                countTC++;
            }
            if (data.get("IsAdv").equalsIgnoreCase("TRUE")) {
                Helper.hitDelayTime();
                ppob.postAdvice(tmpTC,
                        data.get("partnerId"),
                        data.get("productId"),
                        data.get("customerId"),
                        data.get("extendInfo"),
                        data.get("terminalId"));
                countTC++;
            }
        }
    }

    @Then("I get the appropriate response for PPOB")
    public void i_get_the_appropriate_response_for_ppob() {
        for (Map<String, String> data : csvData) {
            String tmpTC = data.get("TC");
            if (data.get("IsInq").equalsIgnoreCase("TRUE")) {
                try {
                    inquiry = ppob.getInquiryPpobList().get(tmpTC);
                    if (!inquiry.getResInq().getRc().equals(" ")) {
                        PpobInquiryValidator inqValidator = PpobInquiryValidator.builder()
                                .resInquiry(inquiry.getResInq())
                                .reqInquiry(inquiry.getReqInq())
                                .build();
                        if (inquiry.getResInq().getRc().equalsIgnoreCase(data.get("RCInq")) & inqValidator.validate()) {
                            status = "Pass";
                            countPass++;
                        } else {
                            status = "Not Pass";
                            countNtPass++;
                        }
                        addValidationNoteData(tmpTC, "Inquiry", inqValidator.getValidationMessage());
                    } else {
                        countNtPass++;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    status = "Not Pass";
                    addValidationNoteData(tmpTC, "Inquiry", Collections.singletonList(e.toString()));
                }
                statusData.put(tmpTC + "-Inquiry", status);
                status = "Not Pass";
            }
            if (data.get("IsPay").equalsIgnoreCase("TRUE")) {
                System.out.println("Checking Payment response: " + tmpTC);
                try {
                    payment = ppob.getPaymentPpobList().get(tmpTC);
                    inquiry = ppob.getInquiryPpobList().get(tmpTC);
                    if (!payment.getResPay().getRc().equals(" ")) {
                        PpobPaymentValidator payValidator = PpobPaymentValidator.builder()
                                .resInquiry(inquiry.getResInq())
                                .resPayment(payment.getResPay()).reqPayment(payment.getReqPay()).build();
                        if (payment.getResPay().getRc().equalsIgnoreCase(data.get("RCPay")) & payValidator.validate()) {
                            status = "Pass";
                            countPass++;
                        } else {
                            countNtPass++;
                        }
                        addValidationNoteData(tmpTC, "Payment", payValidator.getValidationMessage());
                    } else {
                        countNtPass++;
                    }
                } catch (Exception e) {
                    status = "Not Pass";
                    addValidationNoteData(tmpTC, "Payment", Collections.singletonList(e.getMessage()));
                }
                statusData.put(tmpTC + "-Payment", status);
                status = "Not Pass";
            }
            if (data.get("IsAdv").equalsIgnoreCase("TRUE")) {
                System.out.println("Checking Advice response: " + tmpTC);
                try {
                    advice = ppob.getAdvicePpobList().get(tmpTC);
                    if (!advice.getResAdv().getRc().equals(" ")) {
                        PpobAdviceValidator advValidator = PpobAdviceValidator.builder()
                                .reqAdvice(advice.getReqAdv()).resAdvice(advice.getResAdv()).build();
                        if (advice.getResAdv().getRc().equalsIgnoreCase(data.get("RCAdv")) & advValidator.validate()) {
                            status = "Pass";
                            countPass++;
                        } else {
                            countNtPass++;
                        }
                        addValidationNoteData(tmpTC, "Advice", advValidator.getValidationMessage());
                    } else {
                        countNtPass++;
                    }
                } catch (Exception e) {
                    status = "Not Pass";
                    addValidationNoteData(tmpTC, "Advice", Collections.singletonList(e.getMessage()));
                }
                statusData.put(tmpTC + "-Advice", status);
                status = "Not Pass";
            }
        }
    }

    @Then("I create EXCEL report automation PPOB")
    public void i_create_excel_report_automation_ppob() throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            List<FormatReport> resultData = new ArrayList<>();
            for (Map<String, String> data : csvData) {
                InquiryPpob inquiry = null;
                String tmpTC = data.get("TC");
                if (data.get("IsInq").equalsIgnoreCase("TRUE")) {
                    inquiry = ppob.getInquiryPpobList().get(tmpTC);

                    String prettyReq = gson.toJson(inquiry.getReqInq());
                    String prettyRes = "";
                    try {
                        prettyRes = gson.toJson(inquiry.getResInq());
                    } catch (Exception e) {
                        prettyRes = "ERROR, Check Notes for Further explanation! ";
                    }
                    String validationNote = String.join("\n",
                            validationNoteData.getOrDefault(tmpTC + "-Inquiry", Collections.emptyList()));
                    validationNote += "\n Timetaken: ";
                    String rc = null;
                    String rcDesc = null;
                    if (inquiry.getResInq() != null) {
                        rc = inquiry.getResInq().getRc();
                        rcDesc = inquiry.getResInq().getRcdesc();
                    }

                    resultData.add(helper.createDataReport(data.get("TC"), data.get("caseDesc"), "Inquiry", "POST",
                            ConstantPpob.ppobInqV2,
                            data.get("TCTypeInq"), prettyReq, prettyRes, data.get("RCInq"), rc, rcDesc,
                            statusData.get(tmpTC + "-Inquiry"), validationNote));
                }
                if (data.get("IsPay").equalsIgnoreCase("TRUE")) {
                    payment = ppob.getPaymentPpobList().get(tmpTC);
                    String prettyReq = gson.toJson(payment.getReqPay());
                    String prettyRes = "";
                    try {
                        prettyRes = gson.toJson(payment.getResPay());
                    } catch (Exception e) {
                        prettyRes = "ERROR, Check Notes for Further explanation! ";
                    }
                    String validationNote = String.join("\n",
                            validationNoteData.getOrDefault(tmpTC + "-Payment", Collections.emptyList()));

                    validationNote += "\n Timetaken: ";
                    String rc = null;
                    String rcDesc = null;
                    if (payment.getResPay() != null) {
                        rc = payment.getResPay().getRc();
                        rcDesc = payment.getResPay().getRcdesc();
                    }

                    resultData.add(helper.createDataReport(data.get("TC"), data.get("caseDesc"), "Payment", "POST",
                            ConstantPpob.ppobPayV2,
                            data.get("TCTypePay"), prettyReq, prettyRes, data.get("RCPay"), rc, rcDesc,
                            statusData.get(tmpTC + "-Payment"), validationNote));
                }
                if (data.get("IsAdv").equalsIgnoreCase("TRUE")) {
                    advice = ppob.getAdvicePpobList().get(tmpTC);
                    String prettyReq = gson.toJson(advice.getReqAdv());
                    String prettyRes = "";
                    try {
                        prettyRes = gson.toJson(advice.getResAdv());
                    } catch (Exception e) {
                        prettyRes = "ERROR, Check Notes for Further explanation! ";
                    }
                    String validationNote = String.join("\n",
                            validationNoteData.getOrDefault(tmpTC + "-Advice", Collections.emptyList()));

                    validationNote += "\n Timetaken: ";
                    String rc = null;
                    String rcDesc = null;
                    if (advice.getResAdv() != null) {
                        rc = advice.getResAdv().getRc();
                        rcDesc = advice.getResAdv().getRcdesc();
                    }

                    resultData.add(helper.createDataReport(data.get("TC"), data.get("caseDesc"), "Advice", "POST",
                            ConstantPpob.ppobAdvV2,
                            data.get("TCTypeAdv"), prettyReq, prettyRes, data.get("RCAdv"), rc, rcDesc,
                            statusData.get(tmpTC + "-Advice"), validationNote));
                }
            }
            ExcelDataWriter.writeExcelData(projectNm, ConstantPpob.environmentSvr, resultData);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ExcelDataWriter.writeSummExcelData(projectNm, "Summary", countTC, countPass, countNtPass, StartTime);
    }

    private void addValidationNoteData(String tmpTC, String transactionType, List<String> validationNote) {

        validationNoteData.computeIfAbsent(tmpTC + "-" + transactionType, k -> new ArrayList<>())
                .addAll(validationNote);
    }
}
