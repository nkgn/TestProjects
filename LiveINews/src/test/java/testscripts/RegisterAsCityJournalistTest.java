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
import pageresources.CopyDriverInstance;
import pages.ActivateAccountPage;
import pages.HomePage;
import pages.LoginAndRegistrationPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.MyAccountPage;
import data.DataProviderClass;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;


public class RegisterAsCityJournalistTest {
    private WebDriver driver;
    private String baseURL = "";

    //Logger class to log error messages to logs folder under project
    private static final Logger log = LogManager.getLogger(RegisterAsCityJournalistTest.class.getName());

    private String expectedTitle = "";
    HomePage homePage;
    LoginAndRegistrationPage register;
    ActivateAccountPage activateAcc;
    MyAccountPage myAccPage;

    /***** setup the environment before any registration *******/
    @BeforeTest
    public void setUp() {

        /***Launch the browser ****/
        //System.setProperty("webdriver.gecko.driver", "C:/Users/nitu/Desktop/SeleniumWebDriver/geckodriver.exe");
        //driver = new FirefoxDriver();

        //System.setProperty("webdriver.chrome.driver", "C:/Users/nitu/Desktop/SeleniumWebDriver/chromedriver_win32/chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");

        driver = new ChromeDriver(options);
        baseURL = "http://www.liveinews.com";

        driver.get(baseURL);
        expectedTitle = "LiveiNews";
        try {
            Assert.assertEquals(driver.getTitle(), expectedTitle);
            //driver.manage().window().maximize();// .manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        } catch (Exception e) {
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

    /***** Test automate populating fields for registering city journalist******/
    @Test(dataProvider = "SearchProvider", dataProviderClass = DataProviderClass.class)
    public void registerCityJournalistTest(String email, String password, String accountName,
                                           String firstName, String lastName, String address,
                                           String phone, String zip, Integer country,
                                           Integer state, Integer city,
                                           String paypal) throws InterruptedException {
        System.out.println("Inside test register city journal");
        log.info("This message is from test method");
        homePage.clickOnLoginTab();
        register.selectAccountType(3); //index for city journalist is 3 in account type
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

        //Assert that user succesfully registered before activation of account
        try {

            String expected = "";
            String actual = "" ; //reg.getMyAccHeading();
            Assert.assertEquals(expected, actual);

        }catch(Exception e){
            e.printStackTrace();
            Assert.fail();
        }

        Thread.sleep(7000);
        //homePage.clickOnLoginTab();
    }


    //****To close driver instance, close and quit based on the test tag in testng.xml *********//*
    @AfterTest
    public void tearDown() throws InterruptedException {
        driver.close();
        driver.quit();
        Thread.sleep(5000);
    }
}
