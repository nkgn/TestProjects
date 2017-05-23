package testscripts;

import data.DataProviderClass;
import data.GenerateRegData;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pageresources.CopyDriverInstance;
import pages.ActivateAccountPage;
import pages.HomePage;
import pages.LoginAndRegistrationPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.MyAccountPage;

import java.io.File;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.*;

public class RegisterAsEditorTest
{
    private WebDriver driver ;
    private String baseURL = "";
    String emailID="";

    //Logger class to log error messages to logs folder under project
    private static final Logger log = LogManager.getLogger(RegisterAsEditorTest.class.getName());

    private String expectedTitle = "";
    HomePage homePage;
    LoginAndRegistrationPage register;
    ActivateAccountPage activateAcc;
    MyAccountPage myAccPage;

    /***** setup the environment before any registration *******/
    @BeforeTest
    public void setUp()  {
        /***Launch the browser ****/
        //System.setProperty("webdriver.gecko.driver", "C:/Users/nitu/Desktop/SeleniumWebDriver/geckodriver.exe");
        //driver = new FirefoxDriver();
        System.setProperty("webdriver.chrome.driver", "C:/Users/nitu/Desktop/SeleniumWebDriver/chromedriver_win32/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver( options );
        baseURL="http://www.liveinews.com";

        driver.get(baseURL);
        expectedTitle="LiveiNews";
        try {
            assertEquals(driver.getTitle(), expectedTitle);
//            driver.manage().window().maximize();// .manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        }catch(Exception e){
            System.out.println("Exception generated in setUp ");
            e.printStackTrace();
        }
        /*****create page references **********/
        homePage = new HomePage(driver);
        activateAcc = new ActivateAccountPage(driver);
        myAccPage = new MyAccountPage(driver);
        register = new LoginAndRegistrationPage(driver);
        CopyDriverInstance.driver = driver;


    }

    /***** Test automate populating fields for registering ediotr******/
    @Test(dataProvider = "SearchProvider", dataProviderClass = DataProviderClass.class)
    public void registerEditorTest(String email, String password, String accountName,
                                   String firstName, String lastName, String address, String phone,
                                   String zip, Integer country,Integer state, Integer city) throws InterruptedException
    {
        System.out.println("Inside register editor test");
        try {
            homePage.clickOnLoginTab();
            register.selectAccountType(1);
            //register.fillEmailTextBox(email + "@getnada.com");  // create getnada email id
            register.fillEmailTextBox(email + "@mailinator.com");  // create getnada email id

            register.fillPwdTextBox(password);
            register.fillConfirmPwdTextBox(password);
            register.fillAccountName(accountName);
            register.fillFirstName(firstName);
            register.fillLastName(lastName);
            register.fillAddress(address);
            register.fillPhone(phone);
            register.selectCountry(country);
            Thread.sleep(5000);
            register.selectState(state);
            Thread.sleep(5000);
            register.selectCity(city);
            register.fillZipCode(zip);
            register.clickNext();
            Thread.sleep(9000);
            //register.scrollAcceptTermsDialog();
            register.clickOnAcceptTerms();
            register.clickOnNext();
            Thread.sleep(5000);
            register.clickNext();
            Thread.sleep(7000);
            homePage.clickOnLoginTab();
        }catch(Exception e){
            e.printStackTrace();
            Assert.fail();
        }
    }

    //****To close driver instance, close and quit based on the test tag in testng.xml *********//*
    @AfterTest
    public void tearDown(){
        driver.close();
        driver.quit();
    }
}
