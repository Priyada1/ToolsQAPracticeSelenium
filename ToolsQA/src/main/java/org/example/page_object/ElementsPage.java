package org.example.page_object;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ElementsPage {
    private WebDriver driver;
    private WebDriverWait explicitWait;

    public ElementsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }


    @FindBy(xpath = "//div[@class='header-text' and text()='Elements']")
    private WebElement elements;

    @FindBy(xpath = "//span[@class='text' and text() ='Text Box']")
    private WebElement textBox;

    @FindBy(xpath = "//h1[@class='text-center' and text() ='Text Box']")
    private WebElement headingTextBox;

    @FindBy(id = "userName")
    private WebElement fullnameTextBox;

    @FindBy(xpath = "//input[@type='email']")   //input[@placeholder='name@example.com']
    private WebElement emailTextBox;

    @FindBy(id = "currentAddress")
    private WebElement currentAddressTextBox;

    @FindBy(xpath = "//textarea[@id='permanentAddress']")
    private WebElement permanentAdd;

    @FindBy(xpath = "//button[@id='submit' and @type ='button' and text()='Submit']")
    private WebElement submitButton;

    // checkBox webelement

    @FindBy(xpath = "//span[@class='text' and text()='Check Box']")
    private WebElement checkBoxPage;

    @FindBy(xpath = "//h1[@class='text-center' and text() ='Check Box']")
    private WebElement checkBoxHeading;

    @FindBy(css = "#tree-node > div > button.rct-option.rct-option-expand-all")
    private WebElement expandPlusSign;

    @FindBy(css = "#tree-node > div > button.rct-option rct-option-collapse-all")
    private WebElement minimizeMinusSign;

    @FindBy(xpath = "//input[@id='tree-node-desktop' and @type= 'checkbox']")
    private WebElement desktopChkbox;

    @FindBy(xpath = "//span[@class='rct-title' and text() ='React']")
    private WebElement reactCheckBox;

    // Radio bUtton
    @FindBy(xpath = "//li[@id='item-2']/span[text()='Radio Button']")
    private WebElement radioButtonPage;

    @FindBy(xpath = "//h1[@class='text-center' and text() ='Radio Button']")
    private WebElement radioButtonHeading;

    @FindBy(xpath = "//label[text()='Impressive']")
    private WebElement selectRadioButton;

    @FindBy(xpath = "//span[@class='text-success']")
    private WebElement selectedRadioButtonValue;

    //Element method
    public void clickOnElements() {
        elements.click();
    }

    public void clickOnTextBox() {
        textBox.click();
    }

    public void validateTextPage() {
        Assert.assertTrue(headingTextBox.isDisplayed(), "text box is not displaying");
        Assert.assertEquals(headingTextBox.getText().trim(), "Text Box", "textBox heading is not matching");
    }

    public void enterFullName(String fullName) {
        fullnameTextBox.sendKeys(fullName);
    }

    public void enterEmail(String email) {
        System.out.println("email class :  " + emailTextBox.getClass() + " tagname  " + emailTextBox.getTagName());
        emailTextBox.sendKeys(email);
    }

    public void enterCurrentAddress(String address) {
        currentAddressTextBox.sendKeys(address);
    }

    public void enterPermanentAddress(String permanentAddress) {
        permanentAdd.sendKeys(permanentAddress);
    }

    public void clickOnSubmitButton() {
        System.out.println("Submit button -text: " + submitButton.getText());
        JavascriptExecutor je = (JavascriptExecutor) driver;
        je.executeScript("arguments[0].click();", submitButton);
        // submitButton.click();
    }

    // CheckBox Methods

    public void clickOnCheckBoxPageAndValidateHeading() {
        checkBoxPage.click();
        Assert.assertTrue(checkBoxHeading.isDisplayed(), "CheckBox heading is not displaying");
        Assert.assertEquals(checkBoxHeading.getText().trim(), "Check Box");
    }

    public void selectCheckBox() {

        expandPlusSign.click();
        JavascriptExecutor je = (JavascriptExecutor) driver;
        je.executeScript("arguments[0].click();", desktopChkbox);
        //desktopChkbox.click();
        reactCheckBox.click();
    }

    // Radio Buttton Methods
    public void selectRadioButtonPageAndValidateHeading() {
        radioButtonPage.click();
        Assert.assertTrue(radioButtonHeading.isDisplayed());
        Assert.assertEquals(radioButtonHeading.getText().trim(), "Radio Button");
    }

    public void selectedRadioButtonValue(String input) {

        System.out.println("xpath->>>   "+"//label[text()='" + input + "']");
        WebElement element = driver.findElement(By.xpath("//label[text()='" + input + "']"));
        explicitWait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        Assert.assertEquals(selectedRadioButtonValue.getText().trim(),input);


    }

    // Web Table

    @FindBy(xpath = "//li[@id='item-3']/span[text()='Web Tables']")
    private WebElement webTablePage;

    @FindBy(id = "addNewRecordButton")
    private WebElement addButton;

    @FindBy(id = "firstName")
    private WebElement firstName;

    @FindBy(id = "lastName")
    private WebElement lastName;

    @FindBy(id = "userEmail")
    private WebElement email;

    @FindBy(xpath = "//input[@id='age']")
    private WebElement age;

    @FindBy(xpath = "//input[@id='salary']")
    private WebElement salary;

    @FindBy(xpath = "//input[@id='department']")
    private WebElement department;

    @FindBy(id ="submit")
    private WebElement submit;

    @FindBy(xpath = "//h1[@class='text-center' and text() ='Web Tables']")
    private WebElement webTableHeading;

    public void selectWebTablePageAndValidate(){

        webTablePage.click();
        Assert.assertEquals(webTableHeading.getText().trim(),"Web Tables");

    }

    public void addValuesToWebTable(List<String> input){

        addButton.click();
        firstName.sendKeys(input.get(0));
        lastName.sendKeys(input.get(1));
        email.sendKeys(input.get(2));
        age.sendKeys(input.get(3));
        salary.sendKeys(input.get(4));
        department.sendKeys(input.get(5));
        submit.click();
        System.out.println("List value added inside Web Table");
    }

    public void validateWebTable(List<String> output)
    {
        WebElement table = explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='rt-table']")));
        // Locate the first data row (excluding header and padding rows)
        WebElement dataRow = table.findElement(By.xpath("//div[@class='rt-tbody']/div[@class='rt-tr-group'][4]/div[contains(@class,'rt-tr') and not(contains(@class, '-padRow'))]"));

        List<WebElement> elementList = dataRow.findElements(By.xpath("./div[@role='gridcell']"));
        /*
        By.xpath("./div[@role='gridcell']"): This is the XPath used to find the data cells.
./: This is crucial. It means "search within the current context node" (which is dataRow in this case). This ensures that we only find the cells within the selected row, and not cells anywhere else on the page.
div[@role='gridcell']: This selects all div elements that have the role attribute equal to "gridcell". Based on the HTML you provided, these are the individual data cells within the table row.
         */
        List<String> actualValue = new ArrayList<>();
        if(!elementList.isEmpty())
        {
            for(WebElement a: elementList){
                actualValue.add(a.getText());
            }
           // actualValue.removeLast();

            // Validation
            //Assert.assertEquals(actualValue,output,"Web table values do not match expected values.");
            Assert.assertEquals(actualValue.get(0).trim(), output.get(0).trim(), "Web table values do not match expected values.");
            Assert.assertEquals(actualValue.get(1).trim(), output.get(1).trim(), "Web table values do not match expected values.");
            Assert.assertEquals(actualValue.get(3).trim(), output.get(2).trim(), "Web table values do not match expected values.");
            Assert.assertEquals(actualValue.get(2).trim(), output.get(3).trim(), "Web table values do not match expected values.");
            Assert.assertEquals(actualValue.get(4).trim(),output.get(4).trim(),"Web table values do not match expected values.");

        }
        else{

            System.out.println("Value is not added Inside Web Table");

        }

    }

    //Button Page

    @FindBy(xpath = "//li[@id='item-4']/span[text()='Buttons']")
    private WebElement buttonPage;

    @FindBy(xpath = "//h1[@class='text-center' and text() ='Buttons']")
    private WebElement buttonPageHeading;

    @FindBy(id ="doubleClickBtn")
    private WebElement doubleClickButton;

    @FindBy(id ="rightClickBtn")
    private WebElement rightClickBtn;

    @FindBy(xpath ="//button[text()='Click Me']")
    private WebElement ClickMeBtn;


    @FindBy(id ="dynamicClickMessage")
    private WebElement dynamicClickMessage;


    public void selectButtonPageAndValidateHeader()
    {
        explicitWait.until(ExpectedConditions.elementToBeClickable(buttonPage));
        buttonPage.click();
        Assert.assertTrue(buttonPageHeading.isDisplayed());
    }

    public void validateAllButtons(){
        try {
            Actions act = new Actions(driver);
            act.doubleClick(doubleClickButton).perform();
            Assert.assertEquals(dynamicClickMessage.getText().trim(),"You have done a double click");
            act.contextClick(rightClickBtn).perform();

            act.click();
            Assert.assertEquals(dynamicClickMessage.getText().trim(),"You have done a dynamic click");


        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }


    //Link Page

    @FindBy(xpath = "//li[@id='item-5']/span[text()='Links']")
    private WebElement linkPage;

    @FindBy(xpath = "//h1[@class='text-center' and text() ='Links']")
    private WebElement linkPageHeading;

    @FindBy(xpath = "//div[@id ='linkWrapper']/p/a")
    private WebElement allLinks;

    @FindBy(xpath = "//div[@id ='linkWrapper']/p/a[@id='simpleLink']")
    private WebElement simpleLink;

    @FindBy(xpath = "//div[@id ='linkWrapper']/p/a[@id='dynamicLink']")
    private  WebElement dynamicLink;

    @FindBy(xpath ="//p[@id ='linkResponse']")
    private WebElement linkResponse;

    public void clickOnLinksAndValidateLinksPage(){
        linkPage.click();
        Assert.assertTrue(linkPageHeading.isDisplayed(),"Heading is not Visible");
        Assert.assertEquals(linkPageHeading.getText().trim(),"Links");
    }

    public void validateLinksResponseWhichIsPresentInsideLinkPage() throws InterruptedException {

       // List<String> links = new ArrayList<>();
        List<WebElement> linkElement = driver.findElements(By.xpath("//div[@id ='linkWrapper']/p/a"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        System.out.println("Page scrolled to the bottom.");

        if(!linkElement.isEmpty()){
            for(WebElement l :linkElement){
                 if(!l.getDomAttribute("href").equals(simpleLink.getDomAttribute("href")) && !l.getDomAttribute("href").equals(dynamicLink.getDomAttribute("href")))
                 {
                     System.out.println(">>>>>>>>"+l.getText());
                    // if(!l.getText().equals("Bad Request")) { //BAD Request are failing Not sure
                         l.click();
                        // Thread.sleep(3000);
                    // }
                     //driver.findElement(By.xpath("//p[@id ='linkResponse']"));
                     WebElement response = explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[@id ='linkResponse']")));
                     System.out.println("link response: "+response.getText());
                     Assert.assertTrue(response.getText().contains("Link has responded with staus"));
                    // break;
                 }
            }
        }

    }

    public List<String> findAllLinks(){
       List<String> links = new ArrayList<>();
        List<WebElement> linkElement = driver.findElements(By.xpath("//div[@id ='linkWrapper']/p/a"));
        if(!linkElement.isEmpty()){
            for(WebElement l :linkElement){
                links.add(l.getDomAttribute("href"));
                System.out.println(">>>>>>"+l.getDomAttribute("href"));
            }
        }
        return links;
    }

    public String findSimpleLink(){
        return simpleLink.getDomAttribute("href");
       // return simpleLinkUrl;
    }

    public String findDynamicLink(){
        return dynamicLink.getDomAttribute("href");
    }

    public void validateSimpleLinkResponseInOtherTab(){

        String originalWindow= driver.getWindowHandle();
        System.out.println("originalWindow: "+originalWindow);
        simpleLink.click();
        //Get All Window Handles
        Set<String> allWindows = driver.getWindowHandles();
        for(String s: allWindows)
        {
            if(!s.equals(originalWindow)){
                driver.switchTo().window(s);
                System.out.println("new tab Window: "+s);
                String url= driver.getCurrentUrl();
                Assert.assertEquals(url,"https://demoqa.com/");
                driver.close();
                driver.switchTo().window(originalWindow);

               // break;
            }
        }
        String url2= driver.getCurrentUrl();
        System.out.println(url2);
        Assert.assertEquals(url2,"https://demoqa.com/links");



    }







}