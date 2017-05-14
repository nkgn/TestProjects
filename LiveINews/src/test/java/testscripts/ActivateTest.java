package testscripts;

import org.testng.annotations.*;
import pageresources.CopyDriverInstance;
import pageresources.Utilities;
import org.openqa.selenium.WebDriver;
import data.DataProviderClass;


public class ActivateTest
{
    private WebDriver driver ;

    String emailUserName="";


    @BeforeClass
    public void setUp() {
        driver= CopyDriverInstance.driver ;
    }

    /**Test to activate city journalist by opening email inbox and clicking on the link provided****/


    @Test(enabled=true,dataProvider = "SearchProvider", dataProviderClass = DataProviderClass.class)
    public void activateAccountTest(String emailUserName)throws InterruptedException{

        System.out.println("inside activate " );
        Utilities.activateAccount(driver,emailUserName);

    }


}
