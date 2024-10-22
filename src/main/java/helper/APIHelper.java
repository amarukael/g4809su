package helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import com.google.gson.Gson;

import io.cucumber.java.Scenario;
import model.ResUrl;
import model.ResUrl2;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class APIHelper {
    private static Gson gson = new Gson();

    public static ResUrl getHitAPI(Scenario scenario, String idLog, String jsnstr, String url, String contentTypeHead,
            Map<String, String> lheader, int isFlgHeader, int timeOutCon) {
        ResUrl res = new ResUrl();
        HttpClient httpClient = new DefaultHttpClient();
        String resultString = "";
        int isFoundError = 0;
        String cekConnect = "";

        try {
            HttpParams httpParameters = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpParameters, timeOutCon);
            HttpConnectionParams.setSoTimeout(httpParameters, timeOutCon);
            httpClient = new DefaultHttpClient(httpParameters);

            scenario.log("URL API: " + url);
            HttpPost post = new HttpPost(url);
            StringEntity postingString = new StringEntity(jsnstr);
            post.setEntity(postingString);
            post.setHeader("Content-Type", contentTypeHead);
            if (isFlgHeader == 1) {
                for (Map.Entry<String, String> pair : lheader.entrySet()) {
                    post.setHeader(pair.getKey(), pair.getValue());
                }
            }
            scenario.log("Header: " + gson.toJson(post.getAllHeaders()));

            scenario.log("Input Gson: " + jsnstr);
            HttpResponse response = httpClient.execute(post);
            HttpEntity httpEntity = response.getEntity();

            scenario.log("Response API: "
                    + String.valueOf(response.getStatusLine().getStatusCode()));
            if (httpEntity != null) {
                InputStream inputStream = httpEntity.getContent();
                Header contentEncoding = response.getFirstHeader("Content-Encoding");

                resultString = convertStreamToString(inputStream);
                scenario.log("Output GsOn: " + resultString);
                inputStream.close();
                cekConnect = String.valueOf(response.getStatusLine().getStatusCode());
                if (response.getStatusLine().getStatusCode() != 200) {
                    isFoundError = 1;
                }
            }
        } catch (Exception ste) {
            isFoundError = 1;
            scenario.log("ERROR: " + ste);
            if (String.valueOf(ste).contains("timed out") || String.valueOf(ste).contains("failed to respond")) {
                cekConnect = "408";
            } else {
                cekConnect = "5";
            }
        } finally {
            if (cekConnect.isEmpty()) {
                cekConnect = "500";
                isFoundError = 1;
            }
            httpClient.getConnectionManager().shutdown();
        }

        // scenario.log("Cek Error: " + cekConnect + "||" + isFoundError);
        if (isFoundError == 0) {
            try {
                if (!resultString.isEmpty()) {
                    res.setRc("00");
                    res.setRcdesc("SUKSES");
                    res.setDataJson(resultString);
                    res.setHttpCode(cekConnect);

                    return res;
                } else {
                    // scenario.log("ERROR: " + "ERROR");
                    res.setRc("5");
                    res.setRcdesc("GAGAL");
                    res.setHttpCode(cekConnect);

                    return res;
                }
            } catch (Exception ex) {
                scenario.log("ERROR: " + "ERROR");
                res.setRc("5");
                res.setRcdesc("GAGAL");
                res.setHttpCode(cekConnect);

                return res;
            }
        } else {
            scenario.log("Error Found");
            if (!resultString.isEmpty()) {
                res.setRc("5");
                res.setRcdesc("GAGAL");
                res.setDataJson(resultString);
                res.setHttpCode(cekConnect);

                return res;
            } else {
                if (cekConnect.equals("408")) {
                    scenario.log("ERROR: " + "SERVER Time out");
                    res.setRc("408");
                    res.setRcdesc("TIMEOUT");
                    res.setHttpCode("408");

                    return res;
                } else if (cekConnect.equals("500")) {
                    scenario.log("ERROR: " + "SERVER DOWN /ERROR");
                    res.setRc("500");
                    res.setRcdesc("SERVER DOWN");
                    res.setHttpCode("500");

                    return res;
                } else {
                    scenario.log("ERROR: " + "ERROR");
                    res.setRc("5");
                    res.setRcdesc("GAGAL");
                    res.setHttpCode("5");

                    return res;
                }
            }
        }
    }

    public static ResUrl2 getHitAPIApiProject(Scenario scenario, String method, String idLog, String jsnstr, String url,
            String contentTypeHead, Map<String, String> lheader, int isFlgHeader, int timeOutinSeconds) {
        // 1. Create timeout client
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(timeOutinSeconds, TimeUnit.SECONDS) // Connection timeout
                .readTimeout(timeOutinSeconds, TimeUnit.SECONDS) // Read timeout
                .writeTimeout(timeOutinSeconds, TimeUnit.SECONDS) // Write timeout
                .build();

        // 3. Create request builder
        Request.Builder request = new Request.Builder();

        if (!contentTypeHead.isEmpty()) {
            scenario.log("Request Header Content-Type: " + contentTypeHead);
            request.addHeader("Content-Type", contentTypeHead);
        }

        // 4. Add more header if included
        if (lheader != null) {
            for (Map.Entry<String, String> entry : lheader.entrySet()) {
                scenario.log("Request Header " + entry.getKey() + ":" + entry.getValue());
                request.addHeader(entry.getKey(), entry.getValue());
            }
        }
        // 5. Using POST or GET METHOD
        switch (method) {
            case "GET":
                scenario.log("Request URL: " + url + jsnstr);
                request.url(url + jsnstr);
                request.get();
                break;
            case "POST":
                // create request body based on contentTypeHead
                scenario.log("Request URL: " + url);
                request.url(url);
                RequestBody body = RequestBody.create(jsnstr, MediaType.parse(contentTypeHead));
                scenario.log("Request body: " + jsnstr);
                request.post(body);
                break;
        }

        // 6. Response
        // Record start time
        long startTime = System.currentTimeMillis();
        long endTime;
        try (Response response = client.newCall(request.build()).execute()) {
            startTime = response.sentRequestAtMillis();
            endTime = response.receivedResponseAtMillis();
            long timetaken = endTime - startTime;

            scenario.log("Response HTTP Status Code: " + response.code());
            scenario.log("Response HEADER:\n" + response.headers());
            String responseString = response.body() != null ? response.body().string() : "";

            ResUrl2 res = ResUrl2.builder()
                    .isSuccessful(response.isSuccessful())
                    .headers(String.valueOf(response.headers()))
                    .httpcode(response.code())
                    .httpCodeMessage(response.message())
                    .stringBody(responseString)
                    .timeTakenInMilis(timetaken)
                    .build();

            if (response.isSuccessful()) {
                scenario.log("Output JSON: " + responseString);
            } else {
                scenario.log("Error Message: " + res.getStringBody());
            }
            scenario.log("Response Time Taken: " + timetaken);
            return res;
        } catch (IOException e) {
            endTime = System.currentTimeMillis();
            long timetaken = endTime - startTime;

            scenario.log("ERROR: " + e.getMessage());
            ResUrl2 res = ResUrl2.builder()
                    .httpcode(-1)
                    .exceptionMessage(String.valueOf(e))
                    .timeTakenInMilis(timetaken)
                    .build();
            return res;
        }
    }

    public static ResUrl getHitAPIMethodGet(Scenario scenario, String idLog, String jsnstr, String url,
            String contentTypeHead, Map<String, String> lheader, int isFlgHeader, int timeOutCon, int flgParams) {
        ResUrl res = new ResUrl();
        HttpClient httpClient = new DefaultHttpClient();
        String resultString = "";
        int isFoundError = 0;
        String cekConnect = "";

        try {
            HttpParams httpParameters = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpParameters, timeOutCon);
            HttpConnectionParams.setSoTimeout(httpParameters, timeOutCon);
            httpClient = new DefaultHttpClient(httpParameters);

            scenario.log("URL API: " + url);
            HttpGet get = new HttpGet(url);
            if (flgParams == 1)
                get = new HttpGet(url + jsnstr);

            if (!contentTypeHead.isEmpty())
                get.setHeader("Content-Type", contentTypeHead);

            if (isFlgHeader == 1) {
                for (Map.Entry<String, String> pair : lheader.entrySet()) {
                    get.setHeader(pair.getKey(), pair.getValue());
                }
            }
            scenario.log("Header: " + gson.toJson(get.getAllHeaders()));

            scenario.log("Input Gson: " + jsnstr);
            HttpResponse response = httpClient.execute(get);
            HttpEntity httpEntity = response.getEntity();

            scenario.log("Response API: "
                    + String.valueOf(response.getStatusLine().getStatusCode()));
            if (httpEntity != null) {
                InputStream inputStream = httpEntity.getContent();
                Header contentEncoding = response.getFirstHeader("Content-Encoding");

                resultString = convertStreamToString(inputStream);
                scenario.log("Output GsOn: " + resultString);
                inputStream.close();
                cekConnect = String.valueOf(response.getStatusLine().getStatusCode());
                if (response.getStatusLine().getStatusCode() != 200) {
                    isFoundError = 1;
                }
            }
        } catch (Exception ste) {
            isFoundError = 1;
            scenario.log("ERROR: " + ste);
            if (String.valueOf(ste).contains("timed out") || String.valueOf(ste).contains("failed to respond")) {
                cekConnect = "408";
            } else {
                cekConnect = "5";
            }
        } finally {
            if (cekConnect.isEmpty()) {
                cekConnect = "500";
                isFoundError = 1;
            }
            httpClient.getConnectionManager().shutdown();
        }

        // scenario.log("Cek Error: " + cekConnect + "||" + isFoundError);
        if (isFoundError == 0) {
            try {
                if (!resultString.isEmpty()) {
                    res.setRc("00");
                    res.setRcdesc("SUKSES");
                    res.setDataJson(resultString);
                    res.setHttpCode(cekConnect);

                    return res;
                } else {
                    // scenario.log("ERROR: " + "ERROR");
                    res.setRc("5");
                    res.setRcdesc("GAGAL");
                    res.setHttpCode(cekConnect);

                    return res;
                }
            } catch (Exception ex) {
                scenario.log("ERROR: " + "ERROR");
                res.setRc("5");
                res.setRcdesc("GAGAL");
                res.setHttpCode(cekConnect);

                return res;
            }
        } else {
            if (!resultString.isEmpty()) {
                res.setRc("5");
                res.setRcdesc("GAGAL");
                res.setDataJson(resultString);
                res.setHttpCode(cekConnect);

                return res;
            } else {
                if (cekConnect.equals("408")) {
                    scenario.log("ERROR: " + "SERVER Time out");
                    res.setRc("408");
                    res.setRcdesc("TIMEOUT");
                    res.setHttpCode("408");

                    return res;
                } else if (cekConnect.equals("500")) {
                    scenario.log("ERROR: " + "SERVER DOWN /ERROR");
                    res.setRc("500");
                    res.setRcdesc("SERVER DOWN");
                    res.setHttpCode("500");

                    return res;
                } else {
                    scenario.log("ERROR: " + "ERROR");
                    res.setRc("5");
                    res.setRcdesc("GAGAL");
                    res.setHttpCode("5");

                    return res;
                }
            }
        }
    }

    public static ResUrl getHitAPIMethodGet(String idLog, String jsnstr, String url, String contentTypeHead,
            Map<String, String> lheader, int isFlgHeader, int timeOutCon, int flgParams) {
        ResUrl res = new ResUrl();
        HttpClient httpClient = new DefaultHttpClient();
        String resultString = "";
        int isFoundError = 0;
        String cekConnect = "";

        try {
            HttpParams httpParameters = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpParameters, timeOutCon);
            HttpConnectionParams.setSoTimeout(httpParameters, timeOutCon);
            httpClient = new DefaultHttpClient(httpParameters);

            System.out.println("URL API: " + url);
            HttpGet get = new HttpGet(url);
            if (flgParams == 1)
                get = new HttpGet(url + jsnstr);

            if (!contentTypeHead.isEmpty())
                get.setHeader("Content-Type", contentTypeHead);

            if (isFlgHeader == 1) {
                for (Map.Entry<String, String> pair : lheader.entrySet()) {
                    get.setHeader(pair.getKey(), pair.getValue());
                }
            }
            System.out.println("Header: " + gson.toJson(get.getAllHeaders()));

            System.out.println("Input Gson: " + jsnstr);
            HttpResponse response = httpClient.execute(get);
            HttpEntity httpEntity = response.getEntity();

            System.out.println("Response API: "
                    + String.valueOf(response.getStatusLine().getStatusCode()));
            if (httpEntity != null) {
                InputStream inputStream = httpEntity.getContent();
                Header contentEncoding = response.getFirstHeader("Content-Encoding");

                resultString = convertStreamToString(inputStream);
                System.out.println("Output GsOn: " + resultString);
                inputStream.close();
                cekConnect = String.valueOf(response.getStatusLine().getStatusCode());
                if (response.getStatusLine().getStatusCode() != 200) {
                    isFoundError = 1;
                }
            }
        } catch (Exception ste) {
            isFoundError = 1;
            System.out.println("ERROR: " + ste);
            if (String.valueOf(ste).contains("timed out") || String.valueOf(ste).contains("failed to respond")) {
                cekConnect = "408";
            } else {
                cekConnect = "5";
            }
        } finally {
            if (cekConnect.isEmpty()) {
                cekConnect = "500";
                isFoundError = 1;
            }
            httpClient.getConnectionManager().shutdown();
        }

        // scenario.log("Cek Error: " + cekConnect + "||" + isFoundError);
        if (isFoundError == 0) {
            try {
                if (!resultString.isEmpty()) {
                    res.setRc("00");
                    res.setRcdesc("SUKSES");
                    res.setDataJson(resultString);
                    res.setHttpCode(cekConnect);

                    return res;
                } else {
                    // scenario.log("ERROR: " + "ERROR");
                    res.setRc("5");
                    res.setRcdesc("GAGAL");
                    res.setHttpCode(cekConnect);

                    return res;
                }
            } catch (Exception ex) {
                System.out.println("ERROR: " + "ERROR");
                res.setRc("5");
                res.setRcdesc("GAGAL");
                res.setHttpCode(cekConnect);

                return res;
            }
        } else {
            if (!resultString.isEmpty()) {
                res.setRc("5");
                res.setRcdesc("GAGAL");
                res.setDataJson(resultString);
                res.setHttpCode(cekConnect);

                return res;
            } else {
                if (cekConnect.equals("408")) {
                    System.out.println("ERROR: " + "SERVER Time out");
                    res.setRc("408");
                    res.setRcdesc("TIMEOUT");
                    res.setHttpCode("408");

                    return res;
                } else if (cekConnect.equals("500")) {
                    System.out.println("ERROR: " + "SERVER DOWN /ERROR");
                    res.setRc("500");
                    res.setRcdesc("SERVER DOWN");
                    res.setHttpCode("500");

                    return res;
                } else {
                    System.out.println("ERROR: " + "ERROR");
                    res.setRc("5");
                    res.setRcdesc("GAGAL");
                    res.setHttpCode("5");

                    return res;
                }
            }
        }
    }

    private static String convertStreamToString(InputStream inputStream) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;

        try {
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace(); // To change body of catch statement use File | Settings | File Templates.
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace(); // To change body of catch statement use File | Settings | File Templates.
            }
        }

        return stringBuilder.toString();
    }

    public static ResUrl getHitAPI2(String idLog, String jsnstr, String url, String contentTypeHead,
            Map<String, String> lheader, int isFlgHeader, int timeOutCon) {
        ResUrl res = new ResUrl();
        HttpClient httpClient = new DefaultHttpClient();
        String resultString = "";
        int isFoundError = 0;
        String cekConnect = "";

        try {
            HttpParams httpParameters = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpParameters, timeOutCon);
            HttpConnectionParams.setSoTimeout(httpParameters, timeOutCon);
            httpClient = new DefaultHttpClient(httpParameters);

            System.out.println("URL API: " + url);
            HttpPost post = new HttpPost(url);
            StringEntity postingString = new StringEntity(jsnstr);
            post.setEntity(postingString);
            post.setHeader("Content-Type", contentTypeHead);
            if (isFlgHeader == 1) {
                for (Map.Entry<String, String> pair : lheader.entrySet()) {
                    post.setHeader(pair.getKey(), pair.getValue());
                }
            }
            System.out.println("Header: " + gson.toJson(post.getAllHeaders()));

            System.out.println("Input Gson: " + jsnstr);
            HttpResponse response = httpClient.execute(post);
            HttpEntity httpEntity = response.getEntity();

            System.out.println("Response API: "
                    + String.valueOf(response.getStatusLine().getStatusCode()));
            if (httpEntity != null) {
                InputStream inputStream = httpEntity.getContent();
                Header contentEncoding = response.getFirstHeader("Content-Encoding");

                resultString = convertStreamToString(inputStream);
                System.out.println("Output GsOn: " + resultString);
                inputStream.close();
                cekConnect = String.valueOf(response.getStatusLine().getStatusCode());
                if (response.getStatusLine().getStatusCode() != 200) {
                    isFoundError = 1;
                }
            }
        } catch (Exception ste) {
            isFoundError = 1;
            System.out.println("ERROR: " + ste);
            if (String.valueOf(ste).contains("timed out") || String.valueOf(ste).contains("failed to respond")) {
                cekConnect = "408";
            } else {
                cekConnect = "5";
            }
        } finally {
            if (cekConnect.isEmpty()) {
                cekConnect = "500";
                isFoundError = 1;
            }
            httpClient.getConnectionManager().shutdown();
        }

        if (isFoundError == 0) {
            try {
                if (!resultString.isEmpty()) {
                    res.setRc("00");
                    res.setRcdesc("SUKSES");
                    res.setDataJson(resultString);
                    res.setHttpCode(cekConnect);

                    return res;
                } else {
                    res.setRc("5");
                    res.setRcdesc("GAGAL");
                    res.setHttpCode(cekConnect);

                    return res;
                }
            } catch (Exception ex) {
                System.out.println("ERROR: " + "ERROR");
                res.setRc("5");
                res.setRcdesc("GAGAL");
                res.setHttpCode(cekConnect);

                return res;
            }
        } else {
            System.out.println("Error Found");
            if (!resultString.isEmpty()) {
                res.setRc("5");
                res.setRcdesc("GAGAL");
                res.setDataJson(resultString);
                res.setHttpCode(cekConnect);

                return res;
            } else {
                if (cekConnect.equals("408")) {
                    System.out.println("ERROR: " + "SERVER Time out");
                    res.setRc("408");
                    res.setRcdesc("TIMEOUT");
                    res.setHttpCode("408");

                    return res;
                } else if (cekConnect.equals("500")) {
                    System.out.println("ERROR: " + "SERVER DOWN /ERROR");
                    res.setRc("500");
                    res.setRcdesc("SERVER DOWN");
                    res.setHttpCode("500");

                    return res;
                } else {
                    System.out.println("ERROR: " + "ERROR");
                    res.setRc("5");
                    res.setRcdesc("GAGAL");
                    res.setHttpCode("5");

                    return res;
                }
            }
        }
    }

}
