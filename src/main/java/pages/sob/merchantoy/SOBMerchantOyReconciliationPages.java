package pages.sob.merchantoy;

import com.google.gson.Gson;
import helper.SOBHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.sob.SOBGlobalPages;
import pages.sob.SOBHomePages;

import java.time.Duration;

public class SOBMerchantOyReconciliationPages {
    Gson gson = new Gson();
    WebDriver driver;
    WebDriverWait wait;
    SOBHelper sobHelper = new SOBHelper();
    SOBHomePages homePage;
    SOBGlobalPages golbalPages;

    public SOBMerchantOyReconciliationPages(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void fieldFilter(String arg0, String arg1) {
        if (arg0.equalsIgnoreCase("Through")) {
            driver.findElement(By.id("mui-component-select-caId")).click();
            sobHelper.delay(200);
            driver.findElement(By.xpath("//li[text()='" + arg1 + "']")).click();
        } else {
            WebElement field = driver.findElement(By.xpath("(//label[text()='" + arg0 + "']/following::input)[1]"));
            field.click();
            field.clear();
            field.sendKeys(arg1);
        }
        sobHelper.delay(500);
    }
}
