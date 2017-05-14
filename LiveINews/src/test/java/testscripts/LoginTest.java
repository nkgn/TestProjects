package testscripts;

import org.testng.annotations.*;
import pageresources.CopyDriverInstance;
import pageresources.Utilities;
import org.openqa.selenium.WebDriver;
import data.DataProviderClass;
import pages.LoginAndRegistrationPage;


public class LoginTest
{
    private WebDriver driver ;

    //String emailUserName="";


    @BeforeClass
    public void setUp() {
        driver= CopyDriverInstance.driver ;
    }

    /**Test to loggin any type of user****/


    @Test(enabled=true,dataProvider = "SearchProvider", dataProviderClass = DataProviderClass.class)
    public void loggingIn(String accName,String pwd)throws InterruptedException{

        System.out.println("inside logging in " );
        //Utilities.loginAcc(driver,accName,pwd);
        LoginAndRegistrationPage reg = new LoginAndRegistrationPage(driver);
        reg.fillLoginUserName(accName);
        reg.fillLoginPwd(pwd);
        reg.clickOnLogInButton();
        Thread.sleep(3000);
    }


}
