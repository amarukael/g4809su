package pages.solusipayweb.globalpages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import java.time.Duration;

public class MethodPaymentPages {
    static WebDriver driver;
    static WebDriverWait wait;

    public MethodPaymentPages(WebDriver driver) {
        MethodPaymentPages.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }
    public void vrfyElementPaymentMethodPage(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            }
        });
        WebElement btnPaymentDisabled = driver.findElement(By.id("btn_payment_disabled"));
        if (btnPaymentDisabled.isDisplayed()){
            System.out.println("I'm now on Payment Method Page");
        }
    }

    public void btnPayment(){
        WebElement btnPayment = driver.findElement(By.id("btn_payment"));
        btnPayment.click();
    }

    public void btnPaymentAgain(){
        WebElement btnPayment = driver.findElement(By.xpath("//button[text()='Bayar']"));
        btnPayment.click();
    }

    // start -- Payment //
    public void radioVirgo(){
        WebElement radioVirgo = driver.findElement(By.id("radio_instantPayment_1")); // DEV
//        WebElement radioVirgo = driver.findElement(By.id("radio_instantPayment_0")); // STG
        radioVirgo.click();
    }

    // End -- Payment //

    public void inputPasscodeVirgo() throws InterruptedException {
        WebElement passcode = driver.findElement(By.id("step-auth-input-box-1"));
        Actions performAct = new Actions(driver);
        performAct.sendKeys(passcode, "1").build().perform();
        performAct.sendKeys(passcode, "2").build().perform();
        performAct.sendKeys(passcode, "1").build().perform();
        performAct.sendKeys(passcode, "2").build().perform();
        performAct.sendKeys(passcode, "1").build().perform();
        performAct.sendKeys(passcode, "2").build().perform();
        Thread.sleep(2000);
        WebElement btnRedirect = driver.findElement(By.id("redirect"));
        btnRedirect.click();
    }

    public void scrollToButtomOnCardMethod(){
        WebElement cardMethod = driver.findElement(By.id("methodCard"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollTop = arguments[0].scrollHeight;", cardMethod);
    }

    public void choosePayMethodPGDana(){
        WebElement dana = driver.findElement(By.xpath("(//input[@id='radio_instantPayment_0'])[2]"));
        dana.click();
    }
}