package database;

import com.google.gson.Gson;
import constant.ConstantDims;

import java.sql.ResultSet;

public class DimsDataHelper {
    Gson gson = new Gson();
    static String dbEnvironmentName = ConstantDims.dbEnvironmentName;
    static String environmentSvr = ConstantDims.environmentSvr;

    public String getSecretKeyByToken(String token) {
        ResultSet rs = null;
        String result = "";

        try (LoadDataSource ds = new LoadDataSource(dbEnvironmentName, environmentSvr)) {
            String strSQL = "SELECT secret_key " +
                    "FROM tb_m_partner a " +
                    "INNER JOIN tb_r_auth b ON b.partner_id = a.partner_id " +
                    "WHERE token = ?";

            ds.query(strSQL, false);
            ds.appendStatement(token);
//            System.out.println(strSQL);

            rs = ds.execute();
            boolean isHasRow = rs.next();
            if (isHasRow) {
                result = rs.getString(1);
            }
        } catch (Exception e) {
            System.out.println("ERROR : " + e);
        }

        System.out.println("DB Res: " + result);
        return result;
    }
}
