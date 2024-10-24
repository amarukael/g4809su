package steplist.stepdef;

import com.google.gson.Gson;
import io.cucumber.java.en.Then;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utility.Rand;
import model.tokopedia.*;
import java.util.*;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class Mock {
    List<String> TestCase = new ArrayList<>();
    List<String> Rc = new ArrayList<>();
    List<String> Desc = new ArrayList<>();
    List<String> Result = new ArrayList<>();
    List<Map<String, String>> data = Step2.excelData;
    private final Rand r = new Rand();
    static String totalamount = "";

    @Then("I perform get for inq,pay,adv and then verify the rc mock")
    public void i_perform_post_then_verify_the_rc_get() {
        for (Map<String, String> rowData : data) {
            String TC = rowData.get("TestCase");

            if (TC == null || TC.isEmpty()) {
                // If TC is empty, end the processing
                break; // This will exit the method or function
            }
            System.out.println("TestCase: " + TC);

            baseURI = "http://117.54.12.146:3070/";
            String url = rowData.get("url");
            String isInq = rowData.get("isInq");
            String isPay = rowData.get("isPay");
            String isAdv = rowData.get("isAdv");
            String errorCode = rowData.get("error_code");

            if (isInq.equalsIgnoreCase("TRUE")) {
                TestCase.add(TC + " Inquiry");

                try {
                    String requestUri = url + "/" + rowData.get("id");
                    Response response = executeJSONGetRequest(requestUri);

                    String rc = response.then().extract().path("error.code");
                    String desc = response.then().extract().path("error.detail");
                    Rc.add(rc);
                    Desc.add(desc);
                    System.out.println("Response Body:");
                    System.out.println(response.getBody().asString());
                } catch (Exception e) {
                    // Handle exceptions gracefully
                    e.printStackTrace();
                }

            } else if (isPay.equalsIgnoreCase("TRUE")) {
                TestCase.add(TC + " Payment");

                try {
                    String requestUri = url + "/" + rowData.get("id");
                    Response response = executeJSONGetRequest(requestUri);

                    String rc = response.then().extract().path("data.attributes.error_code");
                    String desc = response.then().extract().path("data.attributes.error_detail");
                    Rc.add(rc);
                    Desc.add(desc);
                    System.out.println("Response Body:");
                    System.out.println(response.getBody().asString());
                } catch (Exception e) {
                    // Handle the exception, e.g., log the error
                    e.printStackTrace();
                }

            } else if (isAdv.equalsIgnoreCase("TRUE")) {
                TestCase.add(TC + " Advice");

                try {
                    String requestUri = url + "/" + rowData.get("id");
                    Response response = executeJSONGetRequest(requestUri);

                    String rc = response.then().extract().path("data.attributes.error_code");
                    String desc = response.then().extract().path("data.attributes.error_detail");
                    Rc.add(rc);
                    Desc.add(desc);
                    System.out.println("Response Body:");
                    System.out.println(response.getBody().asString());
                } catch (Exception e) {
                    // Handle exceptions gracefully
                    e.printStackTrace();
                }
            }
        }
        System.out.println("Summary:");
        System.out.println("Test Case Results:");
        int size = Math.min(Math.min(TestCase.size(), Rc.size()), Desc.size());
        for (int i = 0; i < size; i++) {
            String element = TestCase.get(i);
            String rc = Rc.get(i);
            String desc = Desc.get(i);
            System.out.println(element + ": " + rc + ", " + desc);
        }
    }

    @Then("I perform post for inq,pay,adv and then verify the rc mock")
    public void i_perform_post_then_verify_the_rc_post () {

        for (Map<String, String> rowData : data) {
            String TC = rowData.get("TestCase");

            if (TC == null || TC.isEmpty()) {
                // If TC is empty, end the processing
                break; // This will exit the method or function
            }

            System.out.println("TestCase: " + TC);

            baseURI = "http://117.54.12.146:3070/";
            String url = rowData.get("url");
            String isInq = rowData.get("isInq");
            String isPay = rowData.get("isPay");
            String isAdv = rowData.get("isAdv");

            String type = rowData.getOrDefault("type", "").equals("empty") ? "" : rowData.get("type");
            String id = rowData.getOrDefault("id", "").equals("empty") ? "" : rowData.get("id");
            String productCode = rowData.getOrDefault("product_code", "").equals("empty") ? "" : rowData.get("product_code");
            String clientNumber = rowData.getOrDefault("client_number", "").equals("empty") ? "" : rowData.get("client_number");
            String amount = rowData.getOrDefault("amount", "").equals("empty") ? "" : rowData.get("amount");

            TokopediaRequest request = new TokopediaRequest(new Data(type, id, new Attribute(productCode, clientNumber,amount)));
            String jsonString = "";
            Gson gson = new Gson();

            try {
                jsonString = gson.toJson(request);
                System.out.println("RequestBody: " + jsonString); // Output the JSON string
            } catch (Exception e) {
                e.printStackTrace(); // Handle the exception as needed
            }

            if (isInq.equalsIgnoreCase("TRUE")) {
                TestCase.add(TC + " Inquiry");

                try {
                    Response response = executeJSONPostRequest(url, jsonString);
                    handleResponseJSON(response, rowData);
                    String rc = response.then().extract().path("error.code");
                    String desc = response.then().extract().path("error.detail");
                    Rc.add(rc);
                    Desc.add(desc);
                } catch (Exception e) {
                    // Handle exceptions gracefully
                    e.printStackTrace();
                    handleFailure();
                }

            } else if (isPay.equalsIgnoreCase("TRUE")) {
                TestCase.add(TC + " Payment");
                Response response = executeJSONPostRequest(url, jsonString);
                try {
                    System.out.println(response.getBody().asString());

                    String rc = response.then().extract().path("data.attributes.error_code");
                    String desc = response.then().extract().path("data.attributes.error_detail");
                    Rc.add(rc);
                    Desc.add(desc);
                } catch (Exception e) {
                    // Handle the exception, e.g., log the error
                    e.printStackTrace();
                    handleFailure();
                }

            } else if (isAdv.equalsIgnoreCase("TRUE")) {
                TestCase.add(TC + " Advice");

                try {
                    Response response = executeJSONPostRequest(url, jsonString);
                    handleResponseJSON(response, rowData);

                    String rc = response.then().extract().path("data.attributes.error_code");
                    String desc = response.then().extract().path("data.attributes.error_detail");
                    Rc.add(rc);
                    Desc.add(desc);
                } catch (Exception e) {
                    // Handle exceptions gracefully
                    e.printStackTrace();
                    handleFailure();
                }

            } else {
                break;
            }
        }
        System.out.println("Summary:");
        System.out.println("Test Case Results:");
        int size = Math.min(Math.min(TestCase.size(), Rc.size()), Desc.size());
        for (int i = 0; i < size; i++) {
            String element = TestCase.get(i);
            String rc = Rc.get(i);
            String desc = Desc.get(i);
            System.out.println(element + ": " + rc + ", " + desc);
        }
    }

    private Response executeJSONPostRequest (String url, String requestBody){
        // Execute SOAP request and return Response
        return given()
                .with()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .log().method()
                .log().uri()
                .log().headers()
                .log().body()
                .post(baseURI + url);
    }

    private Response executeJSONGetRequest (String requestUri){
        // Execute SOAP request and return Response
        return given()
                .with()
                .log().method()
                .log().uri()
                .log().headers()
                .log().body()
                .get(requestUri);
    }

    private void handleResponseJSON (Response response, Map < String, String > rowData){

        try {
            String ExpectedRc = rowData.get("rc");
            String rc = response.then().extract().path("rc");
            String rcdesc = response.then().extract().path("rcdesc");

            System.out.println("RC: " + rc);
            System.out.println("RCDESC: " + rcdesc);

            Rc.add(rc);
            Desc.add(rcdesc);

            if (ExpectedRc.equals(rc)) {
                Result.add("Passed");
                if ((rc.equals("00") || rc.equals("0"))) {
                    totalamount = response.then().extract().path("totalamount");
                    int intValue = (int) Double.parseDouble(totalamount);
                    totalamount = Integer.toString(intValue);
                }
            } else {
                Result.add("Failed");
            }

            System.out.println("Response Body: ");
            System.out.println(response.asPrettyString());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Response Body: ");
            System.out.println(response.asPrettyString());
        }

    }

    private void handleFailure () {
        // Handle failure in processing
        System.out.println("Response Body: ");
        System.out.println();
        System.out.println("Catch Executed");
        Rc.add("-");
        Desc.add("-");
        Result.add("Failed");
    }

    private void printSummary () {
        System.out.println("Summary:");
        System.out.println("Test Case Results:");
        int size = Math.min(Math.min(Math.min(TestCase.size(), Rc.size()), Desc.size()), Result.size());
        int countPass = 0;
        int countFail = 0;
        for (int i = 0; i < size; i++) {
            String element = TestCase.get(i);
            String rc = Rc.get(i);
            String desc = Desc.get(i);
            String result = Result.get(i);
            System.out.println(element + ", " + result + ": " + rc + ", " + desc);
            if (Result.get(i).equalsIgnoreCase("Passed")) {
                countPass = countPass + 1;
            } else {
                countFail = countFail + 1;
            }
        }
        System.out.println("Total Passed = "+ countPass);
        System.out.println("Total Failed = "+ countFail);
    }
}
