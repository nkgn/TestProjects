/* After QAs have made a bid for project, project owner los in and select all the QAs as winners*/
/* test method is used to select all the QAs who have bid as project winners */



package testscripts;

import data.DataProviderClass;
import data.GenerateData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

import pageResources.* ;
import pages.*;
import pages.HomePage;


public class SelectWinnerTest
{
    private WebDriver driver ;
    private String baseURL = "";
    private HomePage homepage ;
    private LoginPage loginpage;
    private MyAccountPage myaccount ;
    private ProjectPage projectFactory;
    private String expectedTitle = "";
    private  boolean firstTimeExecution   ;

    //Logger class to log error messages to logs folder under project
    private static final Logger log = LogManager.getLogger(SelectWinnerTest.class.getName());


    /* to set flag for one time execution of login */
    @BeforeClass
    public void initVariables(){
        firstTimeExecution = true;
    }


    /* to be used for login as Project owner only for 1 time of test execution*/
    @BeforeMethod
    //@Parameters({"username","password"}) // login parameters for project owner are taken from xml file
    public void selectWinnerSetup()  {

        System.out.println("firstTimeExecution of select winner setup = "+ firstTimeExecution  );
        if(firstTimeExecution == true) {
            log.info("Inside select winner setup   " );
            driver = Utilities.driver;
            homepage = new HomePage(driver);
            loginpage = new LoginPage(driver);
            myaccount = new MyAccountPage(driver);
            projectFactory = new ProjectPage(driver);
            String username = Utilities.projectOwnerName ;
            String password = Utilities.projectOwnerPassword ;
            try {
                //login as project owner
                Thread.sleep(5000);
                Utilities.loginAccount(username, password, driver, loginpage, homepage);
                Thread.sleep(8000);
                Assert.assertEquals(driver.getTitle(), "BidQA | My Account");
                //User successfully logged in
                String welcomeText = "Welcome " + username ;
                Assert.assertTrue(homepage.getwelcomeText().contains(welcomeText),"Logging not successsful");

                String pname= GenerateData.projectTitle;
                log.info("Project owner succesfuly logged in to create a team  for the project" + pname);
                myaccount.fillSearchTextBox(pname);
                myaccount.clickSearchButton();
                Thread.sleep(5000);
                expectedTitle = "BidQA | Project Search";
                Assert.assertEquals(driver.getTitle(), expectedTitle);
                //click on read more button to get project details
                projectFactory.clickOnReadMoreButton();
                firstTimeExecution = false;

            } catch (Exception e) {
                e.printStackTrace();
                Assert.fail("Exception while opening account for selecting winner");
            }
        }
    }


    /*Verify project owner can can select a winner for project
     all the QAs who have bid will be selected as winner
     this est will be executed , no of times of QA provided by dataprovider class */

    @Test( dataProviderClass = DataProviderClass.class, dataProvider = "SearchProvider")
    public void selectWinner(String QAname) {
        try {
            //scroll page down to get the visibility of the element
            ((JavascriptExecutor)driver).executeScript("scroll(0,700)");

            // click on select winner for all 3 QA engineers and click on choose winner button

            // select winner xpath="//div[@id='my_bids']/div[3]/div[1]/a"
            //broken xpath for QAname and Select Winner link
            //same link can be used to create xpaths for  names of QA and Project winner titles

            String xpathWinnerPre = "//*[@id='my_bids']/div[";
            String xpathWinnerMid = "]/div[";
            String xpathWinnerPost = "]/a";

            // create strings for xpaths in loop
            String xpathWinnerLink;  // xpath for select winner link
            String xpathWinnerName;   // xpath for selected Qa name
            String xpathWinnerConfirm;   // xpath to select winner changed to project winner

            // no. of  bids made by QAs
            // get list of all the QAs who have bid for the project  in the table
            List<WebElement> labels = driver.findElements(By.xpath("//div[@class='myrow']"));
            int totalNum = labels.size(); // to get size of the list found
            log.info("Total no of bids found for the project is " + totalNum);
            for(int i = 1; i <= totalNum; i++) {
                // as name is at the first position in record in table
                xpathWinnerName = xpathWinnerPre + i + xpathWinnerMid + "1" + xpathWinnerPost;
                WebElement qaNameEle = driver.findElement(By.xpath(xpathWinnerName));
                String qaName = qaNameEle.getText();
                // to verify the name of the qa and to select him as winner
                if (qaName.equals(QAname)) {
                    // as link for select winner is at 5th position in record in table
                    xpathWinnerLink = xpathWinnerPre + i + xpathWinnerMid + "5" + xpathWinnerPost;

                    WebElement qaLinkEle = driver.findElement(By.xpath(xpathWinnerLink));
                    // to locate the elment in the page
                    Actions actions = new Actions(driver);
                    actions.moveToElement(qaLinkEle);
                    actions.perform();

                    String checkTextOfElement = qaLinkEle.getText();
                    if(!checkTextOfElement.equals("Send Message")) // if not Send Message then click
                    {
                        qaLinkEle.click();
                        Thread.sleep(4000);
                        projectFactory.clickOnChooseWinnerButton();
                    }
                    Thread.sleep(4000);

                    // as confirmation that QA is selected at project winner is at 6th column in record of table
                    xpathWinnerConfirm = xpathWinnerPre + i + xpathWinnerMid + "6" + "]";

                    WebElement qaWinnerConf = driver.findElement(By.xpath(xpathWinnerConfirm));

                    String actualResult = qaWinnerConf.getText();
                    Assert.assertEquals(actualResult,"Project Winner");
                    log.info(qaName + " has been selected as winner ");
                    break; // if record found, then stop loop
                }

            }

        } catch (Exception e) {
            log.info("Exception generated while selecting winner in SelectWinnerTest ");
            e.printStackTrace();
            Assert.fail("Exception generated while selecting winner in SelectWinnerTest ");
        }

    }


    /* to be executed after all the QA are selected as winners */
    @AfterClass
    public void teardown()throws InterruptedException{
        if(firstTimeExecution == false){
            loginpage.clickOnSignOutButton();
            Thread.sleep(4000);

        }
        log.info("select winner afterclass" +
                "");
    }




}
