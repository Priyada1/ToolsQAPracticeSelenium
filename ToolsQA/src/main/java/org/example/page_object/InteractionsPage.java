package org.example.page_object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class InteractionsPage {
    private WebDriver driver;
    private WebDriverWait explicitWait;

    public InteractionsPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


   // @FindBy(xpath = "//div[@class='vertical-list-container mt-4']")
    @FindBy(className = "vertical-list-container mt-4")
    private WebElement listItem;


    @FindBy(xpath = "//div[@class='header-text' and  text()='Interactions']")
    private WebElement interactions;


    public void clickOnInteractions(){
        interactions.click();
    }

    public List<WebElement> getAllListValues(){
        List<WebElement> list = listItem.findElements(By.xpath("//div[@class='vertical-list-container mt-4']"));
        return list;
    }



}