package pages.solusipayweb.product;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import helper.SolusipayWebHelper;
import pages.solusipayweb.globalpages.SolusipayWebPages;

public class AsuransiPages {
    WebDriver driver;
    WebDriverWait wait;
    SolusipayWebPages solusipayWebPages;
    SolusipayWebHelper solpayWebHelper = new SolusipayWebHelper();

    public AsuransiPages(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.solusipayWebPages = new SolusipayWebPages(driver);
        PageFactory.initElements(driver, this);
    }

    public void vrfyAsuransiPage() {
        WebElement asuransi = driver.findElement(By.xpath("//div[normalize-space(text())='Asuransi']"));
        if (asuransi.isDisplayed()) {
            System.out.println("I can see Text Page E-Wallet");
        } else {
            System.out.println("Pastikan Partner yang digunakan sudah benar");
        }
    }

    public void chooseProduct(String args0) {
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
}
