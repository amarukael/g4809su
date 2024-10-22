package helper;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class PropertiesHelper {
    private final Properties properties = new Properties();

    // Constructor that loads properties from a specified file
    public PropertiesHelper(String propertiesFilePath) {
        try (InputStream input = Files.newInputStream(Paths.get(propertiesFilePath))) {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to retrieve a property as a string
    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    // Method to retrieve a property with a default value
    public String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    // Method to retrieve a boolean property
    public boolean getBooleanProperty(String key, boolean defaultValue) {
        String value = properties.getProperty(key);
        if (value != null) {
            return Boolean.parseBoolean(value);
        }
        return defaultValue;
    }

    // Method to retrieve an integer property
    public int getIntProperty(String key, int defaultValue) {
        String value = properties.getProperty(key);
        if (value != null) {
            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return defaultValue;
    }

    // You can add more methods as needed, such as for long, float, double, etc.

}
