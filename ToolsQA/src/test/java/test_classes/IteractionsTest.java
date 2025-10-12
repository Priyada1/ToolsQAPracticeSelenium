package test_classes;

import org.example.base.BaseClass;
import org.example.page_object.InteractionsPage;
import org.example.page_object.ToolQALoginPage;
import org.example.utilities.ReadConfig;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;

public class IteractionsTest extends BaseClass {

    ReadConfig readConfig;
    Random random;
    InteractionsPage iteract1;
    ToolQALoginPage toolQALoginPage;

    @BeforeClass
    public void setUP() throws InterruptedException {
        readConfig = new ReadConfig();
        String userName = readConfig.getUserName();
        String password = readConfig.getPassword();
        setup();
        toolQALoginPage = new ToolQALoginPage(driver);
        toolQALoginPage.enterUsername(userName);
        toolQALoginPage.enterPassword(password);
        toolQALoginPage.clickLoginBtn();
        random = new Random();
    }

    @Test
    public void listValueTest(){
        System.out.println("interaction page test started");
        iteract1 = new InteractionsPage(driver);
        iteract1.clickOnInteractions();
        List<WebElement> list = iteract1.getAllListValues();
        Assert.assertTrue(list.size()>0);
        for(WebElement element:list)
        {
            System.out.println(element.getText());
        }



    }

    @AfterClass
    public void cleanUp() {
        driver.quit();
    }



}