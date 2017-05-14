package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageresources.Utilities;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.util.List;
/**
 * Created by nitu on 5/8/2017.
 */
public class MyAccountPage
{

    private WebDriver driver ;

    @FindBy(xpath="//*[@id='post-8']/div/div/ul/li[4]/a")
    WebElement uploadVideosTab;

    @FindBy(id="title")
    WebElement uploadVideosTitle;

    //id= the-video-file-field,  name = userfile

    @FindBy(id="the-video-file-field")
    WebElement uploadVideosChoosefileButton;

    @FindBy(id="description")
    WebElement uploadVideoDescription ;

    @FindBy(id="category")
    WebElement uploadVideoCategory ;

    @FindBy(id="sub_category")
    WebElement uploadVideoSubCategory ;

    @FindBy(id="add-location")
    WebElement uploadVideoLocation ;

    @FindBy(xpath="//*[@id='wb-upload-form']/div[10]/div/input")
    WebElement submitButton ;

    @FindBy(xpath="//div[starts-with(@id,'product_id')]//span[@class='my-video-item-title']")
    WebElement titleLabel;

    //@FindBy(xpath="//*[@id='wb-upload-form']/div[10]/div/input")

    @FindBy(xpath="//div[starts-with(@id,'product')]/h4[@class='wb-title']")
    WebElement selectedVideoTitle;


    @FindBy(xpath="/html/body/div/div[5]/div[2]/canvas")
    WebElement videoPlayButton;

    public MyAccountPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickOnUploadVideos(){
        Utilities.explicitWaitToClick(driver,30,uploadVideosTab);
        uploadVideosTab.click();
    }

    public void fillUploadVideosTitle(String title){

        Utilities.explicitWaitToClick(driver,30,uploadVideosTitle);
        uploadVideosTitle.sendKeys(title);
    }

    public void setChooseFile(String path) throws InterruptedException, AWTException {
        uploadVideosChoosefileButton.click();

        StringSelection s = new StringSelection(path);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(s, null);
        Robot robot = new Robot();
        robot.keyPress(java.awt.event.KeyEvent.VK_ENTER);
        robot.keyRelease(java.awt.event.KeyEvent.VK_ENTER);
        robot.keyPress(java.awt.event.KeyEvent.VK_CONTROL);
        robot.keyPress(java.awt.event.KeyEvent.VK_V);
        robot.keyRelease(java.awt.event.KeyEvent.VK_CONTROL);
        Thread.sleep(2000);
        robot.keyPress(java.awt.event.KeyEvent.VK_ENTER);
    }

    public void clickOnVideoPlayButton(){
        videoPlayButton.click();
    }



    public void clickOnUploadVideosChoosefileButton (){
        //uploadVideosChoosefileButton.sendKeys("C://QAUBER/LiveINews/byger-20030708-liten.avi");
        //Switch focus on pop window
        try {
            String popupHandle = driver.getWindowHandle();
            WebDriver popup;
            popup = driver.switchTo().window(popupHandle);

            //Click on 'Browse Files' button.
            Thread.sleep(5000);
            //WebElement filebrowse = driver.findElement(By.id("browse-files"));
            uploadVideosChoosefileButton.sendKeys("C://QAUBER/LiveINews/byger-20030708-liten.avi");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    //C:/QAUBER/LiveINews/byger-20030708-liten.avi

    public void selectUploadVideoCategory(int index){
        Select sel = new Select(uploadVideoCategory);
        sel.selectByIndex(index);
    }
    public void selectUploadVideoSubCategory(int index){
        Select sel = new Select(uploadVideoSubCategory);
        sel.selectByIndex(index);
    }

    public void fillUploadVideoDescription(String s){

        uploadVideoDescription.sendKeys(s);
    }

    public void fillUploadVideoLocation (String s){
        try {
            uploadVideoLocation.sendKeys(s);
            System.out.println("s");
            Thread.sleep(3000);
            uploadVideoLocation.sendKeys(Keys.DOWN);
            uploadVideoLocation.sendKeys(Keys.RETURN);

        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public void clickOnSubmitButton(){
        System.out.println("before submit");
        Utilities.explicitWaitToClick(driver,30,submitButton);
        submitButton.click();
    }

    public WebElement getUploadVideosTab(){
        return uploadVideosTab;
    }

    public String getTitleLabel(){
        new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(titleLabel));
        return titleLabel.getText();
    }

    public void clickOnVideoTitle(){
        titleLabel.click();
    }

    public String getSelectedVideoTitleText(){
         return selectedVideoTitle.getText();
    }
}
