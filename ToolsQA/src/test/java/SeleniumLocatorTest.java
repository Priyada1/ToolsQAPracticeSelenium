import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SeleniumLocatorTest {
    WebDriver driver;
    @BeforeClass
    public void setUp() {
        System.out.println("Before Class");
        System.setProperty("webdriver.gecko.driver", "/Users/chakrapanipriyadarshi/Desktop/Selenium_2025_Practice/Selenium_Test_2025/geckodriver");
        driver = new FirefoxDriver();
        driver.get("https://www.toolsqa.com");
        driver.manage().window().maximize();
    }


    @Test
    public void testByLocator_ID() throws InterruptedException {
        System.out.println("Test By Locator ID");
        JavascriptExecutor js = (JavascriptExecutor) driver; // for hidden element we use JavascriptExecutor
        driver.get("https://demoqa.com/automation-practice-form");
        driver.findElement(By.id("firstName")).sendKeys("Chakrapani");
        driver.findElement(By.id("lastName")).sendKeys("Priyadarshi");
        driver.findElement(By.id("userEmail")).sendKeys("cha@gmail.com");
        driver.findElement(By.id("userNumber")).sendKeys("90000090909");
        // Find the radio button by ID and click it
        WebElement femaleRadioButton = driver.findElement(By.xpath("//*[@id='gender-radio-2']"));
        js.executeScript("arguments[0].click();", femaleRadioButton);
        Assert.assertTrue(femaleRadioButton.isSelected());

       driver.findElement(By.id("dateOfBirthInput")).click();
        // Locate the select dropdown
        WebElement monthDropdown = driver.findElement(By.className("react-datepicker__month-select")); // Replace with the actual ID
        Select selectMonth = new Select(monthDropdown);
        selectMonth.selectByIndex(0);
        WebElement yearDropdown = driver.findElement(By.className("react-datepicker__year-select")); // Replace with the actual ID
        Select select = new Select(yearDropdown);
        select.selectByValue("2000");
        WebElement day = driver.findElement(By.xpath("//div[text()='1']"));
        day.click();

//        driver.findElement(By.id("dateOfBirthInput")).sendKeys("01 Jan 1990");
//        driver.findElement(By.id("dateOfBirthInput")).click();
       // driver.findElement(By.id("subjectsInput")).sendKeys("Maths");
        driver.findElement(By.id("subjectsInput")).sendKeys("science");
        WebElement musicCheckbox = driver.findElement(By.id("hobbies-checkbox-3"));
        js.executeScript("arguments[0].click();", musicCheckbox);
       // musicCheckbox.click();

        //Upload docs
        WebElement upload = driver.findElement(By.id("uploadPicture"));
        upload.sendKeys("/Users/chakrapanipriyadarshi/Desktop/Selenium_2025_Practice/Selenium_Test_2025/src/test/test-resources/automation-img.png");

        driver.findElement(By.id("currentAddress")).sendKeys("Bangalore");
        WebElement dropdownPlaceholder = driver.findElement(By.id("state"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", dropdownPlaceholder);
        Thread.sleep(1000);
        dropdownPlaceholder.click();
        // Select dropdown value
        WebElement value = driver.findElement(By.xpath("//div[text()='Rajasthan']"));
        value.click();
/*

If an ad iframe is causing issues:
    Try closing it (Solution 2).
    If that doesn’t work, scroll and use JavaScript click (Solution 1 & 3).
    Ensure the element is clickable using Explicit Wait (Solution 4).
 */

        //select city
        WebElement city = driver.findElement(By.id("city"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", city);
        Thread.sleep(1000);
        city.click();
        driver.findElement(By.xpath("//div[text()='Jaipur']")).click();

        driver.findElement(By.id("submit")).click();
       // driver.findElement(By.id("closeLargeModal")).click();
        driver.findElement(By.xpath("//button[text()='Close']")).click();



    }


    @AfterClass
    public void cleanUp(){
        driver.quit();
    }



}
