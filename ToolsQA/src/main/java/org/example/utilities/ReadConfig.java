package org.example.utilities;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.util.Properties;

@Slf4j
@Getter
@Setter
public class ReadConfig {

    Properties prop;
    String path = "/Users/chakrapanipriyadarshi/Desktop/Selenium_2025_Practice/Selenium_Test_2025/src/Configuration/config.properties";

    public ReadConfig() {
        try {
            prop = new Properties();
            FileInputStream fis = new FileInputStream(path);
            prop.load(fis);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getBaseUrl() {
        String value = prop.getProperty("baseUrl");

        if (value != null)
            return value;
        else
            throw new RuntimeException("url not specified in config file.");

    }


    public String getBrowser() {
        String value = prop.getProperty("browser");

        if (value != null)
        {
            return value;
        }
        else{
            throw new RuntimeException("browser not specified in config file.");
        }
    }

    public String getUserName() {
        String value = prop.getProperty("userName");

        if (value != null)
        {
            return value;
        }
        else{
            throw new RuntimeException("userName not specified in config file.");
        }
    }

    public String getPassword() {
        String value = prop.getProperty("password");

        if (value != null)
        {
            return value;
        }
        else{
            throw new RuntimeException("password not specified in config file.");
        }
    }
}
