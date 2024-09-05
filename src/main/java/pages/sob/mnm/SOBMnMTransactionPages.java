package pages.sob.mnm;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import helper.SOBHelper;

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

    public void scrollDatatable2(Integer left) {
        WebElement dataTableListTransaction = driver.findElement(
                By.xpath("//div[@class='MuiDataGrid-virtualScroller css-frlfct']"));
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollTo({left: " + left + ", top: 0, behavior: 'smooth'});", dataTableListTransaction);
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

    public void detailButton(String s) {
        WebElement btn = driver.findElement(By.xpath("//div[@data-id='" + s + "']" +
                "//button[p[text()='Detail']]"));
        btn.click();
    }

    public void getValueCell(String s, Integer cell) {
        scrollDatatable2(0);
        WebElement row = driver.findElement(By.xpath("//div[@data-id='" + s + "']"));
        for (int i = 0; i < cell; i++) {
            if (i == 7) {
                scrollDatatable2(1000);
                sobHelper.delay(500);
            }
            WebElement cells = row.findElement(By.xpath("//div[@data-colindex='" + i + "']"));

            String cellText = cells.getText();
            sobHelper.saveData(Integer.toString(i), cellText);
            // System.out.println("Data-ColIndex: " + i + ", Value: " + cellText);

        }
    }

    public String validateValue(String s, int in) {
        StringBuilder result = new StringBuilder();
        List<WebElement> elements = driver.findElements(By.cssSelector("p.MuiTypography-root.MuiTypography-body1"));

        for (int i = 0; i < in; i++) {
            String validValue = sobHelper.getData(Integer.toString(i));

            if (i == 1) {
                DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                LocalDateTime dateTime = LocalDateTime.parse(validValue, inputFormatter);
                DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");
                validValue = dateTime.format(outputFormatter);
            }
            boolean found = false;

            for (WebElement element : elements) {
                String elementText = element.getText();
                if (elementText.equals(validValue)) {
                    String sfound = " \n Data \t: " + i + " - Valid value found \t | \t Value Data \t: "
                            + validValue;
                    result.append(sfound);
                    found = true;
                    break;
                }
            }

            if (!found) {
                String sfound = " \n Data \t: " + i + " - Not Found \t | \t Expected\t : "
                        + validValue;
                result.append(sfound);
            }
        }
        String sResult = result.toString();
        return sResult;
    }

}
