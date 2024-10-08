package helper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.cucumber.java.Scenario;
import model.ResUrl;
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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

public class APIHelper {
    private static Gson gson = new Gson();

    public static ResUrl getHitAPI(Scenario scenario, String idLog, String jsnstr, String url
            , String contentTypeHead, Map<String, String> lheader, int isFlgHeader, int timeOutCon) {
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
            if (String.valueOf(ste).contains("timed out")  || String.valueOf(ste).contains("failed to respond")) {
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

//        scenario.log("Cek Error: " + cekConnect + "||" + isFoundError);
        if (isFoundError == 0) {
            try {
                if (!resultString.isEmpty()) {
                    res.setRc("00");
                    res.setRcdesc("SUKSES");
                    res.setDataJson(resultString);
                    res.setHttpCode(cekConnect);

                    return res;
                } else {
//                    scenario.log("ERROR: " + "ERROR");
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

    public static ResUrl getHitAPIMethodGet(Scenario scenario, String idLog, String jsnstr, String url
            , String contentTypeHead, Map<String, String> lheader, int isFlgHeader, int timeOutCon, int flgParams) {
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
            if (String.valueOf(ste).contains("timed out")  || String.valueOf(ste).contains("failed to respond")) {
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

//        scenario.log("Cek Error: " + cekConnect + "||" + isFoundError);
        if (isFoundError == 0) {
            try {
                if (!resultString.isEmpty()) {
                    res.setRc("00");
                    res.setRcdesc("SUKSES");
                    res.setDataJson(resultString);
                    res.setHttpCode(cekConnect);

                    return res;
                } else {
//                    scenario.log("ERROR: " + "ERROR");
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

    private static String convertStreamToString(InputStream inputStream) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;

        try {
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }

        return stringBuilder.toString();
    }
}
