package pages.sob;

import helper.SOBHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SOBLoginPages {
    WebDriver driver;

    public SOBLoginPages(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = ":R4alat1b36:")
    WebElement username;
    @FindBy(id = "password")
    WebElement password;
    @FindBy(css = ".MuiButton-root")
    WebElement submit;

    public String getTextLoginPage() {
        WebElement textWelcome = driver.findElement(
                By.xpath("//p[text()='Develop your business with IDS']"));
        return textWelcome.getText();
    }

    public void setUsername(String Username) {
        typeWithDelay(username, Username);
    }

    public void setPassword(String Password) {
        typeWithDelay(password, Password);
    }

    public void fillUsername(String user) {
        WebElement fUsername = driver.findElement(
                By.xpath("(//input[contains(@class,'MuiInputBase-input MuiOutlinedInput-input')])[1]"));
        fUsername.click();
        fUsername.sendKeys(user);
    }

    public void fillPassword(String pass) {
        WebElement fPassword = driver.findElement(
                By.xpath("(//input[contains(@class,'MuiInputBase-input MuiOutlinedInput-input')])[2]"));
        fPassword.click();
        fPassword.sendKeys(pass);
    }

    public void pressSubmit() {
        Actions builder = new Actions(driver);
        builder.moveToElement(submit).click().perform();
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
