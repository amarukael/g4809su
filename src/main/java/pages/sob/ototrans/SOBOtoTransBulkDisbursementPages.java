package pages.sob.ototrans;

import helper.SOBHelper;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class SOBOtoTransBulkDisbursementPages {
    WebDriver driver;
    WebDriverWait wait;
    SOBHelper sobHelper = new SOBHelper();

    public SOBOtoTransBulkDisbursementPages(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void scrollDatatable() {
        WebElement dataTableListTransaction = driver.findElement(
                By.xpath("//div[@class='MuiDataGrid-virtualScroller css-frlfct']"));
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollTo({left: 900, top: 0, behavior: 'smooth'});", dataTableListTransaction);
        sobHelper.delay(800);
    }

    public String getLblBulkTransaction() {
        WebElement lblTransaction = driver.findElement(
                By.xpath("//h5[text()='Bulk Disbursement List']"));
        return lblTransaction.getText();
    }

    public String getRowId(String hint) {
        WebElement findRow = driver.findElement(
                By.xpath("//div[text()='" + hint + "']"));
        WebElement parent = findRow.findElement(By.xpath("./../.."));
        String res = parent.getAttribute("data-id");
        System.out.println(res);
        return res;
    }

    public boolean getSuccessAlert() {
        try {
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            WebElement successAlert = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//div[text()='Success Insert Bulk Disbursement']")));
            if (successAlert.isDisplayed()) {
                return true;
            }
        } catch (NoSuchElementException e) {
            return false;
        }

        return false;
    }

    public boolean getSuccessFailedAlert() {
        try {
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            WebElement successAlert = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//div[text()='Cancel Success']")));
            if (successAlert.isDisplayed()) {
                return true;
            }
        } catch (NoSuchElementException e) {
            return false;
        }

        return false;
    }

    public boolean getSuccessApproveAlert() {
        try {
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            WebElement successAlert = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//div[text()='Approve Bulk Success']")));
            if (successAlert.isDisplayed()) {
                return true;
            }
        } catch (NoSuchElementException e) {
            return false;
        }

        return false;
    }

    public void choosePartnerId(String value) {
        WebElement partnerId = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.id("mui-component-select-partnerId")));
        // Click the input field to focus it
        partnerId.click();
        sobHelper.delay(1000);
        if (!value.isEmpty()) {
            WebElement dropPartnerId = driver.findElement(
                    By.xpath("//li[text()='" + value + "']"));
            dropPartnerId.click();
            sobHelper.delay(1000);
        }
    }

    public void fillTransNm(String value) {
        WebElement fieldTransNm = driver.findElement(By.name("transactionName"));
        fieldTransNm.sendKeys(value);
        sobHelper.delay(800);
    }

    public void fillTransDesc(String value) {
        WebElement fieldTransDesc = driver.findElement(By.name("transactionDesc"));
        fieldTransDesc.sendKeys(value);
        sobHelper.delay(800);
    }

    public void chooseUploadFormat(String value) {
        WebElement uploadFormat = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.id("mui-component-select-formatCsv")));
        // Click the input field to focus it
        uploadFormat.click();
        sobHelper.delay(1000);
        if (!value.isEmpty()) {
            WebElement dropUploadFormat = driver.findElement(
                    By.xpath("//li[text()='" + value + "']"));
            dropUploadFormat.click();
            sobHelper.delay(1000);
        }
    }

    public void addFile(String path) {
        WebElement fileInput = driver.findElement(By.cssSelector("input[type=file]"));
        fileInput.sendKeys(path);
        sobHelper.delay(1000);
    }

    public void hitAddDisbursement() {
        WebElement btnAddDisbursement = driver.findElement(By.id("button-add"));
        btnAddDisbursement.click();
        sobHelper.delay(800);
    }

    public void hitSubmit() {
        WebElement btnSubmit = driver.findElement(By.xpath("//button[text()='SUBMIT']"));
        btnSubmit.click();
        sobHelper.delay(800);
    }

    public void hitFilter() {
        WebElement btnFilter = driver.findElement(By.id("button-filter"));
        btnFilter.click();
        sobHelper.delay(800);
    }

    public void hitApply() {
        WebElement btnFilter = driver.findElement(By.id("button-submit-form"));
        btnFilter.click();
        sobHelper.delay(800);
    }

    public void hitFailed(String row) {
//        WebElement btnFailed = driver.findElement(
//                By.xpath("//div[@data-id='"+ row +"']" +
//                        "//div[contains(@class,'MuiDataGrid-cell--withRenderer MuiDataGrid-cell MuiDataGrid-cell')]" +
//                        "//div[contains(@class,'MuiStack-root')]" +
//                        "//button[contains(@class,'MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary MuiButton-sizeMedium MuiButton-containedSizeMedium MuiButton-root MuiButton-contained MuiButton-containedPrimary MuiButton-sizeMedium MuiButton-containedSizeMedium')]/following-sibling::button"));
//        btnFilter.click();
        WebElement btnFailed = driver.findElement(
                By.xpath("//div[@data-id='"+ row +"']" +
                        "//div[contains(@class,'MuiDataGrid-cell--withRenderer MuiDataGrid-cell MuiDataGrid-cell')]" +
                        "//div[contains(@class,'MuiStack-root')]" +
                        "//button[contains(@class, 'MuiButtonBase-root MuiButton-root MuiButton-outlined MuiButton-outlinedPrimary MuiButton-sizeMedium MuiButton-outlinedSizeMedium MuiButton-root MuiButton-outlined MuiButton-outlinedPrimary MuiButton-sizeMedium MuiButton-outlinedSizeMedium')]"));
        btnFailed.click();
//        String res = btnFailed.getAttribute("tabindex");
//        System.out.println(res);
        sobHelper.delay(800);
    }

    public void hitApprove(String row) {
        WebElement btnFailed = driver.findElement(
                By.xpath("//div[@data-id='"+ row +"']" +
                        "//div[contains(@class,'MuiDataGrid-cell--withRenderer MuiDataGrid-cell MuiDataGrid-cell')]" +
                        "//div[contains(@class,'MuiStack-root')]" +
                        "//button[contains(@class,'MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary MuiButton-sizeMedium MuiButton-containedSizeMedium MuiButton-root MuiButton-contained MuiButton-containedPrimary MuiButton-sizeMedium MuiButton-containedSizeMedium')]"));
        btnFailed.click();
        sobHelper.delay(800);
    }

    public void hitConfirm() {
        WebElement btnConfirm = driver.findElement(By.xpath("//button[text()='Confirm']"));
        btnConfirm.click();
        sobHelper.delay(800);
    }

    public void hitEmail() {
        WebElement btnFilter = driver.findElement(By.xpath("//button[text()='EMAIL']"));
        btnFilter.click();
        sobHelper.delay(800);
    }
}
