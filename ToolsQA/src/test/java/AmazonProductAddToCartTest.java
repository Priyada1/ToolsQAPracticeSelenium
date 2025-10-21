import org.example.base.AmazonBaseClass;
import org.example.base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AmazonProductAddToCartTest extends AmazonBaseClass {

    @BeforeClass
    public void setUp(){
        setup();
    }

    @Test
    public void addToCartTest() throws InterruptedException {
        System.out.println("started :::     ");

        WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
        searchBox.sendKeys("samsung s24 ultra 5g mobile");
        WebElement searchButton = driver.findElement(By.id("nav-search-submit-button"));
        searchButton.click();

        Thread.sleep(2000);

        JavascriptExecutor je =(JavascriptExecutor) driver;
        je.executeScript("window.scrollBy(0,500)");

        WebElement addtoCart = driver.findElement(By.id("a-autoid-3-announce"));
        addtoCart.click();
        Thread.sleep(2000);

        WebElement cart = driver.findElement(By.id("nav-cart-count-container"));
        cart.click();

        //validate cart has samsung s24 ultra 5g mobile
       // WebElement cartItem = driver.findElement(By.xpath("//span[text()='Samsung']"));
        WebElement item1 = driver.findElement(By.xpath("(//li[@class='sc-item-product-title-cont'])[1]"));
        Assert.assertTrue(item1.getText().contains("Samsung"));











    }


}