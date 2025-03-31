package test_classes;

import org.example.base.BaseClass;
import org.example.page_object.ToolQALoginPage;
import org.example.utilities.ReadConfig;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ToolsQAWidgetsTest extends BaseClass {
    ReadConfig config;
    String userName;
    String password;
    ToolQALoginPage loginPage;
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
    public void test(){

        System.out.println("widget test started");
        System.out.println("widget test started");
        System.out.println("widget test started");

    }


}