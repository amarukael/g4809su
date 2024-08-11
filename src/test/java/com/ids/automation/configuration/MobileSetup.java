package com.ids.automation.configuration;


import java.io.IOException;
import java.net.URL;


import io.appium.java_client.Setting;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;


public class MobileSetup {
    static AppiumDriver driver = null;

    static DesiredCapabilities desiredCapabilities;

    private static void setupMobileDevice(String appPackage, String appActivity){
        desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(CapabilityType.PLATFORM_NAME, "ANDROID");
        desiredCapabilities.setCapability("deviceName", "POCO X3");
        desiredCapabilities.setCapability("udid", "73566ba5");
        desiredCapabilities.setCapability("platformVersion", "12");
        desiredCapabilities.setCapability("automationName", "UiAutomator2");
        desiredCapabilities.setCapability("noReset", true);


        desiredCapabilities.setCapability("appPackage", appPackage);
        desiredCapabilities.setCapability("appActivity", appActivity);

    }

    public static AppiumDriver getDriver(String uri,String appPackage, String appActivity) {
        if(driver == null){
            try {
                setupMobileDevice(appPackage,appActivity);

                URL url = new URL(uri);
                driver = new AppiumDriver(url,desiredCapabilities);
                driver.setSetting(Setting.WAIT_FOR_IDLE_TIMEOUT, 100);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return driver;
    }

    public static void closeDriver() throws IOException {
        if (driver != null) {

            // JANGAN UBAH SCRIPT (com.solusipay.id) ATAU HAPUS!!!!
            // KALAU HAPUS NANTI BAKAL DELETE SEMUA DATA DI HP!!!!
            Runtime.getRuntime().exec("adb shell am force-stop com.solusipay.id");
            Runtime.getRuntime().exec("adb shell pm clear com.solusipay.id");
            driver.quit();
            driver = null;
        }
    }

}
