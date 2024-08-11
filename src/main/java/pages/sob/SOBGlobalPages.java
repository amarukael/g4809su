package pages.sob;

import helper.SOBHelper;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SOBGlobalPages {
    WebDriver driver;
    SOBHelper sobHelper = new SOBHelper();
    WebDriverWait wait;

    public SOBGlobalPages(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void scrollToBottom() {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public void scrollDataTabletoLeft(Integer left) {
        WebElement dataTableListTransaction = driver.findElement(
                By.xpath("//div[@class='MuiDataGrid-virtualScroller css-frlfct']"));
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollTo({left: "+left+", top: 0, behavior: 'smooth'});", dataTableListTransaction);
        sobHelper.delay(500);
    }

    public void waitDatatableAppear() throws Exception {
        try {
            wait.until(ExpectedConditions.presenceOfNestedElementsLocatedBy(By.xpath("//div[contains" +
                    "(@class, 'MuiDataGrid-virtualScrollerRenderZone')]"), By.xpath("*")));
        }catch (Exception e){
            throw new Exception("Data Not Appear");
        }
    }

    public void nextDataTable(Integer arg1) throws Exception {
        waitDatatableAppear();
        scrollToBottom();
        sobHelper.delay(1000);
        WebElement nextBtn =  driver.findElement(By.xpath(
                "//button[@aria-label='Go to next page']"));

        for (int i = 0; i < arg1; i++) {
            if (!nextBtn.isEnabled()) {
                throw new Exception("Next button is not clickable");
            }
            nextBtn.click();
            waitDatatableAppear();
            sobHelper.delay(1000);
        }
    }

    public void prevDataTable(Integer intArg1) throws Exception {
        waitDatatableAppear();
        scrollToBottom();
        sobHelper.delay(1000);
        WebElement prevBtn =  driver.findElement(By.xpath(
                "//button[@aria-label='Go to previous page']"));
        for (int i = 0; i < intArg1; i++) {
            if (!prevBtn.isEnabled()) {
                throw new Exception("Prev button is not clickable");
            }
            prevBtn.click();
            waitDatatableAppear();
            sobHelper.delay(1000);
        }
    }

    public void filterButton() {
        WebElement btnFilter = driver.findElement(By.xpath("//button[text()='FILTER']"));
        btnFilter.click();
        sobHelper.delay(500);
    }

    public void submitButton() {
        sobHelper.delay(500);
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        sobHelper.delay(500);
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
}
