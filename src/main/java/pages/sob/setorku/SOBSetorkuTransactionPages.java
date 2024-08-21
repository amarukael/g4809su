package pages.sob.setorku;

import helper.SOBHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SOBSetorkuTransactionPages {
    WebDriver driver;
    WebDriverWait wait;
    SOBHelper sobHelper = new SOBHelper();

    public SOBSetorkuTransactionPages(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }


    public void fieldProductId() {
        driver.findElement(By.id("mui-component-select-partnerId")).click();
    }

    public void listProductId(String s) {
        driver.findElement(By.xpath("//li[normalize-space(text())='"+s+"']")).click();
    }

    public void fieldStatus() {
        driver.findElement(By.id("mui-component-select-status")).click();
    }

    public void listStatus(String s) {
        driver.findElement(By.xpath("//li[normalize-space(text())='"+s+"']")).click();
    }
}