package step_definations_bdd;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.example.base.AmazonBaseClass;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Hooks {
    
    @Before("@Amazon")
    public void setUp() {
        System.out.println("Setting up test environment for Amazon scenarios");
        // BaseClass setup will be called in the step definitions
    }
    
    @After("@Amazon")
    public void tearDown(Scenario scenario) {
        System.out.println("Tearing down test environment");
        
        // Take screenshot if scenario failed
        if (scenario.isFailed()) {
            try {
                TakesScreenshot takesScreenshot = (TakesScreenshot) AmazonBaseClass.getDriver();
                byte[] screenshot = takesScreenshot.getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", "Screenshot");
                System.out.println("Screenshot captured for failed scenario: " + scenario.getName());
            } catch (Exception e) {
                System.out.println("Failed to capture screenshot: " + e.getMessage());
            }
        }
        
        // Close all windows and browser
        if (AmazonBaseClass.getDriver() != null) {
            try {
                // Close all open windows
                for (String windowHandle : AmazonBaseClass.getDriver().getWindowHandles()) {
                    AmazonBaseClass.getDriver().switchTo().window(windowHandle);
                    AmazonBaseClass.getDriver().close();
                }
            } catch (Exception e) {
                System.out.println("Error closing windows: " + e.getMessage());
            }
            AmazonBaseClass.tearDown();
        }
    }
    
    @Before("@SmokeTest")
    public void setUpSmokeTest() {
        System.out.println("Setting up for Smoke Test");
    }
    
    @After("@SmokeTest")
    public void tearDownSmokeTest() {
        System.out.println("Tearing down Smoke Test");
    }
    
    @Before("@RegressionTest")
    public void setUpRegressionTest() {
        System.out.println("Setting up for Regression Test");
    }
    
    @After("@RegressionTest")
    public void tearDownRegressionTest() {
        System.out.println("Tearing down Regression Test");
    }
}
