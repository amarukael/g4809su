package pages.solusipayweb.product;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PulsaPages {
    static WebDriver driver;
    static WebDriverWait wait;

    public PulsaPages(WebDriver driver) {
        PulsaPages.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void vrfyPulsaPage() {
        WebElement pulsaPage = driver.findElement(By.xpath("//div[text()='Pulsa']"));
        if (pulsaPage.isDisplayed()) {
            System.out.println("I'm now on pulsa page");
        }
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

    public void fillPhoneNumberFieldOnPulsaPage(String phoneNumber) {
        WebElement phoneNoField = driver.findElement(By.id("input_number"));
        phoneNoField.click();
        phoneNoField.sendKeys(phoneNumber);
        // typeWithDelay(phoneNoField, phoneNumber);
        Actions keyDown = new Actions(driver);
        keyDown.sendKeys(Keys.chord(Keys.ENTER)).perform();
    }

    public void chooseNominalPulsa(String nominalPulsa) {
        WebElement nomPulsa = driver.findElement(By.xpath("//p[text()='" + nominalPulsa + "']"));
        nomPulsa.click();
    }

    public void errorMessageFieldPhoneNumberOnPulsaPage() {
        WebElement err = driver.findElement(By.id("input_number-helper-text"));
        err.isDisplayed();
    }

}
