package pages.sob.solusipayweb;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SOBSolusipayWebFAQPages {
    WebDriver driver;
    public SOBSolusipayWebFAQPages(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[contains(text(), 'FAQ Solusipay Web')]")
    WebElement FAQPages;


    public String getFAQPages(){
        return FAQPages.getText();
    }

}
