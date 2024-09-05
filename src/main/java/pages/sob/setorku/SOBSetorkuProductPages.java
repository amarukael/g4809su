package pages.sob.setorku;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import helper.SOBHelper;

public class SOBSetorkuProductPages {
    WebDriver driver;
    WebDriverWait wait;
    SOBHelper sobHelper = new SOBHelper();

    public SOBSetorkuProductPages(WebDriver driver) {
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

    public void trashCan(String arg0) {
        try {
            WebElement button = driver.findElement(By.xpath("//div[@data-id='" + arg0 + "']" +
                    "//button[contains(@class, 'MuiButtonBase-root MuiButton-root')]"));
            button.click();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void editButton(String s) {
        WebElement btn = driver.findElement(By.xpath("//div[@data-id='" + s + "']" +
                "//button[@aria-label='edit']"));
        btn.click();
    }

    public void deleteButton(String s) {
        WebElement btn = driver.findElement(By.xpath("//div[@data-id='" + s + "']" +
                "//button[@aria-label='delete']"));
        btn.click();
    }
}
