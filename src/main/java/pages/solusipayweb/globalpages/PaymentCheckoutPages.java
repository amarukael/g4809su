package pages.solusipayweb.globalpages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PaymentCheckoutPages {
    static WebDriver driver;
    static WebDriverWait wait;

    public PaymentCheckoutPages(WebDriver driver) {
        PaymentCheckoutPages.driver = driver;
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

    public void paymentMethod(String value) {
        WebElement paymentMethod = driver.findElement(By.xpath("//h6[text()='" + value + "']"));
        paymentMethod.click();
    }

    public void hitBtnBayar() {
        WebElement btnBayar = driver.findElement(By.xpath("//button[text()='Bayar']"));
        btnBayar.click();
    }

    public void vrfyAfterChoosePaymentMethod() {
        WebElement tittle = driver.findElement(By.xpath("//p[text()='Bayar ke']"));
        if (tittle.isDisplayed()) {
            System.out.println("Success choose a payment method");
        }
    }

    public String hitBtnCopyVAToken() {
        WebElement btnCopyVAToken = driver.findElement(By.xpath("(//button[text()='Salin'])[1]"));
        btnCopyVAToken.isDisplayed();
        String vaNumber = driver.findElement(By.xpath("//div[@class='MuiBox-root css-iqd6qp']//p[1]")).getText();
        return vaNumber;
    }

    public void hitBtnCopyTotalAmount() {
        WebElement btnCopyTotalAmount = driver.findElement(By.xpath("(//button[text()='Salin'])[2]"));
        btnCopyTotalAmount.click();
    }

}
