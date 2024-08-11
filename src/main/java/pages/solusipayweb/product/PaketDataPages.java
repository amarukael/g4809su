package pages.solusipayweb.product;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PaketDataPages {
    static WebDriver driver;
    static WebDriverWait wait;

    public PaketDataPages(WebDriver driver){
        PaketDataPages.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    private void typeWithDelay(WebElement element, String text) {
        Actions builder = new Actions(driver);
        for (char c : text.toCharArray()) {
            builder.moveToElement(element).click().sendKeys(String.valueOf(c)).perform();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void vrfyPaketDataPage(){
        WebElement paketData = driver.findElement(By.xpath("//div[text()='Paket Data']"));
        if (paketData.isDisplayed()){
            System.out.println("I'm now on paket data page");
        }
    }

    public void chooseDenomPaketData(String denomPaketdata){
        WebElement denom = driver.findElement(By.xpath("(//p[text()='"+denomPaketdata+"'])[1]"));
        denom.click();
    }

    public void fillPhoneNumberOnPaketDataPage(String phoneNumber){
        WebElement phoneNumberField = driver.findElement(By.id("input_number"));
        phoneNumberField.click();
        typeWithDelay(phoneNumberField,phoneNumber);
        Actions keyDown = new Actions(driver);
        keyDown.sendKeys(Keys.chord(Keys.ENTER)).perform();
    }

    public void errorMessageFieldPhoneNumberOnPaketDataPage(){
        WebElement err = driver.findElement(By.id("input_number-helper-text"));
        err.isDisplayed();
    }


}
