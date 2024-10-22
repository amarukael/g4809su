package helper;

import static utility.ConvertKey.*;

import java.security.PrivateKey;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import model.FormatReport;
import utility.ConvertKey;
import utility.db.ConnectToDbSTG;
import utility.db.DBConfigDev;
import utility.db.DBConfigStg;
import utility.db.DatabaseUtility;
import utility.signature.SHA256RSA;

public class Helper {
    public static String getSecretKey(String type, String environmentName, String partnerId, String TableName) {
        String secretKey = "";
        if (type.equalsIgnoreCase("DEV")) {
            DBConfigDev.DatabaseEnvironmentDEV environment = DBConfigDev.getEnvironmentByName(environmentName);
            DBConfigDev.DatabaseConfigDev config = new DBConfigDev.DatabaseConfigDev(environment);
            secretKey = DatabaseUtility.getSecretKeyByPartnerId(partnerId, config, TableName);
        } else if (type.equalsIgnoreCase("STG")) {
            DBConfigStg.DatabaseEnvironmentSTG environment = DBConfigStg.getEnvironmentByName(environmentName);
            DBConfigStg.DatabaseConfigStg config = new DBConfigStg.DatabaseConfigStg(environment);
            secretKey = ConnectToDbSTG.getSecretKeyByPartnerId(config, partnerId, TableName);
        }

        return secretKey;
    }

    public static String getPrivateKey() {
        String privatekey = "MIIEowIBAAKCAQEAvqI1TQm688vrN5rHNF/mAQiJmd/BrpCdysnDP32VRkqn4JIDRaboj5OjZHuYSxPx4xxuIGEAHYv2knROXVU3BaVcPihcxIP3mNw5GAln12SHozCju9TumSjbT4jVCP7TlAXLaowkfpIAwH85ur5JN0UB0SKC0HcbpPtvfQSEnaMf0AvhHKyJ/M1rpaIvD4oPfsNgQtL95vqUgxv3UtmYwVAruQ4WnNUOTEPMtKTz9xt4cX46Kfs8q/5OVUIPrGpKs8/S+fC4evxMxqUZhZf/1hjHT90DWmgtJ3p6O1yjJrIP+MyHgTyu72St3Hbq1CAXKDbTTW/gPFXsLDaJIcAvXwIDAQABAoIBACw76VBJ7LZ7X8YURRrzEaS1vXAWCpQd/G88p/CIjGW2FwQ1/UphM191a5l6UFlDdII1a5xZxbQVcQ6ErFZd2pmVyDSoGvaChKZUm27nZ9AEBLEqLSP9gavKXjEzV/NxZdjYC15azHEOIdyI6PVbCBQqEATeFCqZKLH8od8JeFg3RZCjrLNhOB1rQs5Gd5fBHmCICwcKyGA6uFEIDH0QEsoiYFguxtNevCFYSAt1rhDfSs62W17iiiQjDdR2s+VIxF4f8QG1HwlmaJXbNOXVMjBrj4U6tKe1hlGXD5j0oeS0Guv5kYJ2vSiFr+65yWXQ7it4oIHqjkE42cmOMmJyhGkCgYEA93sB7I0Zuo/mhi9MDHoIdel9arbceNU1EHrU0RjvEcuAJgpPGSCi/lX2p7MWxXFy7/yPHPIbOyn4Dp+1fVLquF+2TLOR2aAwYtkqVV/VLK8qQY3TK46XMJW6mXttCm7H8bbE11nGNovmGdVCYIcCQfcXt3fj8jAqR334sV+Uqj0CgYEAxTI4rodtT9O8lnu4B3F8T83UYp11AY8rRS1S/fCTg028UWTkRzNGyzniZDCUEJ1wckTeg5nI/i4eY89l0B10s9SokFS2F/cA3/lePHHzpOGC1YocZXeTAIYCdGMBpUEdUVlNvpj0rwufKXFiXHwczl4BXN96UXmXjSOtpAhSBcsCgYAYp5+EcnYquOVNXu7jm1C2doFxHTCPtSXpM2N6RVKj4jMtFv/UUAUM84F0KYpON0QtFmsnhhMibMbypgSNrLIHljlyIL2aAbRwVJQ8FyUEo5rBFyD/iOAVDgWd/sbZMDopIX0wuxfdHUjaL/3B4hFegwBQ3dfOq++6WZlqCkykPQKBgAKmny5xcZ2+Ge3n8dxfwS0NdkpxFW6U7x758gCp6EG+nMZ3Vb5DSTNYLtvr70Gd7DINDPS174bDNfNfiV3X14bHWBDAosYFswRPxN2JJp5Tzb9zINiYV+iM5KGN5yrCilNaoPnr8nYb8OLMvGbD4FtB1kDaXDmWKdxi22W2GuwrAoGBALmLwM9btmJ4rTUJjN5Pnaa2bA7bnFPz6xDKHs6GD8YPQJWrd/MN60mb1daQoKDcBi+swfDipguqRNXysGb5wDx7nKHmfXTvvh2jPQ6G+Gr7ReUXfI9Fv54eczoPFXbuLgB2L2GVbFFvZtmWu3VU7X2wbtfYO+OjkYNUPWS+903i";

        return privatekey;
    }

    public static String removeHttps(String url) {
        Pattern pattern = Pattern.compile("https://[^/]+");
        Matcher matcher = pattern.matcher(url);

        if (matcher.find()) {
            return url.substring(matcher.end());
        }

        return url;
    }

    public String createTrxDate(String format) {
        String result = "";
        Date dNow = new Date();
        Locale inLocale = new Locale("id", "ID");
        SimpleDateFormat ftpay = new SimpleDateFormat(format, inLocale);
        result = ftpay.format(dNow);

        return result;
    }

    public static String createDate(String format) {
        String result = "";
        Date dNow = new Date();
        Locale inLocale = new Locale("id", "ID");
        SimpleDateFormat ftpay = new SimpleDateFormat(format, inLocale);
        result = ftpay.format(dNow);

        return result;
    }

    public void delay(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // Return list of string if there's violation
    public static List<String> validateResponseBody(Object res) {
        List<String> validationMessage = new ArrayList<>();

        // Create a ValidatorFactory and Validator instance using Hibernate Validator
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        // Manually validate the instance
        Set<ConstraintViolation<Object>> violations = validator.validate(res);

        // Check for violations and collect the messages
        if (violations.isEmpty()) {
            // validationMessage.add("No validation errors!");
        } else {
            for (ConstraintViolation<Object> violation : violations) {
                validationMessage.add(violation.getPropertyPath() + ":" + violation.getMessage());
            }
        }

        return validationMessage;
    }

    public String Sign256RSA(String message, String privKey) throws Exception {
        String result = "";
        SHA256RSA sha256RSA = new SHA256RSA();
        String privateKeyContent = privKey;
        privateKeyContent = privateKeyContent.replaceAll("\n", "").replace("-----BEGIN PRIVATE KEY-----", "")
                .replace("-----END PRIVATE KEY-----", "");
        privateKeyContent = privateKeyContent.replace("\n", "");

        PrivateKey privateKey = loadPrivateKey(privateKeyContent);
        result = sha256RSA.sign5(message, privateKey);

        return result;
    }

    public String AsysmetricSign(String message, String privKey) throws Exception {
        String result = "";
        SHA256RSA sha256RSA = new SHA256RSA();
        String privateKeyContent = privKey;
        privateKeyContent = privateKeyContent.replaceAll("\n", "").replace("-----BEGIN RSA PRIVATE KEY-----", "")
                .replace("-----END RSA PRIVATE KEY-----", "");
        privateKeyContent = privateKeyContent.replace("\n", "");

        ConvertKey ck = new ConvertKey();
        PrivateKey key = ck.genPrivateKey(privateKeyContent);
        result = sha256RSA.sign(message, key);

        return result;
    }

    public FormatReport createDataReport(String testCase, String caseDesc, String services, String method, String url,
            String type, String request, String response, String expectedRc, String rcResult, String desc,
            String status, String notes) {
        FormatReport fReport = new FormatReport();
        fReport.setTestCase(testCase);
        fReport.setCaseDesc(caseDesc);
        fReport.setServices(services);
        fReport.setMethod(method);
        fReport.setUrl(url);
        fReport.setType(type);
        fReport.setRequest(request);
        fReport.setResponse(response);
        fReport.setExpectedRc(expectedRc);
        fReport.setRcResult(rcResult);
        fReport.setDesc(desc);
        fReport.setStatus(status);
        fReport.setNotes(notes);

        return fReport;
    }

    public FormatReport createSummDataReport(String progress, String total, String status, String totPercent) {
        FormatReport fReport = new FormatReport();
        fReport.setSumProgress(progress);
        fReport.setSumTotalTask(total);
        fReport.setSumStatus(status);
        fReport.setSumTotPercent(totPercent);

        return fReport;
    }

    public FormatReport createSummDataReportTimeTaken(String progress, String total, String status, String totPercent,
            long timeTaken) {
        FormatReport fReport = new FormatReport();
        fReport.setSumProgress(progress);
        fReport.setSumTotalTask(total);
        fReport.setSumStatus(status);
        fReport.setSumTotPercent(totPercent);
        fReport.setStartTime(timeTaken);

        return fReport;
    }

    public static void hitDelayTime() {
        String delayVariableConfig = "automation.hit.delay.miliseconds";
        PropertiesHelper propertiesHelper = new PropertiesHelper("src/test/resources/extent.properties");
        int miliseconds = propertiesHelper.getIntProperty(delayVariableConfig, 2000);
        try {
            System.out.println("delay for " + delayVariableConfig + " : " + miliseconds + "ms");
            Thread.sleep(miliseconds);
        } catch (Exception e) {
            System.out.println("ERROR DELAY: " + Arrays.toString(e.getStackTrace()));
        }
    }
}
