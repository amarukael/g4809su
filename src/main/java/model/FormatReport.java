package model;

public class FormatReport {
    String testCase;
    String caseDesc;
    String services;
    String method;
    String url;
    String type;
    String request;
    String response;
    String expectedRc;
    String rcResult;
    String desc;
    String status;
    String testedDate;
    String testedBy;
    String notes;
    String sumProgress;
    String sumTotalTask;
    String sumStatus;
    String sumTotPercent;

    long StartTime = -1;

    public String getTestCase() {
        return testCase;
    }

    public void setTestCase(String testCase) {
        this.testCase = testCase;
    }

    public String getCaseDesc() {
        return caseDesc;
    }

    public void setCaseDesc(String caseDesc) {
        this.caseDesc = caseDesc;
    }

    public String getServices() {
        return services;
    }

    public void setServices(String services) {
        this.services = services;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getExpectedRc() {
        return expectedRc;
    }

    public void setExpectedRc(String expectedRc) {
        this.expectedRc = expectedRc;
    }

    public String getRcResult() {
        return rcResult;
    }

    public void setRcResult(String rcResult) {
        this.rcResult = rcResult;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTestedDate() {
        return testedDate;
    }

    public void setTestedDate(String testedDate) {
        this.testedDate = testedDate;
    }

    public String getTestedBy() {
        return testedBy;
    }

    public void setTestedBy(String testedBy) {
        this.testedBy = testedBy;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getSumProgress() {
        return sumProgress;
    }

    public void setSumProgress(String sumProgress) {
        this.sumProgress = sumProgress;
    }

    public String getSumTotalTask() {
        return sumTotalTask;
    }

    public void setSumTotalTask(String sumTotalTask) {
        this.sumTotalTask = sumTotalTask;
    }

    public String getSumStatus() {
        return sumStatus;
    }

    public void setSumStatus(String sumStatus) {
        this.sumStatus = sumStatus;
    }

    public String getSumTotPercent() {
        return sumTotPercent;
    }

    public void setSumTotPercent(String sumTotPercent) {
        this.sumTotPercent = sumTotPercent;
    }

    public long getStartTime() {
        return StartTime;
    }

    public void setStartTime(long startTime) {
        this.StartTime = startTime;
    }
}
