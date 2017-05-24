/*This class is used to create bids by Qa engineers

test method is used to submit proposal by QAs .
QA username and password are passed from data provider class
 */



package testscripts;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import pageResources.Utilities ;
import pages.*;
import pages.HomePage;
import data.DataProviderClass;


public class QABidTest
{
    private WebDriver driver ;

    private HomePage homepage ;
    private LoginPage loginpage;
    private MyAccountPage myaccount ;
    private QAProjectSearchPage qaProjectPage;
    private String expectedTitle = "";
    //Logger class to log error messages to logs folder under project
    private static final Logger log = LogManager.getLogger(QABidTest.class.getName());



    @BeforeClass
    public void setUp()  {
        driver = Utilities.driver; // to get driver reference created in  postprojectClass
                                    // and saved the reference in Utilities class

        log.info("before class setup of QABidtest");
        log.info("Driver instance received from helper class");
        homepage = new HomePage(driver);
        loginpage = new LoginPage(driver);
        myaccount= new MyAccountPage(driver);
        qaProjectPage = new QAProjectSearchPage(driver);

    }

    //***Verify QA Engineer can submit a proposal for  new project**/
    /*Each QA logs in , submits proposal and logs out*/

    @Test( dataProviderClass = DataProviderClass.class, dataProvider = "SearchProvider")
    public void bidForProjectTest(String username,String password,
                                  String projectName,String price, String daysToComplete) {
       try{

           Thread.sleep(6000);
           Utilities.loginAccount(username, password, driver, loginpage, homepage);
           Thread.sleep(8000);
           String welcomeText = "Welcome " + username ;
           Assert.assertTrue(homepage.getwelcomeText().contains(welcomeText),"Logging not successsful");
           Assert.assertEquals(driver.getTitle(), "BidQA | My Account");
           log.info(username + " QA successfully logged in");
        //Step 1 : Enter project name to search
        myaccount.fillSearchTextBox(projectName);
        myaccount.clickSearchButton();


        Thread.sleep(5000);
        expectedTitle="BidQA | Project Search";
        Assert.assertEquals(driver.getTitle(),expectedTitle );
        //String projNameFound = qaProjectPage.searchedProjectName(projectName);
        //Assert.assertTrue(projNameFound.contains(projectName));

        qaProjectPage.clickOnReadMoreButton();
        Thread.sleep(3000);
        qaProjectPage.clickOnSubmitProposalButton();
        Thread.sleep(3000);
        qaProjectPage.fillBidPrice(price);
        qaProjectPage.fillDaysDone(daysToComplete);
        qaProjectPage.clickOnProposalCheckBox();
        qaProjectPage.clickOnPlaceBidButton();

        Assert.assertEquals(qaProjectPage.proposalStatusText(),"Your bid has been posted.");
        //System.out.println("Submitted Proposal");
        log.info("Submitted Proposal");
        loginpage.clickOnSignOutButton();
           Thread.sleep(6000);


       }catch(Exception e){

           e.printStackTrace();
           Assert.fail("Exception generated while submit proposal by QA Engineer ");
       }

    }
}
