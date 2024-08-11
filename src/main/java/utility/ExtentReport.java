package utility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class ExtentReport {
    public static void editExtentProperties(String type) {
        // Specify the path to your extent.properties file
        String pathProj = System.getProperty("user.dir");
        String propertiesFilePath = pathProj + "\\src\\test\\resources\\extent.properties";

        // Load existing properties
        Properties properties = loadProperties(propertiesFilePath);

        if (properties != null) {
            // Update the desired property
            properties.setProperty("extent.reporter.spark.config"
                    , "src/test/resources/extentreport/" +type + "-config.xml");
            properties.setProperty("extent.reporter.logger.config"
                    , "src/test/resources/extentreport/" +type + "-config.xml");
            properties.setProperty("extent.reporter.html.config"
                    , "src/test/resources/extentreport/" +type + "-config.xml");

            // Save the updated properties
            saveProperties(properties, propertiesFilePath);
        }
    }

    // Load properties from a file
    private static Properties loadProperties(String filePath) {
        Properties properties = new Properties();
        try (InputStream input = new FileInputStream(filePath)) {
            properties.load(input);
            return properties;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Save properties to a file
    private static void saveProperties(Properties properties, String filePath) {
        try (OutputStream output = new FileOutputStream(filePath)) {
            properties.store(output, null);
            System.out.println("Properties saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
