package pages.sob.solusipayweb;

import helper.SOBHelper;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SOBSolusipayWebTransactionPages {
    WebDriver driver;
    WebDriverWait wait;
    SOBHelper sobHelper = new SOBHelper();

    public SOBSolusipayWebTransactionPages(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[contains(text(), 'Transaction List')]")
    WebElement TransactionPages;

    public String getTransactionPages(){
        return TransactionPages.getText();
    }

    public void hitBtnFilter() {
        WebElement btnFilter = driver.findElement(By.xpath("//button[text()='FILTER']"));
        btnFilter.click();
    }

    public void applyFromDate(String fromDate) {
//        WebElement dateInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='DD-MM-YYYY']")));
        List<WebElement> dateInputs = driver.findElements(By.xpath("//input[@placeholder='DD-MM-YYYY']"));
        System.out.println(dateInputs.size());
        // Click the input field to focus it
        dateInputs.get(0).click();
        // Select all text in the input field and delete it
        dateInputs.get(0).sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        // Set a new date
//        dateInputs.get(0).sendKeys(fromDate);
        typeWithDelay(dateInputs.get(0), fromDate);
        // Optionally, you can add a delay if needed
        sobHelper.delay(800);
    }

    public void applyToDate(String toDate) {
//        WebElement dateInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), 'To Date')]")));
        List<WebElement> dateInputs = driver.findElements(By.xpath("//input[@placeholder='DD-MM-YYYY']"));
        System.out.println(dateInputs.size());
        // Click the input field to focus it
        dateInputs.get(1).click();
        // Select all text in the input field and delete it
        dateInputs.get(1).sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        // Set a new date
//        dateInputs.get(1).sendKeys(toDate);
        typeWithDelay(dateInputs.get(1), toDate);
        // Optionally, you can add a delay if needed
        sobHelper.delay(800);
    }

    public void fillOrderIdField(String value) {
        WebElement orderIdField = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("orderId")));
        // Click the input field to focus it
        orderIdField.click();
//        orderIdField.sendKeys(value);
        typeWithDelay(orderIdField, value);
        sobHelper.delay(800);

    }

    public void fillCustomerIdField(String value) {
        WebElement customerIdField = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("customerId")));
        // Click the input field to focus it
        customerIdField.click();
//        customerIdField.sendKeys(value);
        typeWithDelay(customerIdField, value);
        sobHelper.delay(800);
    }

    public boolean getTitleErrorMsgPartnerId () {
        WebElement errMsg = driver.findElement(By.xpath("//p[text()='Partner ID Cannot be empty']"));
        try {
            if (errMsg.isDisplayed()) {
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
        fromDate.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        sobHelper.delay(800);
        for (int i = 0; i < value.length; i++) {
            fromDate.sendKeys(value[i]);
        }
        sobHelper.delay(800);
    }

    public void fillToDate(String[] value) {
        List<WebElement> toDate = driver.findElements(By.xpath("//input[@placeholder='DD-MM-YYYY']"));
        toDate.get(1).sendKeys(Keys.chord(Keys.CONTROL, "a"));
        sobHelper.delay(800);
        for (int i = 0; i < value.length; i++) {
            toDate.get(1).sendKeys(value[i]);
        }
        sobHelper.delay(800);
    }

    private void typeWithDelay(WebElement element, String text) {
        Actions builder = new Actions(driver);
        for (char c : text.toCharArray()) {
            builder.moveToElement(element).click().sendKeys(String.valueOf(c)).perform();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public String getLblTransaction() {
        WebElement lblTransaction = driver.findElement(
                By.xpath("//h6[text()='Transaction List']"));
        return lblTransaction.getText();
    }
}
