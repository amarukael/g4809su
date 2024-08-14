package pages.sob;

import helper.SOBHelper;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SOBHomePages {
    WebDriver driver;
    SOBHelper sobHelper = new SOBHelper();
    WebDriverWait wait;

    public SOBHomePages(WebDriver driver) {
        this.driver = driver;
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void scrollBody(int scroll) {
        ((JavascriptExecutor) driver).executeScript(
                "window.scrollBy(0," + scroll + ")", "");
    }

    public void MenuDrawer() {
        WebElement menuDrawer = wait.until(ExpectedConditions.
                elementToBeClickable(By.xpath("//button[@aria-label='open drawer']")));
        menuDrawer.click();
    }

    public String getWelcomeMessageText() {
        WebElement welcomeMessage = driver.findElement(
                By.xpath("//p[text()='Welcome to Our Website']"));
        System.out.println(welcomeMessage.getText());
        return welcomeMessage.getText();
    }

    // get Menu SOB
    public void openNavMenu(String menu, int left, int top, String behavior) {
        WebElement containerNav = driver.findElement(
                By.xpath("//div[@class='css-m6hwl0']"));
        WebElement navMenu = driver.findElement(
                By.xpath("//span[text()='" + menu + "']"));
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollTo({left: " + left + ", top: " + top +
                        ", behavior: '" + behavior + "'});", containerNav);
        sobHelper.delay(800);
        navMenu.click();

        if (menu.equals("Digital Goods") || menu.equals("Solusipay Web")) {
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollTo({left: 0, top: 450, behavior: 'smooth'});"
                    , containerNav);
        }
    }

    // check display element
    public boolean checkNoNavMenu(String menu) {
        try {
            WebElement navMenu = driver.findElement(
                    By.xpath("//span[text()='" + menu + "']"));
            if (navMenu.isDisplayed()) {
                return false;
            }
        } catch (NoSuchElementException e) {
            return true;
        }

        return false;
    }

    // open sub menu
    public void openNavSubMenu(String subMenu) {
        WebElement navSubMenu = driver.findElement(
                By.xpath("//span[text()='" + subMenu + "']"));
        navSubMenu.click();
    }
}
