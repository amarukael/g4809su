package pages.sob.digitalgoods;

import helper.SOBHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SOBDigitalGoodsCategoryPages {
    WebDriver driver;
    WebDriverWait wait;
    SOBHelper sobHelper = new SOBHelper();

    public SOBDigitalGoodsCategoryPages(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void tabCategory() {
        driver.findElement(By.xpath("//button[text()='Category']")).click();
    }

    public void listStatus(String arg1) {
        sobHelper.delay(300);
        driver.findElement(By.id("mui-component-select-status")).click();
        sobHelper.delay(300);
        
    }
}
