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

public class PascabayarPages {
    WebDriver driver;
    WebDriverWait wait;
    SolusipayWebPages solusipayWebPages;
    SolusipayWebHelper solpayWebHelper = new SolusipayWebHelper();

    public PascabayarPages(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }



    public void chooseProduct(Integer args0){
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElement(By.xpath("//input[@role='combobox']")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[@role='presentation' " +
                        "and @data-popper-placement='bottom']")));
        Actions keyDown = new Actions(driver);
        solpayWebHelper.delay(2000);
        for (int i = 0; i < args0; i++) {
            keyDown.sendKeys(Keys.chord(Keys.DOWN)).perform();
            solpayWebHelper.delay(500);
        }
        keyDown.sendKeys(Keys.chord(Keys.ENTER)).perform();
    }


    public void fillNumber(String arg0) {
        WebElement fieldNumber = driver.findElement(By.id("field_input"));
        fieldNumber.click();
        fieldNumber.sendKeys(arg0);
        solpayWebHelper.delay(800);
    }

    public void btnPayment(){
        WebElement btnPayment = driver.findElement(By.id("btn_payment"));
        btnPayment.click();
    }
}
