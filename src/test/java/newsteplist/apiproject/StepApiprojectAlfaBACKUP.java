//package newsteplist.apiproject;
//
//import io.cucumber.java.Scenario;
//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
//import org.apache.poi.openxml4j.util.ZipSecureFile;
//import org.apache.poi.ss.usermodel.*;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import services.apiprojectalfa.ApiProjectAlfaAbstract;
//import services.apiprojectalfa.InquiryAlfa;
//import services.apiprojectalfa.PaymentAlfa;
//import services.apiprojectalfa.ReversalAlfa;
//import utility.CSVDataReader;
//import utility.ExcelDataReader;
//import utility.Rand;
//
//import java.io.*;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.*;
//
//public class StepApiprojectAlfaBACKUP {
//    Scenario scenario = Hooks.scenario;
//    public List<Map<String, String>> excelData;
//    public List<Map<String, String>> csvData;
//    public HashMap<String, InquiryAlfa> InquiryHashList = new HashMap<>();
//    public HashMap<String, PaymentAlfa> PaymentHashList = new HashMap<>();
//    public HashMap<String, ReversalAlfa> ReversalHashList = new HashMap<>();
//
//    public  List<Map<String, String>> outputDataList = new ArrayList<>();
//
//    String ActiveSheet;
//    private final Rand r = new Rand();
//
//
//    @Given("Valid Master ApiProjectAlfa {string} and Sheet {string}")
//    public void validMasterApiProjectAlfaAndSheet(String excelFilePath, String sheet) {
//        try {
//            String sheetPath = excelFilePath + "#" + sheet;
//            excelData = ExcelDataReader.readExcelData(sheetPath);
//            ActiveSheet = sheet;
//
//        } catch (IOException e) {
//            e.printStackTrace();
//            scenario.log("Excel data is null. Check the file path and sheet name.");
//        }
//    }
//
//    @Given("Valid Master ApiProjectAlfa CSV {string}")
//    public void validMasterApiProjectAlfaCSV(String csvFilePath) {
//        try {
//            excelData = CSVDataReader.readCSVData(csvFilePath);
//        } catch (IOException e) {
//            e.printStackTrace();
//            scenario.log("CSV data is null. Check the file path and csv name.");
//        }
//        ActiveSheet = "test";
//    }
//
//    @When("I perform testing prefer from Excel data")
//    public void I_perform_testing_prefer_from_excel_data() {
//        String TC = null;
//        String trackingref =  r.getRandomTrxId("DEV","");
//        String secretKey = null;
//
//        for (Map<String, String> rowData : excelData) {
//            String currentTC = rowData.get("TestCase");
//
//            if (TC == null || !TC.equals(currentTC)) {
//                // If TC is null (first iteration) or TC is different from currentTC, generate a new trackingref
//                trackingref = r.getRandomRef();
//            }
//
//            TC = currentTC; // Update TC for the next iteration
//
//            if (TC == null || TC.isEmpty()) {
//                // If TC is empty, end the processing
//                break; // This will exit the method or function
//            }
//            scenario.log("TestCase: " + TC);
//
//            String isInq = rowData.get("isInq");
//            String isPay = rowData.get("isPay");
//            String isRev = rowData.get("isRev");
//
//            if (isInq.equalsIgnoreCase("TRUE")) {
//                InquiryAlfa inquiry = new InquiryAlfa();
//                inquiry.generateRequest(
//                        trackingref,
//                        rowData.get("AgentID"),
//                        rowData.get("AgentPIN"),
//                        rowData.get("AgentStoreID"),
//                        rowData.get("ProductID"),
//                        rowData.get("CustomerID"));
//                inquiry.hitRequest();
//                trackingref = inquiry.getTrackingRef();
//
//                InquiryHashList.put(rowData.get("CustomerID"), inquiry);
//
//                HashMap<String, String> outputdatarow = new HashMap<>();
//                outputdatarow.put("Test Case", rowData.get("TestCase"));
//                outputdatarow.put("Services", "Inquiry");
//                outputdatarow.put("Method", "GET");
//                outputdatarow.put("Uri", inquiry.getUrlInquiry());
//                outputdatarow.put("Type", "QueryParam");
//                outputdatarow.put("Request", inquiry.getRequestBody());
//                outputdatarow.put("Response", inquiry.getResponseBody());
//                outputdatarow.put("ExpectedRC", rowData.get("rcInq"));
//                outputdatarow.put("RC Result", inquiry.getResInq().getResponseCode());
//                outputdatarow.put("RC Desc", inquiry.getResInq().getResponseDesc());
//                String status = (rowData.get("rcInq").equalsIgnoreCase( inquiry.getResInq().getResponseCode())) ? "Passed" : "Failed" ;
//                outputdatarow.put("Status", status);
//                outputdatarow.put("Error Message", inquiry.getResInq().validate());
//                outputDataList.add(outputdatarow);
//
//            }
//            if (isPay.equalsIgnoreCase("TRUE")) {
//                PaymentAlfa payment = new PaymentAlfa();
//                payment.generateRequest(
//                        trackingref,
//                        rowData.get("AgentID"),
//                        rowData.get("AgentPIN"),
//                        rowData.get("AgentStoreID"),
//                        rowData.get("ProductID"),
//                        rowData.get("CustomerID"),
//                        rowData.get("PaymentPeriod"),
//                        rowData.get("Amount"),
//                        rowData.get("Charge"),
//                        rowData.get("Total"),
//                        rowData.get("AdminFee")
//                        );
//                payment.hitRequest();
//                PaymentHashList.put(rowData.get("CustomerID"), payment);
//
//                HashMap<String, String> outputdatarow = new HashMap<>();
//                outputdatarow.put("Test Case", rowData.get("TestCase"));
//                outputdatarow.put("Services", "Payment");
//                outputdatarow.put("Method", "GET");
//                outputdatarow.put("Uri", payment.getUrlPayment());
//                outputdatarow.put("Type", "QueryParam");
//                outputdatarow.put("Request", payment.getRequestBody());
//                outputdatarow.put("Response", payment.getResponseBody());
//                outputdatarow.put("ExpectedRC", rowData.get("rcInq"));
//                outputdatarow.put("RC Result", payment.getResPay().getResponseCode());
//                outputdatarow.put("RC Desc", payment.getResPay().getResponseDesc());
//                String status = (rowData.get("rcPay").equalsIgnoreCase( payment.getResPay().getResponseCode())) ? "Passed" : "Failed" ;
//                outputdatarow.put("Status", status);
//                outputdatarow.put("Error Message", payment.getResPay().validate());
//                outputDataList.add(outputdatarow);
//            }
//            if (isRev.equalsIgnoreCase("TRUE")) {
//                ReversalAlfa reversal = new ReversalAlfa();
//                reversal.generateRequest(
//                        trackingref,
//                        rowData.get("AgentID"),
//                        rowData.get("AgentPIN"),
//                        rowData.get("AgentStoreID"),
//                        rowData.get("CustomerID"),
//                        rowData.get("ProductID"));
//                reversal.hitRequest();
//                ReversalHashList.put(rowData.get("CustomerID"), reversal);
//
//                HashMap<String, String> outputdatarow = new HashMap<>();
//                outputdatarow.put("Test Case", rowData.get("TestCase"));
//                outputdatarow.put("Services", "Reversal");
//                outputdatarow.put("Method", "GET");
//                outputdatarow.put("Uri", reversal.getUrlReversal());
//                outputdatarow.put("Type", "QueryParam");
//                outputdatarow.put("Request", reversal.getRequestBody());
//                outputdatarow.put("Response", reversal.getResponseBody());
//                outputdatarow.put("ExpectedRC", rowData.get("rcInq"));
//                outputdatarow.put("RC Result", reversal.getResRev().getResponseCode());
//                outputdatarow.put("RC Desc", reversal.getResRev().getResponseDesc());
//                String status = (rowData.get("rcRev").equalsIgnoreCase( reversal.getResRev().getResponseCode())) ? "Passed" : "Failed" ;
//                outputdatarow.put("Status", status);
//                outputdatarow.put("Error Message", reversal.getResRev().validate());
//                outputDataList.add(outputdatarow);
//            }
//        }
//    }
//
//    private <T extends ApiProjectAlfaAbstract> void generatereport() throws IOException {
//        String reportconfigfullpath = "src/test/resources/extent.properties";
//        //
//        ZipSecureFile.setMinInflateRatio(0);
//        Properties properties = new Properties();
//        // Load properties from file
//        try (FileInputStream fis = new FileInputStream(reportconfigfullpath)) {
//            properties.load(fis);
//        } catch (IOException e) {
//            e.printStackTrace();
//            System.exit(1);
//        }
//        String filename = properties.getProperty("extent.reporter.excel.filename", null);
//        String filepath = properties.getProperty("extent.reporter.excel.filepath");
//        String fullpath = filepath + filename;
//        scenario.log(fullpath);
//
//        // Generate timestamp
//        LocalDateTime currentDateTime = LocalDateTime.now();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy_MMM_d-HH");
//        String timestamp = currentDateTime.format(formatter);
//
//        boolean replace = Boolean.parseBoolean(properties.getProperty("extent.reporter.excel.replace"));
//
//        // Define inputstream
//        FileInputStream fileInputStream;
//        Workbook workbook = null;
//        try {
//            // Check if file exist
//            fileInputStream = new FileInputStream(fullpath);
//            workbook = new XSSFWorkbook(fileInputStream);
//        } catch (FileNotFoundException ex) {
//            // File name with timestamp
//            filename = "Report-Automation_" + timestamp + ".xlsx";
//
//            //save filename to config
//            properties.setProperty("extent.reporter.excel.filename", filename);
//            try (FileOutputStream fos = new FileOutputStream(reportconfigfullpath)) {
//                properties.store(fos, null);
//                scenario.log("Filename updated to: " + reportconfigfullpath);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            // Write the output to a file
//            // Create new workbook
//            workbook = new XSSFWorkbook();
//        } catch (IOException e) {
//            // Handle other IO exceptions
//            e.printStackTrace();
//        } finally {
//            if (workbook == null) {
//                workbook = new XSSFWorkbook();
//            }
//
//            Sheet sheet = workbook.getSheet(ActiveSheet);
//
//            if (sheet != null && replace) {
//                int index = workbook.getSheetIndex(ActiveSheet);
//                workbook.removeSheetAt(index);
//                sheet = workbook.createSheet(ActiveSheet);
//                scenario.log("Replaced");
//            } else {
//                sheet = workbook.createSheet(ActiveSheet);
//            }
//
//            // Create header row
//            Row headerRow = sheet.createRow(0);
//            String[] headers = {
//                    "Test Case",
//                    "Services",
//                    "Method",
//                    "Uri",
//                    "Type",
//                    "Request",
//                    "Response",
//                    "Expected RC",
//                    "RC result",
//                    "Desc",
//                    "Status",
//                    "Error Message"
//            };
//            CellStyle headerCellStyle = workbook.createCellStyle();
//            headerCellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
//            headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
//            Font headerFont = workbook.createFont();
//            headerFont.setColor(IndexedColors.WHITE.getIndex());
//            headerCellStyle.setFont(headerFont);
//
//            for (int i = 0; i < headers.length; i++) {
//                Cell cell = headerRow.createCell(i);
//                cell.setCellValue(headers[i]);
//                cell.setCellStyle(headerCellStyle);
//            }
//
//            // Create data rows
//            int rowNum = 1;
//            int j = 0; // used for row tracker
//
//            for (Map<String, String> rowData : outputDataList) {
//                Row row = sheet.createRow(rowNum++);
//                // scenario.log(headers.length);
//                for (int i = 0; i < headers.length; i++) {
//                    HashMap<String, String> outputdatarow = new HashMap<>();
////                    outputdatarow.put("Test Case", rowData.get("TestCase"));
////                    outputdatarow.put("Services", "Payment");
////                    outputdatarow.put("Method", "GET");
////                    outputdatarow.put("Uri", reversal.getUrlReversal());
////                    outputdatarow.put("Type", "QueryParam");
////                    outputdatarow.put("Request", reversal.getRequestBody());
////                    outputdatarow.put("Response", reversal.getResponseBody());
////                    outputdatarow.put("ExpectedRC", rowData.get("rcInq"));
////                    outputdatarow.put("RC Result", reversal.getResRev().getResponseCode());
////                    outputdatarow.put("RC Desc", reversal.getResRev().getResponseDesc());
//
//
//                    Cell cell = row.createCell(i);
//                    CellStyle cellStyle = workbook.createCellStyle();
//                    if (i == 0) cell.setCellValue(rowData.get("Test Case"));
//                    if (i == 1) cell.setCellValue(rowData.get("Services"));
//                    if (i == 2) cell.setCellValue(rowData.get("Method"));
//                    if (i == 3) cell.setCellValue(rowData.get("Uri"));
//                    if (i == 4) cell.setCellValue(rowData.get("Type"));
//                    if (i == 5) cell.setCellValue(rowData.get("Request"));
//                    if (i == 6) cell.setCellValue(rowData.get("Response"));
//                    if (i == 7) cell.setCellValue(rowData.get("ExpectedRC"));
//                    if (i == 8) cell.setCellValue(rowData.get("RC Result"));
//                    if (i == 9) cell.setCellValue(rowData.get("RC Desc"));
//                    if (i == 10) cell.setCellValue(rowData.get("Status"));
//                    if (i == 11) cell.setCellValue(rowData.get("Error Message"));
//
//
//                    // Applying style cell
//                    // Applying wrap text
//                    if (cell.getStringCellValue().length() > 50) { // Adjust the threshold as needed
//                        cellStyle.setWrapText(true);
//                        cellStyle.setAlignment(HorizontalAlignment.LEFT);
//                        cellStyle.setVerticalAlignment(VerticalAlignment.TOP);
//                        cell.setCellStyle(cellStyle);
//                    } else {
//                        // Set default alignment if not long text
//                        cellStyle.setAlignment(HorizontalAlignment.LEFT);
//                        cellStyle.setVerticalAlignment(VerticalAlignment.TOP);
//                        cell.setCellStyle(cellStyle);
//                    }
//                }
//                j++;
//            }
//
//            // Auto-size columns
//            for (int i = 0; i < headers.length; i++) {
//                sheet.autoSizeColumn(i);
//            }
//
//            // Write the updated workbook to a file
//            fullpath = filepath + filename;
//            scenario.log(fullpath);
//            File file = new File(fullpath);
//            if (!file.getParentFile().exists()) {
//                file.getParentFile().mkdirs();
//            }
//
//            try (FileOutputStream fileOut = new FileOutputStream(file)) {
//                workbook.write(fileOut);
//            }
//
//            scenario.log("Report generated successfully!");
//        }
//    }
//
//    @Then("I get the appropriate response")
//    public void iGetTheAppropriateResponse() throws IOException {
//        generatereport();
//    }
//}
