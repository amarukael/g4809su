package pages.sob.disi;

import helper.SOBHelper;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class SOBDisiListWithdrawPages {
    WebDriver driver;
    WebDriverWait wait;
    SOBHelper sobHelper = new SOBHelper();

    public SOBDisiListWithdrawPages(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void scrollDatatable() {
        WebElement dataTableListTransaction = driver.findElement(
                By.xpath("//div[@class='MuiDataGrid-virtualScroller css-frlfct']"));
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollTo({left: 1000, top: 0, behavior: 'smooth'});", dataTableListTransaction);
        sobHelper.delay(800);
    }

    public String getLblListWithdraw() {
        WebElement listWithdrawPage = driver.findElement(
                By.xpath("(//p[text()='List Withdraw'])[1]"));
        return listWithdrawPage.getText();
    }

    public boolean getSuccessAlert() {
        try {
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            WebElement successAlert = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("(//div[contains(@class,'MuiPaper-root MuiPaper-elevation')])[2]//div[text()='Data Check successful! Withdrawal status is now SUCCESSFUL']")));
            if (successAlert.isDisplayed()) {
                return true;
            }
        } catch (NoSuchElementException e) {
            return false;
        }

        return false;
    }

    public boolean getPendingAlert() {
        try {
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            WebElement successAlert = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("(//div[contains(@class,'MuiPaper-root MuiPaper-elevation')])[2]//div[text()='Data Check successful! Withdrawal status is still PENDING']")));
            if (successAlert.isDisplayed()) {
                return true;
            }
        } catch (NoSuchElementException e) {
            return false;
        }

        return false;
    }

    public boolean getFailedAlert() {
        try {
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            WebElement successAlert = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("(//div[contains(@class,'MuiPaper-root MuiPaper-elevation')])[2]//div[text()='Data Check successful! Withdrawal status is FAILED']")));
            if (successAlert.isDisplayed()) {
                return true;
            }
        } catch (NoSuchElementException e) {
            return false;
        }

        return false;
    }

    public void fillFromDate(String[] value) {
        WebElement fromDate = driver.findElement(
                By.xpath("(//input[contains(@class,'MuiInputBase-input MuiOutlinedInput-input')])[1]"));
//        fromDate.click();
        fromDate.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        sobHelper.delay(800);
        for (int i = 0; i < value.length; i++) {
            fromDate.sendKeys(value[i]);
        }
        sobHelper.delay(800);
    }

    public void fillTrackingReff(String trackingReff) {
        WebElement fieldTrackingReff = driver.findElement(By.name("trackingReff"));
        fieldTrackingReff.sendKeys(trackingReff);
        sobHelper.delay(800);
    }

    public void fillPartnerId(String partnerId) {
        WebElement listBoxPartnerId = driver.findElement(
                By.xpath("(//div[contains(@class,'MuiSelect-select MuiSelect-outlined')])[2]"));
        listBoxPartnerId.click();
        WebElement listPartnerId = driver.findElement(
                By.xpath("//li[text()='" + partnerId + "']"));
        listPartnerId.click();
        sobHelper.delay(800);
    }

    public void fillMerchantId(String merchantId) {
        WebElement listBoxMerchantId = driver.findElement(
                By.xpath("(//div[contains(@class,'MuiSelect-select MuiSelect-outlined')])[3]"));
        listBoxMerchantId.click();
        sobHelper.delay(800);
        WebElement listMerchantId = driver.findElement(
                By.xpath("//li[text()='"+ merchantId +"']"));
        listMerchantId.click();
        sobHelper.delay(800);
    }

    public void hitBtnFilter() {
        WebElement btnFilter = driver.findElement(By.xpath("//button[text()='FILTER']"));
        btnFilter.click();
    }

    public void hitBtnApply() {
        WebElement btnApply = driver.findElement(By.xpath("//button[text()='APPLY']"));
        btnApply.click();
        sobHelper.delay(800);
    }

    public void hitBtnExport() {
        WebElement btnExport = driver.findElement(
                By.xpath("(//button[contains(@class,'MuiButtonBase-root MuiButton-root')])[3]"));
        btnExport.click();
        sobHelper.delay(800);
    }

    public void hitBtnAdvice() {
        WebElement btnAdvice = driver.findElement(
                By.xpath("//button[text()='Advice']"));
        btnAdvice.click();
        sobHelper.delay(800);
    }

    public void hitBtnConfirm() {
        WebElement btnConfirm = driver.findElement(
                By.xpath("//button[text()='Confirm']"));
        btnConfirm.click();
        sobHelper.delay(800);
    }
}
