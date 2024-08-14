package pages.sob.disi;

import helper.SOBHelper;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class SOBDisiPartnerDepositPages {
    WebDriver driver;
    WebDriverWait wait;
    SOBHelper sobHelper = new SOBHelper();
    DateFormat ft = new SimpleDateFormat("dd-MM-yyyy");

    public SOBDisiPartnerDepositPages(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void scrollDatatable() {
        WebElement dataTableListTransaction = driver.findElement(
                By.xpath("//div[@class='MuiDataGrid-virtualScroller css-frlfct']"));
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollTo({left: 1500, top: 0, behavior: 'smooth'});", dataTableListTransaction);
        sobHelper.delay(800);
    }

    public String getLblPartnerDepo() {
        WebElement partnerDepoPage = driver.findElement(
                By.xpath("//p[text()='Partner Deposit']"));
        return partnerDepoPage.getText();
    }

    public String getAccountsReceivable() {
        String result = "";
        WebElement labelAccountReceivable = driver.findElement(
                By.xpath("(//p[contains(@class,'MuiTypography-root MuiTypography-body1')])[3]"));
        result = labelAccountReceivable.getText().replace("Rp ", "")
                .replace(".", "").replace(",00", "");
        return result;
    }

    public String getBalance() {
        String result = "";
        WebElement labelBalance = driver.findElement(
                By.xpath("(//h4[contains(@class,'MuiTypography-root MuiTypography-h4')])[1]"));
        result = labelBalance.getText().replace("Rp ", "")
                .replace(".", "").replace(",00", "");
        return result;
    }

    public String getPendingBalance() {
        String result = "";
        WebElement labelPendingBalance = driver.findElement(
                By.xpath("(//h4[contains(@class,'MuiTypography-root MuiTypography-h4')])[2]"));
        result = labelPendingBalance.getText().replace("Rp ", "")
                .replace(".", "").replace(",00", "");
        return result;
    }

    public String getAvailableBalance() {
        String result = "";
        WebElement labelAvailableBalance = driver.findElement(
                By.xpath("(//p[contains(@class,'MuiTypography-root MuiTypography-body1')])[2]"));
        result = labelAvailableBalance.getText().replace("Rp ", "")
                .replace(".", "").replace(",00", "");
        return result;
    }

    public boolean getSuccessAlert() {
        try {
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            WebElement successAlert = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("(//div[contains(@class,'MuiPaper-root MuiPaper-elevation')])[2]//div[text()='Withdraw success!']")));
            if (successAlert.isDisplayed()) {
                return true;
            }
        } catch (NoSuchElementException e) {
            return false;
        }

        return false;
    }

    public boolean getFailedAlert(String param) {
        try {
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            WebElement failedAlert = null;
            if (param.equals("amount_Invalid")) {
                failedAlert = wait.until(ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//p[text()='Jumlah minimal penarikan adalah Rp 10.000']")));
            } else if (param.equals("otp_Invalid")) {
                failedAlert = wait.until(ExpectedConditions.presenceOfElementLocated(
                        By.xpath("(//div[contains(@class,'MuiPaper-root MuiPaper-elevation')])[2]//div[text()='INVALID_OTP']")));
            } else if (param.equals("balance_Insufficient")) {
                failedAlert = wait.until(ExpectedConditions.presenceOfElementLocated(
                        By.xpath("(//div[contains(@class,'MuiPaper-root MuiPaper-elevation')])[2]//div[text()='BALANCE_IS_NOT_ENOUGH']")));
            } else if (param.equals("bankAccount_NotFound")) {
                failedAlert = wait.until(ExpectedConditions.presenceOfElementLocated(
                        By.xpath("(//div[contains(@class,'MuiPaper-root MuiPaper-elevation')])[2]//div[text()='BANK_ACCOUNT_NOT_FOUND']")));
            }

            if (failedAlert.isDisplayed()) {
                return true;
            }
        } catch (NoSuchElementException e) {
            return false;
        }

        return false;
    }

    public boolean checkValueFromToDate() {
        boolean result = false;
        try {
            WebElement fromDate = driver.findElement(
                    By.xpath("(//input[contains(@class,'MuiInputBase-input MuiOutlinedInput-input')])[1]"));
            WebElement toDate = driver.findElement(
                    By.xpath("(//input[contains(@class,'MuiInputBase-input MuiOutlinedInput-input')])[2]"));

            Date firstDate = ft.parse(fromDate.getAttribute("value"));
            Date secondDate = ft.parse(toDate.getAttribute("value"));

            long diffInMillies = Math.abs(secondDate.getTime() - firstDate.getTime());
            long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

            if (diff > 7) return true;
        } catch (Exception e) {
            System.out.println(e);
        }

        return result;
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

    public void fillFromDateWithYear(String[] value) {
        WebElement fromDate = driver.findElement(
                By.xpath("(//input[contains(@class,'MuiInputBase-input MuiOutlinedInput-input')])[1]"));
//        fromDate.click();
        fromDate.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        sobHelper.delay(800);
        for (int i = 0; i < value.length; i++) {
            fromDate.sendKeys(value[i]);
        }
        fromDate.sendKeys(Keys.chord(Keys.DOWN));
        sobHelper.delay(800);
    }

    public void fillToDate(String[] value) {
        WebElement toDate = driver.findElement(
                By.xpath("(//input[contains(@class,'MuiInputBase-input MuiOutlinedInput-input')])[2]"));
//        fromDate.click();
        toDate.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        sobHelper.delay(800);
        for (int i = 0; i < value.length; i++) {
            toDate.sendKeys(value[i]);
        }
        sobHelper.delay(800);
    }

    public void fillTransactionType(String transType) {
        WebElement listBoxTransactionType = driver.findElement(
                By.xpath("(//div[contains(@class,'MuiSelect-select MuiSelect-outlined')])[2]"));
        listBoxTransactionType.click();

        Actions keyDown = new Actions(driver);
        keyDown.sendKeys(Keys.chord(Keys.DOWN)).perform();
        if (transType.equals("TOP UP")) {
            keyDown.sendKeys(Keys.chord(Keys.UP)).perform();
        } else if (transType.equals("UPDATE WALLET")) {
            keyDown.sendKeys(Keys.chord(Keys.DOWN)).perform();
        }
        keyDown.sendKeys(Keys.ENTER).perform();
        sobHelper.delay(800);
    }

    public void fillStatus(String status) {
        Actions keyDown = new Actions(driver);
        keyDown.sendKeys(Keys.chord(Keys.DOWN)).perform();
        if (status.equals("All")) {
            keyDown.sendKeys(Keys.chord(Keys.UP)).perform();
        } else if (status.equals("Pending")) {
            keyDown.sendKeys(Keys.chord(Keys.DOWN)).perform();
        } else if (status.equals("Failed")) {
            keyDown.sendKeys(Keys.chord(Keys.DOWN)).perform();
            keyDown.sendKeys(Keys.chord(Keys.DOWN)).perform();
        } else if (status.equals("Refund")) {
            keyDown.sendKeys(Keys.chord(Keys.DOWN)).perform();
            keyDown.sendKeys(Keys.chord(Keys.DOWN)).perform();
            keyDown.sendKeys(Keys.chord(Keys.DOWN)).perform();
        }
        keyDown.sendKeys(Keys.ENTER).perform();
        sobHelper.delay(800);
    }

    public void fillAmountWithdraw(String amount) {
        WebElement fieldAmountWithdraw = driver.findElement(
                By.xpath("//input[contains(@class,'MuiInputBase-input MuiOutlinedInput-input')]"));
        fieldAmountWithdraw.sendKeys(amount);
        sobHelper.delay(800);
    }

    public void fillBankName() {
        WebElement listBoxBankNm = driver.findElement(
                By.xpath("(//div[contains(@class,'MuiSelect-select MuiSelect-outlined')])[2]"));
        listBoxBankNm.click();
        sobHelper.delay(800);
//        listBoxBankNm.sendKeys(Keys.ENTER);
        Actions keyDown = new Actions(driver);
        keyDown.sendKeys(Keys.chord(Keys.ENTER)).perform();
        sobHelper.delay(800);
    }

    public void hitListBoxStatus() {
        WebElement btnFilter = driver.findElement(By.xpath("(//div[contains(@class,'MuiSelect-select MuiSelect-outlined')])[3]"));
        btnFilter.click();
        sobHelper.delay(800);
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

    public void hitBtnExport() {
        WebElement btnExport = driver.findElement(
                By.xpath("//button[text()='EXPORT']"));
        btnExport.click();
        sobHelper.delay(800);
    }

    public void hitBtnWithdraw() {
        WebElement btnWithdraw = driver.findElement(By.xpath("//button[text()='Withdraw']"));
        btnWithdraw.click();
        sobHelper.delay(800);
    }

    public void hitBtnSubmitWithdraw() {
        WebElement btnSubmit = driver.findElement(By.xpath("//button[text()='SUBMIT']"));
        btnSubmit.click();
        sobHelper.delay(800);
    }

    public void hitBtnConfirm() {
        WebElement btnConfirm = driver.findElement(By.xpath("//button[text()='Confirm']"));
        btnConfirm.click();
        sobHelper.delay(800);
    }

    public void hitBtnVerifyOTP() {
        WebElement btnVerifyOTP = driver.findElement(By.xpath("//button[text()='Verify']"));
        btnVerifyOTP.click();
        sobHelper.delay(800);
    }
}
