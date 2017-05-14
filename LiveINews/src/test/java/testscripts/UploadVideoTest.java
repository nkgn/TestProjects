package testscripts;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pageresources.CopyDriverInstance;
import pageresources.Utilities;
import org.openqa.selenium.WebDriver;
import data.DataProviderClass;
import pages.ActivateAccountPage;
import pages.MyAccountPage;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;


public class UploadVideoTest
{
    private WebDriver driver ;


    @BeforeClass
    public void setUp() {
        driver= CopyDriverInstance.driver ;
    }

    /**Test to activate city journalist by opening email inbox and clicking on the link provided****/

    @Test(priority=3,enabled=true,dataProvider = "SearchProvider", dataProviderClass = DataProviderClass.class)
    public void uploadVideo(String title,String description, Integer category, Integer subCategory,String location)throws InterruptedException,AWTException{
        System.out.println("inside upload video");
        MyAccountPage myAccPage =new MyAccountPage(driver);
        myAccPage.clickOnUploadVideos();
        Thread.sleep(3000);
        myAccPage.fillUploadVideosTitle(title);
        //myAccPage.clickOnUploadVideosChoosefileButton();
            myAccPage.setChooseFile("toy_plane_liftoff.avi");
            //myAccPage.setChooseFile("C:\\QAUBER\\LiveINews\\byger-20030708-liten.avi");

        myAccPage.fillUploadVideoDescription(description);

        myAccPage.selectUploadVideoCategory(category);
        Thread.sleep(3000);

        myAccPage.selectUploadVideoSubCategory(subCategory);
        myAccPage.fillUploadVideoLocation(location);

        Thread.sleep(2000);
        myAccPage.clickOnSubmitButton();

        //Thread.sleep(30000);
        System.out.println("title label = " + myAccPage.getTitleLabel());

        Assert.assertEquals(myAccPage.getTitleLabel(),title,"Video title not found");
        //System.out.println("title label = " + myAccPage.getTitleLabel());
        myAccPage.clickOnVideoTitle();

        Thread.sleep(5000);
        Assert.assertEquals(title,myAccPage.getSelectedVideoTitleText(),"Titles are not equal");

        //driver.switchTo().frame("videoplayer");
        driver.switchTo().frame(0);
        myAccPage.clickOnVideoPlayButton();
        Thread.sleep(10000);
        driver.switchTo().defaultContent();
    }
}
