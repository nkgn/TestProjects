/* This class is used to create a team of Qas selected as winner by the project owner*/



package testscripts;

import data.DataProviderClass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

import pageResources.* ;
import pages.*;
import pages.HomePage;


public class CreateTeamTest
{
    private WebDriver driver ;
    private HomePage homepage ;
    private LoginPage loginpage;
    private static boolean firstTimeExecution;
    private TeamManagerPage teamManager ;
    private MyAccountPage myAccountPage ;

    //Logger class to log error messages to logs folder under project
    private static final Logger log = LogManager.getLogger(CreateTeamTest.class.getName());


    /* To initialize the flag*/
    @BeforeClass
    public void initVariables() {
        firstTimeExecution = true;
    }

    /*to be excuted each time before test method so set the prconditions only once  */
    @BeforeMethod
    //@Parameters({"username","password"}) // username and password from xml file
    public void createTeamSetup()  {

        System.out.println("firstTimeExecution of create team setup  = "+ firstTimeExecution  );
        if(firstTimeExecution == true) {
            driver = Utilities.driver;
            homepage = new HomePage(driver);
            loginpage = new LoginPage(driver);
            teamManager = new TeamManagerPage(driver);
            myAccountPage = new MyAccountPage(driver);

            try {
                //preconditions for this test case
                //login as project owner to create a team of  3 QAs
                String username = Utilities.projectOwnerName ;
                String password = Utilities.projectOwnerPassword ;
                Utilities.loginAccount(username, password, driver, loginpage, homepage);
                Thread.sleep(3000);
                String welcomeText = "Welcome " + username ;
                Assert.assertTrue(homepage.getwelcomeText().contains(welcomeText),"Logging not successsful");
                log.info("project owner " + username + "logged in to create a team");


            } catch (Exception e) {
                e.printStackTrace();
                Assert.fail("Exception while creating Team");
            }
        }
    }


    //***Verify project owner can create a team of selected QA engineers**/

    @Test( dataProviderClass = DataProviderClass.class, dataProvider = "SearchProvider")
    public void newTeamTest(String QAname,String projectName,String teamTitle) {

        try {
            //projectName = "FG1WV";
            if(firstTimeExecution == true ) {
                ((JavascriptExecutor) driver).executeScript("scroll(0,300)");
                myAccountPage.clickOnTeamManager();

                // filter projects from dropdown list
                teamManager.selectFilterProject(projectName);
                //click on create team
                teamManager.clickOnCreateTeamButton();

                teamManager.selectProject(projectName);
                //create team title
                teamManager.fillTeamTitle(teamTitle);
                //click submit button
                teamManager.clickOnSubmitTeamButton();
                log.info("create team button clicked");
                //Thread.sleep(3000);
                firstTimeExecution = false;
            } // code till here to be done only first time

            // code from here needs to be executed for each QA
                //add folowing for all 3 QA

            Thread.sleep(4000);
            //1click on add users
            String parentElement = "//*[@class='post-team-manager']";
            String projectNameElement = ".//span[contains(text(),'" + projectName +"')]";
            String addUserElement = ".//a[@data-target='.add-user-modal']";
            // get the list of all project blocks in the page
            List<WebElement> pWE = driver.findElements(By.xpath(parentElement));
            // for waorking with each block at a time
            for (WebElement we:pWE)
            {
                try {
                    //get created team name
                    WebElement projNameEle = we.findElement(By.xpath(projectNameElement));

                    //Assert.assertEquals(projNameEle.getText(),projectName);
                    // get add user button for the selected team
                    WebElement addUserEle = we.findElement(By.xpath(addUserElement));
                    addUserEle.click();
                    break;
                }catch(Exception e)
                {
                    System.out.println("No elements found");
                }
            }

            //2select team name in drop down
            teamManager.selectTeam(teamTitle);
            //3select QA from dropdpwn
            teamManager.selectQAName(QAname);
            //4click on add button
            teamManager.clickOnAddUserSubmitButton();
            Thread.sleep(4000);

        } catch (Exception e) {

            e.printStackTrace();
            Assert.fail();
        }
    }

    /*project owner logs out if logged in during init, this is done to unable
    the class to be excluded in testng.xml file*/

    @AfterClass
    public void teardown()throws InterruptedException{
        // will not be executed if test is excluded since firstTimeExecution is true
        if(firstTimeExecution == false){
            loginpage.clickOnSignOutButton();
            Thread.sleep(4000);
            log.info("Team created successful ");

        }
    }




}
