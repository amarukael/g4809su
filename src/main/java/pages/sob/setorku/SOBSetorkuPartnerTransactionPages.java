package pages.sob.setorku;

import helper.SOBHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SOBSetorkuPartnerTransactionPages {
    WebDriver driver;
    WebDriverWait wait;
    SOBHelper sobHelper = new SOBHelper();

    public SOBSetorkuPartnerTransactionPages(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }


    public void fieldBranch() {
        driver.findElement(By.id("mui-component-select-branchName")).click();
    }

    public void listBranch(String s) {
        driver.findElement(By.xpath("//li[normalize-space(text())='"+s+"']")).click();
    }
}
