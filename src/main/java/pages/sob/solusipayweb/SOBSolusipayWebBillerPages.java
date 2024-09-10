package pages.sob.solusipayweb;

import helper.SOBHelper;
import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.interactions.Actions;
import java.time.Duration;

public class SOBSolusipayWebBillerPages {
    static WebDriver driver;
    static WebDriverWait wait;
    SOBHelper sobHelper = new SOBHelper();

    public SOBSolusipayWebBillerPages(WebDriver driver){
        SOBSolusipayWebBillerPages.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public String getLblBiller(){
        WebElement lblBiller = driver.findElement(By.xpath("//h5[text()='Biller List']"));
        return lblBiller.getText();
    }

    public void hitBtnFilter() {
        WebElement btnFilter = driver.findElement(By.xpath("//button[text()='FILTER']"));
        btnFilter.click();
    }

    public void hitBtnAddBiller() {
        WebElement btnFilter = driver.findElement(By.xpath("//button[text()='ADD BILLER']"));
        btnFilter.click();
    }

    public void hitBtnADD(){
        WebElement btnFilter = driver.findElement(By.xpath("//button[text()='ADD']"));
        btnFilter.click();
    }

    public void hitBtnChangStatus(){
        WebElement btnChangeStatus =  driver.findElement(By.xpath("(//div[@class='MuiBox-root css-0']//span)[3]"));
        btnChangeStatus.click();
    }

    public void hitBtnConfirmStatusYes(){
        WebElement btnYes = driver.findElement(By.xpath("//button[text()='YES']"));
        btnYes.click();
    }
    public void hitBtnConfirmStatusNo(){
        WebElement btnNo = driver.findElement(By.xpath("//button[text()='NO']"));
        btnNo.click();
    }

    public void hitIconEdit(){
        WebElement iconEdit = driver.findElement(By.xpath("//div[contains(@class,'MuiDataGrid-cell--withRenderer MuiDataGrid-cell')]//button[1]"));
        iconEdit.click();
    }

    public void hitBtnEditOnEditBiller(){
        WebElement btnEdit = driver.findElement(By.xpath("//button[text()='EDIT']"));
        btnEdit.click();
    }

    public void fillBillerIdOnBiller(String billerId){
        WebElement fieldBillerId = driver.findElement(By.name("billerId"));
        fieldBillerId.click();
        typeWithDelay(fieldBillerId, billerId);
        sobHelper.delay(800);
    }

    public void fillBillerName(String billerName){
        WebElement fieldBillerName= driver.findElement(By.name("billerName"));
        fieldBillerName.click();
        typeWithDelay(fieldBillerName, billerName);
        sobHelper.delay(800);
    }

    public void editBillerName(String billerName){
        WebElement editBillerName= driver.findElement(By.name("billerName"));
        editBillerName.click();
        editBillerName.sendKeys(Keys.chord(Keys.CONTROL,"a"),Keys.DELETE);
        editBillerName.sendKeys(billerName);
        sobHelper.delay(800);
    }

    public void displayListBoxType() {
        WebElement listBoxStatus = driver.findElement(By.id("mui-component-select-typeId"));
        listBoxStatus.click();
        sobHelper.delay(800);
    }

    public void fillTypeBiller(String status) {
        Actions keyDown = new Actions(driver);
        keyDown.sendKeys(Keys.chord(Keys.DOWN)).perform();
        if (status.equals("Tagihan|Top-up")) {
            keyDown.sendKeys(Keys.chord(Keys.UP)).perform();
        } else if (status.equals("Tagihan")) {
            keyDown.sendKeys(Keys.chord(Keys.DOWN)).perform();
        } else if (status.equals("Top-Up")) {
            keyDown.sendKeys(Keys.chord(Keys.DOWN)).perform();
            keyDown.sendKeys(Keys.chord(Keys.DOWN)).perform();
        } else if (status.equals("Voucher")){
            keyDown.sendKeys(Keys.chord(Keys.DOWN)).perform();
            keyDown.sendKeys(Keys.chord(Keys.DOWN)).perform();
            keyDown.sendKeys(Keys.chord(Keys.DOWN)).perform();
        } else if (status.equals("Transfer")){
            keyDown.sendKeys(Keys.chord(Keys.DOWN)).perform();
            keyDown.sendKeys(Keys.chord(Keys.DOWN)).perform();
            keyDown.sendKeys(Keys.chord(Keys.DOWN)).perform();
            keyDown.sendKeys(Keys.chord(Keys.DOWN)).perform();
        }
        keyDown.sendKeys(Keys.chord(Keys.ENTER)).perform();
        sobHelper.delay(800);
    }

    public void displayListStatus() {
        WebElement listBoxStatus = driver.findElement(By.id("mui-component-select-isActive"));
        listBoxStatus.click();
        sobHelper.delay(800);
    }

    public void fillStatus(String status) {
        Actions keyDown = new Actions(driver);
        keyDown.sendKeys(Keys.chord(Keys.DOWN)).perform();
        if (status.equals("Active")) {
            keyDown.sendKeys(Keys.chord(Keys.UP)).perform();
        } else if (status.equals("Inactive")) {
            keyDown.sendKeys(Keys.chord(Keys.DOWN)).perform();
        }
        keyDown.sendKeys(Keys.chord(Keys.ENTER)).perform();
        sobHelper.delay(800);
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
