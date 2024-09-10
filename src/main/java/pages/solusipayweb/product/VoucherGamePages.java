package pages.solusipayweb.product;

import org.openqa.selenium.By;
import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class VoucherGamePages {
    static WebDriver driver;
    static WebDriverWait wait;

    public VoucherGamePages(WebDriver driver){
        VoucherGamePages.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void  vrfyVoucherGamePage(){
        WebElement searchBar = driver.findElement(By.id("searchInputField"));
        WebElement vG = driver.findElement(By.xpath("//h5[text()='Voucher Games']"));
        WebElement tG = driver.findElement(By.xpath("//h5[text()='Top Up Games']"));
        if (searchBar.isDisplayed()){
            System.out.println("I can see search bar game product");
        } else if (vG.isDisplayed()){
            System.out.println("I can see Voucher Game list");
        } else if (tG.isDisplayed()){
            System.out.println("I can see Top Up Game list");
        } else {
            System.out.println("Pastikan Partner yang digunakan sudah benar");
        }
    }

    public void fillSearchBar(String searchInput){
        WebElement searchBar = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("searchInputField")));
        searchBar.click();
        typeWithDelay(searchBar, searchInput);
        searchBar.sendKeys(Keys.ENTER);
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

    public void btnAll(){
        WebElement all = driver.findElement(By.id("btn_type_all"));
        all.click();
    }

    public void btnCtgVoucher(){
        WebElement voucher = driver.findElement(By.id("btn_type_VOUCHER GAMES"));
        voucher.click();
    }

    public void btnCtgTopUp(){
        WebElement topUp = driver.findElement(By.id("btn_type_TOP UP GAMES"));
        topUp.click();
    }

    public void clickProductTopUp(String game){
//        WebElement pilihProduct = driver.findElement(By.xpath("//p[text()='"+game+"']"));
        WebElement pilihProduct = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[text()='"+game+"']")));
        pilihProduct.click();
    }

    public void clickProductVoucher(String game){
//        WebElement pilihProduct = driver.findElement(By.xpath("//p[text()='"+game+" ']"));
        WebElement pilihProduct = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[text()='"+game+"']")));
        pilihProduct.click();
    }

    public void clickProductOnProductDetail(){
        WebElement prdList = driver.findElement(By.id("basic-select"));
        prdList.click();
    }
    public void chooseOneOfTheProduct(String product){
        WebElement productList = driver.findElement(By.xpath("//li[@data-value='"+product+"']"));
        productList.click();
    }

    public void clickDropdownZoneId(){
        WebElement zoneId = driver.findElement(By.xpath("(//div[@id='basic-select'])[2]"));
        zoneId.click();
    }

    public void clickValueZoneId(String server){
        WebElement zoneId = driver.findElement(By.xpath("//li[@data-value='"+server+"']"));
        zoneId.click();
    }

    public void productDetailTopUpGameUserId_zoneId(String game){
        WebElement navBar = driver.findElement(By.xpath("//p[text()='Top Up Games']"));
        if (navBar.isDisplayed()){
            System.out.println("Navbar TopUp Game appears");
        }
        WebElement nameProduct = driver.findElement(By.xpath("//h5[text()='"+game+"']"));
        if (nameProduct.isDisplayed()){
            System.out.println("Name Product Game appears");
        }
        WebElement ket = driver.findElement(By.xpath("//p[text()='Keterangan']"));
        if (ket.isDisplayed()){
            System.out.println("Keterangan game appears");
        }
        WebElement custId = driver.findElement(By.id("userId"));
        if (custId.isDisplayed()){
            System.out.println("Field Customer Id appears");
        }
        WebElement zoneId = driver.findElement(By.id("zoneId"));
        if (zoneId.isDisplayed()){
            System.out.println("Field Zone id appears");
        }
    }

    public void productDetailTopUpGameUserId(String game){
        WebElement navBar = driver.findElement(By.xpath("//p[text()='Top Up Games']"));
        if (navBar.isDisplayed()){
            System.out.println("Navbar TopUp Game appears");
        }
        WebElement nameProduct = driver.findElement(By.xpath("//h5[text()='"+game+"']"));
        if (nameProduct.isDisplayed()){
            System.out.println("Name Product Game appears");
        }
        WebElement ket = driver.findElement(By.xpath("//p[text()='Keterangan']"));
        if (ket.isDisplayed()){
            System.out.println("Keterangan game appears");
        }
        WebElement custId = driver.findElement(By.id("userId"));
        if (custId.isDisplayed()){
            System.out.println("Field Customer Id appears");
        }
    }

    public void productDetailTopUpGameUserId_dropdown(String game){
        WebElement navBar = driver.findElement(By.xpath("//p[text()='Top Up Games']"));
        if (navBar.isDisplayed()){
            System.out.println("Navbar TopUp Game appears");
        }
        WebElement nameProduct = driver.findElement(By.xpath("//h5[text()='"+game+"']"));
        if (nameProduct.isDisplayed()){
            System.out.println("Name Product Game appears");
        }
        WebElement ket = driver.findElement(By.xpath("//p[text()='Keterangan']"));
        if (ket.isDisplayed()){
            System.out.println("Keterangan game appears");
        }
        WebElement custId = driver.findElement(By.id("userId"));
        if (custId.isDisplayed()){
            System.out.println("Field Customer Id appears");
        }
        WebElement zoneId = driver.findElement(By.xpath("(//div[@id='basic-select'])[2]"));
        if (zoneId.isDisplayed()){
            System.out.println("Field Zone id appears");
        }
    }

    public void productDetailVoucherGame(String game){
        WebElement navBar = driver.findElement(By.xpath("//p[text()='Voucher Games']"));
        if (navBar.isDisplayed()){
            System.out.println("Navbar Voucher Game appears");
        }
        WebElement nameProduct = driver.findElement(By.xpath("//h5[text()='"+game+"']"));
        if (nameProduct.isDisplayed()){
            System.out.println("Name product game appears");
        }
        WebElement productList = driver.findElement(By.xpath("//label[text()='Pilih Produk']"));
        if (productList.isDisplayed()){
            System.out.println("Product list appears");
        }
    }

    public void fillCustId(String customerId){
        WebElement fieldCustId = driver.findElement(By.id("userId"));
        fieldCustId.click();
        typeWithDelay(fieldCustId,customerId);
    }

    public void fillZoneId(String zoneId){
        WebElement fieldZoneId = driver.findElement(By.id("zoneId"));
        fieldZoneId.click();
        typeWithDelay(fieldZoneId, zoneId);
    }

    public void errUserId(){
        WebElement errUsrId = driver.findElement(By.id("userId-helper-text"));
        if (errUsrId.isDisplayed()){
            System.out.println("Error Message User id appear");
        }
    }

    public void errZoneId(){
        WebElement errZoneId = driver.findElement(By.id("zoneId-helper-text"));
        if (errZoneId.isDisplayed()){
            System.out.println("Error Message Zone Id appear");
        }
    }

    public void snackBarErr(){
        WebElement snackBar = driver.findElement(By.xpath("//div[text()='ID Pelanggan yang Anda masukkan salah, Mohon diteliti kembali']"));
        if(snackBar.isDisplayed()){
            System.out.println("Snackbar error message appear");
        }
    }

    public void fillUsername(String username){
        WebElement userName = driver.findElement(By.id("nickname"));
        userName.click();
        typeWithDelay(userName, username);
    }
}
