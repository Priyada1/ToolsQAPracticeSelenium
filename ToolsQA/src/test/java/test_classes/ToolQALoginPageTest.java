package test_classes;

import org.example.base.BaseClass;
import org.example.page_object.BookStorePage;
import org.example.page_object.ToolQALoginPage;
import org.example.page_object.ToolsQAProfilePage;
import org.example.utilities.ReadConfig;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ToolQALoginPageTest extends BaseClass {

String userName;
String password;
ToolQALoginPage loginPage;
ToolsQAProfilePage profilePage;
BookStorePage bookStorePage;
    @BeforeClass
    public void setUPClass() {
        ReadConfig readConfig = new ReadConfig();
        userName = readConfig.getUserName();
        password = readConfig.getPassword();
        setup();
       // driver.get(readConfig.getBaseUrl());
    }

    @Test
    public void loginTest() throws InterruptedException {
         Thread.sleep(2000);
        loginPage = new ToolQALoginPage(driver);
        loginPage.validateLoginPage();
        loginPage.validateLoginPageWelcome();
        loginPage.enterUsername(userName);
        loginPage.enterPassword(password);
        loginPage.clickLoginBtn();

        Thread.sleep(3000);
        profilePage = new ToolsQAProfilePage(driver);
        profilePage.validateProfileText();
        profilePage.validateLoggedInUserDetails(userName);
        profilePage.clickOnBookStore();

        bookStorePage = new BookStorePage(driver);
        bookStorePage.validateBookStorePage();
       // bookStorePage.clickBookStore();
        bookStorePage.searchBookInsideBookStore("JavaScript");
        bookStorePage.validateBrokenLinkForBook();

        //div[@class='header-text' and text()='Elements']

    }




    @AfterClass
    public void tearDownClass() {
        tearDown();
    }
}