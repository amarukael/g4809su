package utility;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ExcelDataReader {

	 public static List<Map<String, String>> readExcelData(String excelFilePathAndSheetName) throws IOException {
	        List<Map<String, String>> data = new ArrayList<>();

	        String[] parts = excelFilePathAndSheetName.split("#");
	        String excelFilePath = parts[0];
	        String sheetName = parts[1];

	        try (InputStream inputStream = ExcelDataReader.class.getClassLoader().getResourceAsStream(excelFilePath);
	             Workbook workbook = WorkbookFactory.create(inputStream)) {

	            Sheet sheet = workbook.getSheet(sheetName);
	            DataFormatter dataFormatter = new DataFormatter();

	            Row headerRow = sheet.getRow(0);
	            List<String> headers = new ArrayList<>();
	            for (Cell cell : headerRow) {
	                headers.add(dataFormatter.formatCellValue(cell));
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
	                        data.add(rowData);
	                    }
	                }
	            }
	        }

	        return data;
	    }
}
