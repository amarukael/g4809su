package pages.solusipayweb.globalpages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SolusipayWebPages {
    static WebDriver driver;
    static WebDriverWait wait;

    public SolusipayWebPages(WebDriver driver) {
        SolusipayWebPages.driver = driver;
        SolusipayWebPages.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
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

    public void vrfyElementSolpayWebPage() {
        WebElement solpayWebPage = driver.findElement(By.xpath("//div[text()='PPOB']"));
        if (solpayWebPage.isDisplayed()) {
            System.out.println("I'm now on Solusipay Web Page");
        }
    }

    public void vrfyDetailPembayaranPopUp() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Detail Pembayaran')]")));
        WebElement detailPembayaranPopUp = driver.findElement(By.xpath("//*[contains(text(), 'Detail Pembayaran')]"));
        if (detailPembayaranPopUp.isDisplayed()) {
            System.out.println("Pop Up Showed");
        } else {
            System.out.println("Timeout Pop Up");
        }
    }

    public void vrfyElementPaymentSuccessPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='Pembayaran Berhasil!']")));
        WebElement successPaymentPage = driver.findElement(By.xpath("//*[text()='Pembayaran Berhasil!']"));
        if (successPaymentPage.isDisplayed()) {
            System.out.println("I'm now on Success Payment Page");
        }
    }

    public void vrfyElementPaymentProcessPage() {
        WebElement processPaymentPage = driver.findElement(By.xpath("//h1[text()='Pembayaran Diproses!']"));
        if (processPaymentPage.isDisplayed()) {
            System.out.println("I'm now on Process Payment Page");
        }
    }

    public void vrfyDetailTransaction() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='Detail Transaksi']")));
        WebElement detailTransactionPlnPostPage = driver.findElement(By.xpath("//*[text()='Detail Transaksi']"));
        if (detailTransactionPlnPostPage.isDisplayed()) {
            System.out.println("I'm now on Detail Transaction Page");
        }
    }

    // start -- Button Category //
    public void ctgAll() {
        WebElement btnCtgAll = driver.findElement(By.id("btn_type_all"));
        btnCtgAll.click();
    }

    public void ctgTagihan() {
        WebElement btnCtgTagihan = driver.findElement(By.id("btn_type_1"));
        btnCtgTagihan.click();
    }

    public void ctgTopUp() {
        WebElement btnCtgTopUp = driver.findElement(By.id("btn_type_2"));
        btnCtgTopUp.click();
    }

    public void ctgVoucher() {
        WebElement btnCtgVoucher = driver.findElement(By.id("btn_type_3"));
        btnCtgVoucher.click();
    }

    public void ctgTransfer() {
        WebElement btnCtgTransfer = driver.findElement(By.id("btn_type_4"));
        btnCtgTransfer.click();
    }

    public void ctgLainnya() {
        WebElement btnCtgLainnya = driver.findElement(By.id("btn_type_other"));
        btnCtgLainnya.click();
    }
    // end -- Button Category //

    // start -- Button Product PPOB //
    public void hitBtnMenuPlnPost() {
        WebElement btnMenuPlnPost = driver.findElement(By.id("btn_menu_tagihanlistrik_page"));
        btnMenuPlnPost.click();
    }

    public void hitBtnMenuBpjs() {
        WebElement btnMenuBpjs = driver.findElement(By.id("btn_menu_bpjs_page"));
        btnMenuBpjs.click();
    }

    public void hitBtnMenuMultifinance() {
        WebElement btnMenuMultifinance = driver.findElement(By.id("btn_menu_multifinance_page"));
        btnMenuMultifinance.click();
    }

    public void hitBtnMenuPdam() {
        WebElement btnMenuPdam = driver.findElement(By.id("btn_menu_pdam_page"));
        btnMenuPdam.click();
    }

    public void hitBtnMenuPascabayar() {
        WebElement btnMenuPascabayar = driver.findElement(By.id("btn_menu_postpaid_page"));
        btnMenuPascabayar.click();
    }

    public void hitBtnMenuInsurance() {
        WebElement btnMenuInsurance = driver.findElement(By.id("btn_menu_insurance_page"));
        btnMenuInsurance.click();
    }

    public void hitBtnMenuTv() {
        WebElement btnMenuTv = driver.findElement(By.id("btn_menu_tv_page"));
        btnMenuTv.click();
    }

    public void hitBtnMenuTelkom() {
        WebElement btnMenuTelkom = driver.findElement(By.id("btn_menu_telkom"));
        btnMenuTelkom.click();
    }

    public void hitBtnMenuPbb() {
        WebElement btnMenuPbb = driver.findElement(By.id("btn_menu_pbb_page"));
        btnMenuPbb.click();
    }

    public void hitBtnMenuIpl() {
        WebElement btnMenuIpl = driver.findElement(By.id("btn_menu_ipl_page"));
        btnMenuIpl.click();
    }

    public void hitBtnMenuBPendidikan() {
        WebElement btnMenuPdkn = driver.findElement(By.id("btn_menu_biayapendidikan_page"));
        btnMenuPdkn.click();
    }

    public void hitBtnMenuPlnPre() {
        WebElement btnMenuPlnPre = driver.findElement(By.id("btn_menu_tokenlistrik_page"));
        btnMenuPlnPre.click();
    }

    public void hitBtnMenuPulsa() {
        WebElement btnMenuPulsa = driver.findElement(By.id("btn_menu_pulsa"));
        btnMenuPulsa.click();
    }

    public void hitBtnMenuPktData() {
        WebElement btnMenuPktData = driver.findElement(By.id("btn_menu_paketdata"));
        btnMenuPktData.click();
    }

    public void hitBtnMenuEW() {
        WebElement btnMenuGame = driver.findElement(By.id("btn_menu_ewallet_page"));
        btnMenuGame.click();
    }

    public void hitBtnMenuVG() {
        WebElement btnMenuGame = driver.findElement(By.id("btn_menu_game"));
        btnMenuGame.click();
    }

    public void hitBtnMenuVB() {
        WebElement btnMenuVBelanja = driver.findElement(By.id("btn_menu_voucherbelajar_page"));
        btnMenuVBelanja.click();
    }

    public void hitBtnMenuTrfUang() {
        WebElement btnMenuTrfUang = driver.findElement(By.id("btn_menu_transferuang_page"));
        btnMenuTrfUang.click();
    }

    public void hitBtnMenuAtrOBA() {
        WebElement btnOba = driver.findElement(By.id("biaya_penanganan_btn"));
        btnOba.click();
    }
    // end -- Button Product PPOB //

    public void scrollToBottomPage() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        long previousHeight = (Long) js.executeScript("return document.body.scrollHeight");
        while (true) {
            js.executeScript("window.scrollBy(0, 1000);");
            long newHeight = (Long) js.executeScript("return document.body.scrollHeight");
            if (newHeight == previousHeight) {
                break;
            }

            previousHeight = newHeight;
        }
    }

    public void scrollToMiddlePage() {
        ((JavascriptExecutor) driver)
                .executeScript("window.scrollTo(0, (document.body.scrollHeight - window.innerHeight) / 3)");
    }

    public void hitBackButton() {
        WebElement backBtn = driver.findElement(By.xpath("//div[@class='mainTxt']//a[1]"));
        backBtn.click();
    }

    public void hitBuyButton() {
        WebElement buyButton = driver.findElement(By.id("btn_payment"));
        buyButton.click();
    }

    public void hitDetailPaymentButton() {
        WebElement detailPaymentButton = driver.findElement(By.id("btn_detail_payment"));
        detailPaymentButton.click();
    }

    public void btnPaymentSuccess() {
        WebElement btnPaymentSuccess = driver.findElement(By.id("btn_payment_success"));
        btnPaymentSuccess.click();
    }

    public void btnInvoice() {
        WebElement btnInvoice = driver.findElement(By.id("invoiceBtn"));
        btnInvoice.click();
    }

    public void hitBtnKembali() {
        WebElement btnKembali = driver.findElement(By.xpath("//button[text()='Kembali']"));
        btnKembali.click();
    }

    public void hitBtnTransaction() {
        WebElement btnTransaction = driver.findElement(By.id("btn_transaksi"));
        btnTransaction.click();
    }

    public void listTransactionCard() {
        WebElement listCard = driver.findElement(By.xpath("(//button[@id='btn_transaction_card'])[1]"));
        listCard.click();
    }

    // Button Filter for Denom Product
    public void hitBtnFilter() {
        WebElement btnFilter = driver.findElement(By.xpath("//h1[text()='Filter']"));
        btnFilter.click();
    }

    public void selectFilter(String condition, String priceMin, String priceMax, String filterRange) {
        if (condition.equals("select filter") && filterRange.equals("Rp 5rb - Rp 50rb")) {
            WebElement filterRange1 = driver.findElement(By.xpath("//button[text()='Rp 5rb - Rp 50rb']"));
            filterRange1.click();
        } else if (condition.equals("select filter") && filterRange.equals("Rp 50rb - Rp 200rb")) {
            WebElement filterRange2 = driver.findElement(By.xpath("//button[text()='Rp 50rb - Rp 200rb']"));
            filterRange2.click();
        } else if (condition.equals("select filter") && filterRange.equals("Rp 200rb - Rp 500rb")) {
            WebElement filterRange3 = driver.findElement(By.xpath("//button[text()='Rp 200rb - Rp 500rb']"));
            filterRange3.click();
        } else if (condition.equals("fill filter")) {
            WebElement fieldMin = driver.findElement(By.id("field_filter_min"));
            fieldMin.click();
            typeWithDelay(fieldMin, priceMin);

            WebElement fieldMax = driver.findElement(By.id("field_filter_max"));
            fieldMax.click();
            typeWithDelay(fieldMax, priceMax);
        }
    }

    public void saveFilter() {
        WebElement saveFilter = driver.findElement(By.id("btn_filter_save"));
        saveFilter.click();
    }

    public void chooseDenom(String arg0) {
        WebElement btnDenom = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath(
                        "//div[contains(@class, 'MuiGrid-root') and contains(@class, 'MuiGrid-item') and .//p[@id='productName' and contains(text(), 'PLN Prepaid Rp "
                                + arg0 + "')]]")));
        btnDenom.click();
    }

    public void chooseDenomProuct(String arg0, String arg1) {
        WebElement btnDenom = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath(
                        "//div[contains(@class, 'MuiGrid-root') and contains(@class, 'MuiGrid-item') and .//p[@id='productName' and contains(text(), '"
                                + arg1 + " Rp "
                                + arg0 + "')]]")));
        btnDenom.click();
    }

    public void nextCatKeuntungan() {
        WebElement button = driver.findElement(By.xpath("//button[.//svg[@data-testid='ArrowForwardIosIcon']]"));
        button.click();
    }

    public void btnCatKeuntungan(String args0) {
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(1000));
        try {
            WebElement button = driver
                    .findElement(By.xpath(
                            "//button[contains(@class,'MuiButtonBase-root MuiIconButton-root')]"));
            int test = 0;
            while (true) {
                if (test == 1) {
                    button = driver
                            .findElement(By.xpath(
                                    "//button[contains(@class,'MuiButtonBase-root MuiIconButton-root')]/following-sibling::button[1]"));
                }
                try {
                    test = 1;
                    WebElement liElement = wait
                            .until(ExpectedConditions
                                    .visibilityOfElementLocated(By.xpath("//li[@aria-hidden='false']")));
                    WebElement targetButton = liElement.findElement(By.xpath(".//button[text()='" + args0 + "']"));
                    System.out.println("Found the button with text 'Bpjs'");
                    targetButton.click();
                    break;
                } catch (Exception e) {
                    System.out.println("ehe");
                }
                button.click();
                Thread.sleep(2000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // End Filter

    public void scrollToTopUp() {
        WebElement element = driver.findElement(By.xpath("//h1[normalize-space(text())='Top-Up']"));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Scroll the element into view
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);

    }

    public void hitBtnFilterTransaction(String s) {
        try {
            WebElement element = driver.findElement(By.id("btn_filter_product"));
            element.click();
            WebElement scrollableDiv = driver.findElement(By.className("drawerContent"));
            WebElement targetElement = scrollableDiv
                    .findElement(By.xpath("//span[normalize-space(text())='" + s + "']"));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", targetElement);
            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void hitValueFilterTransaction(String s) {
        WebElement scrollableDiv = driver.findElement(By.className("drawerContent"));
        scrollableDiv.findElement(By.xpath("//span[normalize-space(text())='" + s + "']")).click();
    }
}