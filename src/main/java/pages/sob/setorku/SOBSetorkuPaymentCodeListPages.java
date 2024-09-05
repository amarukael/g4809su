package pages.sob.setorku;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import helper.SOBHelper;

public class SOBSetorkuPaymentCodeListPages {
    WebDriver driver;
    WebDriverWait wait;
    SOBHelper sobHelper = new SOBHelper();

    public SOBSetorkuPaymentCodeListPages(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void detailPaymentCode(String s) {
        scrollDatatable(5000);
        sobHelper.delay(500);
        try {
            WebElement button = driver.findElement(By.xpath("//div[@data-id='" + s + "']" +
                    "//button[@aria-label='edit']"));
            button.click();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void scrollDatatable(Integer left) {
        WebElement dataTableListTransaction = driver.findElement(
                By.xpath("//div[@class='MuiDataGrid-virtualScroller css-frlfct']"));
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollTo({left: " + left + ", top: 0, behavior: 'smooth'});", dataTableListTransaction);
        sobHelper.delay(800);

    }

    public String getValueCell(String s) {
        scrollDatatable(0);
        sobHelper.delay(500);
        WebElement cells = driver.findElement(By.xpath("//div[@data-id='" + s + "']//div[@data-colindex='3']"));
        String cellText = cells.getText();
        System.out.println(cellText);
        return cellText;
    }

    public Boolean validatePayment(String paymentCode) {
        try {
            WebElement element = driver.findElement(By.xpath("//p[normalize-space(text())='" + paymentCode + "']"));
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
