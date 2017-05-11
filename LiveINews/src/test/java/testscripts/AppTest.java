package testscripts;


import org.openqa.selenium.chrome.ChromeOptions;
import pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.*;

public class AppTest
{
    private WebDriver driver ;
    private String baseURL = "";
    private String expectedTitle = "";
    HomePage homePage ;
    //private RegisterAsCityJournalist registerJournalist;
    //private RegisterAsUser registerUser;
    //private RegisterAsEditor registerEditor;
    //private RegisterAsMedia registerMedia;


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
            driver.manage().window().maximize();// .manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        }catch(Exception e){
            System.out.println("Exception generated in setUp ");
            e.printStackTrace();
        }
        homePage = new HomePage(driver);
        /*registerJournalist = new RegisterAsCityJournalist(driver,homePage);
        registerEditor = new RegisterAsEditor(driver,homePage);
        registerMedia = new RegisterAsMedia(driver,homePage);
        registerUser = new RegisterAsUser(driver,homePage);
        */

    }
    @Test
    public void test0() throws InterruptedException {
        System.out.println("Inside test");
        homePage.clickOnLoginTab();
        //registerJournalist.cityJournalist();
        //registerEditor.editor();
        //registerMedia.media();
        //registerUser.user();

    }

    @AfterClass
    public void tearDown(){
        driver.close();
        //driver.quit();
    }
}
