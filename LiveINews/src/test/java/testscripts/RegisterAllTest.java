package testscripts;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;
import pages.ActivateAccountPage;
import pages.HomePage;
import pages.LoginAndRegistrationPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import data.GenerateRegistrationTestData;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.*;

public class RegisterAllTest
{
    private WebDriver driver ;
    private String baseURL = "";
    private String expectedTitle = "";
    HomePage homePage ;
    LoginAndRegistrationPage register;
    ActivateAccountPage activateAcc ;
    String emailID="";

    GenerateRegistrationTestData loginData;
    private static final Logger log = LogManager.getLogger(RegisterAsUserTest.class.getName());

    @BeforeClass
    public void setUp() {
        //System.setProperty("webdriver.gecko.driver", "C:/Users/nitu/Desktop/SeleniumWebDriver/geckodriver.exe");
        //driver = new FirefoxDriver();
        System.setProperty("webdriver.chrome.driver", "C:/Users/nitu/Desktop/SeleniumWebDriver/chromedriver_win32/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);

        baseURL = "http://www.liveinews.com";
        driver.get(baseURL);
        expectedTitle = "LiveiNews";
        try {
            assertEquals(driver.getTitle(), expectedTitle);
            //driver.manage().window().maximize();// .manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        } catch (Exception e) {
            System.out.println("Exception generated in setUp ");
            e.printStackTrace();
        }
        homePage = new HomePage(driver);

    }
    @BeforeMethod
    public void generateDataTest(){
/*
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        baseURL = "http://www.liveinews.com";
        driver.get(baseURL);
        expectedTitle = "LiveiNews";
        try {
            assertEquals(driver.getTitle(), expectedTitle);
            //driver.manage().window().maximize();// .manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        } catch (Exception e) {
            System.out.println("Exception generated in setUp ");
            e.printStackTrace();
        }
        homePage = new HomePage(driver);

*/
        activateAcc = new ActivateAccountPage(driver);
        loginData = new GenerateRegistrationTestData();
        emailID = loginData.email;
        System.out.println("EmailID in setup of user= " + emailID);
        loginData.email = loginData.email + "@mailinator.com";

        //loginData.email = loginData.email + "@getnada.com";

        register = new LoginAndRegistrationPage(driver);

        System.out.println(loginData.accountName +  "  " +   emailID);
    }

    @Test
    public void registerCityJournalistTest() throws InterruptedException {
        System.out.println("Inside test");
        homePage.clickOnLoginTab();
        register.selectAccountType(3);
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
        register.fillPaypal(loginData.paypal);

        register.clickNext();
        Thread.sleep(9000);
        //register.scrollAcceptTermsDialog();

        register.clickOnAcceptTerms();
        register.clickOnNext();
        Thread.sleep(5000);
        register.clickNext();
        Thread.sleep(7000);
        homePage.clickOnLoginTab();
    }

    @Test
    public void registerEditorTest() throws InterruptedException {
        System.out.println("Inside test");
        homePage.clickOnLoginTab();
        register.selectAccountType(1);

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
        //System.out.println(driver.getWindowHandle());
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
    }

    @Test
    public void registerMediaTest0() throws InterruptedException {
        System.out.println("Inside test");
        homePage.clickOnLoginTab();
        register.selectAccountType(2);
        register.fillEmailTextBox(loginData.email);
        register.fillPwdTextBox(loginData.password);
        register.fillConfirmPwdTextBox(loginData.password);
        register.fillAccountName(loginData.accountName);
        //company name
        register.fillCompanyName(loginData.companyName);
        Thread.sleep(3000);
        register.fillZipCode(loginData.zipCode);
        register.fillAddress(loginData.address);
        register.fillPhone("111-111-1111");
        register.selectCountry(2);
        Thread.sleep(7000);
        register.selectState(2);
        Thread.sleep(7000);
        register.selectCity(1);
        register.fillIdentityNumber(loginData.identity);
        register.fillContact(loginData.contact);
        register.fillLegalContact(loginData.legalContact);
        //System.out.println(driver.getWindowHandle());
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
    }

    @Test
    public void registerUserTest() throws InterruptedException {
        System.out.println("Inside test");
        homePage.clickOnLoginTab();
        register.selectAccountType(0);
        emailID= loginData.email;
        register.fillEmailTextBox(loginData.email);
        register.fillPwdTextBox(loginData.password);
        register.fillConfirmPwdTextBox(loginData.password);
        register.fillAccountName(loginData.accountName);
        register.clickNext();
        Thread.sleep(9000);
        //register.scrollAcceptTermsDialog();
        register.clickOnAcceptTerms();
        register.clickOnNext();
        Thread.sleep(5000);
        register.clickNext();
        Thread.sleep(7000);
        homePage.clickOnLoginTab();
    }
    @AfterMethod()
    public void activateAllAccountTest()throws InterruptedException{
        System.out.println("EmailID =" + emailID);

        // mailinator
        String s1 = "http://www.mailinator.com/inbox2.jsp?public_to=" + emailID + "#/#public_maildirdiv";
        //getnada
        //String s1 = "https://" + "app.getnada.com/inbox/"+ emailUser + "@getnada.com";
        System.out.println("link to mail site is  "+ s1);
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
    }


    @AfterClass
    public void tearDown(){
        driver.close();
        driver.quit();
    }
}
