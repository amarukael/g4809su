package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import helper.CFGhelper;
import helper.Helper;
import io.cucumber.java.Scenario;
import model.FormatReport;

public class ExcelDataWriter {
    static Helper help = new Helper();

    public static void writeSummExcelData(Scenario scenario, String projectNm, String activeSheet, int countTC,
            int countPass, int countNtPass) throws IOException {
        List<FormatReport> resultSummData = new ArrayList<>();
        // generate sheet summary
        List<FormatReport> lSummaryData = new ArrayList<>();
        String[] lblProgres = { "# Test Case :", "# Completed :", "# Passed :", "# Failed :", "# Pending :",
                "# Blocked :" };
        String[] lblTotTask = { String.valueOf(countTC), "", String.valueOf(countPass), String.valueOf(countNtPass),
                "0", "0" };
        String[] lblStatus = { "", "% Completed :", "% Passed :", "% Failed :", "% Pending :", "% Blocked :" };
        String[] lblPercent = { "", "", String.format("%.0f", (float) countPass / countTC * 100),
                String.format("%.0f", (float) countNtPass / countTC * 100), "0", "0" };
        for (int i = 0; i < lblProgres.length; i++) {
            resultSummData.add(help.createSummDataReport(lblProgres[i], lblTotTask[i], lblStatus[i], lblPercent[i]));
        }
        ExcelDataWriter.writeExcelData(scenario, projectNm, activeSheet, resultSummData);
    }

    public static void writeSummExcelData(Scenario scenario, String projectNm, String activeSheet, int countTC,
            int countPass, int countNtPass, long timestart) throws IOException {
        List<FormatReport> resultSummData = new ArrayList<>();
        // generate sheet summary
        List<FormatReport> lSummaryData = new ArrayList<>();
        String[] lblProgres = { "# Test Case :", "# Completed :", "# Passed :", "# Failed :", "# Pending :",
                "# Blocked :" };
        String[] lblTotTask = { String.valueOf(countTC), "", String.valueOf(countPass), String.valueOf(countNtPass),
                "0", "0" };
        String[] lblStatus = { "", "% Completed :", "% Passed :", "% Failed :", "% Pending :", "% Blocked :" };
        String[] lblPercent = { "", "", String.format("%.0f", (float) countPass / countTC * 100),
                String.format("%.0f", (float) countNtPass / countTC * 100), "0", "0" };
        for (int i = 0; i < lblProgres.length; i++) {
            resultSummData.add(help.createSummDataReportTimeTaken(lblProgres[i], lblTotTask[i], lblStatus[i],
                    lblPercent[i], timestart));
        }
        String[] timeTaken = { "Time Taken" };
        ExcelDataWriter.writeExcelData(scenario, projectNm, activeSheet, resultSummData);
    }

    public static void writeSummExcelData(String projectNm, String activeSheet, int countTC,
            int countPass, int countNtPass, long timestart) throws IOException {
        List<FormatReport> resultSummData = new ArrayList<>();
        // generate sheet summary
        List<FormatReport> lSummaryData = new ArrayList<>();
        String[] lblProgres = { "# Test Case :", "# Completed :", "# Passed :", "# Failed :", "# Pending :",
                "# Blocked :" };
        String[] lblTotTask = { String.valueOf(countTC), "", String.valueOf(countPass), String.valueOf(countNtPass),
                "0", "0" };
        String[] lblStatus = { "", "% Completed :", "% Passed :", "% Failed :", "% Pending :", "% Blocked :" };
        String[] lblPercent = { "", "", String.format("%.0f", (float) countPass / countTC * 100),
                String.format("%.0f", (float) countNtPass / countTC * 100), "0", "0" };
        for (int i = 0; i < lblProgres.length; i++) {
            resultSummData.add(help.createSummDataReportTimeTaken(lblProgres[i], lblTotTask[i], lblStatus[i],
                    lblPercent[i], timestart));
        }
        String[] timeTaken = { "Time Taken" };
        ExcelDataWriter.writeExcelData(projectNm, activeSheet, resultSummData);
    }

    public static void writeExcelData(Scenario scenario, String projectNm, String activeSheet,
            List<FormatReport> resultData) throws IOException {
        ZipSecureFile.setMinInflateRatio(0);

        String testedBy = CFGhelper.cons("extent.tested.by");
        String filename = CFGhelper.cons("extent.reporter.excel.filename");
        String filepath = CFGhelper.cons("extent.reporter.excel.filepath");
        String fullpath = filepath + filename;
        scenario.log(fullpath);

        // Generate timestamp
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy_MMM_d-HH");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        String timestamp = currentDateTime.format(formatter);
        String testedDate = currentDateTime.format(formatter2);

        boolean replace = Boolean.parseBoolean(CFGhelper.cons("extent.reporter.excel.replace"));

        // Define inputstream
        FileInputStream fileInputStream;
        Workbook workbook = null;
        try {
            // Check if file exist
            fileInputStream = new FileInputStream(fullpath);
            workbook = new XSSFWorkbook(fileInputStream);
        } catch (FileNotFoundException ex) {
            // File name with timestamp
            filename = "Report-Automation_" + projectNm + "_" + timestamp + ".xlsx";

            // save filename to config
            CFGhelper.insData("extent.reporter.excel.filename", filename);
            // Write the output to a file

            // Create new workbook
            workbook = new XSSFWorkbook();
        } catch (IOException e) {
            // Handle other IO exceptions
            e.printStackTrace();
        } finally {
            if (workbook == null) {
                workbook = new XSSFWorkbook();
            }

            Sheet sheet = workbook.getSheet(activeSheet);
            if (sheet != null && replace) {
                int index = workbook.getSheetIndex(activeSheet);
                workbook.removeSheetAt(index);
                sheet = workbook.createSheet(activeSheet);
                scenario.log("Replaced");
            } else {
                sheet = workbook.createSheet(activeSheet);
            }

            // Create header row
            Row headerRow = sheet.createRow(0);
            String[] headers = null;
            if (activeSheet.equalsIgnoreCase("Summary")) {
                headers = new String[] { "Progress", "Total Task", "Status", "Total Percent" };
            } else {
                headers = new String[] { "Test Case", "Case Desc", "Services", "Method", "Uri", "Type", "Request",
                        "Response", "Expected RC", "RC result", "Desc", "Status", "Tested Date", "Tested By", "Notes" };
            }

            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
            headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            Font headerFont = workbook.createFont();
            headerFont.setColor(IndexedColors.WHITE.getIndex());
            headerCellStyle.setFont(headerFont);

            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerCellStyle);
            }

            // Create data rows
            int rowNum = 1;
            int startRow = 0;
            int endRow = 0;
            int x = 0;
            int totData = resultData.size();
            String tmpTc = "";
            Map<String, String> tmpMerged = new HashMap<>();

            for (FormatReport fRep : resultData) {
                Row row = sheet.createRow(rowNum++);
                if (!activeSheet.equalsIgnoreCase("Summary"))
                    row.setHeight((short) 7000);

                for (int i = 0; i < headers.length; i++) {
                    Cell cell = row.createCell(i);
                    CellStyle cellStyle = workbook.createCellStyle();

                    if (activeSheet.equalsIgnoreCase("Summary")) {
                        if (i == 0)
                            cell.setCellValue(fRep.getSumProgress());
                        if (i == 1)
                            cell.setCellValue(fRep.getSumTotalTask());
                        if (i == 2)
                            cell.setCellValue(fRep.getSumStatus());
                        if (rowNum > 3) {
                            if (i == 3)
                                cell.setCellValue(fRep.getSumTotPercent() + "%");
                        }
                    } else {
                        if (i == 0)
                            cell.setCellValue(fRep.getTestCase());
                        if (i == 1)
                            cell.setCellValue(fRep.getCaseDesc());
                        if (i == 2)
                            cell.setCellValue(fRep.getServices());
                        if (i == 3)
                            cell.setCellValue(fRep.getMethod());
                        if (i == 4)
                            cell.setCellValue(fRep.getUrl());
                        if (i == 5)
                            cell.setCellValue(fRep.getType());
                        if (i == 6)
                            cell.setCellValue(fRep.getRequest());
                        if (i == 7)
                            cell.setCellValue(fRep.getResponse());
                        if (i == 8)
                            cell.setCellValue(fRep.getExpectedRc());
                        if (i == 9)
                            cell.setCellValue(fRep.getRcResult());
                        if (i == 10)
                            cell.setCellValue(fRep.getDesc());
                        if (i == 11) {
                            cell.setCellValue(fRep.getStatus());
                            if (fRep.getStatus().equalsIgnoreCase("Pass")) {
                                cellStyle.setFillForegroundColor(IndexedColors.GREEN.getIndex());
                                cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                            } else {
                                cellStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
                                cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                            }
                        }
                        if (i == 12)
                            cell.setCellValue(testedDate);
                        if (i == 13)
                            cell.setCellValue(testedBy);
                        if (i == 14)
                            cell.setCellValue(fRep.getNotes());
                    }

                    // Applying style cell
                    // Applying wrap text
                    if (cell.getStringCellValue().length() > 50) { // Adjust the threshold as needed
                        cellStyle.setWrapText(true);
                        cellStyle.setAlignment(HorizontalAlignment.LEFT);
                        cellStyle.setVerticalAlignment(VerticalAlignment.TOP);
                        cell.setCellStyle(cellStyle);
                    } else {
                        // Set default alignment if not long text
                        if ((activeSheet.equalsIgnoreCase("Summary") && i == 1)
                                || (activeSheet.equalsIgnoreCase("Summary") && i == 3)) {
                            cellStyle.setAlignment(HorizontalAlignment.RIGHT);
                        } else {
                            cellStyle.setAlignment(HorizontalAlignment.LEFT);
                        }

                        cellStyle.setVerticalAlignment(VerticalAlignment.TOP);
                        cell.setCellStyle(cellStyle);
                    }
                }

                // setting merged test case
                if (!activeSheet.equals("Summary")) {
                    if (!tmpTc.equalsIgnoreCase(fRep.getTestCase())) {
                        if (!tmpTc.equals("")) {
                            if (endRow != 0) {
                                tmpMerged.put("start" + String.valueOf(x), String.valueOf(startRow));
                                tmpMerged.put("end" + String.valueOf(x), String.valueOf(endRow));
                                x++;
                            }
                        }

                        tmpTc = fRep.getTestCase();
                        startRow = rowNum - 1;
                        endRow = 0;
                    } else {
                        endRow = rowNum - 1;
                        if (rowNum == (totData + 1)) {
                            tmpMerged.put("start" + String.valueOf(x), String.valueOf(startRow));
                            tmpMerged.put("end" + String.valueOf(x), String.valueOf(endRow));
                            x++;
                        }
                    }
                }

                // Add detail
                if (activeSheet.equals("Summary")) {
                    printDetailSummary(fRep, sheet);
                }
            }

            // Auto-size columns
            for (int i = 0; i < headers.length; i++) {
                if (activeSheet.equalsIgnoreCase("Summary")) {
                    sheet.autoSizeColumn(i);
                } else {
                    if (i == 4 || i == 6 || i == 7) {
                        sheet.setColumnWidth(i, 16000);
                    } else {
                        sheet.autoSizeColumn(i);
                    }
                }
            }

            // Merged TC
            if (!activeSheet.equals("Summary")) {
                for (int i = 0; i < x; i++) {
                    String start = tmpMerged.get("start" + String.valueOf(i));
                    String end = tmpMerged.get("end" + String.valueOf(i));
                    sheet.addMergedRegion(new CellRangeAddress(Integer.parseInt(start), Integer.parseInt(end), 0, 0));
                    sheet.addMergedRegion(new CellRangeAddress(Integer.parseInt(start), Integer.parseInt(end), 1, 1));
                }
            }

            // Write the updated workbook to a file
            fullpath = filepath + filename;
            scenario.log(fullpath);
            File file = new File(fullpath);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }

            try (FileOutputStream fileOut = new FileOutputStream(file)) {
                workbook.write(fileOut);
            }

            scenario.log("Report generated successfully!");
        }
    }

    public static void writeExcelData(String projectNm, String activeSheet,
            List<FormatReport> resultData) throws IOException {
        ZipSecureFile.setMinInflateRatio(0);

        String testedBy = CFGhelper.cons("extent.tested.by");
        String filename = CFGhelper.cons("extent.reporter.excel.filename");
        String filepath = CFGhelper.cons("extent.reporter.excel.filepath");
        String fullpath = filepath + filename;
        System.out.println(fullpath);

        // Generate timestamp
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy_MMM_d-HH");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        String timestamp = currentDateTime.format(formatter);
        String testedDate = currentDateTime.format(formatter2);

        boolean replace = Boolean.parseBoolean(CFGhelper.cons("extent.reporter.excel.replace"));

        // Define inputstream
        FileInputStream fileInputStream;
        Workbook workbook = null;
        try {
            // Check if file exist
            fileInputStream = new FileInputStream(fullpath);
            workbook = new XSSFWorkbook(fileInputStream);
        } catch (FileNotFoundException ex) {
            // File name with timestamp
            filename = "Report-Automation_" + projectNm + "_" + timestamp + ".xlsx";

            // save filename to config
            CFGhelper.insData("extent.reporter.excel.filename", filename);
            // Write the output to a file

            // Create new workbook
            workbook = new XSSFWorkbook();
        } catch (IOException e) {
            // Handle other IO exceptions
            e.printStackTrace();
        } finally {
            if (workbook == null) {
                workbook = new XSSFWorkbook();
            }

            Sheet sheet = workbook.getSheet(activeSheet);
            if (sheet != null && replace) {
                int index = workbook.getSheetIndex(activeSheet);
                workbook.removeSheetAt(index);
                sheet = workbook.createSheet(activeSheet);
                System.out.println("Replaced");
            } else {
                sheet = workbook.createSheet(activeSheet);
            }

            // Create header row
            Row headerRow = sheet.createRow(0);
            String[] headers = null;
            if (activeSheet.equalsIgnoreCase("Summary")) {
                headers = new String[] { "Progress", "Total Task", "Status", "Total Percent" };
            } else {
                headers = new String[] { "Test Case", "Case Desc", "Services", "Method", "Uri", "Type", "Request",
                        "Response", "Expected RC", "RC result", "Desc", "Status", "Tested Date", "Tested By",
                        "Notes", "Database" };
            }

            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
            headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            Font headerFont = workbook.createFont();
            headerFont.setColor(IndexedColors.WHITE.getIndex());
            headerCellStyle.setFont(headerFont);

            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerCellStyle);
            }

            // Create data rows
            int rowNum = 1;
            int startRow = 0;
            int endRow = 0;
            int x = 0;
            int totData = resultData.size();
            String tmpTc = "";
            Map<String, String> tmpMerged = new HashMap<>();

            for (FormatReport fRep : resultData) {
                Row row = sheet.createRow(rowNum++);
                if (!activeSheet.equalsIgnoreCase("Summary"))
                    row.setHeight((short) 7000);

                for (int i = 0; i < headers.length; i++) {
                    Cell cell = row.createCell(i);
                    CellStyle cellStyle = workbook.createCellStyle();
                    String database = "";
                    String notes = "";

                    if (fRep.getNotes() != null) {
                        String tmpnotes = fRep.getNotes();
                        String[] parts = tmpnotes.split("\\[DATABASE\\]");
                        database = (parts.length > 1) ? parts[1].trim() : "";
                        notes = (parts.length > 2) ? parts[parts.length - 1].trim() : "";
                    }
                    if (notes.equals("")) {
                        notes = fRep.getNotes();
                    }

                    if (activeSheet.equalsIgnoreCase("Summary")) {
                        if (i == 0)
                            cell.setCellValue(fRep.getSumProgress());
                        if (i == 1)
                            cell.setCellValue(fRep.getSumTotalTask());
                        if (i == 2)
                            cell.setCellValue(fRep.getSumStatus());
                        if (rowNum > 3) {
                            if (i == 3)
                                cell.setCellValue(fRep.getSumTotPercent() + "%");
                        }
                    } else {
                        if (i == 0)
                            cell.setCellValue(fRep.getTestCase());
                        if (i == 1)
                            cell.setCellValue(fRep.getCaseDesc());
                        if (i == 2)
                            cell.setCellValue(fRep.getServices());
                        if (i == 3)
                            cell.setCellValue(fRep.getMethod());
                        if (i == 4)
                            cell.setCellValue(fRep.getUrl());
                        if (i == 5)
                            cell.setCellValue(fRep.getType());
                        if (i == 6)
                            cell.setCellValue(fRep.getRequest());
                        if (i == 7)
                            cell.setCellValue(fRep.getResponse());
                        if (i == 8)
                            cell.setCellValue(fRep.getExpectedRc());
                        if (i == 9)
                            cell.setCellValue(fRep.getRcResult());
                        if (i == 10)
                            cell.setCellValue(fRep.getDesc());
                        if (i == 11) {
                            cell.setCellValue(fRep.getStatus());
                            if (fRep.getStatus().equalsIgnoreCase("Pass")) {
                                cellStyle.setFillForegroundColor(IndexedColors.GREEN.getIndex());
                                cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                            } else {
                                cellStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
                                cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                            }
                        }
                        if (i == 12)
                            cell.setCellValue(testedDate);
                        if (i == 13)
                            cell.setCellValue(testedBy);
                        if (i == 14)
                            cell.setCellValue(notes);
                        if (i == 15)
                            cell.setCellValue(database);
                    }

                    // Applying style cell
                    // Applying wrap text
                    if (cell.getStringCellValue().length() > 50) { // Adjust the threshold as needed
                        cellStyle.setWrapText(true);
                        cellStyle.setAlignment(HorizontalAlignment.LEFT);
                        cellStyle.setVerticalAlignment(VerticalAlignment.TOP);
                        cell.setCellStyle(cellStyle);

                    } else {
                        if ((activeSheet.equalsIgnoreCase("Summary") && i == 1)
                                || (activeSheet.equalsIgnoreCase("Summary") && i == 3)) {
                            cellStyle.setAlignment(HorizontalAlignment.RIGHT);
                        } else {
                            cellStyle.setAlignment(HorizontalAlignment.LEFT);
                        }

                        cellStyle.setVerticalAlignment(VerticalAlignment.TOP);
                        cell.setCellStyle(cellStyle);
                    }
                }

                // setting merged test case
                if (!activeSheet.equals("Summary")) {
                    if (!tmpTc.equalsIgnoreCase(fRep.getTestCase())) {
                        if (!tmpTc.equals("")) {
                            if (endRow != 0) {
                                tmpMerged.put("start" + String.valueOf(x), String.valueOf(startRow));
                                tmpMerged.put("end" + String.valueOf(x), String.valueOf(endRow));
                                x++;
                            }
                        }

                        tmpTc = fRep.getTestCase();
                        startRow = rowNum - 1;
                        endRow = 0;
                    } else {
                        endRow = rowNum - 1;
                        if (rowNum == (totData + 1)) {
                            tmpMerged.put("start" + String.valueOf(x), String.valueOf(startRow));
                            tmpMerged.put("end" + String.valueOf(x), String.valueOf(endRow));
                            x++;
                        }
                    }
                }

                if (activeSheet.equals("Summary")) {
                    printDetailSummary(fRep, sheet);
                }
            }

            for (int i = 0; i < headers.length; i++) {
                if (activeSheet.equalsIgnoreCase("Summary")) {
                    sheet.autoSizeColumn(i);
                } else {
                    if (i == 4 || i == 6 || i == 7 || i == 15) {
                        sheet.setColumnWidth(i, 16000);
                    } else {
                        sheet.autoSizeColumn(i);
                    }
                }
            }

            // Merged TC
            if (!activeSheet.equals("Summary")) {
                for (int i = 0; i < x; i++) {
                    String start = tmpMerged.get("start" + String.valueOf(i));
                    String end = tmpMerged.get("end" + String.valueOf(i));
                    sheet.addMergedRegion(new CellRangeAddress(Integer.parseInt(start), Integer.parseInt(end), 0, 0));
                    sheet.addMergedRegion(new CellRangeAddress(Integer.parseInt(start), Integer.parseInt(end), 1, 1));
                }
            }

            // Write the updated workbook to a file
            fullpath = filepath + filename;
            System.out.println(fullpath);
            File file = new File(fullpath);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }

            try (FileOutputStream fileOut = new FileOutputStream(file)) {
                workbook.write(fileOut);
            }

            System.out.println("Report generated successfully!");
        }
    }

    private static void printDetailSummary(FormatReport fRep, Sheet sheet) {
        LinkedHashMap<String, String> testDetails = new LinkedHashMap<>();
        String dateTimeStart = "";
        String elapsedTimeString = "";
        if (fRep.getStartTime() != -1) {
            // Define the custom date-time format
            DateTimeFormatter formatterDateTime = DateTimeFormatter.ofPattern("dd-MMMM-yyyy HH:mm",
                    new Locale("id", "ID")); // Indonesian locale for "Desember"
            dateTimeStart = LocalDateTime.ofInstant(Instant.ofEpochMilli(fRep.getStartTime()), ZoneId.systemDefault())
                    .format(formatterDateTime);

            // Calculate the elapsed time
            long elapsedTime = System.currentTimeMillis() - fRep.getStartTime();
            // Convert the elapsed time to hours, minutes, and seconds
            long hours = (elapsedTime / 1000) / 3600;
            long minutes = ((elapsedTime / 1000) % 3600) / 60;
            long seconds = (elapsedTime / 1000) % 60;
            // Display the time in HH:mm:ss format
            elapsedTimeString = String.format("%02d:%02d:%02d", hours, minutes, seconds);
        }
        testDetails.put("Date Test Start", dateTimeStart);
        testDetails.put("Tested By", CFGhelper.cons("extent.tested.by"));
        testDetails.put("Total Elapsed Time", elapsedTimeString);

        int startRowDetail = 8;
        for (Map.Entry<String, String> entry : testDetails.entrySet()) {
            Row addedRow = sheet.createRow(startRowDetail);
            Cell addedCol = addedRow.createCell(5);
            addedCol.setCellValue(entry.getKey() + "  :  " + entry.getValue());
            startRowDetail++;
        }
    }
}
