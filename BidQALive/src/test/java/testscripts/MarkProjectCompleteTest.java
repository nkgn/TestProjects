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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import pageResources.*;
import pages.*;
import pages.HomePage;


public class MarkProjectCompleteTest

{
    //Logger class to log error messages to logs folder under project
    private static final Logger log = LogManager.getLogger(MarkProjectCompleteTest.class.getName());
    private static boolean completedpaymentPrecondition;
    private static int confirmPaymentCounter;
    private WebDriver driver;
    private HomePage homepage;
    private LoginPage loginpage;
    private TeamManagerPage teamManager;
    private MyAccountPage myAccountPage;


    @BeforeClass
    public void setup() {
        log.info("mark project complete setup called ");

        driver = Utilities.driver;
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        homepage = new HomePage(driver);
        loginpage = new LoginPage(driver);
        teamManager = new TeamManagerPage(driver);
        myAccountPage = new MyAccountPage(driver);
        completedpaymentPrecondition = true;
        confirmPaymentCounter = 0;


        String username = Utilities.projectOwnerName;
        String password = Utilities.projectOwnerPassword;
        //username="UserNamePO";
        //password="projects";
        try {

            log.info("markprojectcomplete setup , url is "+ driver.getCurrentUrl());
            String baseURL = "http://test.bidqa.com";
            driver.get(baseURL);
            // login as project owner
            Utilities.loginAccount(username, password, driver, loginpage, homepage);
            Thread.sleep(3000);

            String welcomeText = "Welcome " + username;
            Assert.assertTrue(homepage.getwelcomeText().contains(welcomeText), "Logging not successsful");
            log.info("project owner " + username + " logged in");

            Thread.sleep(3000);

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Exception while awaiting payments test");
        }
    }

    //***Verify project owner can mark project complete **/

    @Test(priority = 0, dataProviderClass = DataProviderClass.class, dataProvider = "SearchProvider")
    public void clearPayments(String teamTitle, String projName) throws InterruptedException {
        //teamTitle = "TeamQA" ;

        //projName="GSKDF";
        try {
            ((JavascriptExecutor) driver).executeScript("scroll(0,300)");

            //click on awaiting completion
            myAccountPage.clickOnTeamManager();
            Thread.sleep(1000);

            String actionString = "";

            //mark the project complete
            actionString = actionNeeded(driver, "Mark Completion", projName);
            if (actionString != null && !actionString.equals("")) {
                Assert.assertTrue(actionString.contains("Mark the project as complete"));

                //click on confirm mark completion button
                driver.findElement(By.xpath("//*[@id='main']/div/div[1]/div/div/form/input[2]")).click();
                Thread.sleep(3000);
            }

            //make escrow payment
            actionString = actionNeeded(driver, "Escrow Payment", projName);
            if (actionString != null && !actionString.equals("")) {
                Assert.assertTrue(actionString.contains("Make Escrow Payment"));
            }

        } catch (Exception e) {

            e.printStackTrace();
            Assert.fail("Exception generated while action in team manager ");
        }
    }

    /*This is method to reuse the code for multiple actions to be performed on action button*/
    /* method to perform desired actions*/

    public String actionNeeded(WebDriver driver, String actionToBePerformed, String projName) throws InterruptedException {
        try {
            //select project name from drop down list
            teamManager.SelectProjectFilterForCompletion(projName);

            // xpath for parent node
            List<WebElement> elements = driver.findElements(By.xpath("//div[@id='content']/div[@class='post-team-manager']"));
            System.out.println("no of elements in list is =  " + elements.size());
            //int counter = 0;
            for (WebElement myElement : elements) {
                //get lement containing project name in all the nodes
                String projectXpath = ".//span[contains(text(),'(" + projName + ")')]";
                List<WebElement> pNameElement = myElement.findElements(By.xpath(projectXpath));
                String pName = "";

                //Check if we find any valid element with project name
                if (pNameElement.size() > 0)
                    pName = pNameElement.get(0).getText();

                //if node found has project name
                if (pName.equals("(" + projName + ")")) {
                    //xpath for checkbox needed
                    List<WebElement> eleCheckBoxes = myElement.findElements(By.xpath(".//input[@class='select-all']"));
                    eleCheckBoxes.get(0).click();
                    Thread.sleep(2000);

                    WebElement act = myElement.findElement(By.xpath(".//button[@id='action']"));
                    Thread.sleep(1000);
                    //Utilities.explicitWaitToClick(driver,20,act);
                    act.click();
                    Thread.sleep(1000);

                    String returnString;
                    // click on escrow payment
                    if (actionToBePerformed.equals("Escrow Payment")) {
                        // click on escrow payments or mark completion
                        //xpath=".//a[text()="Make escrow"]
                        myElement.findElement(By.className("action_make_escrow")).click();
                        Thread.sleep(1000);
                        // get heading of escrow payment page
                        returnString = driver.findElement(By.xpath("//div[@class='box_title']")).getText();
                    } else if (actionToBePerformed.equals("Release Payment")) {
                        // click on mark as completed

                        myElement.findElement(By.className("action_release_payment")).click();
                        Thread.sleep(1000);
                        //to notify action performed successsful
                        returnString = "Release Payment";
                    } else { // for mark as completed
                        // click on mark as completed
                        //xpath=".//a[text()="Make as completed"]
                        myElement.findElement(By.className("action_mark_completed")).click();
                        Thread.sleep(1000);
                        //verify user asked for confirmation to release payment
                        returnString = driver.findElement(By.xpath("//*[@id='wrapper']/div[3]/div/div")).getText();
                    }

                    return returnString;

                } // end of for loop
            }

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Exception generated while performing action in team manager ");
        }
        return null;
    }// end of method


    //***Verify project owner can mark project complete **/


    //@Test(priority = 1,dependsOnMethods = "clearPayments",dataProviderClass = DataProviderClass.class, dataProvider = "SearchProvider")
    @Test(priority = 1, dependsOnMethods = "clearPayments",dataProviderClass = DataProviderClass.class, dataProvider = "SearchProvider")
    public void makeEscrow(String qaName, String escrow) throws InterruptedException {
        //String projName="GSKDF";
        log.info("Inside makeEscrow");
        System.out.println("Inside makeEscrow");
        try {

            //group of input label of QA name
            String path = "//div[@class='form-group row']";
            //List <WebElement> listOfQANames = driver.findElements(By.xpath(path+"//input[@name='display_uids']"));
            List<WebElement> listOfQANames = driver.findElements(By.name("display_uids")); // returns 3
            //List <WebElement> listOfEscrowAmt = driver.findElements(By.xpath(path +"//input[@id='amount']"));
            List<WebElement> listOfEscrowAmt = driver.findElements(By.id("amount"));  // returns 3
            for (int i = 0; i < listOfQANames.size(); i++) {
                if ((listOfQANames.get(i).getAttribute("value")).equals(qaName)) {
                    listOfEscrowAmt.get(i).sendKeys(escrow);
                    break;
                }
            }

        } catch (Exception e) {

        }
    }

    @Test(priority = 2,dependsOnMethods = "makeEscrow",dataProviderClass = DataProviderClass.class, dataProvider = "SearchProvider")
    //@Test(priority = 2,dataProviderClass = DataProviderClass.class, dataProvider = "SearchProvider")
    public void releasePayment(String qaName,Integer totalQA) throws InterruptedException {
        log.info("Release payment called ");
        String projName = Utilities.projectName;
        //projName = "Z1I7Z";
        int qaCount= totalQA.intValue();

    try{

        if(completedpaymentPrecondition) {

            //disable escrow me for testing only
            driver.findElement(By.name("escrowme")).click();
            Thread.sleep(5000);

            String actionString = actionNeeded(driver, "Release Payment", projName);
            if (actionString != null && !actionString.equals("")) {
                Assert.assertTrue(actionString.contains("Release Payment"));
            }
            Thread.sleep(4000);
            ((JavascriptExecutor) driver).executeScript("scroll(0,300)");
            Thread.sleep(3000);

            myAccountPage.clickOnCompletedPayments();
            Thread.sleep(2000);
            String text = driver.findElement(By.xpath("//div[contains(text(),'Completed Payments')]")).getText();

            Assert.assertEquals(text, "Completed Payments");
            // set flag to false to avoid first time execution code
            completedpaymentPrecondition =false ;
            log.info("1st time execution of releasePayment");
        } // completedpaymentPrecondition (to be executed only once)



        //try {
            //get group of  elements having project names in the page
            List<WebElement> listOfProjects = driver.findElements(By.className("post-title-class"));
            //get group of  elements having qa names in the page
            List<WebElement> listOfWinner = driver.findElements(By.xpath("//ul[@class='project-details1 project-details1_a']//p/a"));
            int i;
            // compare project name of each group with the required project name
            for (i = 0; i < listOfProjects.size(); i++) {

                String pname = listOfProjects.get(i).getText();

                // if project name found, get winner name
                if (pname.equals(projName)) {
                    String winnerName = listOfWinner.get(i).getText();
                    // if winner name matches our qa name , confirm payment counter ++
                    if (winnerName.equals(qaName)) {
                        confirmPaymentCounter++;
                        break;
                    }
                }
            }
            //if loop eneded but we found no matching winner with our qaname , then no results found
            if (i == listOfProjects.size()) {
                log.info("QA user: " + qaName + " not found in winner list");

            }
            // if we found all the qa names as winners , then test successful
            if(confirmPaymentCounter==qaCount){
                Assert.assertTrue(true);
            }

        } catch (Exception e) {

            log.info("Error generated while release payment assertion for " + qaName);
            Assert.fail("Winner not found in release payment list");
        }


    } // release payment end


} // end of class
