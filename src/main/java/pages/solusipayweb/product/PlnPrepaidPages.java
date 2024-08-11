package pages.solusipayweb.product;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PlnPrepaidPages {
    static WebDriver driver;
    static WebDriverWait wait;
    public PlnPrepaidPages(WebDriver driver) {
        PlnPrepaidPages.driver = driver;
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

    public void vrfyElementPlnPrePage(){
        WebElement solpayWebPage = driver.findElement(By.xpath("//div[text()='Token Listrik']"));
        if (solpayWebPage.isDisplayed()){
            System.out.println("I'm now on Pln Prepaid Page");
        }
    }

    public void inputIdPelanggan(String idpel){
        WebElement fieldIdPel = driver.findElement(By.id("field_input"));
        fieldIdPel.click();
        typeWithDelay(fieldIdPel, idpel);
    }

    // start -- Denom PLN Prepaid //

    public void denomPln20k(){
        WebElement pln20k = driver.findElement(By.xpath("//p[text()='PLN Prepaid Rp 20.000']"));
        pln20k.click();
    }

    // end -- Denom PLN Prepaid //


    public void denomPlnPre(String denom){
        WebElement denomPlnPre = driver.findElement(By.xpath("//p[text()='"+denom+"']"));
        denomPlnPre.click();
    }
    public void btnPayment(){
        WebElement btnPayment = driver.findElement(By.id("btn_payment"));
        btnPayment.click();
    }
}
