import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

@Slf4j
public class ToolsQaTest {
    WebDriver driver;

    @BeforeClass
    public void setUp() {
        System.out.println("Before Test");
//    System.setProperty("webdriver..driver", "/Users/chakrapanipriyadarshi/Desktop/Selenium_2025_Practice/Selenium_Test_2025/chromedriver");
//    WebDriver driver = new ChromeDriver();


        System.setProperty("webdriver.gecko.driver", "/Users/chakrapanipriyadarshi/Desktop/Selenium_2025_Practice/Selenium_Test_2025/geckodriver");

        // Create a new instance of FirefoxDriver
        driver = new FirefoxDriver();
        driver.get("https://www.toolsqa.com");
        log.info("title: " + driver.getTitle());

        driver.manage().window().maximize();
    }

    @Test
    public void testLoginToolsQa() throws InterruptedException {
        System.out.println("Test 1");
        driver.get("https://demoqa.com/login");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement login = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='userName']")));

        //  WebElement login = driver.findElement(By.xpath("//*[@id='userName']"));
        WebElement password = driver.findElement(By.xpath("//*[@id='password']"));
        WebElement loginButton = driver.findElement(By.xpath("//*[@id='login']"));
        System.out.println("Title: " + driver.getTitle());
        login.sendKeys("test4321");
        password.sendKeys("Test@4321");
        loginButton.click();
       try {
           WebElement logoutButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='submit']")));
           if (logoutButton.isDisplayed()) {
               System.out.println("Login Successful");
               loginButton.click();
               System.out.println("Logout Successful");
           }
       }
       catch (Exception e){
           log.error("Login Failed");
       }

       System.out.println("get current url: " + driver.getCurrentUrl());
       System.out.println("get page Source: " + driver.getPageSource());
       System.out.println("page source length: "+ driver.getPageSource().length());

    }


    @AfterClass
    public void tearDown() {
        System.out.println("After Test");
        driver.quit();
    }

}
