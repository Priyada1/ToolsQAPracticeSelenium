package org.example.page_object;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class ToolsQAProfilePage {

    WebDriver driver;
    private WebDriverWait wait;
    public ToolsQAProfilePage(WebDriver driver){
        this.driver =driver;
        PageFactory.initElements(driver,this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @FindBy(xpath = "//li[span[contains(text(),'Profile')]]")
    private WebElement profile;

    @FindBy(xpath = "//label[contains(text(),'User Name : ')]")
    private WebElement userNameText;

    @FindBy(id = "userName-value")
    private WebElement userNameValue;

    @FindBy(id = "searchBox")
    private WebElement searchBox;

    @FindBy(id = "gotoStore")
    private WebElement goToBookStoreButton;

    @FindBy(xpath = "//li[@id='item-2']/span[text()='Book Store']")
    private WebElement bookStore;

    @FindBy(linkText = "")
    private WebElement test;


    public void validateProfileText()
    {
        Assert.assertTrue(profile.isDisplayed());
    }

    public void validateLoggedInUserDetails(String userName){
        Assert.assertTrue(userNameText.isDisplayed());
        Assert.assertTrue(userNameValue.isDisplayed());
        System.out.println("userName at profile page: "+userNameValue.getText());
        Assert.assertEquals(userNameValue.getText(),userName);
    }

    public void clickOnBookStore(){
        wait.until(ExpectedConditions.elementToBeClickable(this.bookStore));
        bookStore.click();
    }


}
