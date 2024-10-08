package database;

import com.google.gson.Gson;
import constant.ConstantMnM;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

public class MandMDataHelper {
    Gson gson = new Gson();
    static String dbEnvironmentName = ConstantMnM.dbEnvironmentName;
    static String environmentSvr = ConstantMnM.environmentSvr;

    public Map<String, String> getMessageStatAndRespCustByMessageId(String messageId) {
        ResultSet rs = null;
        Map<String, String> result = new HashMap<>();

        try (LoadDataSource ds = new LoadDataSource(dbEnvironmentName, environmentSvr)) {
            String strSQL = "select status, response_supplier " +
                    "From tb_r_log_trans " +
                    "Where messages_id = ?";

            ds.query(strSQL, false);
            ds.appendStatement(messageId);
//            System.out.println(strSQL);

            rs = ds.execute();
            boolean isHasRow = rs.next();
            if (isHasRow) {
                result.put("status", rs.getString(1));

                if (rs.getString(2) != null) {
                    result.put("respSupplier", rs.getString(2));
                } else {
                    result.put("respSupplier", "");
                }
            } else {
                result.put("status", "");
                result.put("respSupplier", "");
            }
        } catch (Exception e) {
            System.out.println("ERROR : " + e);
        }

        System.out.println("DB Res: " + gson.toJson(result));
        return result;
    }
}
