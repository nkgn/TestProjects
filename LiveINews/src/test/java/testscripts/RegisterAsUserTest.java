package testscripts;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;
import pageresources.CopyDriverInstance;
import pages.ActivateAccountPage;
import pages.HomePage;
import pages.LoginAndRegistrationPage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;
import data.DataProviderClass;
import pages.MyAccountPage;

import static org.testng.Assert.*;

public class RegisterAsUserTest
{
    private WebDriver driver ;
    private String baseURL = "";

    //Logger class to log error messages to logs folder under project
    private static final Logger log = LogManager.getLogger(RegisterAsCityJournalistTest.class.getName());

    private String expectedTitle = "";
    HomePage homePage ;
    LoginAndRegistrationPage register;
    ActivateAccountPage activateAcc ;
    MyAccountPage myAccPage ;


    @BeforeTest
    public void setUp()  {

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
            Thread.sleep(5000);
            assertEquals(driver.getTitle(), expectedTitle);
            //driver.manage().window().maximize();// .manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        }catch(Exception e){
            System.out.println("Exception generated in setUp ");
            e.printStackTrace();
            Assert.fail();
        }
        homePage = new HomePage(driver);
        activateAcc = new ActivateAccountPage(driver);
        myAccPage = new MyAccountPage(driver);
        register = new LoginAndRegistrationPage(driver);
        CopyDriverInstance.driver = driver ; // to store driver instance
    }
    //@Test(priority = 0)
    /***** Test automate populating fields for registering city journalist******/
    @Test(enabled=true,dataProvider = "SearchProvider", dataProviderClass = DataProviderClass.class)
    public void registerUserTest(String email,String password,String accountName) throws InterruptedException {
        System.out.println("Inside test");
        System.out.println("Inside test register user");
        log.info("This message is from test method");
        homePage.clickOnLoginTab();
        register.selectAccountType(0); //index for user is 0 in account type
        register.fillEmailTextBox(email + "@getnada.com");  // create getnada email id
        register.fillPwdTextBox(password);
        register.fillConfirmPwdTextBox(password);
        register.fillAccountName(accountName);
        register.clickNext();
        Thread.sleep(9000);
        //Thread.sleep(6000);
        //register.scrollAcceptTermsDialog();
        register.clickOnAcceptTerms();
        register.clickOnNext();
        Thread.sleep(5000);
        register.clickNext(); // click submit to activate account
        Thread.sleep(7000);
        homePage.clickOnLoginTab();
    }

/*
    @Test(dependsOnMethods = { "registerUserTest" },priority = 1)
    public void activateAccountTest()throws InterruptedException{
        System.out.println("inside activate " );
        Utilities.activateAccount(driver,emailUserName);
    }

    @Test(priority=2,enabled=true)
    public void loginTest() throws InterruptedException{
        System.out.println("inside login test");
        homePage.clickOnLoginTab();
        Utilities.loginAcc(driver,username,pwd,register);
        Thread.sleep(3000);
    }

    @Test(enabled=false,priority = 3)
    public void forgotPwdTest(){
        register.clickOnLostPasswordLink();
    }

    @AfterMethod
    public void tearIt(ITestResult result)
    {
        System.out.println("inside after method , result status is " + result.getStatus());
        // ITestResult describes the result of the test
        if(ITestResult.FAILURE==result.getStatus())
        {
            try
            {
                //Indicates a driver that can capture a screenshot and store it in different ways.

                // selenium interface TakesScreenshot
                TakesScreenshot ts=(TakesScreenshot)driver;
                File source=ts.getScreenshotAs(OutputType.FILE);

                // result.getName() will return name of test case so that screenshot name will be same
                FileUtils.copyFile(source, new File("./Screenshots/"+result.getName()+".png"));
                //creates screenshot folder under project and stores screenshot
                //public class FileUtils 	extends Object  General file manipulation utilities.

                System.out.println("Screenshot taken");
            }
            catch (Exception e)
            {
                System.out.println("Exception while taking screenshot "+e.getMessage());
            }
        }
    }*/

    @AfterTest
    public void tearDown()throws InterruptedException{
        driver.close();
        driver.quit();
        Thread.sleep(5000);
    }
}
