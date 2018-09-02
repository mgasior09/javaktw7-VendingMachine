package pl.sdacademy.vending.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesFileConfiguration implements Configuration {

    private static final String PROPERTIES_FILE_LOCATION = "application.properties";
    private final Properties properties;

    public static PropertiesFileConfiguration getInstance() {
        return InstanceHolder.INSTANCE;
    }

    private PropertiesFileConfiguration() {
        properties = new Properties();
        try (InputStream propertiesFile = ClassLoader.getSystemResourceAsStream(PROPERTIES_FILE_LOCATION)
        ) {
            properties.load(propertiesFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getProperty(String propertyName, String defaultValue) {
        String requestedValue = properties.getProperty(propertyName);
        if (requestedValue != null) {
            return requestedValue;
        } else {
            return defaultValue;
        }
    }

    public Long getProperty(String propertyName, Long defaultValue) {
        String requestedValue = properties.getProperty(propertyName);
        if (requestedValue != null) {
            return Long.parseLong(requestedValue);
        } else {
            return defaultValue;
        }
    }

    private static class InstanceHolder {
        private static final PropertiesFileConfiguration INSTANCE = new PropertiesFileConfiguration();
    }

}
