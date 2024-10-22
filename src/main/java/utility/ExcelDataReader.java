package utility;

import io.cucumber.java.sl.In;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ExcelDataReader {
    public static List<Map<String, String>> readExcelData(String excelFilePathAndSheetName) throws IOException {
        int flgExcel = 0;
        List<Map<String, String>> data = new ArrayList<>();

        String[] parts = excelFilePathAndSheetName.split("#", -1);
        String excelFilePath = parts[0];
        String sheetName = parts[1];

        InputStream inputStream = null;
        Workbook workbook;
        try {
            inputStream = Files.newInputStream(new File(excelFilePath).toPath());
            flgExcel = 1;
        } catch (Exception e) {
            try {
                inputStream = ExcelDataReader.class.getClassLoader().getResourceAsStream(excelFilePath);
                flgExcel = 1;
            } catch (Exception e1) {

            }
        }

        if (flgExcel == 1) {
            workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheet(sheetName);
            DataFormatter dataFormatter = new DataFormatter();
            Row headerRow = sheet.getRow(0);
            List<String> headers = new ArrayList<>();
            for (Cell cell : headerRow) {
                if (!dataFormatter.formatCellValue(cell).isEmpty()) {
                    headers.add(dataFormatter.formatCellValue(cell));
                } else {
                    break;
                }
            }

            for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                Row dataRow = sheet.getRow(rowIndex);
                if (dataRow != null) {
                    Map<String, String> rowData = new LinkedHashMap<>();
                    for (int cellIndex = 0; cellIndex < headers.size(); cellIndex++) {
                        Cell dataCell = dataRow.getCell(cellIndex);
                        if (dataCell != null) {
                            String header = headers.get(cellIndex);
                            String value = dataFormatter.formatCellValue(dataCell);

                            // Cek apakah nilai sel adalah "NULL" (dalam huruf besar atau kecil)
                            if (!value.equalsIgnoreCase("NULL")) {
                                rowData.put(header, value);
                            }
                        }
                    }

                    if (!rowData.isEmpty()) {
                        if (rowData.get("TC").isEmpty()) {
                            break;
                        }
                        data.add(rowData);
                    }
                }
            }
        }

        return data;
    }
}
