package org.example.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.utilities.ReadConfig;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.logging.Logger;

public abstract class BaseClass {

    ReadConfig readConfig = new ReadConfig();
    String url = readConfig.getBaseUrl();
    String browser = readConfig.getBrowser();

    public static WebDriver driver;
//    protected WebDriver driver;
//    protected final WebDriverWait wait;
//
//    public BaseClass(WebDriver driver) {
//        this.driver = driver;
//        ReadConfig readConfig = new ReadConfig(); // Or pass the config object
//        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//        PageFactory.initElements(driver, this);
//    }



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

    /*
    To solve the parallel execution problem, modern Selenium frameworks use a ThreadLocal wrapper around the WebDriver instance.

A ThreadLocal variable ensures that each thread (i.e., each test running in parallel) gets its own separate,
isolated instance of the WebDriver.


        // Use ThreadLocal to store the driver
    public static ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();

    // A method to get the driver for the current thread
    public static WebDriver getDriver() {
        return threadLocalDriver.get();
    }

    public void setup() {
        // ... switch statement for browser ...
        WebDriver driver = new ChromeDriver(); // Create a new driver instance
        threadLocalDriver.set(driver); // Set this driver for the current thread

        // Now use getDriver() to access it
        getDriver().manage().window().maximize();
        getDriver().get(url);
    }

    public void tearDown() {
        if (getDriver() != null) {
            getDriver().quit();
            threadLocalDriver.remove(); // Clean up the thread
        }
    }
     */





    public void tearDown() {
        driver.quit();
    }





}