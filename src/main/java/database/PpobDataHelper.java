package database;

import com.google.gson.Gson;
import constant.ConstantPpob;

import java.sql.ResultSet;

public class PpobDataHelper {
    Gson gson = new Gson();
    static String dbEnvironmentName = ConstantPpob.dbEnvironmentName;
    static String environmentSvr = ConstantPpob.environmentSvr;

    public String getSecretKeyByPartnerId(String partnerId) {
        ResultSet rs = null;
        String result = "";

        try (LoadDataSource ds = new LoadDataSource(dbEnvironmentName, environmentSvr)) {
            String strSQL = "SELECT secretkey " +
                    "FROM tb_m_agentppobv2 " +
                    "WHERE partnerid = ?";

            ds.query(strSQL, false);
            ds.appendStatement(partnerId);
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
