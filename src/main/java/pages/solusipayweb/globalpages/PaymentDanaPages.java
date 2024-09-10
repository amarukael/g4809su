package pages.solusipayweb.globalpages;

import org.openqa.selenium.By;
import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PaymentDanaPages {
    static WebDriver driver;
    static WebDriverWait wait;

    public PaymentDanaPages(WebDriver driver){
        PaymentDanaPages.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    private void typeWithDelay(WebElement element, String text) {
        Actions builder = new Actions(driver);
        for (char c : text.toCharArray()) {
            builder.moveToElement(element).click().sendKeys(String.valueOf(c)).perform();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void fillPhoneNumberDana(String phoneNumber){
        WebElement phoneNumberDana = driver.findElement(By.xpath("//input[@placeholder='Input phone number \"81xx...\"']"));
        phoneNumberDana.click();
        typeWithDelay(phoneNumberDana, phoneNumber);
    }

    public void hitBtnNextOnDanaPage(){
        WebElement btnNext = driver.findElement(By.xpath("//button[contains(@class,'btn-continue btn')]"));
        btnNext.click();
    }

    public void fillPasscodeDana(String passcode){
        WebElement pass = driver.findElement(By.xpath("(//div[@class='digital-password bordered']//div)[1]"));
        pass.click();
        typeWithDelay(pass, passcode);
    }

    public void hitBtnPayOnDanaPage(){
        WebElement btnPay = driver.findElement(By.xpath("//button[@class='btn btn-primary']"));
        btnPay.click();
    }

    public void hitBtnOKDanaPage(){
        WebElement btnOK = driver.findElement(By.xpath("//button[contains(@class,'btn-pay btn')]"));
        btnOK.click();
    }

}
