package utility;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import com.google.gson.JsonObject;

public class PaymentGatewayNotifier {

    public static void main(String args) {
        try {
            sendPostRequest(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void sendPostRequest(String vaNumber) {
        try {
            // Ambil data dari database
            JsonObject data = fetchData(vaNumber);

            // Format tx_date sesuai dengan contoh request
            String txDateFormatted = new SimpleDateFormat("MM/dd/yyyy'T'HH:mm:ss.SSSZ").format(new Date());

            // Buat payload sesuai dengan contoh request
            JsonObject payload = new JsonObject();
            payload.addProperty("va_number", vaNumber);
            payload.addProperty("amount", data.get("amount").getAsInt());
            payload.addProperty("partner_user_id", data.get("partner_user_id").getAsString());
            payload.addProperty("success", true);
            payload.addProperty("tx_date", txDateFormatted);
            payload.addProperty("username_display", "null IDS");
            payload.addProperty("trx_expiration_date", data.get("trx_expiration_date").getAsString());
            payload.addProperty("partner_trx_id", data.get("trackingReff").getAsString());
            payload.addProperty("trx_id", data.get("trx_id").getAsString());

            // URL untuk request POST
            String url = "http://117.54.12.141:8080/PaymentGateway/webresources/notificationoy";
            // String url =
            // "https://private-staging.ids.id/PaymentGateway/webresources/notificationoy";

            // Buat koneksi dan kirim request POST
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("User-Agent", "PostmanRuntime/7.41.0");
            conn.setRequestProperty("Accept", "*/*");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Cache-Control", "no-cache");
            conn.setRequestProperty("Postman-Token", UUID.randomUUID().toString());

            conn.setDoOutput(true);

            try (OutputStream os = conn.getOutputStream()) {
                os.write(payload.toString().getBytes());
                os.flush();
            }

            // Baca respons
            int responseCode = conn.getResponseCode();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println("Request berhasil dikirim");
                System.out.println(response.toString());
            } else {
                System.out.println("Gagal mengirim request. Status code: " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static JsonObject fetchData(String vaNumber) throws Exception {
        // Database connection parameters
        String url = "jdbc:mysql://117.54.12.146:3364/paymentgateway";
        String user = "fahmi";
        String password = "F4hm1@1Ds";

        Connection conn = DriverManager.getConnection(url, user, password);
        PreparedStatement stmt = conn.prepareStatement(
                "SELECT amount, bookId, (SELECT orderid FROM tb_r_payment_gateway WHERE no_transaction = ?) AS trx_id, trackingReff, trxExpired FROM tb_r_transaction_va WHERE va = ?");
        stmt.setString(1, vaNumber);
        stmt.setString(2, vaNumber);

        ResultSet rs = stmt.executeQuery();
        JsonObject data = new JsonObject();

        if (rs.next()) {
            data.addProperty("amount", rs.getInt("amount"));
            data.addProperty("partner_user_id", rs.getString("bookId"));
            data.addProperty("trx_id", rs.getString("trx_id"));
            data.addProperty("trackingReff", rs.getString("trackingReff"));
            data.addProperty("trx_expiration_date", rs.getString("trxExpired"));
        }

        rs.close();
        stmt.close();
        conn.close();

        return data;
    }
}
