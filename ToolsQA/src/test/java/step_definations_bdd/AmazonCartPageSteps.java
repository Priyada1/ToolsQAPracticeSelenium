package step_definations_bdd;

import io.cucumber.java.en.Then;
import org.example.base.AmazonBaseClass;
import org.example.page_object.AmazonCartPage;
import org.testng.Assert;

public class AmazonCartPageSteps {
    
    private AmazonCartPage amazonCartPage;
    
    @Then("I should see the added product in my cart")
    public void i_should_see_the_added_product_in_my_cart() {
        amazonCartPage = new AmazonCartPage(AmazonBaseClass.getDriver());
        Assert.assertTrue(amazonCartPage.isCartPageLoaded(), "Cart page is not loaded properly");
        Assert.assertTrue(amazonCartPage.getCartItemCount() > 0, "No items found in cart");
        Assert.assertTrue(amazonCartPage.isProductInCart("Samsung"), "Samsung product not found in cart");
    }
    
    @Then("the product quantity should be {string}")
    public void the_product_quantity_should_be(String expectedQuantity) {
        amazonCartPage = new AmazonCartPage(AmazonBaseClass.getDriver());
        String actualQuantity = amazonCartPage.getFirstCartItemQuantity();
        Assert.assertEquals(actualQuantity, expectedQuantity, 
            "Expected quantity " + expectedQuantity + " but found " + actualQuantity);
    }
    
    @Then("I should see cart items")
    public void i_should_see_cart_items() {
        amazonCartPage = new AmazonCartPage(AmazonBaseClass.getDriver());
        Assert.assertTrue(amazonCartPage.isCartPageLoaded(), "Cart page is not loaded");
        Assert.assertFalse(amazonCartPage.isCartEmpty(), "Cart is empty");
    }
}
