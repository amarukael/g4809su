package helper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import utility.db.ConnectToDbSTG;
import utility.db.DBConfigDev;
import utility.db.DBConfigStg;
import utility.db.DatabaseUtility;

public class Helper {
    public static String getSecretKey(String type, String environmentName, String partnerId, String TableName) {
        String secretKey = "";
        if (type.equalsIgnoreCase("DEV")) {
            DBConfigDev.DatabaseEnvironmentDEV environment = DBConfigDev.getEnvironmentByName(environmentName);
            DBConfigDev.DatabaseConfigDev config = new DBConfigDev.DatabaseConfigDev(environment);
            secretKey = DatabaseUtility.getSecretKeyByPartnerId(partnerId, config, TableName);
        } else if (type.equalsIgnoreCase("STG")) {
            DBConfigStg.DatabaseEnvironmentSTG environment = DBConfigStg.getEnvironmentByName(environmentName);
            DBConfigStg.DatabaseConfigStg config = new DBConfigStg.DatabaseConfigStg(environment);
            secretKey = ConnectToDbSTG.getSecretKeyByPartnerId(config, partnerId, TableName);
        }

        return secretKey;
    }

    public static String getPrivateKey() {
        String privatekey = "MIIEowIBAAKCAQEAvqI1TQm688vrN5rHNF/mAQiJmd/BrpCdysnDP32VRkqn4JIDRaboj5OjZHuYSxPx4xxuIGEAHYv2knROXVU3BaVcPihcxIP3mNw5GAln12SHozCju9TumSjbT4jVCP7TlAXLaowkfpIAwH85ur5JN0UB0SKC0HcbpPtvfQSEnaMf0AvhHKyJ/M1rpaIvD4oPfsNgQtL95vqUgxv3UtmYwVAruQ4WnNUOTEPMtKTz9xt4cX46Kfs8q/5OVUIPrGpKs8/S+fC4evxMxqUZhZf/1hjHT90DWmgtJ3p6O1yjJrIP+MyHgTyu72St3Hbq1CAXKDbTTW/gPFXsLDaJIcAvXwIDAQABAoIBACw76VBJ7LZ7X8YURRrzEaS1vXAWCpQd/G88p/CIjGW2FwQ1/UphM191a5l6UFlDdII1a5xZxbQVcQ6ErFZd2pmVyDSoGvaChKZUm27nZ9AEBLEqLSP9gavKXjEzV/NxZdjYC15azHEOIdyI6PVbCBQqEATeFCqZKLH8od8JeFg3RZCjrLNhOB1rQs5Gd5fBHmCICwcKyGA6uFEIDH0QEsoiYFguxtNevCFYSAt1rhDfSs62W17iiiQjDdR2s+VIxF4f8QG1HwlmaJXbNOXVMjBrj4U6tKe1hlGXD5j0oeS0Guv5kYJ2vSiFr+65yWXQ7it4oIHqjkE42cmOMmJyhGkCgYEA93sB7I0Zuo/mhi9MDHoIdel9arbceNU1EHrU0RjvEcuAJgpPGSCi/lX2p7MWxXFy7/yPHPIbOyn4Dp+1fVLquF+2TLOR2aAwYtkqVV/VLK8qQY3TK46XMJW6mXttCm7H8bbE11nGNovmGdVCYIcCQfcXt3fj8jAqR334sV+Uqj0CgYEAxTI4rodtT9O8lnu4B3F8T83UYp11AY8rRS1S/fCTg028UWTkRzNGyzniZDCUEJ1wckTeg5nI/i4eY89l0B10s9SokFS2F/cA3/lePHHzpOGC1YocZXeTAIYCdGMBpUEdUVlNvpj0rwufKXFiXHwczl4BXN96UXmXjSOtpAhSBcsCgYAYp5+EcnYquOVNXu7jm1C2doFxHTCPtSXpM2N6RVKj4jMtFv/UUAUM84F0KYpON0QtFmsnhhMibMbypgSNrLIHljlyIL2aAbRwVJQ8FyUEo5rBFyD/iOAVDgWd/sbZMDopIX0wuxfdHUjaL/3B4hFegwBQ3dfOq++6WZlqCkykPQKBgAKmny5xcZ2+Ge3n8dxfwS0NdkpxFW6U7x758gCp6EG+nMZ3Vb5DSTNYLtvr70Gd7DINDPS174bDNfNfiV3X14bHWBDAosYFswRPxN2JJp5Tzb9zINiYV+iM5KGN5yrCilNaoPnr8nYb8OLMvGbD4FtB1kDaXDmWKdxi22W2GuwrAoGBALmLwM9btmJ4rTUJjN5Pnaa2bA7bnFPz6xDKHs6GD8YPQJWrd/MN60mb1daQoKDcBi+swfDipguqRNXysGb5wDx7nKHmfXTvvh2jPQ6G+Gr7ReUXfI9Fv54eczoPFXbuLgB2L2GVbFFvZtmWu3VU7X2wbtfYO+OjkYNUPWS+903i";

        return privatekey;
    }

    public static String removeHttps(String url) {
        Pattern pattern = Pattern.compile("https://[^/]+");
        Matcher matcher = pattern.matcher(url);

        if (matcher.find()) {
            return url.substring(matcher.end());
        }

        return url;
    }

    public String createTrxDate(String format) {
        String result = "";
        Date dNow = new Date();
        Locale inLocale = new Locale("id", "ID");
        SimpleDateFormat ftpay = new SimpleDateFormat(format, inLocale);
        result = ftpay.format(dNow);

        return result;
    }

    public static String createDate(String format) {
        String result = "";
        Date dNow = new Date();
        Locale inLocale = new Locale("id", "ID");
        SimpleDateFormat ftpay = new SimpleDateFormat(format, inLocale);
        result = ftpay.format(dNow);

        return result;
    }

    public void delay(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void generateExcel() {
        String jsonFilePath = "C:\\Users\\fahmi.amaruddin\\Documents\\repo\\Github\\AutoAPI\\target\\report\\cucumber.json";
        String excelFilePath = "C:\\Users\\fahmi.amaruddin\\Documents\\repo\\Github\\AutoAPI\\test-output\\output.xlsx";

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(new File(jsonFilePath));
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Cucumber Report");

            // Create header
            String[] headers = { "Scenario", "Step", "Output", "Status", "Error Message", "Log" };
            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < headers.length; i++) {
                headerRow.createCell(i).setCellValue(headers[i]);
            }

            CellStyle longTextStyle = workbook.createCellStyle();
            longTextStyle.setWrapText(true);
            longTextStyle.setAlignment(HorizontalAlignment.LEFT);
            longTextStyle.setVerticalAlignment(VerticalAlignment.TOP);

            CellStyle defaultStyle = workbook.createCellStyle();
            defaultStyle.setAlignment(HorizontalAlignment.LEFT);
            defaultStyle.setVerticalAlignment(VerticalAlignment.TOP);

            int rowCount = 1;
            for (JsonNode featureNode : rootNode) {
                for (JsonNode scenarioNode : featureNode.path("elements")) {
                    String scenarioName = scenarioNode.path("name").asText();
                    for (JsonNode stepNode : scenarioNode.path("steps")) {
                        String scenarioLog = scenarioNode.path("after").path(0).path("output").path(0).asText();
                        Row row = sheet.createRow(rowCount++);

                        // Create cells directly
                        Cell cell0 = row.createCell(0);
                        cell0.setCellValue(scenarioName);
                        cell0.setCellStyle(scenarioName.length() > 50 ? longTextStyle : defaultStyle);

                        Cell cell1 = row.createCell(1);
                        String stepName = stepNode.path("name").asText();
                        cell1.setCellValue(stepName);
                        cell1.setCellStyle(stepName.length() > 50 ? longTextStyle : defaultStyle);

                        Cell cell2 = row.createCell(2);
                        String output = formatToXml(stepNode.path("output").toString());
                        cell2.setCellValue(output);
                        cell2.setCellStyle(output.length() > 50 ? longTextStyle : defaultStyle);

                        Cell cell3 = row.createCell(3);
                        String status = stepNode.path("result").path("status").asText();
                        cell3.setCellValue(status);
                        cell3.setCellStyle(status.length() > 50 ? longTextStyle : defaultStyle);

                        Cell cell4 = row.createCell(4);
                        String errorMessage = stepNode.path("result").path("error_message").asText();
                        cell4.setCellValue(errorMessage);
                        cell4.setCellStyle(errorMessage.length() > 50 ? longTextStyle : defaultStyle);

                        if (stepNode.path("name").asText().contains("perform")) {
                            scenarioLog = "";
                        }
                        Cell cell5 = row.createCell(5);
                        cell5.setCellValue(scenarioLog);
                        cell5.setCellStyle(scenarioLog.length() > 50 ? longTextStyle : defaultStyle);
                    }
                }
            }

            for (int i = 0; i < headerRow.getLastCellNum(); i++) {
                sheet.autoSizeColumn(i);
                if (sheet.getColumnWidth(i) > 45 * 256) {
                    sheet.setColumnWidth(i, 45 * 256);
                }
            }

            try (FileOutputStream outputStream = new FileOutputStream(excelFilePath)) {
                workbook.write(outputStream);
            }

            workbook.close();
            System.out.println("Excel file created successfully.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String formatToXml(String input) {
        String dataString = input.replaceAll("[\\[\\]\"]", "").trim();
        int dataStartIndex = dataString.indexOf("<data>");
        if (dataStartIndex == -1)
            return input;
        return dataString.substring(dataStartIndex).replace("\\n", "\n");
    }
}
