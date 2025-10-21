package org.example.page_object;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class AmazonHomePage {
    
    private WebDriver driver;
    private WebDriverWait wait;
    
    @FindBy(id = "twotabsearchtextbox")
    private WebElement searchBox;
    
    @FindBy(id = "nav-search-submit-button")
    private WebElement searchButton;
    
    @FindBy(id = "nav-cart")
    private WebElement cartIcon;
    
    @FindBy(id = "nav-link-accountList")
    private WebElement accountList;
    
    public AmazonHomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }
    
    public void searchForProduct(String productName) {
        wait.until(ExpectedConditions.elementToBeClickable(searchBox));
        searchBox.clear();
        searchBox.sendKeys(productName);
    }
    
    public void clickSearchButton() {
        wait.until(ExpectedConditions.elementToBeClickable(searchButton));
        searchButton.click();
    }
    
    public void navigateToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(cartIcon));
        cartIcon.click();
    }
    
    public boolean isSearchBoxDisplayed() {
        return searchBox.isDisplayed();
    }
    
    public String getSearchBoxPlaceholder() {
        return searchBox.getAttribute("placeholder");
    }
}
