package org.example.page_object;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class WidgetsPage {

    private WebDriver driver;
    private WebDriverWait explicitWait;

    public WidgetsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @FindBy(xpath = "//div[@class='header-text' and text()='Widgets']")
    private WebElement widgets;

    @FindBy(xpath = "//span[@class ='text' and text()='Select Menu']")
    private WebElement selectMenu;

    @FindBy(xpath = "//div[@id='selectMenuContainer']/h1[text()='Select Menu']")
    private WebElement selectMenuHeading;

    @FindBy(xpath = "//div[@id='withOptGroup']/div")
    private WebElement selectOptions;



    //
    @FindBy(id="oldSelectMenu")
    public WebElement oldSelectMenu;

    public void clickOnWidgetsSection() {
        widgets.click();
    }

    public void clickOnSelectMenuAndValidatePage(){
        JavascriptExecutor js =(JavascriptExecutor) driver;
        // Scroll down by 500 pixels
        // js.executeScript("window.scrollBy(0, 2500);");
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
       // js.executeScript( "arguments[0].scrollIntoView(true);", selectMenu);
        try {
            Thread.sleep(2000);
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class ='text' and text()='Select Menu']")));
        WebElement element = driver.findElement(By.xpath("//span[@class ='text' and text()='Select Menu']"));
        explicitWait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        js.executeScript("window.scrollBy(0, -500);");
        Assert.assertTrue(selectMenuHeading.isDisplayed());
        Assert.assertEquals(selectMenuHeading.getText().trim(), "Select Menu");
    }

    public void clickOnSelectOptionsAndPickValueFromDropDown(){
        selectOptions.click();
       // Select select = new Select(selectOptions);

      //  List<WebElement> options = driver.findElements(By.cssSelector(".your-dropdown-options-container .your-option-selector"));
        String valueToSelect = "Group 2, option 1";
      //  WebElement element = driver.findElement(By.xpath("//div[@class=' css-1uccc91-singleValue']"));
       // element.sendKeys(valueToSelect);
        selectOptions.sendKeys(Keys.ENTER);

    }

    public void clickOnOldSelectMenuAndSelectValue(String val){

        Select select = new Select(oldSelectMenu);
        select.selectByVisibleText(val);

    }

}