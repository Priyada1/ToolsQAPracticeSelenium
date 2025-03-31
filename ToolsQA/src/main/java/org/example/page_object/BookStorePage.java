package org.example.page_object;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;

public class BookStorePage {
    private WebDriver driver;
    private WebDriverWait wait;

    public BookStorePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @FindBy(xpath = "//li[@id='item-2']/span[contains(text(),'Book Store')]")
    private WebElement bookStore;

    @FindBy(id = "searchBox")
    private WebElement searchBook;

    @FindBy(xpath = "//span[@class ='mr-2']/a")
    private List<WebElement> bookLinks;


    public void validateBookStorePage(){
        Assert.assertTrue(bookStore.isDisplayed(),"book store is not displayed");
        Assert.assertEquals(bookStore.getText().trim(),"Book Store");
    }

    public void clickBookStore(){
        bookStore.click();
    }

    public void searchBookInsideBookStore(String book){
        WebElement search = wait.until(ExpectedConditions.elementToBeClickable(this.searchBook));
        search.sendKeys(book);
        search.click();
    }

    public void validateBrokenLinkForBook(){

        for(WebElement a:bookLinks)
        {
            String url = a.getAttribute("href");

            try{
                HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
                connection.setRequestMethod("HEAD");
                connection.connect();

                if(connection.getResponseCode()>=400)
                {
                    System.out.println("Links are invalid: "+url);
                }
                else {
                    System.out.println("links are valid: "+url);
                }


            }
             catch (MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


        }


    }

}