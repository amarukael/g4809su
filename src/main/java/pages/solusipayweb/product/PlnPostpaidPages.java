package pages.solusipayweb.product;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class PlnPostpaidPages {
    static WebDriver driver;
    static WebDriverWait wait;

    public PlnPostpaidPages(WebDriver driver) {
        PlnPostpaidPages.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    // start -- Verify Page //
    public void vrfyElementPlnPostPage(){
        WebElement solpayWebPage = driver.findElement(By.xpath("//div[text()='Tagihan Listrik']"));
        if (solpayWebPage.isDisplayed()){
            System.out.println("I'm now on Pln Postpaid Page");
        }
    }

    public void vrfyDetailPembayaranPopUp(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Detail Pembayaran')]")));
        WebElement detailPembayaranPopUp = driver.findElement(By.xpath("//*[contains(text(), 'Detail Pembayaran')]"));
        if(detailPembayaranPopUp.isDisplayed()){
            System.out.println("Pop Up Showed");
        }else {
            System.out.println("Timeout Pop Up");
        }
    }

    public void vrfyElementPaymentSuccessPage(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='Pembayaran Berhasil!']")));
        WebElement successPaymentPage = driver.findElement(By.xpath("//*[text()='Pembayaran Berhasil!']"));
        if (successPaymentPage.isDisplayed()){
            System.out.println("I'm now on Success Payment Page");
        }
    }

    public void vrfyDetailTransactionPLNPost(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='Detail Transaksi']")));
        WebElement detailTransactionPlnPostPage = driver.findElement(By.xpath("//*[text()='Detail Transaksi']"));
        if (detailTransactionPlnPostPage.isDisplayed()){
            System.out.println("I'm now on Detail Transaction PLN Postpaid Page");
        }
    }
    // end -- Verify Page //



    public void inputIdPelanggan(String idpel){
        WebElement fieldIdPel = driver.findElement(By.id("field_input"));
        fieldIdPel.click();
        typeWithDelay(fieldIdPel, idpel);
    }

    public void btnSubmit(){
        WebElement btnSubmitPln = driver.findElement(By.id("btn_submit_pln"));
        btnSubmitPln.click();
    }

    public void btnDetailPayment(){
        WebElement btnDetailPayment = driver.findElement(By.id("btn_detail_payment"));
        btnDetailPayment.click();
    }

    public void btnPaymentMethod(){
        WebElement btnPaymentMethod = driver.findElement(By.id("btn_select_payment_method"));
        btnPaymentMethod.click();
    }

    public void btnPayment(){
        WebElement btnPayment = driver.findElement(By.id("btn_payment"));
        btnPayment.click();
    }

    public void inputPasscodeVirgo() throws InterruptedException {
        WebElement passcode = driver.findElement(By.id("step-auth-input-box-1"));
        Actions performAct = new Actions(driver);
        performAct.sendKeys(passcode, "1").build().perform();
        performAct.sendKeys(passcode, "4").build().perform();
        performAct.sendKeys(passcode, "7").build().perform();
        performAct.sendKeys(passcode, "8").build().perform();
        performAct.sendKeys(passcode, "5").build().perform();
        performAct.sendKeys(passcode, "2").build().perform();
        Thread.sleep(2000);
        WebElement btnRedirect = driver.findElement(By.id("redirect"));
        btnRedirect.click();
    }

    public void btnPaymentSuccess(){
        WebElement  btnPaymentSuccess = driver.findElement(By.id("btn_payment_success"));
        btnPaymentSuccess.click();
    }

    public void btnInvoice(){
        WebElement btnInvoice = driver.findElement(By.id("invoiceBtn"));
        btnInvoice.click();
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


}
