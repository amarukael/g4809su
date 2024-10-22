package helper;

import utility.CSVDataReader;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApiProjectAdminFeeHelper {
    Map<String, String> AdminFeeData = new HashMap<>();

    public ApiProjectAdminFeeHelper()  {
        try {
            loadAdminFeeData();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void loadAdminFeeData() throws IOException {
        String adminFeeFilepath = CFGhelper.cons("apiproject.adminfee.csv.filepath");

        List<Map<String,String>> data = CSVDataReader.readGeneralCSVData(adminFeeFilepath);
        for (Map<String, String> rowData : data) {
            AdminFeeData.put(rowData.get("productId")+rowData.get("partnerId"), rowData.get("adminFee"));
        }
    }

    public long getAdminFee(String productId, String partnerId) {
        // Pencarian adminfee
        // 1. Jika coba ambil dengan kombinasi productId+partnerId tidak ada,
        // 2. Maka ambil menggunakan productId key
        String adminFee =  AdminFeeData.get(productId+partnerId);
        if (adminFee == null) {
            adminFee = AdminFeeData.get(productId);
            if (adminFee == null) {
                return -1;
            }
        }
        return Long.parseLong(adminFee.replace(",","").replace(".",""));
    }
}
