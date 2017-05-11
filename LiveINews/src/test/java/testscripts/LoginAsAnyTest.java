package testscripts;

import org.junit.Assert;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.HomePage;
import pages.LoginAndRegistrationPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import data.GenerateRegistrationTestData;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.*;

public class LoginAsAnyTest
{
    private WebDriver driver ;
    private String baseURL = "";
    private String expectedTitle = "";
    HomePage homePage ;
    LoginAndRegistrationPage register;
    String emailID="";
    String username ="";
    String pwd;
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
        loginData = new GenerateRegistrationTestData();
        register = new LoginAndRegistrationPage(driver);
        loginData.accountName = username ;
        loginData.password =pwd;
    }

    @Test
    public void loginTest(){
        System.out.println("inside login test");
        homePage.clickOnLoginTab();
        register.fillLoginUserName(username);
        register.fillLoginPwd(pwd);
        register.clickOnLogInButton();

    }
    @AfterClass
    public void tearDown(){
        driver.close();
        driver.quit();
    }
}
