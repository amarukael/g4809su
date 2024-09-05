package pages.sob.setorku;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import helper.SOBHelper;
import pages.sob.SOBGlobalPages;

public class SOBSetorkuBulkUploadPages {
    WebDriver driver;
    WebDriverWait wait;
    SOBGlobalPages globalPages;
    SOBHelper sobHelper = new SOBHelper();

    public SOBSetorkuBulkUploadPages(WebDriver driver) {
        this.driver = driver;
        this.globalPages = new SOBGlobalPages(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void addBulk() {
        driver.findElement(By.id("button-add")).click();
    }

    public void detailBulk(String s) {
        try {
            WebElement button = driver.findElement(By.xpath("//div[@data-id='" + s + "']" +
                    "//a[p[text()='Detail']]"));
            button.click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getValueCell(String s) {
        globalPages.scrollDataTabletoLeft(0);
        sobHelper.delay(500);
        WebElement row = driver.findElement(By.xpath("//div[@data-id='" + s + "']//div[@data-colindex='2']"));
        String cellText = row.getText();
        globalPages.scrollDataTabletoLeft(10000);
        sobHelper.delay(500);
        System.out.println(cellText + " Cell : " + s);
        sobHelper.saveData("name", cellText);
    }

    public String validateValue(String s) {
        String validValue = sobHelper.getData("name");
        try {
            WebElement value = driver.findElement(By.xpath("//p[normalize-space(text())='" + validValue + "']"));
            value.isDisplayed();
            return validValue;
        } catch (Exception e) {
            return e.toString();
        }
    }

    public void actionBulk(String s, String s1) {
        try {
            if (s.equalsIgnoreCase("approve")) {
                WebElement button = driver.findElement(By.xpath("//div[@data-id='" + s1 + "']" +
                        "//button[contains(@class, 'MuiButton-contained')]"));
                button.click();
            } else if (s.equalsIgnoreCase("reject")) {
                WebElement button = driver.findElement(By.xpath("//div[@data-id='" + s1 + "']" +
                        "//button[contains(@class, 'MuiButton-outlined')]"));
                button.click();
            } else {
                InvalidArgumentException invalid = new InvalidArgumentException("Invalid Argument");
                throw invalid;
            }
        } catch (Exception e) {
            throw new NoSuchElementException("Not Found");
        }
    }

    public String validPayCode() {
        StringBuilder sBuilder = new StringBuilder();
        WebElement rowGroupDiv = driver.findElement(By.cssSelector("[role='rowgroup']"));
        try {
            List<WebElement> divs = rowGroupDiv.findElements(By.xpath("//div[@role='row']"));
            for (int i = 1; i < divs.size(); i++) {
                WebElement element = driver
                        .findElement(By.xpath("//div[@data-id='" + i + "']//div[@data-colindex='3']" +
                                "//a[p[contains(@class,'MuiTypography-body1')]]"));
                String text = element.getText();
                sBuilder.append("Payment Code " + i + " = " + text + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        String result = sBuilder.toString();
        return result;
    }
}
