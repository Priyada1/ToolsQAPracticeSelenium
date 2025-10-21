package SpecificTestcases;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;

public class BrokenLinksTest {
    WebDriver driver;

    @BeforeClass
    public void setUp() {
        // System.setProperty("webdriver.chrome.driver","/Users/chakrapanipriyadarshi/Desktop/Selenium_2025_Practice/Selenium_Test_2025/chromedriver");
        WebDriverManager.chromedriver().setup(); // Automatically downloads the correct ChromeDriver
        driver = new ChromeDriver();
        driver.get("https://beginnersbook.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
    }

    @Test
    public void testbrokenlinks() {
        System.out.println("test started");

        List<WebElement> list = driver.findElements(By.tagName("a"));

        System.out.println("total links: " + list.size());

        for (WebElement a : list) {
            String url = a.getAttribute("href"); // .getAttribute(...): This is a method available on every WebElement.
            // Its job is to read the value of any given HTML attribute from that element.
            // 3."href": This is the specific HTML attribute you are asking for. In an <a> tag,
            // the href attribute contains the destination URL of the link.
            // System.out.println(a);
            //System.out.println("url:>>>>>>> "+ url);
            try {
                HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection(); //creates a connection to the specified URL.
                // HttpURLConnection ->    casts the connection object to HttpURLConnection, which is used for making HTTP requests.
                connection.setRequestMethod("HEAD"); //  Sets the HTTP request method to "HEAD".
                //A HEAD request is similar to a GET request, but it only fetches metadata (like headers) without downloading the full page content.
                connection.connect(); //Establishes a connection to the URL.
                int responseCode = connection.getResponseCode();

                if (responseCode == 200 || responseCode == 201) {
                    System.out.println("it's valid Link : " + url + " with valid response code : " + responseCode);
                } else {

                    System.out.println("its invalid link: " + url + " with response-code : " + responseCode);
                }

            } catch (MalformedURLException e) {
                System.out.println("Invalid URL: " + url);
            } catch (IOException e) {
                System.out.println("Error checking URL: " + url);
            }

        }


    }


    @AfterClass
    public void clean() {
        driver.quit();
    }
}