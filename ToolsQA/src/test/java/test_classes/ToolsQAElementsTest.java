package test_classes;

import org.example.base.BaseClass;
import org.example.page_object.ElementsPage;
import org.example.page_object.ToolQALoginPage;
import org.example.utilities.ReadConfig;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;

public class ToolsQAElementsTest extends BaseClass {
    ReadConfig config;
    String userName;
    String password;
    ToolQALoginPage loginPage;
    ElementsPage elementsPage;
    Random random;


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
        random = new Random();

    }

    @Test
    public void textBoxTest() {
        System.out.println("test started ");
        elementsPage = new ElementsPage(driver);
        elementsPage.clickOnElements();
        elementsPage.clickOnTextBox();
        elementsPage.validateTextPage();
        elementsPage.enterFullName("Test user" + random.nextInt(100));
        elementsPage.enterEmail("Test_User-" + random.nextInt(100) + "@ymail.com");
        elementsPage.enterCurrentAddress("house-no #50- bangalore");
        elementsPage.enterPermanentAddress("house-no #50- bangalore-permanent ");
        elementsPage.clickOnSubmitButton();

        elementsPage.clickOnCheckBoxPageAndValidateHeading();
        elementsPage.selectCheckBox();

        //Radio button
        elementsPage.selectRadioButtonPageAndValidateHeading();
        elementsPage.selectedRadioButtonValue("Impressive");

    }


    @Test
    public void validateWebTableInsideElementPage() {
        System.out.println("2nd test started ");
        elementsPage = new ElementsPage(driver);
        List<String> input = List.of("John", "Millar", "John@gmail.com", "18", "18000", "QA");
        elementsPage.clickOnElements();
        elementsPage.selectWebTablePageAndValidate();
        elementsPage.addValuesToWebTable(input);
        elementsPage.validateWebTable(input);

    }

    @Test
    public void validateActionOperationOnButton() {
        //Double click and right click
        System.out.println("3rd test started ");
        elementsPage = new ElementsPage(driver);
        elementsPage.clickOnElements();
        elementsPage.selectButtonPageAndValidateHeader();
        elementsPage.validateAllButtons();

    }

    @Test
    public void validateLinksPage() throws InterruptedException {
        System.out.println("3rd test started ");
        elementsPage = new ElementsPage(driver);
        elementsPage.clickOnElements();
        elementsPage.clickOnLinksAndValidateLinksPage();
        elementsPage.validateLinksResponseWhichIsPresentInsideLinkPage();
        elementsPage.validateSimpleLinkResponseInOtherTab(); //vvi
    }


    @AfterClass
    public void cleanUp() {
        driver.quit();
    }


}