package pages.sob.disi;

import helper.SOBHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class SOBDisiPettyCashPages {
    WebDriver driver;
    WebDriverWait wait;
    SOBHelper sobHelper = new SOBHelper();
    DateFormat ft = new SimpleDateFormat("dd-MM-yyyy");

    public SOBDisiPettyCashPages(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public String getLblPettyCash() {
        WebElement pettyCashPage = driver.findElement(
                By.xpath("//p[text()='Petty Cash']"));
        return pettyCashPage.getText();
    }

    public boolean checkValueFromToDate() {
        boolean result = false;
        try {
            WebElement fromDate = driver.findElement(
                    By.xpath("(//input[contains(@class,'MuiInputBase-input MuiOutlinedInput-input')])[1]"));
            WebElement toDate = driver.findElement(
                    By.xpath("(//input[contains(@class,'MuiInputBase-input MuiOutlinedInput-input')])[2]"));

            Date firstDate = ft.parse(fromDate.getAttribute("value"));
            Date secondDate = ft.parse(toDate.getAttribute("value"));

            long diffInMillies = Math.abs(secondDate.getTime() - firstDate.getTime());
            long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

            if (diff > 7) return true;
        } catch (Exception e) {
            System.out.println(e);
        }

        return result;
    }

    public void fillFromDateWithYear(String[] value) {
        WebElement fromDate = driver.findElement(
                By.xpath("(//input[contains(@class,'MuiInputBase-input MuiOutlinedInput-input')])[1]"));
//        fromDate.click();
        fromDate.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        sobHelper.delay(800);
        for (int i = 0; i < value.length; i++) {
            fromDate.sendKeys(value[i]);
        }
        fromDate.sendKeys(Keys.chord(Keys.DOWN));
        sobHelper.delay(800);
    }

    public void hitBtnFilter() {
        WebElement btnFilter = driver.findElement(By.xpath("//button[text()='FILTER']"));
        btnFilter.click();
    }

    public void hitBtnApply() {
        WebElement btnApply = driver.findElement(By.xpath("//button[text()='APPLY']"));
        btnApply.click();
        sobHelper.delay(800);
    }
}
