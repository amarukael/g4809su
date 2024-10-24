package steplist.stepexternal;

import com.google.gson.Gson;
import helper.CFGhelper;
import helper.Helper;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.FormatReport;
import model.ResValidasi;
import org.json.JSONObject;
import services.ExternalDana;
import utility.CSVDataReader;
import constant.ConstantExternal;
import utility.ExcelDataWriter;

import java.io.*;
import java.util.*;

public class StepAutomation {
    Gson gson = new Gson();
    Helper help = new Helper();
    ExternalDana extDana = new ExternalDana();
    List<Map<String, String>> excelData;
    Map<String, String> statusData = new HashMap<>();
    int countPass = 0;
    int countNtPass = 0;
    int countTC = 0;
    String projectNm = "External-Dana";
    Scenario scenario = Hooks.scenario;

    @Given("I have Excel data file for testing External-Dana {string}")
    public void i_have_excel_data_file_for_testing_external_dana(String excelNm) {
        //Ini nanti hit fungsi get data di excel
        try {
            String pathExcel = CFGhelper.cons("extent.source.excel.filepath");
            String sheetNm = CFGhelper.cons("extent.source.excel.sheetnm");
            String sheetPath = pathExcel + excelNm + "#" + sheetNm;
            excelData = CSVDataReader.readCSVData(sheetPath);
        } catch (IOException e) {
            e.printStackTrace();
            scenario.log("Excel data is null. Check the file path.");
        }
    }

    @When("I perform testing prefer from Excel data")
    public void i_perform_testing_prefer_from_excel_data() {
        for (Map<String, String> data : excelData) {
            String tmpTC = data.get("TC");
            scenario.log(tmpTC);
            ExternalDana.scenario = scenario;
            if (data.get("IsInq").equalsIgnoreCase("TRUE")) {
                scenario.log("Proses Inq");
                extDana.getInq(tmpTC, data.get("ProductId"), data.get("CustomerId"));
                countTC++;
            }
            if (data.get("IsPay").equalsIgnoreCase("TRUE")) {
                scenario.log("Proses Order Create");
                extDana.getOrderCreate(tmpTC, data.get("ProductId"));
                countTC++;
            }
            if (data.get("IsAdv").equalsIgnoreCase("TRUE")) {
                scenario.log("Proses Order Detail");
                extDana.getOrderDetail(tmpTC);
                countTC++;
            }
        }
    }

    @Then("I get the appropriate response")
    public void i_get_the_appropriate_response() {
        for (Map<String, String> data : excelData) {
            String tmpTC = data.get("TC");
            String status = "Not Pass";
            String remark = "";
            if (data.get("IsInq").equalsIgnoreCase("TRUE")) {
                if (ExternalDana.lInqData.get(tmpTC).getResponse().getBody().getInquiryResults().get(0)
                        .getInquiryStatus().getCode().equals(data.get("RCInq"))) {
                    if (data.get("RCInq").equalsIgnoreCase("10")) {
                        ResValidasi resVal = ExternalDana.validasiResInq(ExternalDana.lInqData.get(tmpTC), tmpTC, data.get("BillerId"));
                        System.out.println("Validasi: " + gson.toJson(resVal));
                        if (resVal.isStatus()) {
                            status = "Pass";
                            countPass++;
                        } else {
                            remark = resVal.getRemark();
                            countNtPass++;
                        }
                    } else {
                        status = "Pass";
                        countPass++;
                    }
                } else {
                    countNtPass++;
                }
                statusData.put(tmpTC + "-Inquiry", status);
                statusData.put(tmpTC + "-InquiryRemark", remark);
                status = "Not Pass";
            }
            if (data.get("IsPay").equalsIgnoreCase("TRUE")) {
                if (ExternalDana.lOCData.get(tmpTC).getResponse().getBody().getOrder().getOrderStatus().getCode()
                        .equals(data.get("RCPay"))) {
                    if (data.get("RCPay").equalsIgnoreCase("10")) {
                        ResValidasi resVal = ExternalDana.validasiResOrderCreate(ExternalDana.lOCData.get(tmpTC), tmpTC, data.get("BillerId"));
                        System.out.println("Validasi: " + gson.toJson(resVal));
                        if (resVal.isStatus()) {
                            status = "Pass";
                            countPass++;
                        } else {
                            remark = resVal.getRemark();
                            countNtPass++;
                        }
                    } else {
                        status = "Pass";
                        countPass++;
                    }
                } else {
                    countNtPass++;
                }
                statusData.put(tmpTC + "-OrderCreate", status);
                statusData.put(tmpTC + "-OrderCreateRemark", remark);
                status = "Not Pass";
            }
            if (data.get("IsAdv").equalsIgnoreCase("TRUE")) {
                if (ExternalDana.lODData.get(tmpTC).getResponse().getBody().getOrders().get(0).getOrderStatus()
                        .getCode().equals(data.get("RCAdv"))) {
                    if (data.get("RCPay").equalsIgnoreCase("10")) {
                        ResValidasi resVal = ExternalDana.validasiResOrderDetail(ExternalDana.lODData.get(tmpTC), tmpTC, data.get("BillerId"));
                        System.out.println("Validasi: " + gson.toJson(resVal));
                        if (resVal.isStatus()) {
                            status = "Pass";
                            countPass++;
                        } else {
                            remark = resVal.getRemark();
                            countNtPass++;
                        }
                    } else {
                        status = "Pass";
                        countPass++;
                    }
                } else {
                    countNtPass++;
                }
                statusData.put(tmpTC + "-OrderDetail", status);
                statusData.put(tmpTC + "-OrderDetailRemark", remark);
            }
        }
    }

    @Then("I create report automation")
    public void i_create_report_automation() {
        try {
            //generate sheet summary
            ExcelDataWriter.writeSummExcelData(scenario, projectNm, "Summary", countTC, countPass, countNtPass);

            List<FormatReport> resultData = new ArrayList<>();
            //generate sheet trx
            for (Map<String, String> data : excelData) {
                String tmpTC = data.get("TC");
                if (data.get("IsInq").equalsIgnoreCase("TRUE")) {
                    JSONObject jObjReq = new JSONObject(ExternalDana.lReqInqData.get(tmpTC));
                    String prettyReq = jObjReq.toString(4);
                    JSONObject jObjRes = new JSONObject(ExternalDana.lInqData.get(tmpTC));
                    String prettyRes = jObjRes.toString(4);

                    resultData.add(help.createDataReport(data.get("TC")
                            , data.get("TCDesc").replaceAll("\\_", " ")
                            , "Inquiry", "POST"
                            , ConstantExternal.danaInq, data.get("TCTypeInq")
                            , prettyReq, prettyRes, data.get("RCInq")
                            , ExternalDana.lInqData.get(tmpTC).getResponse().getBody().getInquiryResults().get(0).getInquiryStatus().getCode()
                            , ExternalDana.lInqData.get(tmpTC).getResponse().getBody().getInquiryResults().get(0).getInquiryStatus().getStatus()
                            , statusData.get(tmpTC + "-Inquiry")
                            , statusData.get(tmpTC + "-InquiryRemark"))
                    );
                }
                if (data.get("IsPay").equalsIgnoreCase("TRUE")) {
                    JSONObject jObjReq = new JSONObject(ExternalDana.lReqOCData.get(tmpTC));
                    String prettyReq = jObjReq.toString(4);
                    JSONObject jObjRes = new JSONObject(ExternalDana.lOCData.get(tmpTC));
                    String prettyRes = jObjRes.toString(4);

                    resultData.add(help.createDataReport(data.get("TC")
                            , data.get("TCDesc").replaceAll("\\_", " ")
                            , "Order Create", "POST"
                            , ConstantExternal.danaOrderCreate, data.get("TCTypePay")
                            , prettyReq, prettyRes, data.get("RCPay")
                            , ExternalDana.lOCData.get(tmpTC).getResponse().getBody().getOrder().getOrderStatus().getCode()
                            , ExternalDana.lOCData.get(tmpTC).getResponse().getBody().getOrder().getOrderStatus().getStatus()
                            , statusData.get(tmpTC + "-OrderCreate")
                            , statusData.get(tmpTC + "-OrderCreateRemark"))
                    );
                }
                if (data.get("IsAdv").equalsIgnoreCase("TRUE")) {
                    JSONObject jObjReq = new JSONObject(ExternalDana.lReqODData.get(tmpTC));
                    String prettyReq = jObjReq.toString(4);
                    JSONObject jObjRes = new JSONObject(ExternalDana.lODData.get(tmpTC));
                    String prettyRes = jObjRes.toString(4);

                    resultData.add(help.createDataReport(data.get("TC")
                            , data.get("TCDesc").replaceAll("\\_", " ")
                            , "Order Detail", "POST"
                            , ConstantExternal.danaOrderDetail, data.get("TCTypeAdv")
                            , prettyReq, prettyRes, data.get("RCAdv")
                            , ExternalDana.lODData.get(tmpTC).getResponse().getBody().getOrders().get(0).getOrderStatus().getCode()
                            , ExternalDana.lODData.get(tmpTC).getResponse().getBody().getOrders().get(0).getOrderStatus().getStatus()
                            , statusData.get(tmpTC + "-OrderDetail")
                            , statusData.get(tmpTC + "-OrderDetailRemark"))
                    );
                }
            }
            ExcelDataWriter.writeExcelData(scenario,  projectNm, ConstantExternal.environmentSvr, resultData);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
