package pages.sob.setorku;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import helper.SOBHelper;

public class SOBSetorkuPartnerPages {
    WebDriver driver;
    WebDriverWait wait;
    SOBHelper sobHelper = new SOBHelper();

    public SOBSetorkuPartnerPages(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void fieldStatus() {
        driver.findElement(By.id("mui-component-select-isActive")).click();
    }

    public void listStatus(String s) {
        driver.findElement(By.xpath("//li[normalize-space(text())='" + s + "']")).click();
    }
}
