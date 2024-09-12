package pages.solusipayweb.globalpages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DetailPembayaranPages {
    static WebDriver driver;
    static WebDriverWait wait;

    public DetailPembayaranPages(WebDriver driver) {
        DetailPembayaranPages.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void vrfyDetailPembayaranTopUpGame() {
        WebElement topUpGame = driver.findElement(By.xpath("//h1[text()='TopUp Games']"));
        if (topUpGame.isDisplayed()) {
            System.out.println("Payment detail TopUp Game appears");
        }
    }

    public void vrfyDetailPembayaranVoucherGame() {
        WebElement topUp = driver.findElement(By.xpath("//h1[text()='VOUCHER GAMES']"));
        if (topUp.isDisplayed()) {
            System.out.println("Payment detail Voucher Game appears");
        }
    }

    public void vrfyDetailPembayaranPulsa() {
        WebElement pulsa = driver.findElement(By.xpath("//h1[text()='Pulsa']"));
        if (pulsa.isDisplayed()) {
            System.out.println("Detail pembayaran pulsa is display");
        }
        // if there is, you can add verify
    }

    public void vrfyDetailPembayaranPaketData() {
        WebElement paketData = driver.findElement(By.xpath("//h1[text()='Paket Data']"));
        if (paketData.isDisplayed()) {
            System.out.println("Detail pembayaran paket data is display");
        }
        // if there is, you can add verify
    }

    public void vrfyDetailPembayaranPLNPostpaid() {
        WebElement tagihanListrik = driver.findElement(By.xpath("//h1[text()='Tagihan Listrik']"));
        if (tagihanListrik.isDisplayed()) {
            System.out.println("Detail pembayaran tagihan listrik is display");
        }
    }

    public void vrfyDetailPembayaranPLNPrepaid() {
        WebElement tokenListrik = driver.findElement(By.xpath("//h1[text()='Token Listrik']"));
        if (tokenListrik.isDisplayed()) {
            System.out.println("Detail pembayaran token listrik is display");
        }
    }

    public void vrfyDetailPembayaranPDAM() {
        WebElement pdam = driver.findElement(By.xpath("//h1[text()='PDAM']"));
        pdam.isDisplayed();
    }

    public boolean vrfyDetailPembayaranTagihanListrik() {
        try {
            WebElement pdam = driver.findElement(By.xpath("//h1[text()='Tagihan Listrik']"));
            return pdam.isDisplayed();

        } catch (Exception e) {
            return false;
        }
    }

    public void hitBtnPilihPembayaran() {
        WebElement btnPilihPembayaran = driver.findElement(By.id("btn_select_payment_method"));
        btnPilihPembayaran.click();
    }

    public boolean discountAdmin() {
        try {
            WebElement titleBiayaAdmin = driver.findElement(By.xpath("//h1[text()='Diskon Admin']"));
            titleBiayaAdmin.isDisplayed();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean discountNominal() {
        try {
            WebElement titleDiscount = driver.findElement(By.xpath("//h1[text()='Diskon']"));
            titleDiscount.isDisplayed();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void vrfyDetailPembayaranTelkom() {
        WebElement telkom = driver.findElement(By.xpath("//h1[text()='Telkom']"));
        telkom.isDisplayed();
    }
}
