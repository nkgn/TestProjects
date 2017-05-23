/*This class is used as base class to set up environment for all the tests*
the preconditions like launch the browser and user logged in

test method is used to post a new  project by project owner
 */

package testscripts;

import data.DataProviderClass;
import data.GenerateData;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
//import java.util.logging.LogManager;
//import java.util.logging.Logger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import pageResources.* ;
import pages.*;
import pages.HomePage;


public class PostProjectTest
{
    private WebDriver driver ;
    private String baseURL = "";
    private HomePage homepage ;
    private LoginPage loginpage;
    private MyAccountPage myaccount ;
    private ProjectPage projectFactory;
    private String expectedTitle = "";
    private PayPalPage payPalPage;

    //Logger class to log error messages to logs folder under project
    private static final Logger log = LogManager.getLogger(PostProjectTest.class.getName());


    /**To setup the environment and launch the browser **/
    /** to be execute at once for whole test */
    @BeforeTest
    public void setUp()  {

        //System.setProperty("webdriver.chrome.driver", "drivers/chromedriver_win32/chromedriver.exe");
        // set chrome browser properties
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("profile.default_content_setting_values.notifications", 2);
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);

        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver_win32/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        options.addArguments("--start-maximized");
        options.addArguments("--disable-save-password-bubble");
        driver = new ChromeDriver(options);
        Utilities.driver = driver;

        //System.setProperty("webdriver.chrome.driver", "drivers/chromedriver_win32/chromedriver.exe");
        //driver = new ChromeDriver(options);
        //Utilities.driver = driver;
        baseURL="http://test.bidqa.com";
        driver.get(baseURL);
        expectedTitle="BidQA | Marketplace for available QA Professionals around a globe";
        try {
            Assert.assertEquals(driver.getTitle(), expectedTitle);
            log.info("---------------------------------------------");
            log.info("BidQA launched from before test in postproject class");
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            // generate random data to be used in various tests
            // generate project title randomly
            GenerateData.generateMyAccountData();
        }catch(Exception e){
            log.debug(e);
            e.printStackTrace();
            Assert.fail("Exception generated in setUp of create project ");
        }
    }

    /* to instantiate the page classes*/
    @BeforeMethod
    public void pageInit() {

        homepage = new HomePage(driver);
        loginpage = new LoginPage(driver);
        myaccount= new MyAccountPage(driver);
        projectFactory = new ProjectPage(driver);
        payPalPage = new PayPalPage(driver);
        log.info("All page instances initialised in create project test");
    }

    //***Verify project owner can post a new project**/
    @Test( dataProviderClass = DataProviderClass.class, dataProvider = "SearchProvider")
    public void testCreateProject(String username,String password, String projectName,String projectDes) {
        try {

            Utilities.projectOwnerName = username ;
            Utilities.projectOwnerPassword = password;
            Utilities.loginAccount(username, password, driver, loginpage, homepage);
            Thread.sleep(8000);
            // verfiy that user is logged in and welcome text appears at top right
            String welcomeText = "Welcome " + username ;
            Assert.assertTrue(homepage.getwelcomeText().contains(welcomeText),"Logging not successsful");
            Assert.assertEquals(driver.getTitle(), "BidQA | My Account");
            log.info("Project owner " + username + "logged in successful");
            homepage.clickOnPostNewProjectTab();
            Thread.sleep(5000);
            projectFactory.fillProjectTitle(projectName);

            projectFactory.fillProjectDescriptionTextArea(projectDes);
            projectFactory.selectSkillsAndCat();
            projectFactory.fillProjectBudget(2);

            projectFactory.fillProjectEndingDate();
            projectFactory.fillProjectCountry(2);
            projectFactory.fillProjectLocationAddress("123");
            projectFactory.clickOnProjectSubmit1();
            projectFactory.clickOnProjectSubmit2();
            Thread.sleep(4000);
            projectFactory.clickOnNextStep();
            projectFactory.selectTermsCheckBox();


            // Pay with PayPal
            projectFactory.clickOnPayByPayPalButton();
            Assert.assertEquals(payPalPage.getPayPalTitle(),"Pay with PayPal");
            //driver.switchTo().frame(0);
            driver.switchTo().frame("injectedUl");
            Thread.sleep(7000);
            payPalPage.fillPayPalEmail("neetiya111@gmail.com");
            payPalPage.fillPayPalPwd("123123123123");
            payPalPage.clickOnPayPalLogInButton();
            driver.switchTo().defaultContent();
            Thread.sleep(7000);
            //((JavascriptExecutor) driver).executeScript("scroll(0,200)");
            payPalPage.clickOnPayPalConfirmButton();
            Thread.sleep(7000);
            payPalPage.clickOnReturnToMerchantButton();


            // Pay by Credits ///////////////////////////////
           /* projectFactory.clickOnPayByCreditsButton();
            //Thread.sleep(5000);
            projectFactory.clickOnPayNowButton();
            Thread.sleep(4000);
            projectFactory.clickOnGoToYourAccountLink();
            Thread.sleep(4000);*/


            // verify project is successfully created
            //String newProject= projectFactory.getAddedProjectText();  // pay by credit
            String newProject= projectFactory.getPostedProjectText();  // pay by paypal
            Assert.assertEquals(projectName,newProject);
            //store project name in helper for future use
            Utilities.projectName = projectName ;

            System.out.println("Create Project test passed");
            log.info("New project created is =" + newProject);
            loginpage.clickOnSignOutButton();
            log.info("Project owner sign out successful");
            Thread.sleep(4000);
        } catch (Exception e) {

            e.printStackTrace();

            Assert.fail("Exception generated in test create project "); // or throw
        }
    }

    /* to be called after the last class*/
    /** to be used to close the browser  and release the driver **/
    @AfterTest
    public void tearDown(){
        log.info("Operation to close driver after all the test completed");
        driver.close();
        driver.quit();
    }

}
