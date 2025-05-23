package com.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static final Properties properties = new Properties();
    private static final String CONFIG_FILE_NAME = "config.properties";

    static {
        try (InputStream input = ConfigReader.class.getClassLoader().getResourceAsStream(CONFIG_FILE_NAME)) {
            if (input == null) {
                System.out.println("Sorry, unable to find " + CONFIG_FILE_NAME);
                // Consider throwing an exception here if the config file is critical
            } else {
                properties.load(input);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            // Consider throwing a runtime exception here
        }
    }

    public static String getProperty(String propertyName) {
        return properties.getProperty(propertyName);
    }

    public static String getMockBaseURI() {
        return properties.getProperty("mock.baseURI");
    }

    public static String getConduitBaseURI() {
        return properties.getProperty("conduit.baseURI");
    }

    public static String getHVBaseURI() {
        return properties.getProperty("hv.baseURI");
    }
}
