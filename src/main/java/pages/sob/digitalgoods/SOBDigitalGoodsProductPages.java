package pages.sob.digitalgoods;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import helper.SOBHelper;

public class SOBDigitalGoodsProductPages {
    WebDriver driver;
    WebDriverWait wait;
    SOBHelper sobHelper;

    public SOBDigitalGoodsProductPages(WebDriver driver) {
        this.driver = driver;
        this.sobHelper = new SOBHelper();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void tabProduct() {
        driver.findElement(By.xpath("//button[text()='Product']")).click();
    }

    public Boolean checkNoOption() {
        Boolean noOption = false;
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        WebElement element = driver.findElement(
                By.xpath("//*[@role='presentation' and contains(@class, 'MuiAutocomplete-popper')]"));
        String htmlContent = element.getAttribute("outerHTML");
        System.out.println("Isi HTML: " + htmlContent);
        try {
            noOption = element
                    .findElement(By
                            .xpath("//div[contains(@class, 'MuiAutocomplete-noOptions') and @role='presentation']"))
                    .isDisplayed();
            return noOption;
        } catch (Exception e) {
            try {
                noOption = element.findElement(By.tagName("li")).isDisplayed();
                return false;
            } catch (Exception ea) {
                return true;
            }
        }

    }

    public void listSubCategory(String arg1) throws Exception {
        String category = "";
        try {
            category = driver
                    .findElement(By.xpath("(//label[normalize-space(text())='Category Name']/following::input)[1]"))
                    .getAttribute("value");
        } catch (Exception e) {
            System.out.println("Null");
        } finally {
            if (!category.equals("")) {
                sobHelper.delay(500);
                WebElement field = driver
                        .findElement(
                                By.xpath("(//label[normalize-space(text())='Sub Category Name']/following::input)[1]"));
                sobHelper.delay(300);
                field.click();
                field.sendKeys(arg1);
                sobHelper.delay(500);
            } else {
                throw new Exception("Category Name is NULL");
            }
        }

    }

    public void dropFill(String arg0, String arg1) {
        WebElement field = driver
                .findElement(By.xpath("(//label[normalize-space(text())='" + arg0 + "']/following::input)[1]"));
        sobHelper.delay(300);
        field.click();
        field.sendKeys(arg1);
    }

    public void addSingleProduct() {
        driver.findElement(By.id("single-add")).click();
        sobHelper.delay(300);
    }
}
