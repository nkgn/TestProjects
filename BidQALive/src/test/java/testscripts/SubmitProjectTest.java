/* this class is used to submit the work by Qa Engineers*/

package testscripts;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import pageResources.Utilities ;
import pages.*;
import pages.HomePage;
import data.DataProviderClass;


public class SubmitProjectTest
{
    private WebDriver driver ;
    private String baseURL = "";
    private HomePage homepage ;
    private LoginPage loginpage;
    private QAMyAccountPage qaAccount;

    //Logger class to log error messages to logs folder under project
    private static final Logger log = LogManager.getLogger(SubmitProjectTest.class.getName());


    @BeforeClass
    public void setUp()  {
        driver = Utilities.driver;
        homepage = new HomePage(driver);
        loginpage = new LoginPage(driver);
        qaAccount = new QAMyAccountPage(driver);

    }


    /*This method will be called as many times as based on values in dataprovider class*/

    //***Verify QA Engineers  can start work timer and complete work and mark it as delivered
    @Test( dataProviderClass = DataProviderClass.class, dataProvider = "SearchProvider")
    public void projectDeliveredTest(String username,String password,
                                  String projectName,String teamName) {
        try{

            //projectName="FG1WV";
            Thread.sleep(2000);
            // login as qa engineer
            Utilities.loginAccount(username, password, driver, loginpage, homepage);
            Thread.sleep(5000);
            // assert project in progress in my account page
            Assert.assertEquals(driver.getTitle(), "BidQA | My Account");
            String welcomeText = "Welcome " + username ;
            Assert.assertTrue(homepage.getwelcomeText().contains(welcomeText),"Logging not successsful");
            log.info(username + "  QA successful log in ");

            //click on start work timer
            qaAccount.clickOnStartWorkTimerButton();

            ((JavascriptExecutor) driver).executeScript("scroll(0,200)");
            Thread.sleep(2000);
            //click on accept checkbox
            qaAccount.clickOnAcceptCheckBox();

            //click on OK button
            qaAccount.clickOnOKButton();

            Thread.sleep(10000);
            //clcik on stop work timer to stop working
            qaAccount.clickOnStopWorkTimerButton();
            qaAccount.clickOnMarkDeliveredButton();
            Thread.sleep(4000);
            String actualSt = qaAccount.getPageHeading();
            Assert.assertTrue(actualSt.contains("Mark the project delivered"),"Project not delivered yet");
            qaAccount.clickOnConfirmDeliveredButton();
            Thread.sleep(4000);

            // QA signout
            loginpage.clickOnSignOutButton();
            log.info(username +" signed out ");
            Thread.sleep(6000);

        }catch(Exception e){
            log.info("Error while submitting project");
            e.printStackTrace();
            Assert.fail("Exception generated while submit project by QA Engineer ");
        }

    }
}
