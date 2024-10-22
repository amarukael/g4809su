package newsteplist.apiprojectnonalfa;

import static helper.Helper.*;
import static newsteplist.apiprojectnonalfa.Hooks.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.json.JSONObject;
import org.json.XML;

import constant.ConstantAPIProject;
import helper.Helper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.FormatReport;
import services.apiprojectnonalfa.AdviceApiproj;
import services.apiprojectnonalfa.ApiProjectNonAlfa;
import services.apiprojectnonalfa.InquiryApiproj;
import services.apiprojectnonalfa.PaymentApiproj;
import services.apiprojectnonalfa.ReversalApiproj;
import utility.CSVDataReader;
import utility.ExcelDataWriter;
import validator.apiprojectnonalfa.ApiProjectNonAlfaAdviceValidator;
import validator.apiprojectnonalfa.ApiProjectNonAlfaInquiryValidator;
import validator.apiprojectnonalfa.ApiProjectNonAlfaPaymentValidator;
import validator.apiprojectnonalfa.ApiProjectNonAlfaReversalValidator;

public class StepApiprojectNonAlfa {
    public List<Map<String, String>> excelData;
    ApiProjectNonAlfa apiProjectNonAlfa = new ApiProjectNonAlfa(scenario);
    Map<String, String> statusData = new HashMap<>();
    Map<String, List<String>> validationNoteData = new HashMap<>();

    Helper help = new Helper();

    int countPass = 0;
    int countNtPass = 0;
    int countTC = 0;
    String projectNm = "APIProject-Non-Alfa";

    long StartTime;

    @Given("I have Excel data file for testing ApiProject Non-Alfa {string}")
    public void i_have_excel_data_file_for_testing_external_dana(String excelNm) {
        StartTime = System.currentTimeMillis();
        // Ini nanti hit fungsi get data di excel
        try {
            Properties properties = new Properties();
            // Load properties from file
            // try (FileInputStream fis = new FileInputStream(excelNm)) {
            // properties.load(fis);
            // } catch (IOException e) {
            // e.printStackTrace();
            // System.exit(1);
            // }

            String pathExcel = properties.getProperty("extent.source.excel.filepath", null);
            String sheetNm = properties.getProperty("extent.source.excel.sheetnm", null);
            // String sheetPath = pathExcel + excelNm + "#" + sheetNm;
            excelData = CSVDataReader.readCSVData(excelNm);
        } catch (IOException e) {
            e.printStackTrace();
            scenario.log("Excel data is null. Check the file path.");
        }
    }

    @When("I perform testing prefer from Excel data for ApiProject Non-Alfa")
    public void i_perform_testing_prefer_from_excel_data() {
        for (Map<String, String> data : excelData) {
            String tmpTC = data.get("TC");

            scenario.log(tmpTC);

            if (data.get("IsInq").equalsIgnoreCase("TRUE")) {
                hitDelayTime();
                scenario.log("Proses Inquiry");
                apiProjectNonAlfa.postInquiry(
                        tmpTC,
                        data.get("partnerId"),
                        data.get("productId"),
                        data.get("customerId"),
                        data.get("terminalId"),
                        Integer.parseInt(data.get("isMD5New")));
                countTC++;
            }
            if (data.get("IsPay").equalsIgnoreCase("TRUE")) {
                hitDelayTime();
                scenario.log("Proses Payment");
                apiProjectNonAlfa.postPayment(
                        tmpTC,
                        data.get("partnerId"),
                        data.get("productId"),
                        data.get("customerId"),
                        data.get("terminalId"),
                        data.get("totalAmount"),
                        Integer.parseInt(data.get("isMD5New")) // TODO
                );
                countTC++;
            }
            if (data.get("IsAdv").equalsIgnoreCase("TRUE")) {
                hitDelayTime();
                scenario.log("Proses Advice");
                apiProjectNonAlfa.postAdvice(
                        tmpTC,
                        data.get("partnerId"),
                        data.get("productId"),
                        data.get("customerId"),
                        data.get("terminalId"),
                        Integer.parseInt(data.get("isMD5New")));
                countTC++;
            }
            if (data.get("IsRev").equalsIgnoreCase("TRUE")) {
                hitDelayTime();
                scenario.log("Proses Reversal");
                apiProjectNonAlfa.postReversal(
                        tmpTC,
                        data.get("partnerId"),
                        data.get("productId"),
                        data.get("customerId"),
                        data.get("terminalId"),
                        data.get("totalAmount"),
                        Integer.parseInt(data.get("isMD5New")) // TODO
                );
                countTC++;
            }
        }
    }

    @Then("I get the appropriate response for ApiProject Non-Alfa")
    public void i_get_the_appropriate_response() {
        for (Map<String, String> data : excelData) {
            String tmpTC = data.get("TC");
            String status = "Not Pass";

            InquiryApiproj inquiry = null;
            PaymentApiproj payment = null;
            AdviceApiproj advice = null;
            ReversalApiproj reversal = null;
            if (data.get("IsInq").equalsIgnoreCase("TRUE")) {
                scenario.log("Checking Inquiry response: " + tmpTC);
                try {
                    inquiry = apiProjectNonAlfa.getInquiryApiProjList().get(tmpTC);
                    // Pengecekan apakah ada pesan exception waktu hit
                    if (inquiry.getResApiInfo().getIsSuccessful()) {
                        ApiProjectNonAlfaInquiryValidator inqValidator = ApiProjectNonAlfaInquiryValidator.builder()
                                .resInquiry(inquiry.getResInq())
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
                        addValidationNoteData(tmpTC, "Inquiry",
                                Collections.singletonList(inquiry.getResApiInfo().getExceptionMessage()));
                    }
                } catch (Exception e) {
                    // Digunakan untuk catch RC response yang tidak ada
                    status = "Not Pass";
                    addValidationNoteData(tmpTC, "Inquiry", Collections.singletonList(e.toString()));
                }
                statusData.put(tmpTC + "-Inquiry", status);
                status = "Not Pass";
            }
            if (data.get("IsPay").equalsIgnoreCase("TRUE")) {
                scenario.log("Checking Payment response: " + tmpTC);
                try {
                    payment = apiProjectNonAlfa.getPaymentApiProjList().get(tmpTC);
                    // Pengecekan apakah ada pesan exception waktu hit
                    if (payment.getResApiInfo().getIsSuccessful()) {
                        ApiProjectNonAlfaPaymentValidator payValidator = ApiProjectNonAlfaPaymentValidator.builder()
                                .resInquiry(inquiry != null ? inquiry.getResInq() : null)
                                .resPayment(payment.getResPay())
                                .build();
                        if (payment.getResPay().getRc().equalsIgnoreCase(data.get("RCPay")) & payValidator.validate()) {
                            status = "Pass";
                            countPass++;
                        } else {
                            countNtPass++;
                        }
                        addValidationNoteData(tmpTC, "Payment", payValidator.getValidationMessage());
                    } else {
                        countNtPass++;
                        addValidationNoteData(tmpTC, "Payment",
                                Collections.singletonList(payment.getResApiInfo().getExceptionMessage()));
                    }
                } catch (Exception e) {
                    // Digunakan untuk catch RC response yang tidak ada
                    status = "Not Pass";
                    addValidationNoteData(tmpTC, "Payment", Collections.singletonList(e.getMessage()));
                }
                statusData.put(tmpTC + "-Payment", status);
                status = "Not Pass";
            }
            if (data.get("IsAdv").equalsIgnoreCase("TRUE")) {
                scenario.log("Checking Advice response: " + tmpTC);
                try {
                    advice = apiProjectNonAlfa.getAdviceApiProjList().get(tmpTC);
                    if (advice.getResApiInfo().getIsSuccessful()) {
                        ApiProjectNonAlfaAdviceValidator advValidator = ApiProjectNonAlfaAdviceValidator.builder()
                                .resPayment(payment != null ? payment.getResPay() : null)
                                .resAdvice(advice.getResAdv())
                                .build();
                        if (advice.getResAdv().getRc().equalsIgnoreCase(data.get("RCAdv")) & advValidator.validate()) {
                            status = "Pass";
                            countPass++;
                        } else {
                            countNtPass++;
                        }
                        addValidationNoteData(tmpTC, "Advice", advValidator.getValidationMessage());
                    } else {
                        // Jika terdapat exception
                        countNtPass++;
                        addValidationNoteData(tmpTC, "Advice",
                                Collections.singletonList(advice.getResApiInfo().getExceptionMessage()));
                    }
                } catch (Exception e) {
                    // Digunakan untuk catch RC response yang tidak ada
                    status = "Not Pass";
                    addValidationNoteData(tmpTC, "Advice", Collections.singletonList(e.getMessage()));
                }
                statusData.put(tmpTC + "-Advice", status);
                status = "Not Pass";
            }
            if (data.get("IsRev").equalsIgnoreCase("TRUE")) {
                scenario.log("Checking Reversal response: " + tmpTC);
                try {
                    reversal = apiProjectNonAlfa.getReversalApiProjList().get(tmpTC);
                    // Pengecekan apakah ada pesan exception waktu hit
                    if (reversal.getResApiInfo().getIsSuccessful()) {
                        ApiProjectNonAlfaReversalValidator revValidator = ApiProjectNonAlfaReversalValidator.builder()
                                .resPayment(payment != null ? payment.getResPay() : null)
                                .resReversal(reversal.getResRev())
                                .build();
                        if (reversal.getResRev().getRc().equalsIgnoreCase(data.get("RCRev"))
                                & revValidator.validate()) {
                            status = "Pass";
                            countPass++;
                        } else {
                            countNtPass++;
                        }
                        addValidationNoteData(tmpTC, "Reversal", revValidator.getValidationMessage());
                    } else {
                        countNtPass++;
                        addValidationNoteData(tmpTC, "Reversal",
                                Collections.singletonList(reversal.getResApiInfo().getExceptionMessage()));
                    }
                } catch (Exception e) {
                    // Digunakan untuk catch RC response yang tidak ada
                    status = "Not Pass";
                    addValidationNoteData(tmpTC, "Reversal", Collections.singletonList(e.getMessage()));
                }
                statusData.put(tmpTC + "-Reversal", status);
            }
        }
    }

    @Then("I create report automation ApiProject Non-Alfa")
    public void i_create_report_automation() throws IOException {
        int PRETTY_PRINT_INDENT_FACTOR = 4;
        try {
            List<FormatReport> resultData = new ArrayList<>();
            // generate sheet trx
            for (Map<String, String> data : excelData) {
                InquiryApiproj inquiry = null;
                PaymentApiproj payment = null;
                AdviceApiproj advice = null;
                ReversalApiproj reversal = null;
                String tmpTC = data.get("TC");
                if (data.get("IsInq").equalsIgnoreCase("TRUE")) {
                    inquiry = apiProjectNonAlfa.getInquiryApiProjList().get(tmpTC);

                    JSONObject jObjReq = new JSONObject(inquiry.getReqInq());
                    String prettyReq = XML.toString(jObjReq, "data", PRETTY_PRINT_INDENT_FACTOR);
                    String prettyRes = "";
                    try {
                        JSONObject jObjRes = new JSONObject(inquiry.getResInq());
                        prettyRes = XML.toString(jObjRes, "data", PRETTY_PRINT_INDENT_FACTOR);
                    } catch (Exception e) {
                        prettyRes = "ERROR, Check Notes for Further explanation! "
                                + inquiry.getResApiInfo().getStringBody();
                    }
                    String validationNote = String.join("\n",
                            validationNoteData.getOrDefault(tmpTC + "-Inquiry", Collections.emptyList()));
                    // temporary // TODO
                    validationNote += "| Timetaken: " + inquiry.getResApiInfo().getTimeTakenInMilis();
                    String rc = null;
                    String rcDesc = null;
                    if (inquiry.getResInq() != null) {
                        rc = inquiry.getResInq().getRc();
                        rcDesc = inquiry.getResInq().getRcdesc();
                    }

                    resultData.add(help.createDataReport(data.get("TC"), data.get("caseDesc"), "Inquiry", "POST",
                            ConstantAPIProject.urlAPIProjNonAlfa + ConstantAPIProject.urlAPIProjInqNonAlfa,
                            data.get("TCTypeInq"), prettyReq, prettyRes, data.get("RCInq"), rc, rcDesc,
                            statusData.get(tmpTC + "-Inquiry"), validationNote));
                }
                if (data.get("IsPay").equalsIgnoreCase("TRUE")) {
                    payment = apiProjectNonAlfa.getPaymentApiProjList().get(tmpTC);
                    JSONObject jObjReq = new JSONObject(payment.getReqPay());
                    String prettyReq = XML.toString(jObjReq, "data", PRETTY_PRINT_INDENT_FACTOR);
                    String prettyRes = "";
                    try {
                        JSONObject jObjRes = new JSONObject(payment.getResPay());
                        prettyRes = XML.toString(jObjRes, "data", PRETTY_PRINT_INDENT_FACTOR);
                    } catch (Exception e) {
                        prettyRes = "ERROR, Check Notes for Further explanation! "
                                + payment.getResApiInfo().getStringBody();
                    }
                    String validationNote = String.join("\n",
                            validationNoteData.getOrDefault(tmpTC + "-Payment", Collections.emptyList()));

                    // temporary // TODO
                    validationNote += "| Timetaken: " + payment.getResApiInfo().getTimeTakenInMilis();
                    String rc = null;
                    String rcDesc = null;
                    if (payment.getResPay() != null) {
                        rc = payment.getResPay().getRc();
                        rcDesc = payment.getResPay().getRcdesc();
                    }

                    resultData.add(help.createDataReport(data.get("TC"), data.get("caseDesc"), "Payment", "POST",
                            ConstantAPIProject.urlAPIProjNonAlfa + ConstantAPIProject.urlAPIProjPayNonAlfa,
                            data.get("TCTypePay"), prettyReq, prettyRes, data.get("RCPay"), rc, rcDesc,
                            statusData.get(tmpTC + "-Payment"), validationNote));
                }
                if (data.get("IsAdv").equalsIgnoreCase("TRUE")) {
                    advice = apiProjectNonAlfa.getAdviceApiProjList().get(tmpTC);
                    JSONObject jObjReq = new JSONObject(advice.getReqAdv());
                    String prettyReq = XML.toString(jObjReq, "data", PRETTY_PRINT_INDENT_FACTOR);
                    String prettyRes;
                    try {
                        JSONObject jObjRes = new JSONObject(advice.getResAdv());
                        prettyRes = XML.toString(jObjRes, "data", PRETTY_PRINT_INDENT_FACTOR);
                    } catch (Exception e) {
                        prettyRes = "ERROR, Check Notes for Further explanation! "
                                + advice.getResApiInfo().getStringBody();
                    }
                    String validationNote = String.join("\n",
                            validationNoteData.getOrDefault(tmpTC + "-Advice", Collections.emptyList()));

                    validationNote += "| Timetaken: " + advice.getResApiInfo().getTimeTakenInMilis();
                    String rc = null;
                    String rcDesc = null;
                    if (advice.getResAdv() != null) {
                        rc = advice.getResAdv().getRc();
                        rcDesc = advice.getResAdv().getRcdesc();
                    }

                    resultData.add(help.createDataReport(data.get("TC"), data.get("caseDesc"), "Advice", "POST",
                            ConstantAPIProject.urlAPIProjNonAlfa + ConstantAPIProject.urlAPIProjAdvNonAlfa,
                            data.get("TCTypeAdv"), prettyReq, prettyRes, data.get("RCAdv"), rc, rcDesc,
                            statusData.get(tmpTC + "-Advice"), validationNote));
                }
                if (data.get("IsRev").equalsIgnoreCase("TRUE")) {
                    reversal = apiProjectNonAlfa.getReversalApiProjList().get(tmpTC);
                    JSONObject jObjReq = new JSONObject((reversal.getReqRev()));
                    String prettyReq = XML.toString(jObjReq, "data", PRETTY_PRINT_INDENT_FACTOR);

                    String prettyRes = "";
                    try {
                        JSONObject jObjRes = new JSONObject(reversal.getResRev());
                        prettyRes = XML.toString(jObjRes, "data", PRETTY_PRINT_INDENT_FACTOR);
                    } catch (Exception e) {
                        prettyRes = "ERROR, Check Notes for Further explanation! "
                                + reversal.getResApiInfo().getStringBody();
                    }
                    String validationNote = String.join("\n",
                            validationNoteData.getOrDefault(tmpTC + "-Reversal", Collections.emptyList()));

                    // Used for handle if response is Null because of Exception error // TODO
                    validationNote += "| Timetaken: " + reversal.getResApiInfo().getTimeTakenInMilis();
                    String rc = null;
                    String rcDesc = null;
                    if (reversal.getResRev() != null) {
                        rc = reversal.getResRev().getRc();
                        rcDesc = reversal.getResRev().getRcdesc();
                    }

                    resultData.add(help.createDataReport(data.get("TC"), data.get("caseDesc"), "Reversal", "POST",
                            ConstantAPIProject.urlAPIProjNonAlfa + ConstantAPIProject.urlAPIProjRevNonAlfa,
                            data.get("TCTypeRev"), prettyReq, prettyRes, data.get("RCRev"), rc, rcDesc,
                            statusData.get(tmpTC + "-Reversal"), validationNote));
                }
            }
            ExcelDataWriter.writeExcelData(scenario, projectNm, ConstantAPIProject.environmentSvr, resultData);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        // long timeFinish = System.currentTimeMillis() - timeStart;

        // generate sheet summary
        ExcelDataWriter.writeSummExcelData(scenario, projectNm, "Summary", countTC, countPass, countNtPass, StartTime);
    }

    private void addValidationNoteData(String tmpTC, String transactionType, List<String> validationNote) {
        // TC -> Value TC
        // transactoinType -> value (Inquiry, Payment, Reversal, Advice)
        // validationNote -> value data validasi berbentuk list
        validationNoteData.computeIfAbsent(tmpTC + "-" + transactionType, k -> new ArrayList<>())
                .addAll(validationNote);
    }
}
