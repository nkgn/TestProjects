/*This is used as helper class for all the pages*/
/* used for code reusability */


package pageResources;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pages.*;

public class Utilities {

    public static WebDriver driver;
    public static String projectOwnerName;
    public static String projectOwnerPassword;
    public static String projectName;

    public static void explicitWaitToClick(WebDriver driver, int time, WebElement element){
        new WebDriverWait(driver,time).until(ExpectedConditions.elementToBeClickable(element));
    }

    /* used to login user based on credentials passed as arguments */
    public static void loginAccount (String username, String password,
                                           WebDriver driver, LoginPage lp,
                                           HomePage hp)throws InterruptedException
    {

        String baseURL="http://test.bidqa.com";
        driver.get(baseURL);
        Thread.sleep(5000);
        hp.clickOnLoginTab();
        Thread.sleep(7000);
        lp.fillLoginTextBox(username);
        lp.fillPasswordTextBox(password);
        lp.clickOnSignInButton();
    }

}
