package utility.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectToDbSTG {

    public static String getSecretKeyByPartnerId(DBConfigStg.DatabaseConfigStg config, String partnerId, String TableName) {
        String secretKey = null;
        String query = "SELECT secretkey FROM "+ TableName +" WHERE partnerid=? AND isactive='1' LIMIT 1";

        try (Connection conn = DriverManager.getConnection(config.JDBC_URL, config.DB_USERNAME, config.DB_PASSWORD)) {
            System.out.println("LOG: Connection Established!");

            try (PreparedStatement statement = conn.prepareStatement(query)) {
                statement.setString(1, partnerId);
                try (ResultSet rs = statement.executeQuery()) {
                    if (rs.next()) {
                        secretKey = rs.getString("secretkey");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return secretKey;
    }
}
