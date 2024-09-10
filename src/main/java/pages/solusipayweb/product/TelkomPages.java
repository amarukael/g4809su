package pages.solusipayweb.product;

import org.openqa.selenium.By;
import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TelkomPages {
    static WebDriver driver;
    static WebDriverWait wait;

    public TelkomPages(WebDriver driver){
        TelkomPages.driver = driver;
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

    public void verifyTelkomPage(){
        WebElement telkom = driver.findElement(By.xpath("//div[text()='Telkom']"));
        telkom.isDisplayed();
    }

    public void choosePenyediaLayanan(String layanan){
        WebElement fieldLayanan = driver.findElement(By.xpath("(//input[contains(@class,'MuiInputBase-input MuiOutlinedInput-input')])[1]"));
        fieldLayanan.click();
        typeWithDelay(fieldLayanan, layanan);
        Actions keyDown = new Actions(driver);
        keyDown.sendKeys(Keys.chord(Keys.DOWN)).perform();
        keyDown.sendKeys(Keys.chord(Keys.ENTER)).perform();
    }

    public void fillCustomerId(String customerId){
        WebElement cstId = driver.findElement(By.id("field_input"));
        cstId.click();
        typeWithDelay(cstId, customerId);
    }

    public void verifyErrorMessageCustId(){
        WebElement errCustId = driver.findElement(By.id("field_input-helper-text"));
        errCustId.isDisplayed();
    }
}
