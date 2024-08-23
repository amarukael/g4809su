package pages.sob.setorku;

import java.time.Duration;

import org.openqa.selenium.By;
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
        try {
            WebElement button = driver.findElement(By.xpath("//div[@data-id='" + s + "']" +
                    "//button[@aria-label='edit']"));
            button.click();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
