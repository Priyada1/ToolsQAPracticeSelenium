package step_definations_bdd;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.example.base.AmazonBaseClass;
import org.example.page_object.AmazonHomePage;
import org.testng.Assert;

public class AmazonHomePageSteps {
    
    private AmazonHomePage amazonHomePage;
    
    @Given("I am on the Amazon homepage")
    public void i_am_on_the_amazon_homepage() {
        AmazonBaseClass.setup();
        amazonHomePage = new AmazonHomePage(AmazonBaseClass.getDriver());
        Assert.assertTrue(amazonHomePage.isSearchBoxDisplayed(), "Amazon homepage is not loaded properly");
    }
    
    @When("I search for {string}")
    public void i_search_for(String productName) {
        amazonHomePage.searchForProduct(productName);
        amazonHomePage.clickSearchButton();
    }
    
    @When("I navigate to cart")
    public void i_navigate_to_cart() {
        amazonHomePage.navigateToCart();
    }
}
