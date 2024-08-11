package pages.solusipayweb.product;

import helper.SolusipayWebHelper;
import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pages.solusipayweb.globalpages.SolusipayWebPages;

import java.time.Duration;

public class EWalletPages {
    WebDriver driver;
    WebDriverWait wait;
    SolusipayWebPages solusipayWebPages;
    SolusipayWebHelper solpayWebHelper = new SolusipayWebHelper();

    public EWalletPages(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.solusipayWebPages = new SolusipayWebPages(driver);
        PageFactory.initElements(driver, this);
    }

    public void  vrfyEWalletPage(){
        WebElement radioBtnTypeInput = driver.findElement(By.xpath("//div[@role='radiogroup']"));
        WebElement productEWallet = driver.findElement(By.xpath("(//label[text()='Produk']/following::input)[1]"));
        WebElement inputCustID = driver.findElement(By.id("field_input"));
        WebElement eW = driver.findElement(By.xpath("//div[text()='E-Wallet']"));
        if (eW.isDisplayed()){
            System.out.println("I can see Text Page E-Wallet");
        } else if (productEWallet.isDisplayed()) {
            System.out.println("I can see Dropdown Product EWallet");
        } else if (inputCustID.isDisplayed()){
            System.out.println("I can see Field Input Customer Id");
        } else if (radioBtnTypeInput.isDisplayed()){
            System.out.println("I can see Type Input Nominal");
        } else {
            System.out.println("Pastikan Partner yang digunakan sudah benar");
        }
    }

    public void chooseProduct(String args0){
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement fieldProduct = driver.findElement(By.xpath("//input[@role='combobox']"));
        fieldProduct.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[@role='presentation' " +
                        "and @data-popper-placement='bottom']")));
        fieldProduct.sendKeys(args0);
        solpayWebHelper.delay(500);
        Actions keyDown = new Actions(driver);
        keyDown.sendKeys(Keys.chord(Keys.DOWN)).perform();
        solpayWebHelper.delay(500);
        keyDown.sendKeys(Keys.chord(Keys.ENTER)).perform();
    }

    public void fillNumber(String arg0) {
        WebElement fieldNumber = driver.findElement(By.id("field_input"));
        fieldNumber.click();
        fieldNumber.sendKeys(arg0);
        solpayWebHelper.delay(800);
    }

    public void chooseDenom(String arg0) {
        int intArgs0 = Integer.parseInt(arg0);
        intArgs0 = intArgs0 - 1;
        WebElement btnDenom = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("productBtn_"+intArgs0)));
        btnDenom.click();
        solpayWebHelper.delay(500);
    }
}
