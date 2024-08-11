package pages.sob.solusipayweb;

import helper.SOBHelper;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


public class SOBSolusipayWebPartnerSettingPages {
    WebDriver driver;
    WebDriverWait wait;
    SOBHelper sobHelper = new SOBHelper();

    public SOBSolusipayWebPartnerSettingPages(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void hitBtnFilter() {
        WebElement btnFilter = driver.findElement(By.xpath("//button[text()='FILTER']"));
        btnFilter.click();
    }

    public void hitBtnAddPartner (){
        WebElement btnAddPartner = driver.findElement(By.xpath("//button[text()='ADD PARTNER']"));
        btnAddPartner.click();
    }

    public void hitBtnChangeData(){
        WebElement btnChangeData = driver.findElement(By.xpath("//button[text()='Change Data']"));
        btnChangeData.click();
    }

    public void hitBtnSaveData(){
        WebElement btnSaveData = driver.findElement(By.xpath("//button[text()='SAVE DATA']"));
        btnSaveData.click();
    }

    public void hitBtnAddBiller(){
        WebElement btnAddBiller = driver.findElement(By.xpath("//button[text()='Add Biller']"));
        btnAddBiller.click();
    }

    public void hitBtnChangeStatus(){
        WebElement element = driver.findElement(By.xpath("//input[contains(@class,'PrivateSwitchBase-input MuiSwitch-input')]"));
        element.click();
    }

    public void hitBtnYes(){
        WebElement element = driver.findElement(By.xpath("//button[text()='YES']"));
        element.click();
    }

    public void hitBtnDelete(){
        WebElement element = driver.findElement(By.xpath("//div[@class='MuiBox-root css-0']//button[1]"));
        element.click();


    }

    public String getLblPartnerSetting() {
        WebElement lblTransaction = driver.findElement(
                By.xpath("//h6[text()='Partner List']"));
        return lblTransaction.getText();
    }
    
    public void fillPartnerIdField(String value) {
        WebElement partnerIdField = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("partnerId")));
        partnerIdField.click();
        typeWithDelay(partnerIdField, value);
        sobHelper.delay(800);
    }

    public void fillPartnerNameField(String partnerName) {
        WebElement partnerNameField = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//input[@name='description'])[1]")));
        partnerNameField.click();
        typeWithDelay(partnerNameField, partnerName);
        sobHelper.delay(800);
    }

    public void fillAdminFee(String value) {
        WebElement adminFeeField = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("adminFee")));
        adminFeeField.click();
        typeWithDelay(adminFeeField, value);
        sobHelper.delay(800);
    }

    public void changeSessionTimePartner(String sessionTime){
        WebElement stField = driver.findElement(By.name("sessionTime"));
        stField.click();
        stField.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        stField.sendKeys(sessionTime);
        sobHelper.delay(800);
    }

    public void fillSessionTime(String value) {
        WebElement sessionTimeField = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("sessionTime")));
        sessionTimeField.click();
        typeWithDelay(sessionTimeField, value);
        sobHelper.delay(800);
    }

    public void displayListStatus(){
        WebElement listStatus = driver.findElement(By.id("mui-component-select-isActive"));
        listStatus.click();
        sobHelper.delay(800);
    }

    public void fillStatusAddParter(String status){
        Actions keyDown = new Actions(driver);
        keyDown.sendKeys(Keys.chord(Keys.DOWN)).perform();
        if (status.equals("Inactive")) {
            keyDown.sendKeys(Keys.chord(Keys.UP)).perform();
        } else if (status.equals("Active")) {
            keyDown.sendKeys(Keys.chord(Keys.DOWN)).perform();
        }

        keyDown.sendKeys(Keys.chord(Keys.ENTER)).perform();
        sobHelper.delay(800);
    }

    public void fillBillerIdOnBillerSetting(String billerId){
        WebElement fieldBillerId = driver.findElement(By.name("billerId"));
        fieldBillerId.click();
        typeWithDelay(fieldBillerId, billerId);
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
