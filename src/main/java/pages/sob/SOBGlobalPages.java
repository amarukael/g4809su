package pages.sob;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import helper.SOBHelper;

public class SOBGlobalPages {
    WebDriver driver;
    SOBHelper sobHelper = new SOBHelper();
    WebDriverWait wait;
    Map<String, String> dataSwitch = new HashMap<>();

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
                "arguments[0].scrollTo({left: " + left + ", top: 0, behavior: 'smooth'});", dataTableListTransaction);
        sobHelper.delay(500);
    }

    public void waitDatatableAppear() throws Exception {
        try {
            wait.until(ExpectedConditions.presenceOfNestedElementsLocatedBy(By.xpath("//div[contains" +
                    "(@class, 'MuiDataGrid-virtualScrollerRenderZone')]"), By.xpath("*")));
        } catch (Exception e) {
            throw new Exception("Data Not Appear");
        }
    }

    public void nextDataTable(Integer arg1) throws Exception {
        waitDatatableAppear();
        scrollToBottom();
        sobHelper.delay(1000);
        WebElement nextBtn = driver.findElement(By.xpath(
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
        WebElement prevBtn = driver.findElement(By.xpath(
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
        driver.findElement(By.id("button-submit-form")).click();
    }

    public void fillFromDate(String[] value) {
        WebElement fromDate = wait
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='DD-MM-YYYY']")));
        fromDate.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        sobHelper.delay(800);
        for (String s : value) {
            fromDate.sendKeys(s);
        }
        sobHelper.delay(800);
    }

    public void fillToDate(String[] value) {
        WebElement fromDate = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("(//input[@placeholder='DD-MM-YYYY'])[2]")));
        fromDate.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        sobHelper.delay(800);
        for (String s : value) {
            fromDate.sendKeys(s);
        }
        sobHelper.delay(800);
    }

    public String inputText(String arg0, String arg1) {
        WebElement field = driver.findElement(By.xpath("(//label[text()='" + arg0 + "']/following::input)[1]"));
        field.click();
        field.clear();
        field.sendKeys(arg1);
        return arg1;
    }

    public boolean errorAlert() {
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        try {
            driver.findElement(By.xpath("//*[contains(@class, 'MuiAlert-filledError')]"));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean successAlert() {
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        try {
            driver.findElement(By.xpath("//*[contains(@class, 'MuiAlert-filledSuccess')]"));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void statusConfirmation(String arg0) throws Exception {
        switch (arg0.toUpperCase()) {
            case "YES":
                driver.findElement(By.xpath("//button[text()='YES']")).click();
                break;
            case "NO":
                driver.findElement(By.xpath("//button[text()='NO']")).click();
                break;
            default:
                throw new Exception("Invalid argument");
        }
    }

    public boolean errorField() {
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        try {
            driver.findElement(
                    By.xpath("//p[contains(@class,'MuiFormHelperText-root Mui-error')]"));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void dropList(String arg0, String arg1) {
        if (arg1.equalsIgnoreCase("null") || arg1.equals("")) {
            System.out.println("Skiping");
        } else {
            WebElement label = driver.findElement(By.xpath("//label[contains(text(), '" + arg0 + "')]"));
            WebElement container = label.findElement(
                    By.xpath("./following-sibling::div//div[@role='button' and @aria-haspopup='listbox']"));
            container.click();
            sobHelper.delay(500);

            sobHelper.delay(500);
            driver.findElement(By.xpath("//li[normalize-space(text())='" + arg1 + "']")).click();
        }
    }

    public void SwitchButton(String arg0, String arg1) throws Exception {
        WebElement checkbox = driver.findElement(By.xpath("//div[@data-id='" + arg0 + "']" +
                "//input[@type='checkbox']"));
        boolean checked = checkbox.isSelected();
        arg1 = arg1.toUpperCase();
        dataSwitch.put("Row", arg0);
        dataSwitch.put("updateTo", arg1);
        if ((arg1.equals("ACTIVE") && checked) || (arg1.equals("INACTIVE") && !checked)) {
            throw new Exception("Switch Row " + arg0 + " Already " + arg1);
        }
        checkbox.click();
    }

}
