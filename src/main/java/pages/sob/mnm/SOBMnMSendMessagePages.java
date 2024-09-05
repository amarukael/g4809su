package pages.sob.mnm;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import helper.SOBHelper;

public class SOBMnMSendMessagePages {
    WebDriver driver;
    WebDriverWait wait;
    SOBHelper sobHelper = new SOBHelper();

    public SOBMnMSendMessagePages(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public String getSendMessage() {
        WebElement sendMessage = driver.findElement(
                By.xpath("//h5[text()='Send Messages']"));
        return sendMessage.getText();
    }

    public String getTitle() {
        WebElement title = driver.findElement(
                By.xpath("//p[text()='Title']"));
        return title.getText();
    }

    public String getValTitle() {
        WebElement titleVal = driver.findElement(
                By.xpath("//div[contains(@class,'template__header MuiBox-root')]" +
                        "//div[@class='MuiBox-root css-0']" +
                        "//p[contains(@class,'MuiTypography-root MuiTypography-body1')]"));
        return titleVal.getText();
    }

    public String getValBody() {
        WebElement bodyVal = driver.findElement(
                By.xpath("//div[contains(@class,'template__body MuiBox-root')]" +
                        "//div[@class='MuiBox-root css-0']" +
                        "//p[contains(@class,'MuiTypography-root MuiTypography-body1')]"));
        return bodyVal.getText();
    }

    public String getValBtnWeb() {
        WebElement btnWebVal = driver.findElement(
                By.xpath("//p[contains(text(),'https://ids.id/')]"));
        return btnWebVal.getText();
    }

    public String getValBtnCopyCode() {
        WebElement btnCopyCode = driver.findElement(
                By.xpath("//p[text()='{{1}}']"));
        return btnCopyCode.getText();
    }

    public boolean getTitleErrors(String type) {
        try {
            driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
            if (type.equals("text") || type.equals("3_button_static_dynamic")) {
                if (driver.findElement(
                        By.xpath("//div[contains(@class,'template__header MuiBox-root')]" +
                                "//div[@class='MuiBox-root css-0']" +
                                "//div[contains(@class,'MuiBox-root')]" +
                                "//p[contains(@class,'MuiTypography-root MuiTypography-body1')]/following-sibling::p"))
                        .isDisplayed()) {
                    return true;
                }
            } else {
                if (driver.findElement(
                        By.xpath("//div[contains(@class,'template__header MuiBox-root')]" +
                                "//div[contains(@class,'MuiBox-root')]" +
                                "//span[contains(@class,'MuiTypography-root MuiTypography-caption')]"))
                        .isDisplayed()) {
                    return true;
                }
            }

        } catch (NoSuchElementException e) {
            return false;
        }

        return false;
    }

    public boolean getBodyErrors() {
        try {
            driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
            if (driver.findElement(
                    By.xpath("//div[contains(@class,'template__body MuiBox-root')]" +
                            "//div[@class='MuiBox-root css-0']" +
                            "//div[contains(@class,'MuiBox-root')]" +
                            "//p[contains(@class,'MuiTypography-root MuiTypography-body1')]/following-sibling::p"))
                    .isDisplayed()) {
                return true;
            }
        } catch (NoSuchElementException e) {
            return false;
        }

        return false;
    }

    public boolean getToManualErrors() {
        try {
            driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
            if (driver.findElement(
                    By.xpath("//p[contains(@class,'MuiFormHelperText-root Mui-error')]"))
                    .isDisplayed()) {
                return true;
            }
        } catch (NoSuchElementException e) {
            return false;
        }

        return false;
    }

    public boolean getToBulkErrors() {
        try {
            driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
            if (driver.findElement(
                    By.xpath("//div[contains(@class,'MuiFormGroup-root')]" +
                            "//div[contains(@class,'MuiBox-root')]" +
                            "//div[contains(@class,'MuiBox-root')]" +
                            "//p[contains(@class,'MuiTypography-root MuiTypography-body1')]"))
                    .isDisplayed()) {
                return true;
            }
        } catch (NoSuchElementException e) {
            return false;
        }

        return false;
    }

    public boolean getBtnWeb() {
        try {
            driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
            if (driver.findElement(
                    By.xpath("//div[contains(@class,'template__buttons MuiBox-root')]" +
                            "//div[contains(@class,'template__button MuiBox-root')][1]" +
                            "//div[@class='MuiBox-root css-0']" +
                            "//div[contains(@class,'MuiBox-root')]" +
                            "//p[contains(@class,'MuiTypography-root MuiTypography-body1')]" +
                            "/following-sibling::p"))
                    .isDisplayed()) {
                return true;
            }
        } catch (NoSuchElementException e) {
            return false;
        }

        return false;
    }

    public boolean getBtnCopyCode() {
        try {
            driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
            if (driver.findElement(
                    By.xpath("//div[contains(@class,'template__buttons MuiBox-root')]" +
                            "//div[contains(@class,'template__button MuiBox-root')][3]" +
                            "//div[@class='MuiBox-root css-0']" +
                            "//div[contains(@class,'MuiBox-root')]" +
                            "//p[contains(@class,'MuiTypography-root MuiTypography-body1')]" +
                            "/following-sibling::p"))
                    .isDisplayed()) {
                return true;
            }
        } catch (NoSuchElementException e) {
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

    public boolean getFailedAlert(String flag) {
        try {
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            WebElement successAlert = null;
            if (flag.equals("Failed Url")) {
                successAlert = wait.until(ExpectedConditions.presenceOfElementLocated(
                        By.xpath(
                                "//div[contains(@class,'MuiPaper-root MuiPaper-elevation MuiPaper-rounded MuiPaper-elevation0 MuiAlert-root MuiAlert-filledError MuiAlert-filled')]"
                                        +
                                        "//div[text()='Internal Server Error']")));
            } else {
                successAlert = wait.until(ExpectedConditions.presenceOfElementLocated(
                        By.xpath(
                                "//div[contains(@class,'MuiPaper-root MuiPaper-elevation MuiPaper-rounded MuiPaper-elevation0 MuiAlert-root MuiAlert-filledError MuiAlert-filled')]"
                                        +
                                        "//div[text()='Invalid Messaging AccessToken']")));
            }

            if (successAlert.isDisplayed()) {
                return true;
            }
        } catch (NoSuchElementException e) {
            return false;
        }

        return false;
    }

    public void chooseMessagingProduct(String value) {
        WebElement messagingProduct = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[@id=':ra:']")));
        // Click the input field to focus it
        messagingProduct.click();
        Actions keyAct = new Actions(driver);
        keyAct.sendKeys(Keys.chord(Keys.DOWN, Keys.UP)).perform();
        sobHelper.delay(800);
        keyAct.sendKeys(Keys.chord(Keys.ENTER)).perform();
    }

    public void chooseCatagory(String value) {
        WebElement chooseCatagory = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[@id=':rb:']")));
        // Click the input field to focus it
        chooseCatagory.click();
        Actions keyAct = new Actions(driver);
        keyAct.sendKeys(Keys.chord(Keys.DOWN, Keys.UP)).perform();
        sobHelper.delay(800);
        keyAct.sendKeys(Keys.chord(Keys.ENTER)).perform();
    }

    public void templateNm(String value) {
        WebElement templateNm = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[@id=':rc:']")));
        // Click the input field to focus it
        templateNm.click();
        sobHelper.delay(1000);
        if (!value.equals("") || !value.isEmpty()) {
            WebElement dropTemplateNm = driver.findElement(
                    By.xpath("//li[text()='" + value + "']"));
            dropTemplateNm.click();
            sobHelper.delay(1000);
        }
    }

    public void addFile(String path) {
        WebElement fileInput = driver.findElement(By.cssSelector("input[type=file]"));
        fileInput.sendKeys(path);
        sobHelper.delay(5000);
    }

    public void fillTitle(int param, String value) {
        WebElement fieldTitle = driver.findElement(By.xpath("(//input[@name='{{" + param + "}}'])"));
        fieldTitle.click();
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = arguments[1]", fieldTitle, value);
        fieldTitle.sendKeys(Keys.BACK_SPACE);
        sobHelper.delay(800);
    }

    public void fillBody(int param, String value) {
        WebElement fieldBody = driver.findElement(
                By.xpath("(//div[@class='template__body MuiBox-root css-0']//input[@name='{{" + param + "}}'])"));
        fieldBody.click();
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = arguments[1]", fieldBody, value);
        fieldBody.sendKeys(Keys.BACK_SPACE);
        sobHelper.delay(800);
    }

    public void fillBtnWeb(int param, String value) {
        WebElement fieldBtnWeb = driver.findElement(
                By.xpath("(//div[@class='template__button MuiBox-root css-0'][1]//input[@name='{{" + param + "}}'])"));
        fieldBtnWeb.click();
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = arguments[1]", fieldBtnWeb, value);
        fieldBtnWeb.sendKeys(Keys.BACK_SPACE);
        sobHelper.delay(800);
    }

    public void fillBtnCopyCode(int param, String value) {
        WebElement fieldBtnCopyCode = driver.findElement(
                By.xpath("(//div[@class='template__button MuiBox-root css-0'][3]//input[@name='{{" + param + "}}'])"));
        fieldBtnCopyCode.click();
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = arguments[1]", fieldBtnCopyCode, value);
        fieldBtnCopyCode.sendKeys(Keys.BACK_SPACE);
        sobHelper.delay(800);
    }

    public void chooseFillMode(String type, String value) {
        if (type.equals("manual")) {
            WebElement rbTo = driver.findElement(By.xpath("//input[@value='manual']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].checked = true;", rbTo);
            // WebElement fTo = driver.findElement(
            // By.xpath("//input[contains(@class,'MuiInputBase-input
            // MuiOutlinedInput-input')]"));
            WebElement fTo = driver.findElement(By.name("recipients"));
            // fTo.click();
            fTo.sendKeys(value);
        } else {
            WebElement rbTo = driver.findElement(By.xpath("//input[@value='bulk']"));
            rbTo.click();
            WebElement fileInput = driver.findElement(
                    By.xpath(
                            "//div[@class='MuiFormControl-root MuiFormControl-fullWidth css-m7nzkw']//div[@class='MuiFormGroup-root css-1h7anqn']//input[@type='file']"));
            fileInput.sendKeys(value);
        }
    }

    public void hitSendMessage() {
        WebElement btnSendMessage = driver.findElement(By.xpath("//button[text()='Send Messages']"));
        btnSendMessage.click();
        sobHelper.delay(800);
    }

    public void hitBtnSubmit() {
        WebElement btnSubmit = driver.findElement(By.xpath("//button[text()='SUBMIT']"));
        btnSubmit.click();
        sobHelper.delay(800);
    }

}
