package SpecificTestcases;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import org.example.utilities.ReadConfig;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class TakeScreenshotOfToolsQALoginPage {


    @Test
    public void takeScreenShotTest(){
        ReadConfig config = new ReadConfig();
        String url = config.getBaseUrl();
        String browser = config.getBrowser();

//        WebDriverManager driverManager = new ChromeDriverManager();
//        driverManager.setup();
//        WebDriver driver = new ChromeDriver();
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(url);
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File target = new File("/Users/chakrapanipriyadarshi/Desktop/Web-Automation-March-2025/ToolsQAPracticeSelenium/ToolsQA/target/Screenshots"+Math.random()+".png");
        try {
            FileHandler.copy(source, target);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}