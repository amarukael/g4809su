package constant;

import helper.CFGhelper;

public class ConstantExternal {
    //DEV
//    public static final String reportconfigfullpath = "src/test/resources/extent.properties";
//    public static final String urlExternalDana = "http://117.54.12.141:8080/external-be/webresources";
//    public static final String danaInq = urlExternalDana + "/inquiry";
//    public static final String danaOrderCreate = urlExternalDana + "/ordercreate";
//    public static final String danaOrderDetail = urlExternalDana + "/orderdetail";
//    public static final String danaPrivateKey = "-----BEGIN PRIVATE KEY-----MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBALK5LA7siHV3IfcqON5tVNIyOECrPwuF6AKHLVfj7P3gWycpPbhd2FFP09P4XJNC4aDkEcsLi4CyPq/pTZquD5kJqEMCJgLlgYz+IXDAlNON0CZbzXQW1hQeoX3onOx5F77Ey2JqRmzVLifb9rcEi16c26SoyucwU2iNX61W1L+LAgMBAAECgYEAiYD6KslE+8sasMUlV8waNFY6/VZDfSQbKsbB1hsgXPteZjTOrahWI8kdGrq8zvfoDBcssWFChVOd8022TUmZ6USGuaYQduA/dxvhHnyMNqJ2bBkPu6YRujz6Ss9BmhPczYGRG4/wYgxqAA8HlSuOT9xL9REtM4ahIAP6RlOwbDECQQDmFeRvqvH6775I/T1rvfsc0qZlxfBe2V19RnjW7OKCmH4OKSs8OTGuDzXtnKKiDkKzEDxtrDuEA6FBWHas4cKtAkEAxtpXmjKKEEfoYYeej/6fvxoU01+j26mRhd17uW8/avZgRWZkDnt0vnM7xJQzqhGowqvFA+EKZKt1WKDQ6GeKFwJAUWPnJfVTmg/awRkL9CQFQ2HkyNWnPPYpUxptvqGXUtk8pie8Cpa00zzAN+iTwz3GLG+O4MDvYLij/2iaunc2FQJAX/EQuzEPM/O9xvCsLEVpxcZLrgBDCsSxlaja/QkTzAO7R0Czndkq5oiqmZn3o2KJzgMaN1jortGa+Hv+z2LFWQJBAMoo7vJBNkyIs5dSAjbYtFSlhFMyWstYXtvcDFhzilzAJJnrdP6uGtoXDWJkTlwJBKOZD1m2HSdNctuNgQuXhvU=-----END PRIVATE KEY-----";
//    public static final int timeOut = 40000;
//    public static final String urlExternalLJA = "http://117.54.12.141:8080/external-be/webresources/idsapi";
//    public static final String dbEnvironmentName = "External";
//    public static final String environmentSvr = "DEV";

    //STG
//    public static final String reportconfigfullpath = "src/test/resources/extent.properties";
//    public static final String urlExternalDana = "https://private-staging.ids.id/external/webresources";
//    public static final String danaInq = urlExternalDana + "/inquiry";
//    public static final String danaOrderCreate = urlExternalDana + "/ordercreate";
//    public static final String danaOrderDetail = urlExternalDana + "/orderdetail";
//    public static final String danaPrivateKey = "-----BEGIN PRIVATE KEY-----MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBALK5LA7siHV3IfcqON5tVNIyOECrPwuF6AKHLVfj7P3gWycpPbhd2FFP09P4XJNC4aDkEcsLi4CyPq/pTZquD5kJqEMCJgLlgYz+IXDAlNON0CZbzXQW1hQeoX3onOx5F77Ey2JqRmzVLifb9rcEi16c26SoyucwU2iNX61W1L+LAgMBAAECgYEAiYD6KslE+8sasMUlV8waNFY6/VZDfSQbKsbB1hsgXPteZjTOrahWI8kdGrq8zvfoDBcssWFChVOd8022TUmZ6USGuaYQduA/dxvhHnyMNqJ2bBkPu6YRujz6Ss9BmhPczYGRG4/wYgxqAA8HlSuOT9xL9REtM4ahIAP6RlOwbDECQQDmFeRvqvH6775I/T1rvfsc0qZlxfBe2V19RnjW7OKCmH4OKSs8OTGuDzXtnKKiDkKzEDxtrDuEA6FBWHas4cKtAkEAxtpXmjKKEEfoYYeej/6fvxoU01+j26mRhd17uW8/avZgRWZkDnt0vnM7xJQzqhGowqvFA+EKZKt1WKDQ6GeKFwJAUWPnJfVTmg/awRkL9CQFQ2HkyNWnPPYpUxptvqGXUtk8pie8Cpa00zzAN+iTwz3GLG+O4MDvYLij/2iaunc2FQJAX/EQuzEPM/O9xvCsLEVpxcZLrgBDCsSxlaja/QkTzAO7R0Czndkq5oiqmZn3o2KJzgMaN1jortGa+Hv+z2LFWQJBAMoo7vJBNkyIs5dSAjbYtFSlhFMyWstYXtvcDFhzilzAJJnrdP6uGtoXDWJkTlwJBKOZD1m2HSdNctuNgQuXhvU=-----END PRIVATE KEY-----";
//    public static final int timeOut = 30000;
//    public static final String urlExternalLJA = "https://private-staging.ids.id/external/webresources/idsapi";
//    public static final String dbEnvironmentName = "External";
//    public static final String environmentSvr = "STG";

    // via properties
    public static final String urlExternalDana = CFGhelper.cons("urlExternalDana");
    public static final String danaInq = urlExternalDana + "/inquiry";
    public static final String danaOrderCreate = urlExternalDana + "/ordercreate";
    public static final String danaOrderDetail = urlExternalDana + "/orderdetail";
    public static final String danaPrivateKey = CFGhelper.cons("danaPrivateKey");
    public static final int danaTimeOut = Integer.parseInt(CFGhelper.cons("danaTimeOut"));
    public static final String urlExternalLJA = CFGhelper.cons("urlExternalLJA");
    public static final String danaDBEnvironmentName = CFGhelper.cons("danaDBEnvironmentName");
    public static final String environmentSvr = CFGhelper.cons("environmentSvr");
}