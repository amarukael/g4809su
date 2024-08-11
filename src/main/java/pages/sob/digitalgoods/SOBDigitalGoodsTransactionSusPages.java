package pages.sob.digitalgoods;

import helper.SOBHelper;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SOBDigitalGoodsTransactionSusPages {
    WebDriver driver;
    WebDriverWait wait;
    SOBHelper sobHelper = new SOBHelper();

    public SOBDigitalGoodsTransactionSusPages(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
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

    public void transPendButton() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("tab-1")));
        driver.findElement(By.id("tab-1")).click();
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
        switch (arg0) {
            case "Transactions ID" -> {
                WebElement field = driver.findElement(By.name("trxId"));
                field.click();
                field.sendKeys(arg1);
            }
            case "Customer ID" -> {
                WebElement field = driver.findElement(By.name("customerId"));
                field.click();
                field.sendKeys(arg1);
            }
            case "Ref" -> {
                WebElement field = driver.findElement(By.name("trackingRef"));
                field.click();
                field.sendKeys(arg1);
                sobHelper.delay(500);
            }
            case "Product Name" -> {
                WebElement field = driver.findElement(By.xpath(
                        "(//label[text()='Product Name']/following::input)[1]"));
                field.click();
                field.sendKeys(arg1);
                sobHelper.delay(500);
                Actions keyDown = new Actions(driver);
                keyDown.sendKeys(Keys.chord(Keys.DOWN)).perform();
                sobHelper.delay(500);
                keyDown.sendKeys(Keys.chord(Keys.ENTER)).perform();
            }
            default -> throw new Exception("Field Filter Not Found");
        }
    }

    public void forceSuccess(String arg0) throws Exception {
        int row = Integer.parseInt(arg0);
        try {
            WebElement dataRow = driver.findElement(By.xpath("//div[@data-id='" + row + "']"));
            WebElement button = dataRow.findElement(By.xpath("(//button[contains(@class,'MuiButton-contained MuiButton-containedPrimary')])"));
            button.click();

        } catch (NoSuchElementException | TimeoutException e) {
            throw new Exception(e);
        }
    }

    public void forceFailed(String arg1) throws Exception {
        int row = Integer.parseInt(arg1);
        try {
            WebElement dataRow = driver.findElement(By.xpath("//div[@data-id='" + row + "']"));
            WebElement button = dataRow.findElement(By.xpath("(//button[contains(@class,'MuiButton-outlined MuiButton-outlinedError')])"));
            button.click();

        } catch (NoSuchElementException | TimeoutException e) {
            throw new Exception(e);
        }
    }

    public void dropdownRejectReason(String rc) {
        sobHelper.delay(500);
        driver.findElement(By.id("mui-component-select-rc")).click();
        sobHelper.delay(500);
        driver.findElement(By.xpath("//li[@data-value='"+rc+"']")).click();
        sobHelper.delay(500);
    }

    public void modalStatusConfirmation() {
        driver.findElement(By.xpath("//button[text()='Confirm']")).click();
    }
}
