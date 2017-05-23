package testscripts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import pageResources.* ;
import pages.*;
import pages.HomePage;


public class AppTest
{
    private WebDriver driver ;
    private String baseURL = "";
    private HomePage homepage ;
    private LoginPage loginpage;
    private MyAccountPage myaccount ;
    private ProjectPage projectFactory;
    private String expectedTitle = "";

    @BeforeClass
    @Parameters({"username","password"}) //paramterized test need to run from testng.xml
    public void setUp(String username,String password)  {
        //System.setProperty("webdriver.gecko.driver", "C:/Users/nitu/Desktop/SeleniumWebDriver/geckodriver.exe");
        //driver = new FirefoxDriver();
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver_win32/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver( options );
        Utilities.driver = driver;
        baseURL="http://test.bidqa.com";
        driver.get(baseURL);
        expectedTitle="BidQA | Marketplace for available QA Professionals around a globe";
        try {
            Assert.assertEquals(driver.getTitle(), expectedTitle);
            driver.manage().window().maximize();// .manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        }catch(Exception e){
            System.out.println("Exception generated in setUp ");
            e.printStackTrace();
        }
        homepage = new HomePage(driver);
        loginpage = new LoginPage(driver);
        myaccount= new MyAccountPage(driver);
        projectFactory = new ProjectPage(driver);
        try{
            Utilities.loginAccount(username, password, driver, loginpage, homepage);
            Thread.sleep(8000);
            Assert.assertEquals(driver.getTitle(), "BidQA | My Account");
        }catch(Exception e){
            System.out.println("Exception generated while logging in  with valid credentials ");
            e.printStackTrace();
            Assert.fail();
        }
    }


    /***Verify that project owner can edit thr project description***/
    @Parameters({"projectName"})
    @Test(enabled = true,priority = 1)
    public void testEditProjectDescription(String projectName)  {
        try {
            //Step 1 : Enter project name to search
            myaccount.fillSearchTextBox(projectName);
            myaccount.clickSearchButton();
            Thread.sleep(5000);
            expectedTitle="BidQA | Project Search";
            Assert.assertEquals(driver.getTitle(),expectedTitle );

            //Step 2 : Click edit project button under the selected project
            //Thread.sleep(4000);
            myaccount.clickEditButton();
            Thread.sleep(4000);
            expectedTitle = "BidQAEdit Project - | Marketplace for available QA Professionals around a globe";
            Assert.assertEquals(driver.getTitle(), expectedTitle);

            //step 3: edit the project details

            String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
            projectFactory.fillProjectDescription(timeStamp);


            //step 4: save the changes by clicking save project button
            projectFactory.clickOnSaveProjectButton();
            Thread.sleep(2000);

            //step 5 : confirm the changes and press "click here"
            projectFactory.clickOnClickHere();
            Thread.sleep(5000);
            //Expected results : Project owner can see the changes made in project details
            String updatedProjDescription = projectFactory.getUpdatedProjDescription();
            Assert.assertEquals(updatedProjDescription, timeStamp);
            System.out.println("Edit proj desc test passed ");


            // get back to home page
//            driver.get(baseURL);
//            System.out.println("Testcase pass");

        }catch(Exception e){
            System.out.println("Exception generated while searching project in test0 ");
            e.printStackTrace();
            Assert.fail();
        }
    }

    //***Verify project owner can post a new project**/
    @Parameters({"projectName","projectDes"})
    @Test(enabled = true,priority = 0)
        public void testCreateProject(String projectName,String projectDes) {
        try {
            homepage.clickOnPostNewProjectTab();
            Thread.sleep(5000);
            projectFactory.fillProjectTitle(projectName);
            projectFactory.fillProjectDescription(projectDes);
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
            projectFactory.clickOnPayByCreditsButton();
            projectFactory.clickOnPayNowButton();
            //Thread.sleep(4000);
            projectFactory.clickOnGoToYourAccountLink();

            Thread.sleep(4000);

            Assert.assertEquals(driver.getTitle() ,"BidQA | My Account");
            String newProject= projectFactory.getAddedProjectText();
            Assert.assertEquals(projectName,newProject);
            System.out.println("Create Project test passed");
        } catch (Exception e) {
            System.out.println("Exception generated in test3 ");
            e.printStackTrace();

            Assert.fail(); // or throw
        }
    }

    //***Verify project owner can post a new project**/
    @Parameters({"projectName"})
    @Test(enabled = true,priority = 2)
    public void testDeleteProject(String projectName) {
        try {
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



            @AfterClass
    public void tearDown(){
        loginpage.clickOnSignOutButton();
        driver.close();
        driver.quit();
    }

}
