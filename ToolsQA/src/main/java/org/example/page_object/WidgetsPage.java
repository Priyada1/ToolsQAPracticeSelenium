package org.example.page_object;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
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
    @FindBy(id = "oldSelectMenu")
    public WebElement oldSelectMenu;

    public void clickOnWidgetsSection() {
        widgets.click();
    }

    public void clickOnSelectMenuAndValidatePage() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        // Scroll down by 500 pixels
        // js.executeScript("window.scrollBy(0, 2500);");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        // js.executeScript( "arguments[0].scrollIntoView(true);", selectMenu);

        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class ='text' and text()='Select Menu']")));
        WebElement element = driver.findElement(By.xpath("//span[@class ='text' and text()='Select Menu']"));
        explicitWait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        js.executeScript("window.scrollBy(0, -500);");
        Assert.assertTrue(selectMenuHeading.isDisplayed());
        Assert.assertEquals(selectMenuHeading.getText().trim(), "Select Menu");
    }

    public void clickOnSelectOptionsAndPickValueFromDropDown() {
        selectOptions.click();
        WebElement element = driver.findElement(By.xpath("//div[@class =' css-26l3qy-menu']/descendant::div[2]/child::div[2]/div[1]"));
        element.click();

    }

    public void clickOnOldSelectMenuAndSelectValue(String val) {

        Select select = new Select(oldSelectMenu);
        select.selectByVisibleText(val);

        List<WebElement> list= select.getOptions();
        for(WebElement option:list)
        {
            System.out.println(option.getText());
        }

    }

    //Slider

    @FindBy(xpath = "//span[@class = 'text' and text() ='Slider']")
    private WebElement slider;

    @FindBy(xpath = "//input[@type='range']")
    private WebElement sliderRange;

    @FindBy(id ="sliderValue")
    private WebElement sliderValue;

    @FindBy(xpath ="//h1[@class ='text-center' and text() = 'Slider']")
    private WebElement sliderHeading;


    public void clickOnSliderPageAndValidate(){

        slider.click();
        Assert.assertTrue(sliderHeading.isDisplayed());
        Assert.assertEquals(sliderHeading.getText().trim(),"Slider");

    }

    public void moveSliderAndValidateSliderValue(){

        Actions action = new Actions(driver);
        System.out.println("slider-location before : "+ sliderRange.getLocation()); //(455, 523)
//        System.out.println("slider-size: "+ sliderRange.getSize()); //(605, 38)
//        action.dragAndDropBy(sliderRange,50,363).perform();
//        System.out.println("slider-location: "+ sliderRange.getLocation());
        action.moveToElement(sliderRange)
                .clickAndHold()
                .moveByOffset(50, 0)  // Horizontal
                .release()
                .perform();
        System.out.println("slider-location after : "+ sliderRange.getLocation()); //(455, 363)
        System.out.println("slider- value: "+ sliderValue.getDomAttribute("value"));

        //2nd way

        JavascriptExecutor je = (JavascriptExecutor) driver;
        je.executeScript("arguments[0].setAttribute('value','97')",sliderValue);

        System.out.println("slider-location after : "+ sliderRange.getLocation()); //(455, 363)
        System.out.println("slider-value: "+ sliderValue.getDomAttribute("value"));







    }


}