package testscripts;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import pages.ActivateAccountPage;
import pages.HomePage;
import pages.LoginAndRegistrationPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import data.GenerateRegistrationTestData;

import java.io.File;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.*;

public class RegisterAsEditorTest
{
    private WebDriver driver ;
    private String baseURL = "";
    String emailID="";
    private static final Logger log = LogManager.getLogger(RegisterAsEditorTest.class.getName());

    private String expectedTitle = "";
    HomePage homePage ;
    LoginAndRegistrationPage register;
    ActivateAccountPage activateAcc ;
    GenerateRegistrationTestData loginData;

    @BeforeClass
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
            assertEquals(driver.getTitle(), expectedTitle);
//            driver.manage().window().maximize();// .manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        }catch(Exception e){
            System.out.println("Exception generated in setUp ");
            e.printStackTrace();
        }
        homePage = new HomePage(driver);
        activateAcc = new ActivateAccountPage(driver);
        loginData = new GenerateRegistrationTestData();
        emailID = loginData.email;
        loginData.email = loginData.email + "@mailinator.com";
        //loginData.email = loginData.email + "@getnada.com";
        register = new LoginAndRegistrationPage(driver);
        System.out.println(loginData.accountName +  "  " +   emailID);
    }
    @Test
    public void registerEditorTest() throws InterruptedException {
        System.out.println("Inside register editor test");
        try {
            homePage.clickOnLoginTab();
            register.selectAccountType(1);
            //emailID= loginData.email;
            register.fillEmailTextBox(loginData.email);
            register.fillPwdTextBox(loginData.password);
            register.fillConfirmPwdTextBox(loginData.password);
            register.fillAccountName(loginData.accountName);
            register.fillFirstName(loginData.firstName);
            register.fillLastName(loginData.lastName);
            register.fillAddress(loginData.address);
            register.fillPhone("111-111-1111");
            register.selectCountry(2);
            Thread.sleep(5000);
            register.selectState(2);
            Thread.sleep(5000);
            register.selectCity(1);
            register.fillZipCode("94539");
            //registerEditor.fillPaypal(loginData.paypal);
            System.out.println(driver.getWindowHandle());
            register.clickNext();
            Thread.sleep(9000);
            //register.scrollAcceptTermsDialog();
            //System.out.println(driver.getWindowHandle());
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
    // needs aproval before activation of account
    @Test(dependsOnMethods = { "registerEditorTest" },priority = 1,enabled = false)
    public void activateAccountTest()throws InterruptedException{
        System.out.println("EmailID =" + emailID);

        // mailinator
        String s1 = "http://www.mailinator.com/inbox2.jsp?public_to=" + emailID + "#/#public_maildirdiv";
        //getnada
        //String s1 = "https://" + "app.getnada.com/inbox/"+ emailUser + "@getnada.com";
        System.out.println("link to mail site is  "+ s1);
        try {
            driver.get(s1);
            Thread.sleep(5000);
            log.info("link to getnada/mailinator" + s1);
            // from AcctivateAccountPage

            activateAcc.clickOnMailinatorInboxMessage();
            //activateAcc.clickOnGetnadaInboxMessage();

            Thread.sleep(3000);
            driver.switchTo().frame("publicshowmaildivcontent");
            String newlink = activateAcc.clickOnLinkInEmail();
            System.out.println("newlink = " + newlink);
            log.debug("newlink = " + newlink);
            driver.get(newlink);
        }catch(Exception e){
            e.printStackTrace();
            Assert.fail();
        }

    }

    @Test(priority=2,enabled =false)
    public void loginTest(){
        System.out.println("inside login test");
        try {
            register.fillLoginUserName(loginData.accountName);
            register.fillLoginPwd(loginData.password);
            register.clickOnLogInButton();
        }catch(Exception e){
            e.printStackTrace();
            Assert.fail();
        }

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

    }
    @AfterClass
    public void tearDown(){
        driver.close();
        driver.quit();
    }
}
