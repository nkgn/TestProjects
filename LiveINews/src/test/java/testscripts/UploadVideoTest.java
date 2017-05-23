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

/*****Test for uploading video by registered user/media ******/
public class UploadVideoTest
{
    private WebDriver driver ;

    @BeforeClass
    public void setUp() {
        driver= CopyDriverInstance.driver ;
    }

    /**Test to upload video functionality once user is logged in ****/

    @Test(priority=3,enabled=true,dataProvider = "SearchProvider", dataProviderClass = DataProviderClass.class)
    public void uploadVideo(String title,String description, Integer category, Integer subCategory,String location)throws InterruptedException,AWTException{
        System.out.println("inside upload video");
        MyAccountPage myAccPage =new MyAccountPage(driver);
        myAccPage.clickOnUploadVideos();
        Thread.sleep(3000);
        myAccPage.fillUploadVideosTitle(title);
        myAccPage.setChooseFile("toy_plane_liftoff.avi");
        myAccPage.fillUploadVideoDescription(description);
        myAccPage.selectUploadVideoCategory(category);
        Thread.sleep(3000);

        myAccPage.selectUploadVideoSubCategory(subCategory);
        myAccPage.fillUploadVideoLocation(location);

        Thread.sleep(1000);
        myAccPage.clickOnSubmitButton();
        Thread.sleep(3000);

        System.out.println("title label = " + myAccPage.getTitleLabel());
        /*Assert that video is sucessfully uploaded ***/
        Assert.assertEquals(myAccPage.getTitleLabel(),title,"Video title not found");
        myAccPage.clickOnVideoTitle();

        Thread.sleep(5000);

        /**Assert that uploaded video can be played on clicking ***/
        Assert.assertEquals(myAccPage.getSelectedVideoTitle(),title,"Titles are not equal");

        //driver.switchTo().frame("videoplayer");
        driver.switchTo().frame(0);
        myAccPage.clickOnVideoPlayButton();
        Thread.sleep(10000);
        driver.switchTo().defaultContent();
    }
}
