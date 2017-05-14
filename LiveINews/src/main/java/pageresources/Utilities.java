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

    public static String testingValue="";
    public static String getValue(){
        return testingValue;
    }

    private static final Logger log = LogManager.getLogger(Utilities.class.getName());

    public static void explicitWaitToClick(WebDriver driver, int time, WebElement element) {
        new WebDriverWait(driver, time).until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void activateAccount(WebDriver driver, String emailUsername) throws InterruptedException {
        ActivateAccountPage activateAcc = new ActivateAccountPage(driver);
        //System.out.println("inside activate ");
        try {
            // //create url to specific mailinator account inbox
            //String s1 = "http://www.mailinator.com/inbox2.jsp?public_to=" + emailUsername + "#/#public_maildirdiv";
            String s1= "http://www.mailinator.com/inbox2.jsp?to="+emailUsername+"#/#public_maildirdiv" ;
            //create url to specific getnada account inbox
            //String s1 = "https://app.getnada.com/inbox/" + emailUsername + "@getnada.com";
            driver.get(s1);
            Thread.sleep(3000);
            log.info("link to getnada/mailinator" + s1);

            // from AcctivateAccountPage
            activateAcc.clickOnMailinatorInboxMessage();
            //activateAcc.clickOnGetnadaInboxMessage();

            Thread.sleep(5000);
            //for mallinator
            driver.switchTo().frame("publicshowmaildivcontent");   // switch to frame in mailinator inbox
            //for getnada
            //driver.switchTo().frame("idIframe");  //switch to frame in getnada inbox
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

    public static void loggingIn(WebDriver driver, String username,String pwd,LoginAndRegistrationPage register) throws InterruptedException {
        ActivateAccountPage activateAcc = new ActivateAccountPage(driver);
        System.out.println("inside activate ");
        //LoginAndRegistrationPage register = new LoginAndRegistrationPage(driver);
        register.fillLoginUserName(username);
        register.fillLoginPwd(pwd);
        register.clickOnLogInButton();
    }


}
