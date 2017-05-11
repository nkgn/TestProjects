package pageresources;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import pages.ActivateAccountPage;
import pages.LoginAndRegistrationPage;

import java.io.File;

public class Utilities {

    private static final Logger log = LogManager.getLogger(Utilities.class.getName());

    public static void explicitWaitToClick(WebDriver driver, int time, WebElement element) {
        new WebDriverWait(driver, time).until(ExpectedConditions.elementToBeClickable(element));
    }


  /*  public void takeScreenShot(ITestResult result,WebDriver driver){
    	if(ITestResult.FAILURE==result.getStatus())
    {
        try
        {
            //Indicates a driver that can capture a screenshot and store it in different ways.
            // selenium interface TakesScreenshot
            TakesScreenshot ts=(TakesScreenshot)tw.driver;
            File source=ts.getScreenshotAs(OutputType.FILE);

            // result.getName() will return name of test case so that screenshot name will be same
            FileUtils.copyFile(source, new File("./Screenshots/"+result.getName()+".png"));
            //public class FileUtils 	extends Object  General file manipulation utilities.

            System.out.println("Screenshot taken");
        }
        catch (Exception e)
        {
            System.out.println("Exception while taking screenshot "+e.getMessage());
        }
    }
*/

    /*public static void loginAsProjectOwner(String username, String password,
                                           WebDriver driver, LoginAndRegistrationPage lp,
                                           HomePage hp){

        hp.clickOnLoginTab();
        lp.fillLoginTextBox(username);
        lp.fillPasswordTextBox(password);
        lp.clickOnSignInButton();
    }*/

    public static void activateAccount(WebDriver driver, String emailUsername) throws InterruptedException {
        ActivateAccountPage activateAcc = new ActivateAccountPage(driver);
        System.out.println("inside activate ");
        try {
            // //create url to specific mailinator account inbox
            //String s1 = "http://www.mailinator.com/inbox2.jsp?public_to=" + emailUsername + "#/#public_maildirdiv";
            //create url to specific getnada account inbox
            String s1 = "https://app.getnada.com/inbox/" + emailUsername + "@getnada.com";
            driver.get(s1);
            Thread.sleep(3000);
            log.info("link to getnada/mailinator" + s1);

            // from AcctivateAccountPage
            //activateAcc.clickOnMailinatorInboxMessage();
            activateAcc.clickOnGetnadaInboxMessage();

            Thread.sleep(5000);
            //for mallinator
            //driver.switchTo().frame("publicshowmaildivcontent");   // switch to frame in mailinator inbox
            //for getnada
            driver.switchTo().frame("idIframe");  //switch to frame in getnada inbox
            Thread.sleep(3000);
            String newlink = activateAcc.clickOnLinkInEmail();
            System.out.println("newlink = " + newlink);
            //driver.get(newlink);
            driver.navigate().to(newlink);
            Thread.sleep(2000);
            //homePage.clickOnLoginTab();
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
            //Assert.fail();
        }
    }
    public static void loginAcc(WebDriver driver, String username,String pwd,LoginAndRegistrationPage register) throws InterruptedException {
        ActivateAccountPage activateAcc = new ActivateAccountPage(driver);
        System.out.println("inside activate ");
        //LoginAndRegistrationPage register = new LoginAndRegistrationPage(driver);
        register.fillLoginUserName(username);
        register.fillLoginPwd(pwd);
        register.clickOnLogInButton();
    }



}
