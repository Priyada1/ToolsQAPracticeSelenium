package SpecificTestcases;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class CalendarTest {

    WebDriver driver;

    @BeforeClass
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.redbus.in/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
    }


    @Test
    public void redbusDateSelection(){
        System.out.println("test started");
        WebElement datePicker = driver.findElement(By.className("dateInputWrapper___50eff3"));
        datePicker.click();

        WebElement nextMonthArrow = driver.findElement(By.xpath("//div[@class='monthArea___371fc8']/i[2]"));
        nextMonthArrow.click();
        nextMonthArrow.click();
        nextMonthArrow.click();

        driver.findElement(By.xpath("//div[@class='dateWrap___5cf7a2']/div/span")).click();

//        WebElement to=driver.findElement(By.xpath("//div[@class='labelCityWrapper___dd1d0e']/div"));
//        to.sendKeys("patna");
        WebElement inputElement = driver.findElement(By.xpath("//div[@class='labelCityWrapper___dd1d0e']/div[@class='srcDest___3c0d7e']"));
        inputElement.sendKeys("delhi");


        driver.findElement(By.xpath("//button[@class='primaryButton___93b44e searchButtonWrapper___1e2c20 ']")).click();




    }


    @AfterClass
    public void cleanUp(){
        driver.quit();
    }

}