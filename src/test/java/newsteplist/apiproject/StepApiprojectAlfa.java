package newsteplist.apiproject;

import com.google.gson.Gson;
import constant.ConstantAPIProject;
import constant.ConstantExternal;
import helper.Helper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.FormatReport;
import org.json.JSONObject;
import org.json.XML;
import services.apiprojectalfa.ApiProjectAlfa;
import services.apiprojectalfa.InquiryAlfa;
import services.apiprojectalfa.PaymentAlfa;
import services.apiprojectalfa.ReversalAlfa;
import utility.CSVDataReader;
import utility.ExcelDataWriter;
import utility.JSONtoURLEncode;
import validator.apiprojectalfa.ApiProjectAlfaInquiryValidator;
import validator.apiprojectalfa.ApiProjectAlfaPaymentValidator;
import validator.apiprojectalfa.ApiProjectAlfaReversalValidator;
import validator.apiprojectnonalfa.ApiProjectNonAlfaPaymentValidator;
import validator.apiprojectnonalfa.ApiProjectNonAlfaReversalValidator;

import java.io.*;
import java.util.*;

import static newsteplist.apiprojectnonalfa.Hooks.scenario;

public class StepApiprojectAlfa {
    Gson gson = new Gson();
    Helper help = new Helper();
    public List<Map<String, String>> excelData;
    ApiProjectAlfa apiProjectAlfa = new ApiProjectAlfa(scenario);

    // Notes
    Map<String, String> statusData = new HashMap<>();
    Map<String, List<String>> validationNoteData = new HashMap<>();

//    String ActiveSheet;
//    private final Rand r = new Rand();
//    Helper help = new Helper();

    int countPass = 0;
    int countNtPass = 0;
    int countTC = 0;
    String projectNm = "APIProject-Alfa";
    long StartTime;


    @Given("I have Excel data file for testing ApiProject Alfa {string}")
    public void i_have_excel_data_file_for_testing_apiproject_alfa(String excelNm) {
        StartTime = System.currentTimeMillis();
        //Ini nanti hit fungsi get data di excel
        try {
            Properties properties = new Properties();
            // Load properties from file
//            try (FileInputStream fis = new FileInputStream(excelNm)) {
//                properties.load(fis);
//            } catch (IOException e) {
//                e.printStackTrace();
//                System.exit(1);
//            }

            String pathExcel = properties.getProperty("extent.source.excel.filepath", null);
            String sheetNm = properties.getProperty("extent.source.excel.sheetnm", null);
//            String sheetPath = pathExcel + excelNm + "#" + sheetNm;
            excelData = CSVDataReader.readCSVData(excelNm);
        } catch (IOException e) {
            e.printStackTrace();
            scenario.log("Excel data is null. Check the file path.");
        }
    }

    @When("I perform testing prefer from Excel data for ApiProject Alfa")
    public void i_perform_testing_prefer_from_excel_data_for_apiproject_alfa() {
        for (Map<String, String> data : excelData) {
            String tmpTC = data.get("TC");

            scenario.log(tmpTC);

            if (data.get("IsInq").equalsIgnoreCase("TRUE")) {
                scenario.log("Proses Inquiry");
                apiProjectAlfa.getInquiry(
                        tmpTC,
                        data.get("AgentID"),
                        data.get("AgentPIN"),
                        data.get("AgentStoreID"),
                        data.get("ProductID"),
                        data.get("CustomerID")
                );
                countTC++;
            }
            if (data.get("IsPay").equalsIgnoreCase("TRUE")) {
                scenario.log("Proses Payment");
                apiProjectAlfa.getPayment(
                        tmpTC,
                        data.get("AgentID"),
                        data.get("AgentPIN"),
                        data.get("AgentStoreID"),
                        data.get("ProductID"),
                        data.get("CustomerID"),
                        data.get("PaymentPeriod"),
                        data.get("Amount"),
                        data.get("Charge"),
                        data.get("Total"),
                        data.get("AdminFee")
                );
                countTC++;
            }
            if (data.get("IsRev").equalsIgnoreCase("TRUE")) {
                scenario.log("Proses Reversal");
                apiProjectAlfa.getReversal(
                        tmpTC,
                        data.get("AgentID"),
                        data.get("AgentPIN"),
                        data.get("AgentStoreID"),
                        data.get("CustomerID"),
                        data.get("ProductID")
                );
                countTC++;
            }
        }
    }


    @Then("I get the appropriate response for ApiProject Alfa")
    public void i_get_the_appropriate_response_apiproject_alfa() {
        for (Map<String, String> data : excelData) {
            String tmpTC = data.get("TC");
            String status = "Not Pass";
            InquiryAlfa inquiry = null;
            PaymentAlfa payment = null;
            ReversalAlfa reversal = null;

            if (data.get("IsInq").equalsIgnoreCase("TRUE")) {
                inquiry = apiProjectAlfa.getInquiryAlfaList().get(tmpTC);
                if (inquiry.getResApiInfo().getIsSuccessful()) {
                    ApiProjectAlfaInquiryValidator inqValidator = ApiProjectAlfaInquiryValidator.builder()
                            .resInquiry(inquiry.getResInq())
                            .build();
                    if (inquiry.getResInq().getResponseCode().equalsIgnoreCase(data.get("RCInq")) & inqValidator.validate()) {
                        status = "Pass";
                        countPass++;
                    } else {
                        countNtPass++;
                    }
                    validationNoteData.put(tmpTC + "-Inquiry", inqValidator.getValidationMessage());
                } else {
                    countNtPass++;
                    validationNoteData.put(tmpTC + "-Inquiry", Collections.singletonList(inquiry.getResApiInfo().getExceptionMessage()));
                }
                statusData.put(tmpTC + "-Inquiry", status);
                status = "Not Pass";
            }
            if (data.get("IsPay").equalsIgnoreCase("TRUE")) {
                payment = apiProjectAlfa.getPaymentAlfaList().get(tmpTC);
                if (payment.getResApiInfo().getIsSuccessful()) {
                    ApiProjectAlfaPaymentValidator payValidator = ApiProjectAlfaPaymentValidator.builder()
                            .resInquiry(inquiry.getResInq())
                            .resPayment(payment.getResPay())
                            .build();
                    if (payment.getResPay().getResponseCode().equalsIgnoreCase(data.get("RCPay")) & payValidator.validate()) {
                        status = "Pass";
                        countPass++;
                    } else {
                        countNtPass++;
                    }
                    validationNoteData.put(tmpTC + "-Payment", payValidator.getValidationMessage());
                } else {
                    countNtPass++;
                    validationNoteData.put(tmpTC+"-Payment", Collections.singletonList(inquiry.getResApiInfo().getExceptionMessage()));
                }
                statusData.put(tmpTC + "-Payment", status);
                status = "Not Pass";
            }
            if (data.get("IsRev").equalsIgnoreCase("TRUE")) {
                reversal = apiProjectAlfa.getReversalAlfaList().get(tmpTC);
                if (reversal.getResApiInfo().getIsSuccessful()) {
                    ApiProjectAlfaReversalValidator revValidator = ApiProjectAlfaReversalValidator.builder()
                            .resPayment(payment.getResPay())
                            .resReversal(reversal.getResRev())
                            .build();
                    if (reversal.getResRev().getResponseCode().equalsIgnoreCase(data.get("RCRev")) & revValidator.validate()) {
                        status = "Pass";
                        countPass++;
                    } else {
                        countNtPass++;
                    }
                    validationNoteData.put(tmpTC + "-Reversal", revValidator.getValidationMessage());
                } else {
                    countNtPass++;
                    validationNoteData.put(tmpTC+"-Reversal", Collections.singletonList(reversal.getResApiInfo().getExceptionMessage()));
                }

                statusData.put(tmpTC + "-Reversal", status);
            }
        }
    }


    @Then("I create report automation ApiProject Alfa")
    public void i_create_report_automation_apipoject_alfa() throws IOException {
        int PRETTY_PRINT_INDENT_FACTOR = 4;
        try {
            List<FormatReport> resultData = new ArrayList<>();
            //generate sheet trx
            for (Map<String, String> data : excelData) {
                String tmpTC = data.get("TC");
                if (data.get("IsInq").equalsIgnoreCase("TRUE")) {
                    InquiryAlfa inquiry = apiProjectAlfa.getInquiryAlfaList().get(tmpTC);

                    //Get req and res
                    String ReqUrl = inquiry.getRequestURLString();

                    String rc = "";
                    String rcDesc = "";
                    String ResBody = "";
                    try {
                        ResBody = inquiry.getResponseBody();
                        rc = inquiry.getResInq().getResponseCode();
                        rcDesc = inquiry.getResInq().getResponseDesc();
                    } catch (Exception e) {
                        ResBody = "ERROR, Check Notes for Further explanation! " + inquiry.getResApiInfo().getStringBody();
                    }
                    String validationNote = String.join("\n",
                            validationNoteData.getOrDefault(tmpTC + "-Inquiry", Collections.emptyList()));

                    resultData.add(help.createDataReport(data.get("TC"), data.get("caseDesc"),"Inquiry", "GET"
                            , ConstantAPIProject.urlAPIProjNonAlfa+ConstantAPIProject.urlAPIProjInqNonAlfa, data.get("TCTypeInq")
                            , ReqUrl, ResBody, data.get("RCInq")
                            , rc
                            , rcDesc
                            , statusData.get(tmpTC + "-Inquiry")
                            , validationNote)
                    );
                }
                if (data.get("IsPay").equalsIgnoreCase("TRUE")) {
                    PaymentAlfa payment = apiProjectAlfa.getPaymentAlfaList().get(tmpTC);
                    //Get req and res
                    String ReqUrl = payment.getRequestURLString();

                    String rc = "";
                    String rcDesc = "";
                    String ResBody = "";
                    try {
                        ResBody = payment.getResponseBody();
                        rc = payment.getResPay().getResponseCode();
                        rcDesc = payment.getResPay().getResponseDesc();
                    } catch (Exception e) {
                        ResBody = "ERROR, Check Notes for Further explanation! " + payment.getResApiInfo().getStringBody();
                    }
                    String validationNote = String.join("\n",
                            validationNoteData.getOrDefault(tmpTC + "-Payment", Collections.emptyList()));

                    resultData.add(help.createDataReport(data.get("TC"), data.get("caseDesc"), "Payment", "GET"
                            , ConstantAPIProject.urlAPIProjNonAlfa+ConstantAPIProject.urlAPIProjPayNonAlfa, data.get("TCTypePay")
                            , ReqUrl, ResBody, data.get("RCPay")
                            , rc
                            , rcDesc
                            , statusData.get(tmpTC + "-Payment")
                            , validationNote)
                    );
                }
                if (data.get("IsRev").equalsIgnoreCase("TRUE")) {
                    ReversalAlfa reversal = apiProjectAlfa.getReversalAlfaList().get(tmpTC);

                    //Get req and res
                    String ReqUrl = reversal.getRequestURLString();

                    String rc = "";
                    String rcDesc = "";
                    String ResBody = "";
                    try {
                        ResBody = reversal.getResponseBody();
                        rc = reversal.getResRev().getResponseCode();
                        rcDesc = reversal.getResRev().getResponseDesc();
                    } catch (Exception e) {
                        ResBody = "ERROR, Check Notes for Further explanation! " + reversal.getResApiInfo().getStringBody();
                    }

                    String validationNote = String.join("\n",
                            validationNoteData.getOrDefault(tmpTC + "-Reversal", Collections.emptyList()));

                    resultData.add(help.createDataReport(
                            data.get("TC"),
                            data.get("caseDesc"),
                            "Reversal",
                            "POST",
                            ConstantAPIProject.urlAPIProjNonAlfa+ConstantAPIProject.urlAPIProjRevNonAlfa,
                            data.get("TCTypeRev"),
                            ReqUrl,
                            ResBody,
                            data.get("RCRev"),
                            rc,
                            rcDesc,
                            statusData.get(tmpTC + "-Reversal"),
                            validationNote
                    ));
                }
            }
            ExcelDataWriter.writeExcelData(scenario,  projectNm, ConstantAPIProject.environmentSvr, resultData);
        } catch (IOException e) {
            System.out.println(e);
        }

        //generate sheet summary
        ExcelDataWriter.writeSummExcelData(scenario, projectNm, "Summary", countTC, countPass, countNtPass, StartTime);
    }
}
