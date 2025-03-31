package org.example.page_object;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class ToolQALoginPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public ToolQALoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @FindBy(id = "userName")
    private WebElement username;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(xpath ="//button[@id='login' and text()= 'Login']")
    private WebElement login;

    @FindBy(id = "newUser")
    private WebElement newUser;

    @FindBy(className = "text-center")
    private WebElement loginText;

    @FindBy(xpath = "//div/h2")
    private WebElement welcome;

    @FindBy(xpath = "//div/h5")
    private WebElement loginBookStore;

    public void enterUsername(String username) {
        this.username.sendKeys(username);
    }

    public void enterPassword(String password) {
        this.password.sendKeys(password);
    }

    public void clickLoginBtn() throws InterruptedException {
        Thread.sleep(2000);
        WebElement loginbtn = wait.until(ExpectedConditions.elementToBeClickable(this.login));
        JavascriptExecutor js =(JavascriptExecutor) driver;   //blocking add sometime
        js.executeScript("arguments[0].click();",loginbtn);
       // loginbtn.click();
    }

    public void clickNewUser() {
        this.newUser.click();
    }

    public void validateLoginPage() {
        Assert.assertTrue(this.loginText.isDisplayed());
        System.out.println("login page text: " + this.loginText.getText());
        Assert.assertTrue(this.loginText.getText().trim().equalsIgnoreCase("Login"));
    }

    public void validateLoginPageWelcome() {
        Assert.assertTrue(this.welcome.isDisplayed());
        Assert.assertTrue(this.loginBookStore.isDisplayed());
        Assert.assertEquals(this.welcome.getText().trim(), "Welcome,");
        Assert.assertEquals(this.loginBookStore.getText().trim(), "Login in Book Store");
    }
}