package testscripts;

import data.DataProviderClass;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pageresources.CopyDriverInstance;
import pages.ActivateAccountPage;
import pages.HomePage;
import pages.LoginAndRegistrationPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import data.GenerateRegData;
import pages.MyAccountPage;

import java.io.File;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.*;

public class RegisterAsMediaTest
{
    private WebDriver driver ;
    private String baseURL = "";
    String emailID="";

    //Logger class to log error messages to logs folder under project
    private static final Logger log = LogManager.getLogger(RegisterAsMediaTest.class.getName());

    private String expectedTitle = "";
    HomePage homePage;
    LoginAndRegistrationPage register;
    ActivateAccountPage activateAcc;
    MyAccountPage myAccPage;

    /***** setup the environment before any registration *******/
    @BeforeTest
    public void setUp()  {
        /***launch firefox/chrome browser****/
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
            //driver.manage().window().maximize();// .manage().window().maximize();
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

    /***** Test automate populating fields for registering media******/
    @Test(dataProvider = "SearchProvider", dataProviderClass = DataProviderClass.class)
    public void registerMediaTest(String email, String password, String accountName,
                                  String companyName, String zip, String address, String phone,
                                  Integer country,Integer state, Integer city,
                                  String identity,String contact,
                                  String legalContact  ) throws InterruptedException {
        System.out.println("Inside register media test");
        homePage.clickOnLoginTab();
        register.selectAccountType(2);
        //register.fillEmailTextBox(email + "@getnada.com");  // create getnada email id
        register.fillEmailTextBox(email + "@mailinator.com");  // create getnada email id

        register.fillPwdTextBox(password);
        register.fillConfirmPwdTextBox(password);
        register.fillAccountName(accountName);
        register.fillCompanyName(companyName);
        Thread.sleep(3000);
        register.fillZipCode(zip);
        register.fillAddress(address);
        register.fillPhone(phone);
        register.selectCountry(country);
        Thread.sleep(7000);
        register.selectState(state);
        Thread.sleep(7000);
        register.selectCity(city);
        register.fillIdentityNumber(identity);
        register.fillContact(contact);
        register.fillLegalContact(legalContact);
        register.clickNext();
        Thread.sleep(9000);
        register.clickOnAcceptTerms();
        register.clickOnNext();
        Thread.sleep(5000);
        register.clickNext();

        //Assert that user succesfully registered before activation of account
        try {

            String expected = register.getRegistrationSuccessfulMessage();
            String actual = "Registration is complete" ;
            Assert.assertEquals(expected, actual);

        }catch(Exception e){
            e.printStackTrace();
            Assert.fail("Unsuccessful registration");
        }

        Thread.sleep(7000);

        //homePage.clickOnLoginTab();
    }
    //****To close driver instance, close and quit based on the test tag in testng.xml *********//*
    @AfterTest
    public void tearDown(){
      //  driver.close();
       // driver.quit();
    }
}
