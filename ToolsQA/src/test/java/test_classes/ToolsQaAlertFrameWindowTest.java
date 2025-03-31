package test_classes;

import org.example.base.BaseClass;
import org.example.page_object.AlertsFramesWindowPage;
import org.example.page_object.ToolQALoginPage;
import org.example.utilities.ReadConfig;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.apache.poi.ss.formula.CollaboratingWorkbooksEnvironment.setup;

public class ToolsQaAlertFrameWindowTest extends BaseClass {
    ReadConfig config;
    String userName;
    String password;
    ToolQALoginPage loginPage;
    AlertsFramesWindowPage alertsFramesWindowPage;
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


    @Test(invocationCount = 1,enabled = true,invocationTimeOut = 10000)
    public void validateAlerts(){
        System.out.println("Alerts test started ");
        alertsFramesWindowPage = new AlertsFramesWindowPage(driver);
        alertsFramesWindowPage.selectAlertsFrameWindow();
        alertsFramesWindowPage.selectAlertsAndValidateAlertsPage();
        alertsFramesWindowPage.validateNormalAlertButton();
        alertsFramesWindowPage.validateConfirmAlert();
        alertsFramesWindowPage.validatePromptAlert();
    }

    @Test
    public void validatedNestedFrames(){
        System.out.println("Frame test started ");
        alertsFramesWindowPage = new AlertsFramesWindowPage(driver);
        alertsFramesWindowPage.selectAlertsFrameWindow();
        alertsFramesWindowPage.clickOnNestedFrameAndValidateHeading();
        alertsFramesWindowPage.validateInnerFrame();
    }

    // TO DO Browser Windows

    // TO DOModal Dialogue


   @AfterClass
    public void clean()
   {
       driver.quit();
   }
}