package pages.sob.mnm;

import helper.SOBHelper;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SOBMnMTransactionPages {
    WebDriver driver;
    WebDriverWait wait;
    SOBHelper sobHelper = new SOBHelper();

    public SOBMnMTransactionPages(WebDriver driver) {
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

    public String getLblTransaction() {
        WebElement lblTransaction = driver.findElement(
                By.xpath("//p[text()='Transaction']"));
        return lblTransaction.getText();
    }

    public String getCountData() {
        WebElement lblCountData = driver.findElement(
                By.xpath("//p[@class='MuiTablePagination-displayedRows css-18eums3']"));
        return lblCountData.getText();
    }

    public void fillFromDate(String[] value) {
        WebElement fromDate = driver.findElement(
                By.xpath("(//input[contains(@class,'MuiInputBase-input MuiOutlinedInput-input')])[1]"));
        fromDate.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        sobHelper.delay(800);
        for (int i = 0; i < value.length; i++) {
            fromDate.sendKeys(value[i]);
        }
        sobHelper.delay(800);
    }

    public void displayListBoxStatus() {
        WebElement listBoxStatus = driver.findElement(
                By.xpath("(//div[contains(@class,'MuiSelect-select MuiSelect-outlined')])[3]"));
        listBoxStatus.click();
        sobHelper.delay(800);
    }

    public void fillStatus(String status) {
        Actions keyDown = new Actions(driver);
        keyDown.sendKeys(Keys.chord(Keys.DOWN)).perform();
        if (status.equals("Success")) {
            keyDown.sendKeys(Keys.chord(Keys.UP)).perform();
        } else if (status.equals("Waiting")) {
            keyDown.sendKeys(Keys.chord(Keys.DOWN)).perform();
        } else if (status.equals("Failed")) {
            keyDown.sendKeys(Keys.chord(Keys.DOWN)).perform();
            keyDown.sendKeys(Keys.chord(Keys.DOWN)).perform();
        }
        keyDown.sendKeys(Keys.chord(Keys.ENTER)).perform();
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
}
