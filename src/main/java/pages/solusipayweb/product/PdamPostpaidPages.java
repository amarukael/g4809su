package pages.solusipayweb.product;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PdamPostpaidPages {
    static WebDriver driver;
    static WebDriverWait wait;

    public PdamPostpaidPages(WebDriver driver){
        PdamPostpaidPages.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver,this);
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

    public void verifyPDAMPage(){
        WebElement pdamPage = driver.findElement(By.xpath("//div[text()='PDAM']"));
        if(pdamPage.isDisplayed()){
            System.out.println("I'm Now on Solusipay Web PDAM");
        }
    }

    public void choosePDAMWilayahPenyedia(String pdam){
        WebElement fieldPdam = driver.findElement(By.xpath("(//label[text()='Wilayah Penyedia']/following::input)[1]"));
        fieldPdam.click();
        typeWithDelay(fieldPdam, pdam);
        Actions keyDown = new Actions(driver);
        keyDown.sendKeys(Keys.chord(Keys.DOWN)).perform();
        keyDown.sendKeys(Keys.chord(Keys.ENTER)).perform();
    }

    public void fillNoPelanggan(String customerId){
        WebElement noPlenggan = driver.findElement(By.id("field_input"));
        noPlenggan.click();
        typeWithDelay(noPlenggan, customerId);
    }

    public void errMsgCustId(){
        WebElement errMsgCustId = driver.findElement(By.id("field_input-helper-text"));
        errMsgCustId.isDisplayed();
    }
    public void errMsgSnackBarWrongCustId(){
        WebElement errMsgSnackBar = driver.findElement(By.xpath("//div[text()='ERROR - No pelanggan salah']"));
        errMsgSnackBar.isDisplayed();
    }

    public void errMsgSnackBarCustIdHasBeenPay(){
        WebElement errMsgSnackBar = driver.findElement(By.xpath("//div[text()='ERROR - Tagihan sudah dibayar']"));
        errMsgSnackBar.isDisplayed();
    }
}
