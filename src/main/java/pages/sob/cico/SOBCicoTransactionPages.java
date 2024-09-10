package pages.sob.cico;

import helper.SOBHelper;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class SOBCicoTransactionPages {
    WebDriver driver;
    WebDriverWait wait;
    SOBHelper sobHelper = new SOBHelper();

    public SOBCicoTransactionPages(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }


    public void fieldFilter(String arg0, String arg1) {
        Map<String, String> fieldNames = new HashMap<>();
        fieldNames.put("BCI Transaction ID", "bciId");
        fieldNames.put("Partner Transaction ID", "trxId");
        fieldNames.put("Partner Name", "partnerName");
        fieldNames.put("Merchant Transaction ID", "transCAId");
        fieldNames.put("Merchant Name", "ca");
        fieldNames.put("Customer ID", "noHp");
        fieldNames.put("Customer Name", "name");
        fieldNames.put("Token", "token");
        fieldNames.put("Transaction Type", "transType");
        fieldNames.put("Nominal", "amount");

        if ("Status".equalsIgnoreCase(arg0)) {
            selectStatus(arg1);
        } else {
            String fieldName = fieldNames.get(arg0);
            if (fieldName == null) {
                throw new NotFoundException("Field Not Defined");
            }
            fillField(fieldName, arg1);
        }
    }

    private void selectStatus(String status) {
        driver.findElement(By.id("mui-component-select-status")).click();
        sobHelper.delay(500);
        String xpath = String.format("//li[@data-value='%s']", status.toUpperCase());
        WebElement statusElement = driver.findElement(By.xpath(xpath));
        if (statusElement == null) {
            throw new NoSuchElementException("Status Not Found");
        }
        statusElement.click();
    }

    public void fillField(String fieldName, String value) {
        WebElement field = driver.findElement(By.name(fieldName));
        field.click();
        field.clear();
        field.sendKeys(value);
        sobHelper.delay(500);
    }


}
