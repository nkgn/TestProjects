package testscripts;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pageresources.Utilities;
import pages.ActivateAccountPage;
import pages.HomePage;
import pages.LoginAndRegistrationPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.MyAccountPage;
import data.DataProviderClass;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;


public class RegisterAsCityJournalistTest
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
    String emailUserName="";
    String username="",pwd="";

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
            Assert.assertEquals(driver.getTitle(), expectedTitle);
            //driver.manage().window().maximize();// .manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
        }catch(Exception e){
            System.out.println("Exception generated in setUp ");
            e.printStackTrace();
        }

        homePage = new HomePage(driver);
        activateAcc = new ActivateAccountPage(driver);
        myAccPage = new MyAccountPage(driver);
        register = new LoginAndRegistrationPage(driver);

    }

/***** Test automate populating fields for registering city journalist******/
    @Test(priority=0,dataProvider = "SearchProvider", dataProviderClass = DataProviderClass.class)
    public void registerCityJournalistTest(String email,String password,String accountName,
                                           String firstName, String lastName,String address,
                                           String phone,String zip,Integer country,
                                           Integer state,Integer city,
                                           String paypal) throws InterruptedException {
        System.out.println("Inside test register city journal");
        log.info("This message is from test method");
        homePage.clickOnLoginTab();
        register.selectAccountType(3); //index for city journalist is 3 in account type
        register.fillEmailTextBox(email + "@getnada.com");  // create getnada email id
        emailUserName = email ;  // store email id for activation of account
        register.fillPwdTextBox(password);
        register.fillConfirmPwdTextBox(password);
        register.fillAccountName(accountName);
        username=accountName;  //store username for login
        pwd=password;   //store pwd for login
        register.fillFirstName(firstName);
        register.fillLastName(lastName);
        register.fillAddress(address);
        register.fillPhone(phone);
        register.selectCountry(country);
        Thread.sleep(3000);
        register.selectState(state);
        Thread.sleep(3000);
        register.selectCity(city);
        register.fillZipCode(zip);
        register.fillPaypal(paypal);
        Thread.sleep(2000);
        register.clickNext();
        Thread.sleep(5000);
        //register.scrollAcceptTermsDialog();

        register.clickOnAcceptTerms();
        register.clickOnNext();
        Thread.sleep(5000);
        register.clickNext();  // click on submit button to register city journalist
        //Thread.sleep(7000);
        //homePage.clickOnLoginTab();
    }



/**Test to activate city journalist by opening email inbox and clicking on the link provided****/

    @Test(dependsOnMethods = { "registerCityJournalistTest" },priority = 1,
            enabled=true)
    public void activateAccountTest()throws InterruptedException{

        System.out.println("inside activate " );
        Utilities.activateAccount(driver,emailUserName);

    }


    /*** Test to login after activation of city journalist
           username and pwd are stored while registration of city journalist  ****/
    @Test(priority=2,enabled=true)
    public void loginTest() throws InterruptedException{
        System.out.println("inside login test");
        homePage.clickOnLoginTab();
        Utilities.loginAcc(driver,username,pwd,register);
        Thread.sleep(3000);
        //new WebDriverWait(driver,20).until(ExpectedConditions.elementToBeClickable((myAccPage.getUploadVideosTab())));
    }

    /****Test to upload video after logging in and clicking on upload video tab*******/

    //@Test(priority=3,dependsOnMethods={"loginTest"},enabled=false)
    @Test(priority=3,enabled=true,dataProvider = "SearchProvider", dataProviderClass = DataProviderClass.class)
    public void uploadVideo(String title,String description, Integer category, Integer subCategory,String location)throws InterruptedException{
        System.out.println("insisde upload video");

        myAccPage.clickOnUploadVideos();
        Thread.sleep(3000);
        myAccPage.fillUploadVideosTitle(title);
        //myAccPage.clickOnUploadVideosChoosefileButton();
        try {
            myAccPage.setChooseFile("C:\\QAUBER\\LiveINews\\byger-20030708-liten.avi");
        }catch(Exception e){
            e.printStackTrace();
            Assert.fail();
        }
        myAccPage.fillUploadVideoDescription(description);

        myAccPage.selectUploadVideoCategory(category);
        Thread.sleep(3000);

        myAccPage.selectUploadVideoSubCategory(subCategory);
        myAccPage.fillUploadVideoLocation(location);

        Thread.sleep(2000);
        myAccPage.clickOnSubmitButton();

        Thread.sleep(6000);
        //Assert.assertEquals("Upload");

    }
    /** To create screen shot if test fails* ***
    creates screenshot folder under project and stores screenshot
    public class FileUtils 	extends Object  General file manipulation utilities. ***/

    @AfterMethod
    public void takeScreenShotOnFailure(ITestResult testResult) throws IOException {
        try{
            // ITestResult describes the result of the test
        if (testResult.getStatus() == ITestResult.FAILURE) {
            // selenium interface TakesScreenshot
            File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            /** result.getName() will return name of test case so that screenshot name will be same***/
            FileUtils.copyFile(scrFile, new File("errorScreenshots\\" + testResult.getName() + "-"
                    + Arrays.toString(testResult.getParameters()) +  ".jpg"));
            System.out.println("Screenshot taken");
        }
            //creates screenshot folder under project and stores screenshot
            //public class FileUtils 	extends Object  General file manipulation utilities.
            System.out.println("No test failure so No Screenshot");

        }
        catch (Exception e)
        {
            System.out.println("Exception while taking screenshot "+e.getMessage());
        }
    }

    /****To close driver instance, close and quit *********/
    @AfterClass
    public void tearDown(){
        driver.close();
        driver.quit();
    }
}
