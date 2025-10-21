package org.example.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class AmazonConfigReader {
    
    private static Properties properties;
    private static final String CONFIG_FILE_PATH = "config/amazon-config.properties";
    
    static {
        loadProperties();
    }
    
    private static void loadProperties() {
        properties = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream(CONFIG_FILE_PATH);
            properties.load(fileInputStream);
            fileInputStream.close();
        } catch (IOException e) {
            System.err.println("Error loading Amazon configuration file: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public static String getAmazonBaseUrl() {
        return properties.getProperty("amazonBaseUrl", "https://www.amazon.in");
    }
    
    public static String getBrowser() {
        return properties.getProperty("browser", "chrome");
    }
    
    public static int getTimeout() {
        return Integer.parseInt(properties.getProperty("timeout", "10"));
    }
    
    public static int getImplicitWait() {
        return Integer.parseInt(properties.getProperty("implicitWait", "10"));
    }
    
    public static int getPageLoadTimeout() {
        return Integer.parseInt(properties.getProperty("pageLoadTimeout", "30"));
    }
}
