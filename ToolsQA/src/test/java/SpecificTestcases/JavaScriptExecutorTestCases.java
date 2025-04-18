package SpecificTestcases;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;


public class JavaScriptExecutorTestCases {

    @Test
    public void testScrollingPage() throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.amazon.in/");

        driver.manage().window().maximize();

        JavascriptExecutor je = (JavascriptExecutor) driver;

//        WebElement element = driver.findElement(By.xpath("//h2[@class='rhf-sign-in-title']"));
//        je.executeScript("arguments[0].scrollIntoView();",element);
//        System.out.println(je.executeScript( "return window.pageYOffset;"));

        je.executeScript( "window.scrollBy(0,4300)","");
        System.out.println(je.executeScript( "return window.pageYOffset;")); //4614.5
       // Thread.sleep(3000);
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5000));
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@class='rhf-sign-in-title']")));
        WebElement element = driver.findElement(By.xpath("//h2[@class='rhf-sign-in-title']"));
        je.executeScript("arguments[0].click();",element);


        //
        je.executeScript("window.scrollBy(0,document.body.scrollHeight)");
        System.out.println(je.executeScript( "return window.pageYOffset;"));
        Thread.sleep(3000);

        je.executeScript("window.scrollBy(0, -document.body.scrollHeight)");
        System.out.println(je.executeScript( "return window.pageYOffset;"));


       // zoom browser

        je.executeScript("document.body.style.zoom='40%'");

        driver.quit();





    }
}