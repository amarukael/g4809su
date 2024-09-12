package com.ids.automation.configuration;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;
//import utility.PDFCreater;

public class BrowserSetup {
    private static WebDriver driver;
    private static WebDriver driver2;
    private static WebDriver driverMobile;
    // public static Document document = PDFCreater.createDocument();

    public static WebDriver getDriver() {
        if (driver == null) {
            System.out.println("Creating a new instance of WebDriver.");
            initializeChromeDriver();
        } else {
            System.out.println("Returning existing instance of WebDriver.");
        }
        return driver;
    }

    public static WebDriver getDriver2() {
        if (driver2 == null) {
            System.out.println("Creating a new instance of WebDriver.");
            initializeChromeDriver2();
        } else {
            System.out.println("Returning existing instance of WebDriver.");
        }
        return driver2;
    }

    public static WebDriver getDriverMobile() {
        if (driverMobile == null) {
            System.out.println("Creating a new instance of WebDriver.");
            initializeChromeDriverMobile();
        } else {
            System.out.println("Returning existing instance of WebDriver.");
        }
        return driverMobile;
    }

    private static void initializeChromeDriver() {
        System.out.println("Setting up ChromeDriver...");
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("useAutomationExtension", false);
        options.setExperimentalOption("prefs", prefs);
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
        System.out.println("ChromeDriver setup completed.");
    }

    private static void initializeChromeDriver2() {
        System.out.println("Setting up ChromeDriver...");
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-infobars");
        driver2 = new ChromeDriver(options);
        driver2.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver2.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
        driver2.manage().window().maximize();
        System.out.println("ChromeDriver setup completed.");
    }

    private static void initializeChromeDriverMobile() {
        System.out.println("Setting up ChromeDriver for mobile view...");
        Map<String, String> mobileEmulation = new HashMap<>();
        mobileEmulation.put("deviceName", "iPhone 12 Pro");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
        chromeOptions.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        chromeOptions.setExperimentalOption("useAutomationExtension", false);
        chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
        driverMobile = new ChromeDriver(chromeOptions);
        driverMobile.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driverMobile.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
        System.out.println("ChromeDriver setup for mobile view completed.");
    }

    public static void closeDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    public static void closeDriver2() {
        if (driver2 != null) {
            driver2.quit();
            driver2 = null;
        }
    }

    public static void closeDriverMobile() {
        if (driverMobile != null) {
            driverMobile.quit();
            driverMobile = null;
        }
    }
}
