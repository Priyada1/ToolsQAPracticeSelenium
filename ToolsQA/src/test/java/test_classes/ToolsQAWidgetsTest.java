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

    @AfterClass
    public void cleanUp() {
        driver.quit();
    }








}