package org.example.page_object;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import java.util.List;

public class AmazonCartPage {
    
    private WebDriver driver;
    private WebDriverWait wait;
    
    @FindBy(css = "[data-name='Active Items']")
    private WebElement activeItemsSection;
    
    @FindBy(css = "[data-name='Active Items'] .sc-product-title")
    private List<WebElement> cartProductTitles;
    
    @FindBy(css = "[data-name='Active Items'] .sc-product-price")
    private List<WebElement> cartProductPrices;
    
    @FindBy(css = "[data-name='Active Items'] .a-dropdown-prompt")
    private List<WebElement> cartProductQuantities;
    
    @FindBy(css = "#sc-subtotal-label-activecart")
    private WebElement subtotalLabel;
    
    @FindBy(css = "#sc-subtotal-amount-activecart")
    private WebElement subtotalAmount;
    
    @FindBy(css = "#sc-buy-box-ptc-button")
    private WebElement proceedToCheckoutButton;
    
    @FindBy(css = ".sc-empty-cart-header")
    private WebElement emptyCartMessage;
    
    public AmazonCartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }
    
    public boolean isCartPageLoaded() {
        try {
            wait.until(ExpectedConditions.visibilityOf(activeItemsSection));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public int getCartItemCount() {
        wait.until(ExpectedConditions.visibilityOf(activeItemsSection));
        return cartProductTitles.size();
    }
    
    public String getFirstCartItemTitle() {
        wait.until(ExpectedConditions.visibilityOf(activeItemsSection));
        return cartProductTitles.get(0).getText();
    }
    
    public String getFirstCartItemPrice() {
        wait.until(ExpectedConditions.visibilityOf(activeItemsSection));
        return cartProductPrices.get(0).getText();
    }
    
    public String getFirstCartItemQuantity() {
        wait.until(ExpectedConditions.visibilityOf(activeItemsSection));
        return cartProductQuantities.get(0).getText();
    }
    
    public boolean isProductInCart(String productName) {
        wait.until(ExpectedConditions.visibilityOf(activeItemsSection));
        for (WebElement title : cartProductTitles) {
            if (title.getText().toLowerCase().contains(productName.toLowerCase())) {
                return true;
            }
        }
        return false;
    }
    
    public String getSubtotalAmount() {
        wait.until(ExpectedConditions.visibilityOf(subtotalAmount));
        return subtotalAmount.getText();
    }
    
    public boolean isCartEmpty() {
        try {
            return emptyCartMessage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public void clickProceedToCheckout() {
        wait.until(ExpectedConditions.elementToBeClickable(proceedToCheckoutButton));
        proceedToCheckoutButton.click();
    }
}
