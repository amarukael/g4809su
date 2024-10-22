package utility;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

public class CSVDataReader {
    static Gson gson = new Gson();

    public static List<Map<String, String>> readCSVData(String excelFilePath) throws IOException {
        String line = "";
        String splitBy = ",";
        List<Map<String, String>> data = new ArrayList<>();

        // String[] parts = excelFilePathAndSheetName.split("#", -1);
        // String excelFilePath = parts[0];
        // String sheetName = parts[1];
        try {
            // parsing a CSV file into BufferedReader class constructor
            BufferedReader br = new BufferedReader(new FileReader(excelFilePath));
            List<String[]> ltmpData = new ArrayList<>();
            while ((line = br.readLine()) != null) // returns a Boolean value
            {
                String[] tmpData = line.split(splitBy); // use comma as separator
                ltmpData.add(tmpData);
            }

            List<String> headers = new ArrayList<>();
            if (ltmpData.get(0).length > 1) {
                for (String head : ltmpData.get(0)) {
                    headers.add(head);
                }
            }

            for (int i = 1; i <= (ltmpData.size() - 1); i++) {
                int countHead = 0;
                Map<String, String> rowData = new LinkedHashMap<>();
                for (String row : ltmpData.get(i)) {
                    String header = headers.get(countHead);
                    String value = row;
                    // Cek apakah nilai sel adalah "NULL" (dalam huruf besar atau kecil)
                    if (!value.equalsIgnoreCase("NULL")) {
                        rowData.put(header, value);
                    }
                    countHead++;
                }

                if (!rowData.isEmpty()) {
                    if (rowData.get("TC").isEmpty()) {
                        break;
                    }
                    data.add(rowData);
                }
            }
            br.close();
        } catch (IOException e) {
            System.out.println(e);
        }
        return data;
    }

    public static List<Map<String, String>> readGeneralCSVData(String excelFilePath) {
        String line = "";
        String splitBy = ",";
        List<Map<String, String>> data = new ArrayList<>();
        try {
            // parsing a CSV file into BufferedReader class constructor
            BufferedReader br = new BufferedReader(new FileReader(excelFilePath));
            List<String[]> ltmpData = new ArrayList<>();
            while ((line = br.readLine()) != null) // returns a Boolean value
            {
                String[] tmpData = line.split(splitBy); // use comma as separator
                ltmpData.add(tmpData);
            }

            List<String> headers = new ArrayList<>();
            if (ltmpData.get(0).length > 1) {
                for (String head : ltmpData.get(0)) {
                    headers.add(head);
                }
            }

            for (int i = 1; i <= (ltmpData.size() - 1); i++) {
                int countHead = 0;
                Map<String, String> rowData = new LinkedHashMap<>();
                for (String row : ltmpData.get(i)) {
                    String header = headers.get(countHead);
                    String value = row;
                    // Cek apakah nilai sel adalah "NULL" (dalam huruf besar atau kecil)
                    if (!value.equalsIgnoreCase("NULL")) {
                        rowData.put(header, value);
                    }
                    countHead++;
                }

                if (!rowData.isEmpty()) {
                    data.add(rowData);
                }
            }
            br.close();
        } catch (IOException e) {
            System.out.println(e);
        }

        return data;
    }
}
