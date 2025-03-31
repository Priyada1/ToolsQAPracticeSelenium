package org.example.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.utilities.ReadConfig;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;
import java.util.logging.Logger;

public class BaseClass {

    ReadConfig readConfig = new ReadConfig();
    String url = readConfig.getBaseUrl();
    String browser = readConfig.getBrowser();

    public static WebDriver driver;
    //public static Logger logger;
    public static Logger logger = Logger.getLogger(BaseClass.class.getName());

    public void setup() {
        //launch browser
        switch (browser.toLowerCase()) {
            case "chrome":
                /*  WebDriverManager -
WebDriverManager is a library that helps manage WebDriver binaries (like ChromeDriver, GeckoDriver, etc.) automatically.
 It eliminates the need to manually download, set up, and specify the driver executable path in Selenium automation projects.*/
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;

            default:
                driver = null;
                break;
        }

        //for logging


//        public static void initLogger() {
//            logger.info("Logger Initialized");
//        }

        assert driver != null;
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        //open url
        driver.get(url);
        logger.info("url opened:  "+url);
    }


    public void tearDown() {
        driver.quit();
    }


}