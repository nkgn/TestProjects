package testscripts;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import pageResources.* ;
import pages.*;
import pages.HomePage;


public class DeleteProjectTest
{
    private WebDriver driver ;
    private String baseURL = "";
    private HomePage homepage ;
    private LoginPage loginpage;
    private MyAccountPage myaccount ;
    //private ProjectPage projectFactory;
    private String expectedTitle = "";

    //Logger class to log error messages to logs folder under project
    private static final Logger log = LogManager.getLogger(SubmitProjectTest.class.getName());



    @BeforeClass
    public void setUp()  {
        driver = Utilities.driver; // to get driver reference created in  postprojectClass
        // and saved the reference in Utilities class

        log.info("before class setup of QABidtest");
        log.info("Driver instance received from helper class");
        homepage = new HomePage(driver);
        loginpage = new LoginPage(driver);
        myaccount= new MyAccountPage(driver);
        //qaProjectPage = new QAProjectSearchPage(driver);

    }






    //***Verify project owner can delete the completed project**/

    @Test
    public void testDeleteProject() {
        try {

            //String projectName ="Travel test";
            String projectName = Utilities.projectName ;
            homepage.clickOnMyAccountTab();

            Thread.sleep(5000);
            myaccount.clickOnTextWelcome();
            //Step 1 : Enter project name to search
            myaccount.fillSearchTextBox(projectName);
            myaccount.clickSearchButton();
            Thread.sleep(5000);
            expectedTitle="BidQA | Project Search";
            Assert.assertEquals(driver.getTitle(),expectedTitle );

            Thread.sleep(5000);

            myaccount.clickDeleteButton();
            myaccount.clickOnConfirmDeleteButton();
            Thread.sleep(4000);
            String status = myaccount.getProjectDeleteStatus();
            Assert.assertEquals(status,"The project has been deleted. Return to your account.");

            System.out.println("Project Delete test passed");

        } catch (Exception e) {
            System.out.println("Exception generated in test3 ");
            e.printStackTrace();

            Assert.fail(); // or throw
        }
    }

}
