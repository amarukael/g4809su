package pages.sob.dana;

import com.google.gson.Gson;
import helper.SOBHelper;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.sob.SOBGlobalPages;
import pages.sob.SOBHomePages;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class SOBDanaProductListPages {
    Gson gson = new Gson();
    WebDriver driver;
    WebDriverWait wait;
    SOBHelper sobHelper = new SOBHelper();
    SOBHomePages homePage;
    SOBGlobalPages golbalPages;
    Map<String, String> dataSwitch = new HashMap<>();

    public SOBDanaProductListPages(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.homePage = new SOBHomePages(driver);
        PageFactory.initElements(driver, this);
    }

    public void scrollToTop() {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");
    }

    public void scrollDatatabletoLeft(Integer left) {
        WebElement dataTableListTransaction = driver.findElement(
                By.xpath("//div[@class='MuiDataGrid-virtualScroller css-frlfct']"));
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollTo({left: " + left + ", top: 0, behavior: 'smooth'});", dataTableListTransaction);
        sobHelper.delay(500);
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
    public void waitDatatableDanaAppear() {
        wait.until(ExpectedConditions.presenceOfNestedElementsLocatedBy(By.xpath("//div[contains" +
                "(@class, 'MuiDataGrid-virtualScrollerRenderZone')]"), By.xpath("*")));
    }
    public void waitButtonSwitched() throws Exception {
        String row = dataSwitch.get("Row");
        String updateTo = dataSwitch.get("updateTo");
        WebElement checkbox = driver.findElement(By.xpath("//div[@data-id='" + row + "']" +
                "//input[@type='checkbox']"));
        switch (updateTo) {
            case "ACTIVE":
                wait.until(ExpectedConditions.elementToBeSelected(checkbox));
                break;
            case "INACTIVE":
                wait.until(ExpectedConditions.elementSelectionStateToBe(checkbox, false));
                break;
            default:
                throw new Exception("Invalid argument");
        }
    }
    public boolean getAlert() {
        try {
            wait = new WebDriverWait(driver, Duration.ofSeconds(3));
            WebElement Alert = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("(//div[contains(@class,'MuiPaper-root MuiPaper-elevation')])[3]")));
            if (Alert.isDisplayed()) {
                return true;
            }
        } catch (NoSuchElementException | TimeoutException e) {
            return false;
        }
        return false;
    }
    public void hitBtnApply() {
        WebElement btnApply = driver.findElement(By.xpath("//button[text()='APPLY']"));
        btnApply.click();
        sobHelper.delay(1000);
    }
    public void hitBtnFilter() {
        WebElement btnFilter = driver.findElement(By.xpath("//button[text()='FILTER']"));
        btnFilter.click();
        sobHelper.delay(800);
    }
    public void fillFiterField(String value, String Field) {
        if (Field.equals("mui-component-select-isActive")) {
            WebElement transIdField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(Field)));
            transIdField.click();
            driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
            transIdField.findElement(By.xpath("//li[@data-value='" + value + "']")).click();
        } else {
            WebElement transIdField = wait.until(ExpectedConditions.presenceOfElementLocated(By.name(Field)));
            transIdField.click();
            transIdField.sendKeys(value);
        }
        sobHelper.delay(800);
    }
    public void SwitchButton(String arg0, String arg1) throws Exception {
        WebElement checkbox = driver.findElement(By.xpath("//div[@data-id='" + arg0 + "']" +
                "//input[@type='checkbox']"));
        boolean checked =  checkbox.isSelected();
        arg1 = arg1.toUpperCase();
        dataSwitch.put("Row",arg0);
        dataSwitch.put("updateTo",arg1);
        if ((arg1.equals("ACTIVE") && checked) || (arg1.equals("INACTIVE") && !checked)) {
            throw new Exception("Switch Row "+arg0+" Already "+arg1);
        }
        checkbox.click();
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
    public void selectSizeData(String arg0) {
        golbalPages.scrollToBottom();
        driver.findElement(By.xpath("//div[contains" +
                "(@class,'MuiSelect-select MuiTablePagination-select')]")).click();
        sobHelper.delay(1000);
        driver.findElement(By.xpath("//li[@data-value='"+arg0+"']")).click();
        scrollToTop();
        waitDatatableDanaAppear();
    }
}
