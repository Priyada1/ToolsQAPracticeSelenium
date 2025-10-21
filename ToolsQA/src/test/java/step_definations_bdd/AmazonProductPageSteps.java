package step_definations_bdd;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.base.AmazonBaseClass;
import org.example.page_object.AmazonProductPage;
import org.testng.Assert;

public class AmazonProductPageSteps {
    
    private AmazonProductPage amazonProductPage;
    
    @Then("I should be on the product details page")
    public void i_should_be_on_the_product_details_page() {
        // Wait a moment for the new window to fully load
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        amazonProductPage = new AmazonProductPage(AmazonBaseClass.getDriver());
        
        // Verify we're on a product page (URL should contain /dp/ or /gp/)
        String currentUrl = AmazonBaseClass.getDriver().getCurrentUrl();
        boolean isProductPage = currentUrl.contains("/dp/") || currentUrl.contains("/gp/") || currentUrl.contains("amazon.in");
        
        Assert.assertTrue(isProductPage, "Not on a product page. Current URL: " + currentUrl);
        Assert.assertTrue(amazonProductPage.isProductPageLoaded(), "Product page is not loaded properly");
        Assert.assertTrue(amazonProductPage.isAddToCartButtonEnabled(), "Add to Cart button is not enabled");
    }
    
    @When("I click on {string} button")
    public void i_click_on_button(String buttonName) {
        amazonProductPage = new AmazonProductPage(AmazonBaseClass.getDriver());
        if (buttonName.equals("Add to Cart")) {
            amazonProductPage.clickAddToCart();
        }
    }
    
    @Then("I should see {string} confirmation message")
    public void i_should_see_confirmation_message(String expectedMessage) {
        amazonProductPage = new AmazonProductPage(AmazonBaseClass.getDriver());
        Assert.assertTrue(amazonProductPage.isAddedToCartMessageDisplayed(), 
            "Added to cart confirmation message is not displayed");
        
        String actualMessage = amazonProductPage.getAddedToCartMessage();
        Assert.assertTrue(actualMessage.contains("Added to Cart") || actualMessage.contains("added to Cart"), 
            "Expected confirmation message not found. Actual: " + actualMessage);
    }
    
    @Then("the product should be added to my cart")
    public void the_product_should_be_added_to_my_cart() {
        amazonProductPage = new AmazonProductPage(AmazonBaseClass.getDriver());
        Assert.assertTrue(amazonProductPage.isAddedToCartMessageDisplayed(), 
            "Product was not successfully added to cart");
    }
}
