package org.example.page_object;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import java.util.List;

public class AmazonSearchResultsPage {
    
    private WebDriver driver;
    private WebDriverWait wait;
    
    @FindBy(css = "[data-component-type='s-search-result']")
    private List<WebElement> searchResults;
    
    @FindBy(css = "[data-component-type='s-search-result'] h2 a span")
    private List<WebElement> productTitles;
    
    @FindBy(css = "[data-component-type='s-search-result'] .a-price-whole")
    private List<WebElement> productPrices;
    
    @FindBy(css = "[data-component-type='s-search-result'] .a-size-medium")
    private List<WebElement> productBrands;
    
    @FindBy(css = "[data-component-type='s-search-result'] h2 a")
    private List<WebElement> productLinks;
    
    @FindBy(css = ".s-search-results")
    private WebElement searchResultsContainer;
    
    // Alternative selectors for different Amazon layouts
    @FindBy(css = ".s-result-item")
    private List<WebElement> alternativeSearchResults;
    
    @FindBy(css = ".s-result-item h2 a span")
    private List<WebElement> alternativeProductTitles;
    
    @FindBy(css = ".s-result-item h2 a")
    private List<WebElement> alternativeProductLinks;
    
    public AmazonSearchResultsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }
    
    public void clickFirstProduct() {
        wait.until(ExpectedConditions.visibilityOf(searchResultsContainer));
        
        // Store the current window handle before clicking
        String originalWindow = driver.getWindowHandle();
        
        // Try primary selector first
        if (productLinks.size() > 0) {
            wait.until(ExpectedConditions.elementToBeClickable(productLinks.get(0)));
            productLinks.get(0).click();
        } else if (alternativeProductLinks.size() > 0) {
            // Fallback to alternative selector
            wait.until(ExpectedConditions.elementToBeClickable(alternativeProductLinks.get(0)));
            alternativeProductLinks.get(0).click();
        } else {
            throw new RuntimeException("No product links found on search results page");
        }
        
        // Wait for new window/tab to open and switch to it
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        
        // Switch to the new window
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                System.out.println("Switched to new product window: " + windowHandle);
                break;
            }
        }
    }
    
    public String getFirstProductTitle() {
        wait.until(ExpectedConditions.visibilityOf(searchResultsContainer));
        
        if (productTitles.size() > 0) {
            return productTitles.get(0).getText();
        } else if (alternativeProductTitles.size() > 0) {
            return alternativeProductTitles.get(0).getText();
        } else {
            return "No product title found";
        }
    }
    
    public boolean isSearchResultsDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(searchResultsContainer));
            return (searchResults.size() > 0) || (alternativeSearchResults.size() > 0);
        } catch (Exception e) {
            return false;
        }
    }
    
    public int getSearchResultsCount() {
        wait.until(ExpectedConditions.visibilityOf(searchResultsContainer));
        return Math.max(searchResults.size(), alternativeSearchResults.size());
    }
    
    public boolean isProductTitleContains(String keyword) {
        wait.until(ExpectedConditions.visibilityOf(searchResultsContainer));
        
        // Debug: Print all product titles found
        System.out.println("=== SEARCH RESULTS DEBUG ===");
        System.out.println("Primary product titles count: " + productTitles.size());
        System.out.println("Alternative product titles count: " + alternativeProductTitles.size());
        
        // Check primary product titles
        for (int i = 0; i < Math.min(productTitles.size(), 3); i++) {
            String titleText = productTitles.get(i).getText();
            System.out.println("Primary title " + (i+1) + ": " + titleText);
            if (titleText.toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println("✓ Found keyword '" + keyword + "' in primary title: " + titleText);
                return true;
            }
        }
        
        // Check alternative product titles
        for (int i = 0; i < Math.min(alternativeProductTitles.size(), 3); i++) {
            String titleText = alternativeProductTitles.get(i).getText();
            System.out.println("Alternative title " + (i+1) + ": " + titleText);
            if (titleText.toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println("✓ Found keyword '" + keyword + "' in alternative title: " + titleText);
                return true;
            }
        }
        
        System.out.println("✗ Keyword '" + keyword + "' not found in any product titles");
        System.out.println("=== END DEBUG ===");
        return false;
    }
    
    public List<WebElement> getProductTitles() {
        wait.until(ExpectedConditions.visibilityOf(searchResultsContainer));
        return productTitles.size() > 0 ? productTitles : alternativeProductTitles;
    }
}
