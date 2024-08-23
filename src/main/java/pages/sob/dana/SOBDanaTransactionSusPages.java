package pages.sob.dana;

import com.google.gson.Gson;
import helper.SOBHelper;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class SOBDanaTransactionSusPages {
    Gson gson = new Gson();
    WebDriver driver;
    WebDriverWait wait;
    SOBHelper sobHelper = new SOBHelper();

    public SOBDanaTransactionSusPages(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void scrollNavFilter(int scroll) {
        WebElement navFilterPage = driver.findElement(By.xpath("//div[@class='MuiStack-root css-vrnrl8']"));
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollTo({left: 0, top: " + scroll + ", behavior: 'smooth'});", navFilterPage);
        sobHelper.delay(800);
    }

    public Integer getRowsDataTable() {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement dataTable = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[contains(@class,'MuiDataGrid-root MuiDataGrid-root--densityStandard')]")));
        Integer result = Integer.parseInt(dataTable.getAttribute("aria-rowcount")) - 1;
        return result;
    }

    public Integer getRowsPerPage() {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement rowsPerPage = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//p[@class='MuiTablePagination-displayedRows css-m2ckgc']")));
        Integer idx = rowsPerPage.getText().indexOf("of ") + 3;
        Integer result = Integer.parseInt(rowsPerPage.getText().substring(idx));
        return result;
    }

    public boolean getDataTable() {
        try {
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            WebElement dataTable = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("(//div[@class='MuiDataGrid-cellContent'])[1]")));
            if (dataTable.isDisplayed()) {
                return true;
            }
        } catch (Exception e) {
            return false;
        }

        return false;
    }

    public boolean getSuccessAlert() {
        try {
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            WebElement successAlert = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("(//div[contains(@class,'MuiPaper-root MuiPaper-elevation')])[3]")));
            if (successAlert.isDisplayed()) {
                return true;
            }
        } catch (NoSuchElementException e) {
            return false;
        }

        return false;
    }

    public void fillFromDate(String[] value) {
        WebElement fromDate = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='DD-MM-YYYY']")));
        fromDate.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        sobHelper.delay(800);
        for (int i = 0; i < value.length; i++) {
            fromDate.sendKeys(value[i]);
        }
        sobHelper.delay(800);
    }

    public void fillToDate(String[] value) {
        WebElement fromDate = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//input[@placeholder='DD-MM-YYYY'])[2]")));
        fromDate.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        sobHelper.delay(800);
        for (int i = 0; i < value.length; i++) {
            fromDate.sendKeys(value[i]);
        }
        sobHelper.delay(800);
    }

    public void fillAcqIdField(String value) {
        WebElement transIdField = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("acquirementId")));
        // Click the input field to focus it
        transIdField.click();
        transIdField.sendKeys(value);
        sobHelper.delay(800);
    }

    public void fillProdNameField(String value) {
        WebElement productIdField = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("productName")));
        // Click the input field to focus it
        productIdField.click();
        productIdField.sendKeys(value);
        sobHelper.delay(800);
    }

    public void fillProdIdField(String value) {
        WebElement productIdField = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("productId")));
        // Click the input field to focus it
        productIdField.click();
        productIdField.sendKeys(value);
        sobHelper.delay(800);
    }

    public void fillCustomerIdField(String value) {
        WebElement customerIdField = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("customerId")));
        // Click the input field to focus it
        customerIdField.click();
        customerIdField.sendKeys(value);
        sobHelper.delay(800);
    }

    public void fillRefField(String value) {
        WebElement refField = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("trackingReff")));
        // Click the input field to focus it
        refField.click();
        refField.sendKeys(value);
        sobHelper.delay(800);
    }

    public void fillAmnField(String value) {
        WebElement refField = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("amount")));
        // Click the input field to focus it
        refField.click();
        refField.sendKeys(value);
        sobHelper.delay(800);
    }

    public void fillReceiptNo(String receiptNo) {
        WebElement receiptNoField = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("receiptNo")));
        receiptNoField.click();
        receiptNoField.sendKeys(receiptNo);
        sobHelper.delay(800);
    }

    public void fillReceiptCodeAction(String receiptCode) {
        WebElement fillReceiptCodeActionField = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("receiptCode")));
        fillReceiptCodeActionField.click();
        fillReceiptCodeActionField.sendKeys(receiptCode);
        sobHelper.delay(800);
    }

    public void fillRejectReasonAction(String RC) {
        WebElement fillRejectReasonActionField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("mui-component-select-rc")));
        fillRejectReasonActionField.click();
        sobHelper.delay(1000);
        WebElement valueReject = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//li[@data-value='" + RC + "']")));
        valueReject.click();
        sobHelper.delay(1000);
    }

    public void hitBtnFilter() {
        WebElement btnFilter = driver.findElement(By.xpath("//button[text()='FILTER']"));
        btnFilter.click();
        sobHelper.delay(800);
    }

    public void hitBtnApply() {
        WebElement btnApply = driver.findElement(By.xpath("//button[text()='APPLY']"));
        btnApply.click();
        sobHelper.delay(800);
    }

    public void hitBtnSuccess() {
        WebElement btnSuccess = driver.findElement(
                By.xpath("//div[contains(@class,'MuiDataGrid-cell--withRenderer MuiDataGrid-cell')]//button[1]"));
        btnSuccess.click();
        sobHelper.delay(800);
    }

    public void hitBtnEdit() {
        WebElement btnEdit = driver.findElement(
                By.xpath("//button[text()='EDIT']"));
        btnEdit.click();
        sobHelper.delay(300);
    }

    public void hitBtnFailed() {
        WebElement btnFailed = driver.findElement(
                By.xpath("//div[contains(@class,'MuiDataGrid-cell--withRenderer MuiDataGrid-cell')]//button[2]"));
        btnFailed.click();
        sobHelper.delay(800);
    }

    public void hitBtnConfirm() {
        WebElement btnFailed = driver.findElement(
                By.xpath("//button[text()='YES']"));
        btnFailed.click();
        sobHelper.delay(800);
    }

    public void getSpanInfoDate(String type) throws Exception {
        if (type.equals("Limit")){
            WebElement Limit = driver.findElement(
                    By.xpath("//span[text()='Maximum 31 Days']"));
            Limit.isDisplayed();
        } else if (type.equals("Invalid")) {
            WebElement Limit = driver.findElement(
                    By.xpath("//span[text()='Invalid From Date']"));
            Limit.isDisplayed();
        } else {
            throw new Exception("Type not recognized");
        }
    }
    public void headerDatatable(String value, String type) throws Exception {
        WebElement header = value.equals("RC") ?
                driver.findElement(By.xpath("(//div[@aria-label='RC'])[1]")) :
                driver.findElement(By.xpath("//div[text()='" + value + "']"));

        if (type.equals("ASC")) {
            header.click();
            sobHelper.delay(100);
        } else if (type.equals("DSC")) {
            header.click();
            sobHelper.delay(500);
            header.click();
            sobHelper.delay(500);
        } else {
            throw new Exception("Type not recognized");
        }
    }

}