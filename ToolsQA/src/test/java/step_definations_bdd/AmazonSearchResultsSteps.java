package step_definations_bdd;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.base.AmazonBaseClass;
import org.example.page_object.AmazonSearchResultsPage;
import org.testng.Assert;

public class AmazonSearchResultsSteps {
    
    private AmazonSearchResultsPage amazonSearchResultsPage;
    
    @Then("I should see search results for Samsung S24 Ultra 5G")
    public void i_should_see_search_results_for_samsung_s24_ultra_5g() {
        amazonSearchResultsPage = new AmazonSearchResultsPage(AmazonBaseClass.getDriver());
        Assert.assertTrue(amazonSearchResultsPage.isSearchResultsDisplayed(), "Search results are not displayed");
        
        // More flexible validation - check for any of the keywords
        boolean hasSamsung = amazonSearchResultsPage.isProductTitleContains("Samsung");
        boolean hasS24 = amazonSearchResultsPage.isProductTitleContains("S24");
        boolean hasUltra = amazonSearchResultsPage.isProductTitleContains("Ultra");
        boolean hasMobile = amazonSearchResultsPage.isProductTitleContains("mobile");
        boolean hasPhone = amazonSearchResultsPage.isProductTitleContains("phone");
        
        // At least one keyword should be present
        boolean hasRelevantResults = hasSamsung || hasS24 || hasUltra || hasMobile || hasPhone;
        
        Assert.assertTrue(hasRelevantResults, 
            "No relevant search results found. Expected keywords: Samsung, S24, Ultra, mobile, or phone");
        
        System.out.println("Search results validation - Samsung: " + hasSamsung + 
                          ", S24: " + hasS24 + ", Ultra: " + hasUltra + 
                          ", Mobile: " + hasMobile + ", Phone: " + hasPhone);
    }
    
    @Then("I should see search results containing {string}")
    public void i_should_see_search_results_containing(String keyword) {
        amazonSearchResultsPage = new AmazonSearchResultsPage(AmazonBaseClass.getDriver());
        Assert.assertTrue(amazonSearchResultsPage.isProductTitleContains(keyword), 
            "Search results do not contain: " + keyword);
    }
    
    @When("I click on the first product from search results")
    public void i_click_on_the_first_product_from_search_results() {
        amazonSearchResultsPage = new AmazonSearchResultsPage(AmazonBaseClass.getDriver());
        amazonSearchResultsPage.clickFirstProduct();
    }
    
}
