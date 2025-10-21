package org.example.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.utilities.AmazonConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.logging.Logger;

public class AmazonBaseClass {

    private static final AmazonConfigReader amazonConfig = new AmazonConfigReader();
    private static final String amazonUrl = AmazonConfigReader.getAmazonBaseUrl();
    private static final String browser = AmazonConfigReader.getBrowser();
    private static final int timeout = AmazonConfigReader.getTimeout();
    private static final int implicitWait = AmazonConfigReader.getImplicitWait();
    private static final int pageLoadTimeout = AmazonConfigReader.getPageLoadTimeout();

    public static WebDriver driver;
    public static WebDriverWait wait;
    public static Logger logger = Logger.getLogger(AmazonBaseClass.class.getName());

    public static void setup() {
        logger.info("Setting up Amazon test environment");
        
        // Launch browser
        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--no-sandbox");
                chromeOptions.addArguments("--disable-dev-shm-usage");
                chromeOptions.addArguments("--disable-blink-features=AutomationControlled");
                chromeOptions.addArguments("--disable-extensions");
                chromeOptions.addArguments("--disable-gpu");
                chromeOptions.addArguments("--remote-allow-origins=*");
                chromeOptions.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
                chromeOptions.setExperimentalOption("useAutomationExtension", false);
                driver = new ChromeDriver(chromeOptions);
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }

        // Configure driver
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(pageLoadTimeout));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));
        
        // Initialize WebDriverWait
        wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));

        // Navigate to Amazon with retry mechanism
        int maxRetries = 3;
        for (int i = 0; i < maxRetries; i++) {
            try {
                logger.info("Attempting to navigate to Amazon (attempt " + (i + 1) + "/" + maxRetries + ")");
                driver.get(amazonUrl);
                logger.info("Amazon URL opened successfully: " + amazonUrl);
                break;
            } catch (Exception e) {
                logger.warning("Failed to navigate to Amazon (attempt " + (i + 1) + "/" + maxRetries + "): " + e.getMessage());
                if (i == maxRetries - 1) {
                    throw new RuntimeException("Failed to navigate to Amazon after " + maxRetries + " attempts", e);
                }
                try {
                    Thread.sleep(2000); // Wait 2 seconds before retry
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public static void tearDown() {
        if (driver != null) {
            logger.info("Closing Amazon test environment");
            driver.quit();
            driver = null;
        }
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static WebDriverWait getWait() {
        return wait;
    }

    public static String getAmazonUrl() {
        return amazonUrl;
    }
}
