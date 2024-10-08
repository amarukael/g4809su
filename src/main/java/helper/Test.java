package helper;

import org.json.JSONObject;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Test {
    public static void main(String[] args) {
        String jsonString = "{\"TrxDate\":\"2024-10-08 11:34:11\",\"PartnerID\":\"SAT\",\"CustomerID\":\"00030001\",\"TrackingRef\":\"QA1728362049\",\"Signature\":\"31497d24dcdbc6284dba3505fb391cce\"}";

        // Mengonversi string ke JSONObject
        JSONObject jsonObject = new JSONObject(jsonString);
        JsonObject reqObject = JsonParser.parseString(jsonString).getAsJsonObject();
        // Mencetak JSONObject
        System.out.println(reqObject.toString()); // Indentasi 4 spasi
    }
}
