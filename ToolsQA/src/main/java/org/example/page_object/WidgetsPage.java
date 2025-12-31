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
        By selectMenuLocator = By.xpath("//span[@class ='text' and text()='Select Menu']");
        
        // Wait for Widgets section to expand - wait for any menu item to be visible
        try {
            WebDriverWait waitForMenu = new WebDriverWait(driver, Duration.ofSeconds(15));
            // Wait for at least one menu item in Widgets section to be visible
            waitForMenu.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class,'element-list')]//span[@class='text']")));
        } catch (Exception e) {
            System.out.println("Warning: Menu items may not be fully loaded");
        }
        
        // Wait for the Select Menu element to be present in the DOM
        WebElement element = explicitWait.until(ExpectedConditions.presenceOfElementLocated(selectMenuLocator));
        
        // Scroll the element into view using JavaScript
        js.executeScript("arguments[0].scrollIntoView({behavior: 'auto', block: 'center', inline: 'center'});", element);
        
        // Wait a bit for scroll animation
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        
        // Try to wait for visibility, but if it fails, use JavaScript click
        try {
            WebDriverWait longerWait = new WebDriverWait(driver, Duration.ofSeconds(15));
            element = longerWait.until(ExpectedConditions.visibilityOfElementLocated(selectMenuLocator));
            // Element is visible, try normal click
            longerWait.until(ExpectedConditions.elementToBeClickable(element));
            try {
                element.click();
            } catch (Exception e) {
                // Fallback to JavaScript click
                js.executeScript("arguments[0].click();", element);
            }
        } catch (TimeoutException e) {
            // Element exists but not visible - use JavaScript click directly
            System.out.println("Element not visible after wait, using JavaScript click");
            element = driver.findElement(selectMenuLocator);
            js.executeScript("arguments[0].click();", element);
        }
        
        // Wait for the page to load and validate
        explicitWait.until(ExpectedConditions.visibilityOf(selectMenuHeading));
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

    //Date Picker
    @FindBy(xpath = "//span[@class ='text' and text()='Date Picker']")
    private WebElement datePicker;

    @FindBy(xpath = "//h1[@class ='text-center' and text() = 'Date Picker']")
    private WebElement datePickerHeading;

    @FindBy(id = "datePickerMonthYearInput")
    private WebElement datePickerInput;

    public void clickOnDatePickerPageAndValidate() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        By datePickerLocator = By.xpath("//span[@class ='text' and text()='Date Picker']");
        
        // Wait for Widgets section to expand - wait for any menu item to be visible
        try {
            WebDriverWait waitForMenu = new WebDriverWait(driver, Duration.ofSeconds(15));
            waitForMenu.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class,'element-list')]//span[@class='text']")));
        } catch (Exception e) {
            System.out.println("Warning: Menu items may not be fully loaded");
        }
        
        // Wait for the Date Picker element to be present in the DOM
        WebElement element = explicitWait.until(ExpectedConditions.presenceOfElementLocated(datePickerLocator));
        
        // Scroll the element into view using JavaScript
        js.executeScript("arguments[0].scrollIntoView({behavior: 'auto', block: 'center', inline: 'center'});", element);
        
        // Wait a bit for scroll animation
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        
        // Try to wait for visibility, but if it fails, use JavaScript click
        try {
            WebDriverWait longerWait = new WebDriverWait(driver, Duration.ofSeconds(15));
            element = longerWait.until(ExpectedConditions.visibilityOfElementLocated(datePickerLocator));
            longerWait.until(ExpectedConditions.elementToBeClickable(element));
            try {
                element.click();
            } catch (Exception e) {
                js.executeScript("arguments[0].click();", element);
            }
        } catch (TimeoutException e) {
            System.out.println("Element not visible after wait, using JavaScript click");
            element = driver.findElement(datePickerLocator);
            js.executeScript("arguments[0].click();", element);
        }
        
        // Wait for the page to load and validate
        explicitWait.until(ExpectedConditions.visibilityOf(datePickerHeading));
        Assert.assertTrue(datePickerHeading.isDisplayed());
        Assert.assertEquals(datePickerHeading.getText().trim(), "Date Picker");
    }

    public void selectTodaysDate() {
        // Get today's date
        java.time.LocalDate today = java.time.LocalDate.now();
        int day = today.getDayOfMonth();
        int month = today.getMonthValue();
        int year = today.getYear();
        
        // Format: MM/DD/YYYY for the date picker
        String formattedDate = String.format("%02d/%02d/%d", month, day, year);
        
        // Click on the date picker input to open calendar
        explicitWait.until(ExpectedConditions.elementToBeClickable(datePickerInput));
        datePickerInput.click();
        
        // Wait a bit for calendar to open
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        
        JavascriptExecutor js = (JavascriptExecutor) driver;
        
        // Try multiple approaches to select today's date
        // Approach 1: Look for today's date with "today" class
        try {
            WebElement todayDateElement = explicitWait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[contains(@class,'react-datepicker__day--today') and not(contains(@class,'outside-month'))]")));
            todayDateElement.click();
            System.out.println("Selected today's date using 'today' class");
            return;
        } catch (Exception e) {
            System.out.println("Could not find today's date with 'today' class, trying alternative approach");
        }
        
        // Approach 2: Find today's date by day number in current month
        try {
            String todayXpath = String.format("//div[contains(@class,'react-datepicker__day') and not(contains(@class,'outside-month')) and text()='%d']", day);
            WebElement todayDateElement = explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath(todayXpath)));
            todayDateElement.click();
            System.out.println("Selected today's date using day number");
            return;
        } catch (Exception e) {
            System.out.println("Could not find today's date by day number, using JavaScript fallback");
        }
        
        // Approach 3: Use JavaScript to set the date directly
        try {
            js.executeScript("arguments[0].value = arguments[1];", datePickerInput, formattedDate);
            // Trigger input and change events
            js.executeScript("arguments[0].dispatchEvent(new Event('input', { bubbles: true }));", datePickerInput);
            js.executeScript("arguments[0].dispatchEvent(new Event('change', { bubbles: true }));", datePickerInput);
            System.out.println("Selected today's date using JavaScript: " + formattedDate);
        } catch (Exception e) {
            System.out.println("Error setting date via JavaScript: " + e.getMessage());
            throw new RuntimeException("Failed to select today's date", e);
        }
    }


}