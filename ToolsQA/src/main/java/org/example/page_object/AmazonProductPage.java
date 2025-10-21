package org.example.page_object;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class AmazonProductPage {
    
    private WebDriver driver;
    private WebDriverWait wait;
    
    @FindBy(id = "add-to-cart-button")
    private WebElement addToCartButton;
    
    @FindBy(id = "buy-now-button")
    private WebElement buyNowButton;
    
    @FindBy(id = "productTitle")
    private WebElement productTitle;
    
    @FindBy(css = ".a-price-whole")
    private WebElement productPrice;
    
    @FindBy(css = "#NATC_SMART_WAGON_CONF_MSG_SUCCESS span")
    private WebElement addedToCartMessage;
    
    @FindBy(css = "#attach-sidesheet-view-cart-button")
    private WebElement viewCartButton;
    
    @FindBy(css = "#attach-close_sideSheet-link")
    private WebElement closeSideSheetButton;
    
    @FindBy(css = "#huc-v2-order-row-confirm-text")
    private WebElement cartConfirmationText;
    
    public AmazonProductPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
        
        // Debug: Print current window information
        System.out.println("=== PRODUCT PAGE DEBUG ===");
        System.out.println("Current window handle: " + driver.getWindowHandle());
        System.out.println("Total windows open: " + driver.getWindowHandles().size());
        System.out.println("Current URL: " + driver.getCurrentUrl());
        System.out.println("Page title: " + driver.getTitle());
        System.out.println("=== END PRODUCT PAGE DEBUG ===");
    }
    
    public void clickAddToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
        addToCartButton.click();
    }
    
    public String getProductTitle() {
        wait.until(ExpectedConditions.visibilityOf(productTitle));
        return productTitle.getText();
    }
    
    public String getProductPrice() {
        wait.until(ExpectedConditions.visibilityOf(productPrice));
        return productPrice.getText();
    }
    
    public boolean isAddedToCartMessageDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(addedToCartMessage));
            return addedToCartMessage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public String getAddedToCartMessage() {
        wait.until(ExpectedConditions.visibilityOf(addedToCartMessage));
        return addedToCartMessage.getText();
    }
    
    public void clickViewCart() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(viewCartButton));
            viewCartButton.click();
        } catch (Exception e) {
            // If view cart button is not available, try to close side sheet
            if (closeSideSheetButton.isDisplayed()) {
                closeSideSheetButton.click();
            }
        }
    }
    
    public boolean isProductPageLoaded() {
        return productTitle.isDisplayed() && addToCartButton.isDisplayed();
    }
    
    public boolean isAddToCartButtonEnabled() {
        return addToCartButton.isEnabled();
    }
}
