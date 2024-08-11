package pages.sob.digitalgoods;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import helper.SOBHelper;

public class SOBDigitalGoodsTransactionPages {
    WebDriver driver;
    WebDriverWait wait;
    SOBHelper sobHelper = new SOBHelper();

    public SOBDigitalGoodsTransactionPages(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void introPage() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class, 'MuiBox-root')]")));
    }

    public void nextIntroBtn() {
        driver.findElement(By.xpath("//button[text()='Next']")).click();
    }

    public void backIntroBtn() {
        driver.findElement(By.xpath("//button[text()='Back']")).click();
    }

    public void closeIntroBtn() {
        driver.findElement(By.xpath("//button[text()='Close']")).click();
    }

    public void hitBtnFilter() {
        WebElement btnFilter = driver.findElement(By.xpath("//button[text()='FILTER']"));
        btnFilter.click();
        sobHelper.delay(500);
    }

    public void fillFromDate(String[] value) {
        WebElement fromDate = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='DD-MM-YYYY']")));
        fromDate.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        sobHelper.delay(800);
        for (String s : value) {
            fromDate.sendKeys(s);
        }
        sobHelper.delay(800);
    }

    public void fillToDate(String[] value) {
        WebElement fromDate = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//input[@placeholder='DD-MM-YYYY'])[2]")));
        fromDate.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        sobHelper.delay(800);
        for (String s : value) {
            fromDate.sendKeys(s);
        }
        sobHelper.delay(800);
    }

    public void fieldFilter(String arg0, String arg1) throws Exception {
        Map<String, String> fieldNames = new HashMap<>();
        fieldNames.put("Transactions ID", "trxId");
        fieldNames.put("Product Code", "productId");
        fieldNames.put("Customer ID", "customerId");
        fieldNames.put("Serial Number", "receiptNo");
        fieldNames.put("Ref", "trackingRef");

        try {
            if (arg0.equals("Status")) {
                selectDropdownFilter("mui-component-select-status", arg1);
            } else if (fieldNames.containsKey(arg0)) {
                fillField(fieldNames.get(arg0), arg1);
            } else if (arg0.equals("Partner")) {
                selectDropdownFilter("mui-component-select-partnerId", arg1);
            } else {
                throw new Exception("Partner Already Defined");
            }
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public void selectDropdownFilter(String dropdownId, String value) {
        WebElement field = driver.findElement(By.id(dropdownId));
        field.click();
        sobHelper.delay(500);
        WebElement option = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//li[text()='" + value + "']")));
        option.click();
    }

    public void fillField(String fieldName, String value) {
        WebElement field = driver.findElement(By.name(fieldName));
        field.click();
        field.sendKeys(value);
        sobHelper.delay(500);
    }

    public void detailTrx(String arg0) throws Exception {
        int row = Integer.parseInt(arg0) - 1;
        try {
            WebElement dataRow = driver.findElement(By.xpath("//div[@data-rowindex='" + row + "']"));
            WebElement successElement = dataRow.findElement(By.xpath(".//div[contains(@class, 'MuiDataGrid-cellContent') and @title='SUCCESS']"));

            if (successElement != null) {
                WebElement button = dataRow.findElement(By.xpath(".//button[contains(@class, 'button-receipt')]"));
                button.click();

                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("modal-modal-description")));
            }
        } catch (NoSuchElementException | TimeoutException e) {
            throw new Exception(e);
        }
    }

    public void forceSuccess(String arg0) throws Exception {
        int row = Integer.parseInt(arg0);
        try {
            WebElement dataRow = driver.findElement(By.xpath("//div[@data-id='" + row + "']"));
            WebElement button = dataRow.findElement(By.xpath("//button[contains(@class,'button-success')]"));
            button.click();

        } catch (NoSuchElementException | TimeoutException e) {
            throw new Exception(e);
        }
    }

    public void seeMoreButton() throws Exception {
        try {
            WebElement button = driver.findElement(By.xpath("//button[text()='See More']"));
            button.click();
            new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(By.id("modal-modal-description")));
        } catch (Exception e) {
            throw new Exception("Button is disabled");
        }
    }

    public void forceFailed(String arg1) throws Exception {
        int row = Integer.parseInt(arg1);
        try {
            WebElement dataRow = driver.findElement(By.xpath("//div[@data-id='" + row + "']"));
            WebElement button = dataRow.findElement(By.xpath("//button[contains(@class,'button-failed')]"));
            button.click();

        } catch (NoSuchElementException | TimeoutException e) {
            throw new Exception(e);
        }
    }

    public void modalTransactionApproval(String arg0, String arg1) {
        WebElement field = driver.findElement(By.name("receiptNo"));
        field.click();
        field.sendKeys(arg0);
        sobHelper.delay(500);
        field = driver.findElement(By.xpath("//label[text()='PLN Token / Voucher Code']/following::input"));
        field.click();
        field.sendKeys(arg1);
        sobHelper.delay(500);
        field = driver.findElement(By.id("button-submit-form"));
        field.click();
    }

    public void modalStatusConfirmation() {
        driver.findElement(By.xpath("//button[text()='Confirm']")).click();
    }

    public void modalTransactionCancellation(String value) throws Exception {
        sobHelper.delay(500);
        if (value.equalsIgnoreCase("yes")) {
            driver.findElement(By.xpath("//button[text()='Yes,cancel']")).click();
        } else if (value.equalsIgnoreCase("cancel")) {
            driver.findElement(By.xpath("//button[text()='CANCEL']")).click();
        } else {
            throw new Exception("Whats Button?");
        }
        sobHelper.delay(500);
    }

    public void alertSuccess() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[text()='SUCCESS' or " +
                        "text()='Data has been approved' or " +
                        "text()='Data has been restored']")));
    }

    public void alertFailedDataNotFound() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[text()='Data Tidak Ditemukan']")));
    }
}


