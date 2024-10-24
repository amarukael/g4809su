package steplist.stepdef;

import io.cucumber.java.Scenario;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import helper.Helper;
import org.json.JSONArray;
import org.json.JSONObject;
import utility.*;
import utility.signature.SHA1;

public class Step1Get {
	private final Rand r = new Rand();
	static String totalamount = "";
	private static String secretKey;
	static String signature = null;
	private final String timestamp = r.getTimeStamp();

	List<Map<String, String>> data = Step2.excelData;
	Scenario scenario = Hooks.scenario;

	@Then("I perform GET request {string} and get Response")
	public void iPerformGetRequestAndGetResponse(String url) throws UnsupportedEncodingException {
		String currentTestCase = null;
		String trackingRef = null;

		for (Map<String, String> rowData : data) {
			String testCase = rowData.get("TestCase");

			if (currentTestCase == null || !currentTestCase.equals(testCase)) {
				trackingRef = r.getRandomRef();
			}

			currentTestCase = testCase;

			if (currentTestCase == null || currentTestCase.isEmpty()) {
				break;
			}

			scenario.log("Test Case: " + currentTestCase);

			String tableName = rowData.get("TableName");
			String partnerId = rowData.get("partnerid");
			String type = rowData.get("type");
			String environmentName = rowData.get("schema");
			scenario.log("PUBLISH BRANCH");

			String secretKey;
			try {
				secretKey = Helper.getSecretKey(type, environmentName, partnerId, tableName);
			} catch (Exception e) {
				// Handle exceptions gracefully
				e.printStackTrace();
				secretKey = rowData.get("secretKey");
			}
			scenario.log("Secret Key: " + secretKey);

			String endpoint = rowData.get("endpoint");
			baseURI = EndpointList.getEndpoint(endpoint);

			JSONArray jsonArray = buildRequestBody(rowData, trackingRef);

			StringBuilder queryParamsBuilder = new StringBuilder();
			StringBuilder stringToSign = new StringBuilder();

			for (int i = 0; i < jsonArray.length(); i++) {
				JSONArray keyValueArray = jsonArray.getJSONArray(i);
				String key = keyValueArray.getString(0);
				String value = keyValueArray.getString(1);
				queryParamsBuilder.append(key).append("=").append(value).append("&");
				stringToSign.append(value);
			}

			scenario.log("String to sign: " + stringToSign);

			String queryParams = queryParamsBuilder.toString();
			if (queryParams.endsWith("&")) {
				queryParams = queryParams.substring(0, queryParams.length() - 1);
			}

			String signature = SHA1.crypt(stringToSign + secretKey);
			queryParams += "&Signature=" + signature;
			String requestUri = baseURI + url + "?" + queryParams;

			Response response = executeGetRequest(requestUri);

			scenario.log("=============================");
			scenario.log("Response: " + response.asPrettyString());
			scenario.log("Status Code: " + response.getStatusCode());
			scenario.log("=============================");
		}
	}

	@Then("I perform GET request for inq pay rev and get Response")
	public void i_perform_get_for_inq_pay_rev() throws UnsupportedEncodingException {
		String currentTestCase = null;
		String trackingRef = null;

		for (Map<String, String> rowData : data) {
			String testCase = rowData.get("TestCase");

			if (currentTestCase == null || !currentTestCase.equals(testCase)) {
				trackingRef = r.getRandomRef();
			}

			currentTestCase = testCase;

			if (currentTestCase == null || currentTestCase.isEmpty()) {
				break;
			}

			scenario.log("Test Case: " + currentTestCase);

			String tableName = rowData.get("TableName");
			String partnerId = rowData.get("partnerid");
			String type = rowData.get("type");
			String environmentName = rowData.get("schema");

			secretKey = rowData.get("secretKey");

//			try {
//				secretKey = Helper.getSecretKey(type, environmentName, partnerId, tableName);
//			} catch (Exception e) {
//				// Handle exceptions gracefully
//				e.printStackTrace();
//				secretKey = rowData.get("secretKey");
//			}
			scenario.log("Secret Key: " + secretKey);

			String endpoint = rowData.get("endpoint");
			baseURI = EndpointList.getEndpoint(endpoint);
			String url = rowData.get("url");

			String isInq = rowData.get("isInq");
			String isPay = rowData.get("isPay");
			String isRev = rowData.get("isRev");

			JSONArray jsonArray = buildRequestBody(rowData, trackingRef);

			StringBuilder queryParamsBuilder = new StringBuilder();
			StringBuilder stringToSign = new StringBuilder();

			for (int i = 0; i < jsonArray.length(); i++) {
				JSONArray keyValueArray = jsonArray.getJSONArray(i);
				String key = keyValueArray.getString(0);
				String value = keyValueArray.getString(1);
				queryParamsBuilder.append(key).append("=").append(value).append("&");
				stringToSign.append(value);
			}

			scenario.log("String to sign: " + stringToSign);

			String queryParams = queryParamsBuilder.toString();
			if (queryParams.endsWith("&")) {
				queryParams = queryParams.substring(0, queryParams.length() - 1);
			}

			if (isInq.equalsIgnoreCase("TRUE")) {
				scenario.log("Inquiry");
				String signature = SHA1.crypt(stringToSign + secretKey);
				queryParams += "&Signature=" + signature;
				String requestUri = baseURI + url + "?" + queryParams;

				Response response = executeGetRequest(requestUri);

				scenario.log("=============================");
				scenario.log("Request: " + requestUri);
				scenario.log("Response: " + response.asPrettyString());
				scenario.log("Status Code: " + response.getStatusCode());
				scenario.log("=============================");

			} else if (isPay.equalsIgnoreCase("TRUE")) {
				scenario.log("Payment");
				String signature = SHA1.crypt(stringToSign + secretKey);
				queryParams += "&Signature=" + signature;
				String requestUri = baseURI + url + "?" + queryParams;

				Response response = executeGetRequest(requestUri);

				scenario.log("=============================");
				scenario.log("Request: " + requestUri);
				scenario.log("Response: " + response.asPrettyString());
				scenario.log("Status Code: " + response.getStatusCode());
				scenario.log("=============================");

			} else if (isRev.equalsIgnoreCase("TRUE")) {
				scenario.log("Reversal");
				String signature = SHA1.crypt(stringToSign + secretKey);
				queryParams += "&Signature=" + signature;
				String requestUri = baseURI + url + "?" + queryParams;

				Response response = executeGetRequest(requestUri);

				scenario.log("=============================");
				scenario.log("Request: " + requestUri);
				scenario.log("Response: " + response.asPrettyString());
				scenario.log("Status Code: " + response.getStatusCode());
				scenario.log("=============================");

			} else {
				break;
			}
		}
	}

	private String SHA1(JSONObject jsonObject, StringBuilder concatenatedString) throws UnsupportedEncodingException {
		String requestBody;
		signature = SHA1.crypt(concatenatedString.toString().trim() + secretKey);
		jsonObject.put("signature", signature);
		requestBody = jsonObject.toString();

		return requestBody;
	}

	private Response executeGetRequest(String requestUri) {
		// Execute SOAP request and return Response
		return given()
				.log().method()
				.log().uri()
				.log().headers()
				.log().body()
				.get(requestUri);
	}


	private JSONArray buildRequestBody(Map<String, String> rowData, String trackingref){
		String trxid = r.getRandomTrxId();
		String trxdate = r.getCurrentDate();
		String endpoint = rowData.get("endpoint");
		String datetimerequest = r.getDateTimeReq();

		JSONArray jsonArray = new JSONArray();
		// Iterate through rowData and add [key, value] pairs to jsonArray
		for (Map.Entry<String, String> entry : rowData.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();

			Set<String> body = new HashSet<>(Arrays.asList("TestCase", "TableName", "endpoint", "url", "rc", "schema", "type", "sig", "xExternal", "xTimestamp", "xSignature", "xAuth", "xPartner", "isInq", "isAdv", "isPay", "isRev", "secretKey"));
			if (endpoint.equalsIgnoreCase("stgrestprojectdana")) {
				body.add("partnerid");
			}
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
}
