package steplist.stepdef;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertEquals;

//import com.sun.org.apache.xpath.internal.operations.Bool;
import helper.Helper;
import io.cucumber.java.Scenario;
import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.XML;
import io.restassured.path.json.JsonPath;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.jsoup.parser.Parser;
import utility.*;
import utility.signature.MD5;
import utility.signature.SHA1;
import utility.signature.SHA256;
import utility.signature.SHA256RSA;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Step2 {
    public static List<Map<String, String>> excelData;
    private Response response;
    private final Rand r = new Rand();
    private static String secretKey;
    private final String timestamp = r.getTimeStamp();
    private static String token;
    private final SHA256RSA sha256rsa = new SHA256RSA();
    private static String auth;
    static String totalamount = "";
    static String signature = null;

    // Create a new ArrayList
    List<String> TestCase = new ArrayList<>();
    List<String> Rc = new ArrayList<>();
    List<String> Desc = new ArrayList<>();
    List<String> Result = new ArrayList<>();

    // Additional ArrayList
    List<String> RequestBodyList = new ArrayList<>();
    List<String> RequestMethodList = new ArrayList<>();
    List<String> RequestUriList = new ArrayList<>();
    List<String> ContentTypeList = new ArrayList<>();
    List<String> ResponseBodyList = new ArrayList<>();
    List<String> ExpectedRcList = new ArrayList<>();
    List<String> ResTimeList = new ArrayList<>();
    List<String> ResStatusCodeList = new ArrayList<>();

    Scenario scenario = Hooks.scenario;

    //Sheetname
    String ActiveSheet;
    String reportconfigfullpath = "src/test/resources/extent.properties";

    //for Transferlink
    static String trxidTransferlink = "";
    //for Transferlink
    @Given("I have Excel data file {string} and sheet {string}")
    public void i_have_excel_data_file_and_sheet(String excelFilePath, String sheetName) {
        try {
            String sheetPath = excelFilePath + "#" + sheetName;
            excelData = ExcelDataReader.readExcelData(sheetPath);
            ActiveSheet = sheetName;

        } catch (IOException e) {
            e.printStackTrace();
            scenario.log("Excel data is null. Check the file path and sheet name.");
        }
    }

    @Then("I perform POST request {string} and get Token")
    public void i_perform_post_to_get_token(String url) {
        for (Map<String, String> rowData : excelData) {
            String partnerId = rowData.get("partnerid");
            String endpoint = rowData.get("endpoint");
            baseURI = EndpointList.getEndpoint(endpoint);

            // Body Request
            String requestBody = "{\r\n"
                    + "    \"granttype\":\"partner_credentials\"\r\n"
                    + "}";

            String StringToSign = partnerId + "|" + timestamp;

            try {
                PrivateKey privateKey = sha256rsa.loadPrivateKey(Helper.getPrivateKey());
                auth = sha256rsa.sign(StringToSign, privateKey);
            } catch (Exception e) {
                e.printStackTrace();
            }

            response = given()
                    .with()
                    .header("X-SIGNATURE", auth)
                    .header("X-TIMESTAMP", timestamp)
                    .header("X-PARTNER", partnerId)
                    .body(requestBody)
                    .contentType(ContentType.JSON)
                    .log().method() // Log the request method
                    .log().uri() // Log the request URI
                    .log().headers() // Log the request headers
                    .log().body() // Log the request body
                    .post(url);

            String responseBody = response.getBody().asPrettyString();
            scenario.log("Response Body: " + responseBody);
            assertEquals(response.getStatusCode(), 200);
            token = response.then().extract().path("accessToken");
        }
    }

    @Then("I perform POST request {string} and get Response")
    public void i_perform_post_request_and_get_response(String url) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException {
        // Loop through excelData and make API requests
        String TC = null;
        String trackingref = null;

        for (Map<String, String> rowData : excelData) {
            String currentTC = rowData.get("TestCase");

            if (TC == null || !TC.equals(currentTC)) {
                // If TC is null (first iteration) or TC is different from currentTC, generate a new trackingref
                trackingref = r.getRandomRef();
            }

            TC = currentTC; // Update TC for the next iteration

            if (TC == null || TC.isEmpty()) {
                // If TC is empty, end the processing
                break; // This will exit the method or function
            }
            scenario.log("TestCase: " + TC);

            String endpoint = rowData.get("endpoint");
            baseURI = EndpointList.getEndpoint(endpoint);
            String partnerId = rowData.get("partnerid");

            secretKey = getSecretKey(rowData, partnerId);

            String xAuth = rowData.get("xAuth");
            String xSignature = rowData.get("xSignature");
            String xTimestamp = rowData.get("xTimestamp");
            String xExternal = rowData.get("xExternal");
            String xPartner = rowData.get("xPartner");
            String ExpectedRc = rowData.get("rc");
            String sigMethod = rowData.get("sig");

            JSONArray jsonArray = buildRequestBody(rowData, trackingref);

            StringBuilder concatenatedString = new StringBuilder();
            JSONObject jsonObject = new JSONObject();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONArray keyValueArray = jsonArray.getJSONArray(i);
                String key = keyValueArray.getString(0);
                String value = keyValueArray.getString(1);
                jsonObject.put(key, value);
                concatenatedString.append(value);
            }

            scenario.log("Sign method: " + sigMethod);
            scenario.log("String to sign: " + concatenatedString);

            String requestBody = generateSignature(sigMethod, jsonObject, url, concatenatedString);

            Set<String> multipart = new HashSet<>(Arrays.asList("/productinventory/request", "/supplierfee/request", "/provider/request"));

            // Create the request specification
            RequestSpecification request = given()
                    .log().method() // Log the request method
                    .log().uri() // Log the request URI
                    .log().headers(); // Log the request headers

            // Add headers conditionally based on your requirements
            if (xAuth.equalsIgnoreCase("TRUE")) {
                request.header("Authorization", "bearer " + token);
            } else if (xAuth.equalsIgnoreCase("-")) {
                request.header("Authorization", "");
            }
            if (xSignature.equalsIgnoreCase("TRUE")) {
                request.header("X-SIGNATURE", signature);
            } else if (xSignature.equalsIgnoreCase("-")) {
                request.header("X-SIGNATURE", "");
            }
            if (xTimestamp.equalsIgnoreCase("TRUE")) {
                request.header("X-TIMESTAMP", timestamp);
            } else if (xTimestamp.equalsIgnoreCase("-")) {
                request.header("X-TIMESTAMP", "");
            }
            if (xExternal.equalsIgnoreCase("TRUE")) {
                request.header("X-EXTERNAL", r.getRandomTrxId());
            } else if (xExternal.equalsIgnoreCase("-")) {
                request.header("X-EXTERNAL", "");
            }
            if (xPartner.equalsIgnoreCase("TRUE")) {
                request.header("X-PARTNER", partnerId);
            } else if (xPartner.equalsIgnoreCase("-")) {
                request.header("X-PARTNER", "");
            }

            try {
                if (multipart.stream().anyMatch(url::startsWith)) {
                    String filepath = "src/test/resources/file/txt.csv";
                    response = request.multiPart(new File(filepath))
                            .multiPart("JSON", requestBody)
                            .contentType(ContentType.MULTIPART)
                            .log().body() // Log the request body for multipart
                            .post(url);
                } else {
                    response = request.contentType(ContentType.JSON)
                            .body(requestBody)
                            .log().body() // Log the request body for JSON
                            .post(url);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                String rc = response.then().extract().path("rc");
                String rcdesc = response.then().extract().path("rcdesc");
                TestCase.add(TC);
                Rc.add(rc);
                Desc.add(rcdesc);
                if (ExpectedRc.equals(rc)) {
                    Result.add("Passed");
                    if (url.equalsIgnoreCase("/inquiry") && (rc.equals("00") || rc.equals("0"))) {
                        totalamount = response.then().extract().path("totalamount");
                        int intValue = (int) Double.parseDouble(totalamount);
                        totalamount = Integer.toString(intValue);
                        //to print data from database
//						String table = "tb_r_inquiry";
//						DBConfigDev.DatabaseEnvironmentDEV environment = DBConfigDev.getEnvironmentByName(environmentName);
//						DBConfigDev.DatabaseConfigDev config = new DBConfigDev.DatabaseConfigDev(environment);
//						DatabaseUtility.printTableByTrackingRef(trackingref, config, table);
                    }
                } else {
                    Result.add("Failed");
                }
                scenario.log("Response: " + response.asPrettyString());
            } catch (Exception e) {
                // Handle the exception, e.g., log the error
                e.printStackTrace();
                handleFailure();
            }
        }
        printSummary();
    }

    @Then("I perform post for inq,pay,adv and then verify the rc")
    public void i_perform_post_then_verify_the_rc() throws IOException, NoSuchAlgorithmException, InvalidKeyException {

        String TC = null;
        String trackingref = null;

        for (Map<String, String> rowData : excelData) {
            String currentTC = rowData.get("TestCase");

            if (TC == null || !TC.equals(currentTC)) {
                // If TC is null (first iteration) or TC is different from currentTC, generate a new trackingref
                trackingref = r.getRandomRef();
            }

            TC = currentTC; // Update TC for the next iteration

            if (TC == null || TC.isEmpty()) {
                // If TC is empty, end the processing
                break; // This will exit the method or function
            }

            scenario.log("TestCase: " + TC);

            String endpoint = rowData.get("endpoint");
            String url = rowData.get("url");
            baseURI = EndpointList.getEndpoint(endpoint);
            String partnerId = rowData.get("partnerid");

            try {
                secretKey = rowData.get("secretKey");
            } catch (Exception e) {
                // Handle exceptions gracefully
                e.printStackTrace();
                secretKey = getSecretKey(rowData, partnerId);
            }

            scenario.log("Secret Key: " + secretKey);

//			String ExpectedRc = rowData.get("rc");
            String sigMethod = rowData.get("sig");
            String isInq = rowData.get("isInq");
            String isPay = rowData.get("isPay");
            String isAdv = rowData.get("isAdv");
            String isRev = rowData.get("isRev");

            JSONArray jsonArray = buildRequestBody(rowData, trackingref);

            StringBuilder concatenatedString = new StringBuilder();
            JSONObject jsonObject = new JSONObject();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONArray keyValueArray = jsonArray.getJSONArray(i);
                String key = keyValueArray.getString(0);
                String value = keyValueArray.getString(1);
                jsonObject.put(key, value);
                concatenatedString.append(value);
            }

            if (isInq.equalsIgnoreCase("TRUE")) {
                TestCase.add(TC + " Inquiry");

                scenario.log("Sign method: " + sigMethod);
                scenario.log("String to sign: " + concatenatedString);

                String requestBody;
                if (sigMethod.equalsIgnoreCase("MD5")) {
                    signature = MD5.generateMD5Signature(partnerId + secretKey);
                    jsonObject.put("signature", signature);
                    requestBody = jsonObject.toString();
                } else {
                    requestBody = generateSignature(sigMethod, jsonObject, url, concatenatedString);
                }

                try {
                    Response response = executeJSONPostRequest(url, requestBody);
                    handleResponseJSON(response, rowData);
                } catch (Exception e) {
                    // Handle exceptions gracefully
                    e.printStackTrace();
                    handleFailure();
                }

            } else if (isPay.equalsIgnoreCase("TRUE")) {
                TestCase.add(TC + " Payment");

                scenario.log("Sign method: " + sigMethod);
                scenario.log("String to sign: " + concatenatedString);

                String requestBody;
                if (sigMethod.equalsIgnoreCase("MD5")) {
                    signature = MD5.generateMD5Signature(concatenatedString + secretKey);
                    jsonObject.put("signature", signature);
                    requestBody = jsonObject.toString();
                } else {
                    requestBody = generateSignature(sigMethod, jsonObject, url, concatenatedString);
                }

                try {
                    Response response = executeJSONPostRequest(url, requestBody);
                    handleResponseJSON(response, rowData);
                } catch (Exception e) {
                    // Handle exceptions gracefully
                    e.printStackTrace();
                    handleFailure();
                }

//				String requestBody = generateSignature(sigMethod, jsonObject, url, concatenatedString);
//				Response response = executeJSONPostRequest(url, requestBody);
//
//				try {
//					String rc = response.then().extract().path("rc");
//					String rcdesc = response.then().extract().path("rcdesc");
//					Rc.add(rc);
//					Desc.add(rcdesc);
//					if(rc.equalsIgnoreCase(ExpectedRc)) {
//						Result.add("Passed");
//                        if ((rc.equals("00") || rc.equals("0"))) {
//                            DBConfigDev.DatabaseEnvironmentDEV environment = DBConfigDev.getEnvironmentByName(environmentName);
//                            DBConfigDev.DatabaseConfigDev config = new DBConfigDev.DatabaseConfigDev(environment);
//                            DatabaseUtility.deleteLogByTrackingRef(trackingref, config);
//                        }
//					}
//					else {
//						Result.add("Failed");
//					}
//					scenario.log("Response: " + response.asPrettyString());
//				} catch (Exception e) {
//					// Handle the exception, e.g., log the error
//					e.printStackTrace();
//					handleFailure();
//				}

            } else if (isAdv.equalsIgnoreCase("TRUE")) {
                TestCase.add(TC + " Advice");

                scenario.log("Sign method: " + sigMethod);
                scenario.log("String to sign: " + concatenatedString);

                String requestBody;
                if (sigMethod.equalsIgnoreCase("MD5")) {
                    signature = MD5.generateMD5Signature(partnerId + secretKey);
                    jsonObject.put("signature", signature);
                    requestBody = jsonObject.toString();
                } else {
                    requestBody = generateSignature(sigMethod, jsonObject, url, concatenatedString);
                }

                try {
                    Response response = executeJSONPostRequest(url, requestBody);
                    handleResponseJSON(response, rowData);
                } catch (Exception e) {
                    // Handle exceptions gracefully
                    e.printStackTrace();
                    handleFailure();
                }

            } else if (isRev.equalsIgnoreCase("TRUE")) {
                TestCase.add(TC + " Reversal");

                scenario.log("Sign method: " + sigMethod);
                scenario.log("String to sign: " + concatenatedString);

                String requestBody;
                if (sigMethod.equalsIgnoreCase("MD5")) {
                    signature = MD5.generateMD5Signature(partnerId + secretKey);
                    jsonObject.put("signature", signature);
                    requestBody = jsonObject.toString();
                } else {
                    requestBody = generateSignature(sigMethod, jsonObject, url, concatenatedString);
                }

                try {
                    Response response = executeJSONPostRequest(url, requestBody);
                    handleResponseJSON(response, rowData);
                } catch (Exception e) {
                    // Handle exceptions gracefully
                    e.printStackTrace();
                    handleFailure();
                }

            } else {
                break;
            }

        }
        printSummary();
        generatereport();
    }

    @Then("I perform post for inq,pay,adv and then verify the rc for Transferlink")
    public void i_perform_post_then_verify_the_rc_for_transferlink() throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException {

        String TC = null;
        String trackingref = null;

        for (Map<String, String> rowData : excelData) {
            String currentTC = rowData.get("TestCase");

            if (TC == null || !TC.equals(currentTC)) {
                // If TC is null (first iteration) or TC is different from currentTC, generate a new trackingref
                trackingref = r.getRandomRef();
            }

            TC = currentTC; // Update TC for the next iteration

            if (TC == null || TC.isEmpty()) {
                // If TC is empty, end the processing
                break; // This will exit the method or function
            }

            scenario.log("TestCase: " + TC);

            String endpoint = rowData.get("endpoint");
            String url = rowData.get("url");
            baseURI = EndpointList.getEndpoint(endpoint);
            String partnerId = rowData.get("partnerid");

            secretKey = rowData.get("secretKey");

            scenario.log("Secret Key: " + secretKey);

//			String ExpectedRc = rowData.get("rc");
            String sigMethod = rowData.get("sig");
            String isInq = rowData.get("isInq");
            String isPay = rowData.get("isTrans");
            String isAdv = rowData.get("isCommit");
            String isRev = rowData.get("isRev");

            Boolean is0667 = rowData.get("reffNumberTl").startsWith("0667");
            scenario.log("is 0667: " + is0667);

            JSONArray jsonArray = buildRequestBodyTransferlink(rowData, trackingref);

            StringBuilder concatenatedString = new StringBuilder();
            JSONObject jsonObject = new JSONObject();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONArray keyValueArray = jsonArray.getJSONArray(i);
                String key = keyValueArray.getString(0);
                String value = keyValueArray.getString(1);
                jsonObject.put(key, value);

                if (!is0667) {
                    concatenatedString.append(value);
                } else {
                    if (!key.equals("BankCode") && !key.equals("BankName") && !key.equals("AccountNo") && !key.equals("reffNumberTl")) {
                        concatenatedString.append(value);
                    }
                }
            }

            if (isInq.equalsIgnoreCase("TRUE")) {
                TestCase.add(TC + " Inquiry");

                scenario.log("Sign method: " + sigMethod);
                scenario.log("String to sign: " + concatenatedString + secretKey);

                String requestBody;
                if (sigMethod.equalsIgnoreCase("MD5")) {
                    signature = MD5.generateMD5Signature(concatenatedString + secretKey);
                    jsonObject.put("signature", signature);
                    requestBody = jsonObject.toString();
                } else {
                    requestBody = generateSignature(sigMethod, jsonObject, url, concatenatedString);
                }

                try {
                    Response response = executeJSONPostRequest(url, requestBody);
                    handleResponseTransferlink(response, rowData);
                } catch (Exception e) {
                    // Handle exceptions gracefully
                    e.printStackTrace();
                    handleFailure();
                }

            } else if (isPay.equalsIgnoreCase("TRUE")) {
                TestCase.add(TC + " Trans");

                scenario.log("Sign method: " + sigMethod);
                scenario.log("String to sign: " + concatenatedString);

                String requestBody;
                if (sigMethod.equalsIgnoreCase("MD5")) {
                    signature = MD5.generateMD5Signature(concatenatedString + secretKey);
                    jsonObject.put("signature", signature);
                    requestBody = jsonObject.toString();
                } else {
                    requestBody = generateSignature(sigMethod, jsonObject, url, concatenatedString);
                }

                try {
                    Response response = executeJSONPostRequest(url, requestBody);
                    handleResponseTransferlink(response, rowData);
                } catch (Exception e) {
                    // Handle exceptions gracefully
                    e.printStackTrace();
                    handleFailure();
                }

//				String requestBody = generateSignature(sigMethod, jsonObject, url, concatenatedString);
//				Response response = executeJSONPostRequest(url, requestBody);
//
//				try {
//					String rc = response.then().extract().path("rc");
//					String rcdesc = response.then().extract().path("rcdesc");
//					Rc.add(rc);
//					Desc.add(rcdesc);
//					if(rc.equalsIgnoreCase(ExpectedRc)) {
//						Result.add("Passed");
//                        if ((rc.equals("00") || rc.equals("0"))) {
//                            DBConfigDev.DatabaseEnvironmentDEV environment = DBConfigDev.getEnvironmentByName(environmentName);
//                            DBConfigDev.DatabaseConfigDev config = new DBConfigDev.DatabaseConfigDev(environment);
//                            DatabaseUtility.deleteLogByTrackingRef(trackingref, config);
//                        }
//					}
//					else {
//						Result.add("Failed");
//					}
//					scenario.log("Response: " + response.asPrettyString());
//				} catch (Exception e) {
//					// Handle the exception, e.g., log the error
//					e.printStackTrace();
//					handleFailure();
//				}

            } else if (isAdv.equalsIgnoreCase("TRUE")) {
                TestCase.add(TC + " Commit");

                scenario.log("Sign method: " + sigMethod);
                scenario.log("String to sign: " + concatenatedString);

                String requestBody;
                if (sigMethod.equalsIgnoreCase("MD5")) {
                    signature = MD5.generateMD5Signature(concatenatedString + secretKey);
                    jsonObject.put("signature", signature);
                    requestBody = jsonObject.toString();
                } else {
                    requestBody = generateSignature(sigMethod, jsonObject, url, concatenatedString);
                }

                try {
                    Response response = executeJSONPostRequest(url, requestBody);
                    handleResponseTransferlink(response, rowData);
                } catch (Exception e) {
                    // Handle exceptions gracefully
                    e.printStackTrace();
                    handleFailure();
                }

            } else if (isRev.equalsIgnoreCase("TRUE")) {
                TestCase.add(TC + " Reversal");

                scenario.log("Sign method: " + sigMethod);
                scenario.log("String to sign: " + concatenatedString);

                String requestBody;
                if (sigMethod.equalsIgnoreCase("MD5")) {
                    signature = MD5.generateMD5Signature(partnerId + secretKey);
                    jsonObject.put("signature", signature);
                    requestBody = jsonObject.toString();
                } else {
                    requestBody = generateSignature(sigMethod, jsonObject, url, concatenatedString);
                }

                try {
                    Response response = executeJSONPostRequest(url, requestBody);
                    handleResponseTransferlink(response, rowData);
                } catch (Exception e) {
                    // Handle exceptions gracefully
                    e.printStackTrace();
                    handleFailure();
                }

            } else {
                break;
            }

        }
        printSummaryTransferlink();
    }

    @Then("I perform POST request {string} and get Response for XML")
    public void i_perform_post_request_and_get_response_xml(String url) {
        // Loop through excelData and make API requests
        String TC = null;
        String trackingref = null;

        for (Map<String, String> rowData : excelData) {
            String currentTC = rowData.get("TestCase");

            if (TC == null || !TC.equals(currentTC)) {
                // If TC is null (first iteration) or TC is different from currentTC, generate a new trackingref
                trackingref = r.getRandomRef();
            }

            TC = currentTC; // Update TC for the next iteration

            if (TC == null || TC.isEmpty()) {
                // If TC is empty, end the processing
                break; // This will exit the method or function
            }

            scenario.log("Test Case: " + TC);
            TestCase.add(TC);

            String endpoint = rowData.get("url");
            baseURI = EndpointList.getEndpoint(endpoint);
            String partnerId = rowData.get("partnerid");

            // get secret key from db
//			secretKey = getSecretKey(rowData, partnerId);

            secretKey = "1Ds!201020";
            String sigMethod = rowData.get("sig");

            JSONArray jsonArray = buildRequestBody(rowData, trackingref);

            StringBuilder concatenatedstring = new StringBuilder();
            JSONObject jsonObject = new JSONObject();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONArray keyValueArray = jsonArray.getJSONArray(i);
                String key = keyValueArray.getString(0);
                String value = keyValueArray.getString(1);
                jsonObject.put(key, value);
                concatenatedstring.append(value);
            }

            scenario.log("Sign method: " + sigMethod);
            scenario.log("String to sign: " + concatenatedstring);
            if (sigMethod.equalsIgnoreCase("MD5")) {
                String signature = MD5.generateMD5Signature(partnerId + secretKey);
                jsonObject.put("signature", signature);
                scenario.log("Formatted XML String:");
                scenario.log(convertJsonToXml(jsonObject));
            }

            try {
                Response response = executeXMLPostRequest(url, convertJsonToXml(jsonObject));

                handleResponseXML(response, rowData);
            } catch (Exception e) {
                // Handle exceptions gracefully
                e.printStackTrace();
                handleFailure();
            }
        }
        printSummary();
    }

    @Then("I perform POST for inq pay rev XML")
    public void i_perform_post_for_inq_pay_rev_XML() throws IOException {
        // Loop through excelData and make API requests
        String TC = null;
        String trackingref = null;

        for (Map<String, String> rowData : excelData) {
            String currentTC = rowData.get("TestCase");

            if (TC == null || !TC.equals(currentTC)) {
                // If TC is null (first iteration) or TC is different from currentTC, generate a new trackingref
                trackingref = r.getRandomRef();
            }

            TC = currentTC; // Update TC for the next iteration

            if (TC == null || TC.isEmpty()) {
                // If TC is empty, end the processing
                break; // This will exit the method or function
            }
            scenario.log("Test Case: " + TC);

            String endpoint = rowData.get("endpoint");
            String url = rowData.get("url");
            baseURI = EndpointList.getEndpoint(endpoint);
            String partnerId = rowData.get("partnerid");

            String sigMethod = rowData.get("sig");
            String isInq = rowData.get("isInq");
            String isPay = rowData.get("isPay");
            String isRev = rowData.get("isRev");
            String isAdv = rowData.get("isAdv");

            try {
                secretKey = rowData.get("secretKey");
            } catch (Exception e) {
                secretKey = getSecretKey(rowData, partnerId);
            }

            JSONArray jsonArray = buildRequestBody(rowData, trackingref);

            StringBuilder concatenatedstring = new StringBuilder();
            JSONObject jsonObject = new JSONObject();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONArray keyValueArray = jsonArray.getJSONArray(i);
                String key = keyValueArray.getString(0);
                String value = keyValueArray.getString(1);
                jsonObject.put(key, value);
                concatenatedstring.append(value);
            }
            scenario.log("partnerid: " + partnerId);

            if (isInq.equalsIgnoreCase("TRUE")) {
                TestCase.add(TC + " Inquiry");

                scenario.log("Sign method: " + sigMethod);
                if (sigMethod.equalsIgnoreCase("MD5")) {
                    String signature = MD5.generateMD5Signature(partnerId + secretKey);
                    jsonObject.put("signature", signature);
                    scenario.log("Formatted XML String:");
                    scenario.log(convertJsonToXml(jsonObject));
                } else if (sigMethod.equalsIgnoreCase("MD5New")) {
                    String signature = MD5.generateMD5Signature(concatenatedstring + secretKey);
                    scenario.log("Signature String: " + concatenatedstring + secretKey);
                    jsonObject.put("signature", signature);
                    scenario.log("Formatted XML String:");
                    scenario.log(convertJsonToXml(jsonObject));
                }

                try {
                    Response response = executeXMLPostRequest(url, convertJsonToXml(jsonObject));
                    handleResponseXML(response, rowData);
                } catch (Exception e) {
                    // Handle exceptions gracefully
                    e.printStackTrace();
                    handleFailure();
                }

            } else if (isPay.equalsIgnoreCase("TRUE")) {
                TestCase.add(TC + " Payment");

                scenario.log("Sign method: " + sigMethod);
                if (sigMethod.equalsIgnoreCase("MD5")) {
                    String signature = MD5.generateMD5Signature(partnerId + secretKey);
                    jsonObject.put("signature", signature);
                    scenario.log("Formatted XML String:");
                    scenario.log(convertJsonToXml(jsonObject));
                } else if (sigMethod.equalsIgnoreCase("MD5New")) {
                    String signature = MD5.generateMD5Signature(concatenatedstring + secretKey);
                    scenario.log("Signature String: " + concatenatedstring + secretKey);
                    jsonObject.put("signature", signature);
                    scenario.log("Formatted XML String:");
                    scenario.log(convertJsonToXml(jsonObject));
                }

                try {
                    Response response = executeXMLPostRequest(url, convertJsonToXml(jsonObject));
                    handleResponseXML(response, rowData);
                } catch (Exception e) {
                    // Handle exceptions gracefully
                    e.printStackTrace();
                    handleFailure();
                }

            } else if (isRev.equalsIgnoreCase("TRUE")) {
                TestCase.add(TC + " Reversal");

                scenario.log("Sign method: " + sigMethod);
                if (sigMethod.equalsIgnoreCase("MD5")) {
                    String signature = MD5.generateMD5Signature(partnerId + secretKey);
                    jsonObject.put("signature", signature);
                    scenario.log("Formatted XML String:");
                    scenario.log(convertJsonToXml(jsonObject));
                } else if (sigMethod.equalsIgnoreCase("MD5New")) {
                    String signature = MD5.generateMD5Signature(concatenatedstring + secretKey);
                    scenario.log("Signature String: " + concatenatedstring + secretKey);
                    jsonObject.put("signature", signature);
                    scenario.log("Formatted XML String:");
                    scenario.log(convertJsonToXml(jsonObject));
                }

                try {
                    Response response = executeXMLPostRequest(url, convertJsonToXml(jsonObject));
                    handleResponseXML(response, rowData);
                } catch (Exception e) {
                    // Handle exceptions gracefully
                    e.printStackTrace();
                    handleFailure();
                }

            } else if (isAdv.equalsIgnoreCase("TRUE")) {
                TestCase.add(TC + " Advice");

                scenario.log("Sign method: " + sigMethod);
                if (sigMethod.equalsIgnoreCase("MD5")) {
                    String signature = MD5.generateMD5Signature(partnerId + secretKey);
                    jsonObject.put("signature", signature);
                    scenario.log("Formatted XML String:");
                    scenario.log(convertJsonToXml(jsonObject));
                } else if (sigMethod.equalsIgnoreCase("MD5New")) {
                    String signature = MD5.generateMD5Signature(concatenatedstring + secretKey);
                    scenario.log("Signature String: " + concatenatedstring + secretKey);
                    jsonObject.put("signature", signature);
                    scenario.log("Formatted XML String:");
                    scenario.log(convertJsonToXml(jsonObject));
                }

                try {
                    Response response = executeXMLPostRequest(url, convertJsonToXml(jsonObject));
                    handleResponseXML(response, rowData);
                } catch (Exception e) {
                    e.printStackTrace();
                    handleFailure();
                }

            } else {
                break;
            }
        }
        printSummary();
        generatereport();
    }

    @Then("I perform POST request {string} and get Response for SOAP")
    public void i_perform_post_request_and_get_response_soap(String url) {
        String TC = null;
        String trackingref = null;

        for (Map<String, String> rowData : excelData) {
            String currentTC = rowData.get("TestCase");

            if (TC == null || !TC.equals(currentTC)) {
                // If TC is null (first iteration) or TC is different from currentTC, generate a new trackingref
                trackingref = r.getRandomRef();
            }

            TC = currentTC; // Update TC for the next iteration

            if (TC == null || TC.isEmpty()) {
                // If TC is empty, end the processing
                break; // This will exit the method or function
            }
            scenario.log("Test Case: " + TC);
            TestCase.add(TC + " " + rowData.get("endpoint"));

            try {
                String requestBody = buildSoapRequest(rowData, trackingref);
                Response response = executeSoapPostRequest(url, requestBody);

                handleResponseSOAP(response, rowData);
            } catch (Exception e) {
                // Handle exceptions gracefully
                e.printStackTrace();
                handleFailure();
            }
        }
        printSummary();
    }

    private String getSecretKey(Map<String, String> rowData, String partnerId) {
        String type = rowData.get("type");
        String environmentName = rowData.get("schema");
        String TableName = rowData.get("TableName");

        try {
            secretKey = Helper.getSecretKey(type, environmentName, partnerId, TableName);
            scenario.log("Secret Key: " + secretKey);
        } catch (Exception e) {
            e.printStackTrace();
            secretKey = "";
        }
        return secretKey;
    }

    private JSONArray buildRequestBodyTransferlink(Map<String, String> rowData, String trackingref) {
        Random rand = new Random();
        String trxid = String.valueOf(rand.nextInt(10));

        JSONArray jsonArray = new JSONArray();
        // Iterate through rowData and add [key, value] pairs to jsonArray
        for (Map.Entry<String, String> entry : rowData.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            Set<String> body = new HashSet<>(Arrays.asList("TestCase", "secretKey", "TableName", "endpoint", "url", "rc", "schema", "type", "sig", "xExternal", "xTimestamp", "xSignature", "xAuth", "xPartner", "isInq", "isAdv", "isTrans", "isCommit"));
//			}
            if (!body.contains(key)) {
                // Create a JSONArray for [key, value] pair
                JSONArray innerArray = new JSONArray();
                switch (value) {
                    case "trxid":
                        innerArray.put(key).put(trxidTransferlink);
                        break;
                    default:
                        innerArray.put(key);
                        innerArray.put(value.equalsIgnoreCase("-") ? "" : value);
                        break;
                }

                // Add [key, value] pair to the jsonArray
                jsonArray.put(innerArray);
            }
        }
        return jsonArray;
    }

    private JSONArray buildRequestBody(Map<String, String> rowData, String trackingref) {
        String trxid = r.getRandomTrxId();
        String trxdate = r.getCurrentDate();
        String datetimerequest = r.getDateTimeReq();

        JSONArray jsonArray = new JSONArray();
        // Iterate through rowData and add [key, value] pairs to jsonArray
        for (Map.Entry<String, String> entry : rowData.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            Set<String> body = new HashSet<>(Arrays.asList("TestCase", "secretKey", "TableName", "endpoint", "url", "rc", "schema", "type", "sig", "xExternal", "xTimestamp", "xSignature", "xAuth", "xPartner", "isInq", "isAdv", "isPay", "isRev"));
            if (!body.contains(key)) {
                // Create a JSONArray for [key, value] pair
                JSONArray innerArray = new JSONArray();
                switch (value) {
                    case "trxid":
                        innerArray.put(key).put(trxid);
                        break;
                    case "trackingref":
                        innerArray.put(key).put(trackingref);
                        break;
                    case "totalamount":
                        innerArray.put(key).put(totalamount);
                        break;
                    case "trxdate":
                        innerArray.put(key).put(trxdate);
                        break;
                    case "datetimerequest":
                        innerArray.put(key).put(datetimerequest);
                        break;
                    default:
                        innerArray.put(key);
                        innerArray.put(value.equalsIgnoreCase("-") ? "" : value);
                        break;
                }

                // Add [key, value] pair to the jsonArray
                jsonArray.put(innerArray);
            }
        }
        return jsonArray;
    }

    private String buildSoapRequest(Map<String, String> rowData, String trackingref) {
        String trxid = r.getRandomTrxId();
        String trxdate = r.getCurrentDate();
        String type = rowData.get("type");

        StringBuilder soapRequest = new StringBuilder();
        soapRequest.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ban=\"bankmandiri.h2h.billpayment.ws\">");
        soapRequest.append("<soapenv:Header/>");
        soapRequest.append("<soapenv:Body>");
        soapRequest.append("<ban:").append(type).append(">");
        soapRequest.append("<ban:request>");

//		soapRequest.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:idm=\"http://idm.ids.com/\">");
//		soapRequest.append("<soapenv:Header/>");
//		soapRequest.append("<soapenv:Body>");
//		soapRequest.append("<idm:").append(type).append(">");
//		soapRequest.append("<input>");

        for (Map.Entry<String, String> entry : rowData.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            Set<String> body = new HashSet<>(Arrays.asList("TestCase", "url", "type", "rc"));
            if (!body.contains(key)) {
                switch (value) {
                    case "trxid":
                        soapRequest.append("<").append(key).append(">").append(trxid).append("</").append(key).append(">");
                        break;
                    case "trackingref":
                        soapRequest.append("<").append(key).append(">").append(trackingref).append("</").append(key).append(">");
                        break;
                    case "totalamount":
                        soapRequest.append("<").append(key).append(">").append(totalamount).append("</").append(key).append(">");
                        break;
                    case "trxdate":
                        soapRequest.append("<").append(key).append(">").append(trxdate).append("</").append(key).append(">");
                        break;
                    default:
                        soapRequest.append("<").append(key).append(">").append(value).append("</").append(key).append(">");
                        break;
                }
            }
        }
        soapRequest.append("</ban:request>");
        soapRequest.append("</ban:").append(type).append(">");
        soapRequest.append("</soapenv:Body>");
        soapRequest.append("</soapenv:Envelope>");
//		soapRequest.append("</input>");
//		soapRequest.append("</idm:").append(type).append(">");
//		soapRequest.append("</soapenv:Body>");
//		soapRequest.append("</soapenv:Envelope>");

        return soapRequest.toString();
    }

    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private Response executeJSONPostRequest(String url, String requestBody) {
        // Execute SOAP request and return Response
        Response response = given()
                .with()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .log().method()
                .log().uri()
                .log().headers()
                .log().body()
                .post(url);

        Object jsonObject = gson.fromJson(requestBody, Object.class);
        String formatter = gson.toJson(jsonObject);
        RequestBodyList.add(formatter);
        RequestMethodList.add("POST");
        RequestUriList.add(url);

        return response;
    }

    private Response executeXMLPostRequest(String url, String requestBody) {
        Response response = given()
                .with()
                .contentType(ContentType.XML)
                .body(requestBody)
                .log().method() // Log the request method
                .log().uri() // Log the request URI
                .log().headers() // Log the request headers
                .log().body() // Log the request body
                .post(url);

        // Adding to List
        RequestBodyList.add(requestBody);
        RequestMethodList.add("POST");
        RequestUriList.add(url);

        return response;
    }

    private Response executeSoapPostRequest(String url, String requestBody) {
        // Execute SOAP request and return Response
        return given()
                .with()
                .header("Content-Type", "text/xml")
                .body(requestBody)
                .log().method()
                .log().uri()
                .log().headers()
                .log().body()
                .post(url);
    }

    private void handleResponseJSON(Response response, Map<String, String> rowData) {

        try {
            String ExpectedRc = rowData.get("rc");
            String responseBody = response.then().extract().asString();
            String rc = JsonPath.from(responseBody).get("rc");
            String rcdesc = JsonPath.from(responseBody).get("rcdesc");

            scenario.log("RC: " + rc);
            scenario.log("RCDESC: " + rcdesc);

            ResponseBodyList.add(response.asPrettyString());
            ExpectedRcList.add(ExpectedRc);
            Rc.add(rc);
            Desc.add(rcdesc);
            // test
            // Adding to List
            ResTimeList.add(String.valueOf(response.getTime()));
            ResStatusCodeList.add(String.valueOf(response.statusCode()));
            ContentTypeList.add(response.getContentType());

            if (ExpectedRc.equalsIgnoreCase(rc)) {
                Result.add("Passed");
                if ((rc.equals("00") || rc.equals("0"))) {
                    totalamount = response.then().extract().path("totalamount");
//					int intValue = (int) Double.parseDouble(totalamount);
//					totalamount = Integer.toString(intValue);
                }
            } else {
                Result.add("Failed");
            }

            scenario.log("Response Body: ");
            scenario.log(response.asPrettyString());
        } catch (Exception e) {
            e.printStackTrace();
            scenario.log("Response Body: ");
            scenario.log(response.asPrettyString());
        }

    }

    private void handleResponseTransferlink(Response response, Map<String, String> rowData) {

        try {
            String ExpectedRc = rowData.get("rc");
            String responseBody = response.then().extract().asString().toLowerCase();
            String rc = JsonPath.from(responseBody).get("status");
            String rcdesc = JsonPath.from(responseBody).get("msg");

            scenario.log("status: " + rc);
            scenario.log("msg: " + rcdesc);

            Rc.add(rc);
            Desc.add(rcdesc);

            if (ExpectedRc.equalsIgnoreCase(rc)) {
                Result.add("Passed");
                if ((rc.equals("00") || rc.equals("0"))) {
                    trxidTransferlink = response.then().extract().path("trxid");
//					int intValue = (int) Double.parseDouble(totalamount);
//					totalamount = Integer.toString(intValue);
                }
            } else {
                Result.add("Failed");
            }

            scenario.log("Response Body: ");
            scenario.log(response.asPrettyString());
        } catch (Exception e) {
            e.printStackTrace();
            scenario.log("Response Body: ");
            scenario.log(response.asPrettyString());
        }

    }

    private void handleResponseXML(Response response, Map<String, String> rowData) {

        try {
            String ExpectedRc = rowData.get("rc");
            String responseBody = response.getBody().asString();
            String rc = extractXmlValueFromResponse(responseBody, "rc");
            String rcdesc = extractXmlValueFromResponse(responseBody, "rcdesc");

            scenario.log("RC response: " + rc);
            scenario.log("RCDESC response: " + rcdesc);
            scenario.log("Expected RC: " + ExpectedRc);

            // Adding new list Response body and expected rc list
            ResponseBodyList.add(response.asPrettyString());
            ExpectedRcList.add(ExpectedRc);
            Rc.add(rc);
            Desc.add(rcdesc);
            // test
            // Adding to List
            ResTimeList.add(String.valueOf(response.getTime()));
            ResStatusCodeList.add(String.valueOf(response.statusCode()));
            ContentTypeList.add(response.getContentType());

            if (ExpectedRc.equals(rc)) {
                Result.add("Passed");
//				if ((rc.equals("00") || rc.equals("0"))) {
//					totalamount = extractXmlValueFromResponse(responseBody, "totalamount");
//                    assert totalamount != null;
//                    int intValue = (int) Double.parseDouble(totalamount);
//					totalamount = Integer.toString(intValue);
//				}
            } else {
                Result.add("Failed");
            }

            scenario.log("Response Body: ");
            scenario.log(response.asPrettyString());
        } catch (Exception e) {
            e.printStackTrace();
            scenario.log("Response Body: ");
            scenario.log(response.asPrettyString());
        }

    }

    private void handleResponseSOAP(Response response, Map<String, String> rowData) {

        try {
            String ExpectedRc = rowData.get("rc");
            String responseBody = response.getBody().asString();
            String rc = extractXmlValueFromResponse(responseBody, "responseCode");
            String rcdesc = extractXmlValueFromResponse(responseBody, "responseDesc");

            scenario.log("RC: " + rc);
            scenario.log("RCDESC: " + rcdesc);

            Rc.add(rc);
            Desc.add(rcdesc);

            if (ExpectedRc.equals(rc)) {
                Result.add("Passed");
            } else {
                Result.add("Failed");
            }

            scenario.log("Response Body: ");
            scenario.log(response.asPrettyString());
        } catch (Exception e) {
            e.printStackTrace();
            scenario.log("Response Body: ");
            scenario.log(response.asPrettyString());
        }

    }

    private void handleFailure() {
        // Handle failure in processing
        scenario.log("Response Body: ");
        scenario.log(response.asString());
        scenario.log("Catch Executed");
        Rc.add("-");
        Desc.add("-");
        Result.add("Failed");
    }

    private void printSummary() {
        scenario.log("Summary:");
        scenario.log("Test Case Results:");

        int size = Math.min(Math.min(Math.min(TestCase.size(), Rc.size()), Desc.size()), Result.size());
        int countPass = 0;
        int countFail = 0;

        for (int i = 0; i < size; i++) {
            String element = TestCase.get(i);
            String result = Result.get(i);
            String rc = Rc.get(i);
            String desc = Desc.get(i);

            scenario.log(element + ", " + result + ": " + rc + ", " + desc);

            if (result.equalsIgnoreCase("Passed")) {
                countPass++;
            } else {
                countFail++;
            }
        }

        scenario.log("Total Passed = " + countPass);
        scenario.log("Total Failed = " + countFail);
    }

    private void printSummaryTransferlink() {
        scenario.log("Summary:");
        scenario.log("Test Case Results:");

        int size = Math.min(Math.min(Math.min(TestCase.size(), Rc.size()), Desc.size()), Result.size());
        int countPass = 0;
        int countFail = 0;

        for (int i = 0; i < size; i++) {
            String element = TestCase.get(i);
            String result = Result.get(i);
            String rc = Rc.get(i);
            String desc = Desc.get(i);

            scenario.log(element + ", " + result + ": " + rc + ", " + desc);

            if (result.equalsIgnoreCase("Passed")) {
                countPass++;
            } else {
                countFail++;
            }
        }

        scenario.log("Total Passed = " + countPass);
        scenario.log("Total Failed = " + countFail);
    }


    public static String removeHttps(String url) {
        Pattern pattern = Pattern.compile("https://[^/]+");
        Matcher matcher = pattern.matcher(url);

        if (matcher.find()) {
            return url.substring(matcher.end());
        }

        return url;
    }

    public static String convertJsonToXml(JSONObject jsonObject) {
        // Wrap the JSONObject with a "data" key and convert to XML using org.json library's built-in method with indentation
        int PRETTY_PRINT_INDENT_FACTOR = 4;
        return XML.toString(jsonObject, "data", PRETTY_PRINT_INDENT_FACTOR);
    }

    public static String extractXmlValueFromResponse(String responseBody, String elementName) {
        // Use Jsoup to parse the XML response
        Document doc = Jsoup.parse(responseBody, "", Parser.xmlParser());
        Element element = doc.selectFirst(elementName);

        if (element != null) {
            return element.text();
        }

        return null;
    }

    private String generateSignature(String sigMethod, JSONObject jsonObject, String url, StringBuilder concatenatedString) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException {
        String requestBody;

        if (sigMethod.equalsIgnoreCase("SHA1")) {
            requestBody = SHA1(jsonObject, concatenatedString);
        } else if (sigMethod.equalsIgnoreCase("HMAC512")) {
            requestBody = HMAC512(jsonObject, url, timestamp);
        } else if (sigMethod.equalsIgnoreCase("-")) {
            jsonObject.put("signature", signature);
            requestBody = jsonObject.toString();
        } else {
            requestBody = jsonObject.toString();
        }
        return requestBody;
    }

    private String SHA1(JSONObject jsonObject, StringBuilder concatenatedString) throws UnsupportedEncodingException {
        String requestBody;
        signature = SHA1.crypt(concatenatedString.toString().trim() + secretKey);
        jsonObject.put("signature", signature);
        requestBody = jsonObject.toString();

        return requestBody;
    }

    private String HMAC512(JSONObject jsonObject, String url, String timestamp) throws NoSuchAlgorithmException, InvalidKeyException {
        String requestBody;

        requestBody = jsonObject.toString();
        byte[] sha256 = SHA256.getSHA256(requestBody);
        String relativepath = removeHttps(baseURI);
        String data = "POST:" + relativepath + url + ":" + token + ":" + SHA256.toHexString(sha256) + ":" + timestamp;
        Mac hmacSha512 = Mac.getInstance("HmacSHA512");
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), "HmacSHA512");
        hmacSha512.init(secretKeySpec);
        byte[] hash = hmacSha512.doFinal(data.getBytes());
        signature = Base64.getEncoder().encodeToString(hash);
        jsonObject.put("signature", signature);
        requestBody = jsonObject.toString();

        return requestBody;
    }

    private void generatereport() throws IOException {
        ZipSecureFile.setMinInflateRatio(0);
        Properties properties = new Properties();
        // Load properties from file
        try (FileInputStream fis = new FileInputStream(reportconfigfullpath)) {
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        String filename = properties.getProperty("extent.reporter.excel.filename", null);
        String filepath = properties.getProperty("extent.reporter.excel.filepath");
        String fullpath = filepath + filename;
        scenario.log(fullpath);

        // Generate timestamp
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy_MMM_d-HH");
        String timestamp = currentDateTime.format(formatter);

        boolean replace = Boolean.parseBoolean(properties.getProperty("extent.reporter.excel.replace"));

        // Define inputstream
        FileInputStream fileInputStream;
        Workbook workbook = null;
        try {
            // Check if file exist
            fileInputStream = new FileInputStream(fullpath);
            workbook = new XSSFWorkbook(fileInputStream);
        } catch (FileNotFoundException ex) {
            // File name with timestamp
            filename = "Report-Automation_" + timestamp + ".xlsx";

            //save filename to config
            properties.setProperty("extent.reporter.excel.filename", filename);
            try (FileOutputStream fos = new FileOutputStream(reportconfigfullpath)) {
                properties.store(fos, null);
                scenario.log("Filename updated to: " + reportconfigfullpath);
            } catch (IOException e) {
                e.printStackTrace();
            }
            // Write the output to a file
            // Create new workbook
            workbook = new XSSFWorkbook();
        } catch (IOException e) {
            // Handle other IO exceptions
            e.printStackTrace();
        } finally {
            if (workbook == null) {
                workbook = new XSSFWorkbook();
            }

            Sheet sheet = workbook.getSheet(ActiveSheet);

            if (sheet != null && replace) {
                int index = workbook.getSheetIndex(ActiveSheet);
                workbook.removeSheetAt(index);
                sheet = workbook.createSheet(ActiveSheet);
                scenario.log("Replaced");
            } else {
                sheet = workbook.createSheet(ActiveSheet);
            }

            // Create header row
            Row headerRow = sheet.createRow(0);
            String[] headers = {"Test Case", "Method", "Uri", "Type", "Request", "Response", "Expected RC", "RC result", "Desc", "Status"};
            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
            headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            Font headerFont = workbook.createFont();
            headerFont.setColor(IndexedColors.WHITE.getIndex());
            headerCellStyle.setFont(headerFont);

            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerCellStyle);
            }

            // Create data rows
            int rowNum = 1;
            int j = 0; // used for row tracker

            for (String rowData : TestCase) {
                Row row = sheet.createRow(rowNum++);
                // scenario.log(headers.length);
                for (int i = 0; i < headers.length; i++) {
                    Cell cell = row.createCell(i);
                    CellStyle cellStyle = workbook.createCellStyle();
                    if (i == 0) cell.setCellValue(TestCase.get(j));
                    if (i == 1) cell.setCellValue(RequestMethodList.get(j));
                    if (i == 2) cell.setCellValue(RequestUriList.get(j));
                    if (i == 3) cell.setCellValue(ContentTypeList.get(j));
                    if (i == 4) cell.setCellValue(RequestBodyList.get(j));
                    if (i == 5) cell.setCellValue(ResponseBodyList.get(j));
                    if (i == 6) cell.setCellValue(ExpectedRcList.get(j));
                    if (i == 7) cell.setCellValue(Rc.get(j));
                    if (i == 8) cell.setCellValue(Desc.get(j));
                    if (i == 9) cell.setCellValue(Result.get(j));

                    // Applying style cell
                    // Applying wrap text
                    if (cell.getStringCellValue().length() > 50) { // Adjust the threshold as needed
                        cellStyle.setWrapText(true);
                        cellStyle.setAlignment(HorizontalAlignment.LEFT);
                        cellStyle.setVerticalAlignment(VerticalAlignment.TOP);
                        cell.setCellStyle(cellStyle);
                    } else {
                        // Set default alignment if not long text
                        cellStyle.setAlignment(HorizontalAlignment.LEFT);
                        cellStyle.setVerticalAlignment(VerticalAlignment.TOP);
                        cell.setCellStyle(cellStyle);
                    }
                }
                j++;
            }

            // Auto-size columns
            for (int i = 0; i < headers.length; i++) {
                sheet.autoSizeColumn(i);
            }

            // Write the updated workbook to a file
            fullpath = filepath + filename;
            scenario.log(fullpath);
            File file = new File(fullpath);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }

            try (FileOutputStream fileOut = new FileOutputStream(file)) {
                workbook.write(fileOut);
            }

            scenario.log("Report generated successfully!");
        }
    }
}