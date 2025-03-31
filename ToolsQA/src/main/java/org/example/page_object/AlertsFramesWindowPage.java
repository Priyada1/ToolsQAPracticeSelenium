package org.example.page_object;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class AlertsFramesWindowPage {
    private WebDriver driver;
    private WebDriverWait explicitWait;

    public AlertsFramesWindowPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    //Alerts
    @FindBy(xpath = "//div[@class='header-text' and text() ='Alerts, Frame & Windows']")
    private WebElement alertsWindowsFrame;

    @FindBy(xpath = "//span[@class='text' and text() ='Alerts']")
    private WebElement alerts;

    @FindBy(xpath = "//h1[@class='text-center' and text() ='Alerts']")
    private WebElement alertsPageHeading;

    @FindBy(id = "alertButton")
    private WebElement alertButton;

    @FindBy(id = "timerAlertButton")
    private WebElement timerAlertButton;

    @FindBy(id = "confirmButton")
    private WebElement confirmAlert;

    @FindBy(id = "confirmResult")
    private WebElement confirmResult;

    @FindBy(id = "promtButton")
    private WebElement promtButton;

    public void selectAlertsFrameWindow() {
        alertsWindowsFrame.click();
    }

    public void selectAlertsAndValidateAlertsPage() {
        alerts.click();
        Assert.assertTrue(alertsPageHeading.isDisplayed());
        Assert.assertEquals(alertsPageHeading.getText().trim(), "Alerts");
    }

    public void validateNormalAlertButton() {
        alertButton.click();
        Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());
        System.out.println(alert.getText());
        alert.accept();
        // Verify that the alert is gone.
        explicitWait.until(ExpectedConditions.not(ExpectedConditions.alertIsPresent()));
        System.out.println("Alert Handled successfully");
    }

    public void validateAlertButtonWhichTakesFiveMinute() {
        timerAlertButton.click();
        Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());
        System.out.println(alert.getText());
        alert.dismiss();
        explicitWait.until(ExpectedConditions.not(ExpectedConditions.alertIsPresent()));
        System.out.println("timer Alert handeld successfully");
    }

    public void validateConfirmAlert() {
        confirmAlert.click();
        Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());
        System.out.println(alert.getText());
        alert.accept();
        explicitWait.until(ExpectedConditions.not(ExpectedConditions.alertIsPresent()));
        System.out.println(confirmResult.getText());
        Assert.assertEquals(confirmResult.getText(), "You selected Ok");
        //dismiss alert
        confirmAlert.click();
        Alert alert2 = explicitWait.until(ExpectedConditions.alertIsPresent());
        System.out.println(alert2.getText());
        alert2.dismiss();
        explicitWait.until(ExpectedConditions.not(ExpectedConditions.alertIsPresent()));
        System.out.println(confirmResult.getText());
        Assert.assertEquals(confirmResult.getText(), "You selected Cancel");
    }

    public void validatePromptAlert() {
        String name = "Sachin";
        promtButton.click();
        Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());
        alert.sendKeys(name);
        alert.accept();
        explicitWait.until(ExpectedConditions.not(ExpectedConditions.alertIsPresent()));
        WebElement promptResult = driver.findElement(By.id("promptResult"));
        System.out.println(promptResult.getText());
        Assert.assertEquals(promptResult.getText(), "You entered " + name);
    }

    //Nested Frames
    @FindBy(xpath = "//span[@class='text' and text() ='Nested Frames']")
    private WebElement nestedFrame;

    @FindBy(xpath = "//h1[@class='text-center' and text() ='Nested Frames']")
    private WebElement nestedFramePageHeading;

    public void clickOnNestedFrameAndValidateHeading(){
        nestedFrame.click();
        Assert.assertEquals(nestedFramePageHeading.getText().trim(),"Nested Frames");
    }

    public void validateInnerFrame(){

       driver.switchTo().frame("frame1");
       WebElement parentFrame= driver.findElement(By.xpath("//body[text()='Parent frame']"));
       Assert.assertEquals(parentFrame.getText(),"Parent frame");
       System.out.println("parent-frame-text: "+parentFrame.getText());
       driver.switchTo().defaultContent();
       WebElement element = driver.findElement(By.xpath("//*[@id='framesWrapper']/div[contains(text(), 'Sample Nested Iframe page.')]"));
       String content = element.getText();
       System.out.println(content);
       Assert.assertEquals(content,"Sample Nested Iframe page. There are nested iframes in this page. Use browser inspecter or firebug to check out the HTML source. In total you can switch between the parent frame and the nested child frame.");

       driver.switchTo().frame("frame1");
        // 1. Find the iframe element
        WebElement iframeElement = driver.findElement(By.xpath("//iframe[@srcdoc='<p>Child Iframe</p>']"));

        // 2. Switch to the iframe
        driver.switchTo().frame(iframeElement);
       Assert.assertEquals(driver.findElement(By.tagName("p")).getText(),"Child Iframe");
        driver.switchTo().defaultContent();

        System.out.println("Iframe handled successfully.");



    }


}