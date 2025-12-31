package test_classes;

import org.example.base.BaseClass;
import org.example.page_object.ToolQALoginPage;
import org.example.page_object.WidgetsPage;
import org.example.utilities.ReadConfig;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ToolsQAWidgetsTest extends BaseClass {
    ReadConfig config;
    String userName;
    String password;
    ToolQALoginPage loginPage;
    WidgetsPage widgetsPage;
    @BeforeClass
    public void setUp() throws InterruptedException {

        config = new ReadConfig();
        userName = config.getUserName();
        password = config.getPassword();
        setup();
        loginPage = new ToolQALoginPage(driver);
        loginPage.enterUsername(userName);
        loginPage.enterPassword(password);
        loginPage.clickLoginBtn();

    }

    @Test
    public void testWidgetPageDropDown() {

        System.out.println("widget test started");
        widgetsPage = new WidgetsPage(driver);
        widgetsPage.clickOnWidgetsSection();
        widgetsPage.clickOnSelectMenuAndValidatePage();
        widgetsPage.clickOnSelectOptionsAndPickValueFromDropDown();
        widgetsPage.clickOnOldSelectMenuAndSelectValue("Indigo");
    }

    @Test
    public void testSlider(){

        System.out.println("slider test started");
        widgetsPage = new WidgetsPage(driver);
        widgetsPage.clickOnWidgetsSection();
        widgetsPage.clickOnSliderPageAndValidate();
        widgetsPage.moveSliderAndValidateSliderValue();
    }

    @Test
    public void datepickerWidgetTest() {
        System.out.println("date picker test started");
        
        // Step 1: Open https://demoqa.com/login URL (already done in @BeforeClass setup)
        // Step 2: Login to portal (already done in @BeforeClass setUp)
        
        // Step 3: Click on widgets
        widgetsPage = new WidgetsPage(driver);
        widgetsPage.clickOnWidgetsSection();
        
        // Step 4: Click on date picker
        widgetsPage.clickOnDatePickerPageAndValidate();
        
        // Step 5: Select today's date
        widgetsPage.selectTodaysDate();
        
        System.out.println("date picker test completed");
    }

    @AfterClass
    public void cleanUp() {
        driver.quit();
    }








}