package testscripts;

import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import pageResources.* ;
import pages.*;


public class EditProjectTest
{
    private WebDriver driver ;
    //private HomePage homepage ;
    //private LoginPage loginpage;
    private MyAccountPage myaccount ;
    private ProjectPage projectFactory;
    private String expectedTitle = "";

    @BeforeClass
    @Parameters({"username","password"}) //paramterized test need to run from testng.xml
    public void setUp(String username,String password)  {

        driver = Utilities.driver ;
        projectFactory = new ProjectPage(driver);
        myaccount = new MyAccountPage(driver);
    }


    /***Verify that project owner can edit thr project description***/
    @Parameters({"projectName"})
    @Test
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
            Thread.sleep(3000);
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
            System.out.println("Exception generated while searching project in edit project description ");
            e.printStackTrace();
            Assert.fail();
        }
    }


}
