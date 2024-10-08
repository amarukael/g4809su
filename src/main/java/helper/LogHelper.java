package helper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class LogHelper {
    static String search = "QA1728357036";
    static String traceId;
    public static Map<String, String> logReqInqBiller = new HashMap<>();

    public static void main(String[] args) {
        try {
            String url = "https://ids-fe1.com/monitoringsrvr/webresources/getLogv2?key=logIDS123&userid=IDS1&fullpath=apiproject/logger.log";
            Document document = Jsoup.connect(url).get();

            String regex = "\\{(\"message\":\".*?\",\"timestamp\":\\{\"seconds\":\\d+,\"nanos\":\\d+\\},\"traceId\":\"([^\"]*)\",\"severity\":\"([^\"]*)\"\\})";
            Pattern pattern = Pattern.compile(regex);

            List<String> logs = new ArrayList<>();
            for (Element div : document.select("div")) {
                Matcher matcher = pattern.matcher(div.text());
                while (matcher.find()) {
                    logs.add(matcher.group());
                }
            }

            for (String log : logs) {
                regex = "\\[REQUEST\\]\\s*(.*)";
                String[] parts = null;

                if (log.matches(".*" + regex + ".*")) {
                    String[] messages = log.split("message\":\"");
                    String messagePart = messages[1].split("\"")[0]; // Mengambil isi message
                    parts = messagePart.split("\\[REQUEST\\]\\s*")[1].split("\\|");
                }

                if (parts != null) {
                    if (parts[2].equals(search)) {
                        JsonObject jsonObject = JsonParser.parseString(log).getAsJsonObject();
                        traceId = jsonObject.get("traceId").getAsString();
                    }
                }

                try {
                    JsonObject jsonObject = JsonParser.parseString(log).getAsJsonObject();
                    String message = jsonObject.get("message").getAsString();

                    String traceIdPattern = "\\{(" + traceId + ")\\}";
                    pattern = Pattern.compile(traceIdPattern);
                    Matcher matcher = pattern.matcher(message);

                    if (matcher.find()) {
                        System.out.println(jsonObject.toString());
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String inqAlfaDKF(String trackingref, JSONObject reqJson) {
        StringBuilder sb = new StringBuilder();
        try {
            String url = "https://ids-fe1.com/monitoringsrvr/webresources/getLogv2?key=logIDS123&userid=IDS1&fullpath=apiproject/logger.log";
            Document document = Jsoup.connect(url).get();

            String regex = "\\{(\"message\":\".*?\",\"timestamp\":\\{\"seconds\":\\d+,\"nanos\":\\d+\\},\"traceId\":\"([^\"]*)\",\"severity\":\"([^\"]*)\"\\})";
            Pattern pattern = Pattern.compile(regex);

            List<String> logs = new ArrayList<>();
            for (Element div : document.select("div")) {
                Matcher matcher = pattern.matcher(div.text());
                while (matcher.find()) {
                    logs.add(matcher.group());
                }
            }

            for (String log : logs) {
                String req = "\\[REQUEST\\]\\s*(.*)";
                String reqBiller = "[HTTP REQUEST INQUIRY]";
                String resBiller = "[RESPONSE INQUIRY BILLER DKF]";
                String res = "[RESPONSE]";
                String[] parts = null;

                if (log.matches(".*" + req + ".*")) {
                    String[] messages = log.split("message\":\"");
                    String messagePart = messages[1].split("\"")[0]; // Mengambil isi message
                    parts = messagePart.split("\\[REQUEST\\]\\s*")[1].split("\\|");
                }

                if (parts != null) {
                    if (parts[2].equals(trackingref)) {
                        JsonObject jsonObject = JsonParser.parseString(log).getAsJsonObject();
                        traceId = jsonObject.get("traceId").getAsString();
                    }
                }

                try {
                    JsonObject jsonObject = JsonParser.parseString(log).getAsJsonObject();
                    String message = jsonObject.get("message").getAsString();

                    String traceIdPattern = "\\{(" + traceId + ")\\}";
                    pattern = Pattern.compile(traceIdPattern);
                    Matcher matcher = pattern.matcher(message);

                    if (matcher.find()) {
                        sb.append(jsonObject.toString());
                        String trackLog = jsonObject.toString();
                        if (trackLog.contains(reqBiller)) {
                            regex = "\\[HTTP REQUEST INQUIRY\\] (\\{.*?\\})";
                            pattern = Pattern.compile(regex);
                            matcher = pattern.matcher(trackLog);
                            if (matcher.find()) {
                                String extractedJson = matcher.group(1);
                                try {
                                    extractedJson = extractedJson.replace("\\", "");
                                    JsonObject reqObject = JsonParser.parseString(extractedJson).getAsJsonObject();
                                    System.out.println(reqObject);
                                } catch (JSONException e) {
                                    System.out.println("Error creating JSONObject: " + e.getMessage());
                                }
                            }

                        } else if (trackLog.contains(resBiller)) {
                            System.out.println("Res Biller : " + jsonObject.toString());
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
