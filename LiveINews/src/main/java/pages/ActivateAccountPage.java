package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageresources.Utilities;

public class ActivateAccountPage {

    private WebDriver driver ;

    @FindBy(xpath="//*[starts-with(@id,'row_public')]/div[2]/div[5]/div")
    WebElement mailinatorInboxMessage ;

    //@FindBy(xpath="//body[contains(text(),'Here is your activation link')]")
    @FindBy(xpath="/html/body[contains(text(),'Here is')]")
    //@FindBy(xpath="/html/body")
    WebElement linkInEmail;

    //@FindBy(xpath=("//div[@id='body/']//a//small/span[2]"))
    @FindBy(xpath="//div[@class='inbox-avatar']")
    //*[@id="body"]/div/div[1]/div/div/div/div[2]/div/div/div/div[2]/div/div/div/div/div/a/div
    WebElement getnadaInboxMessage ;

    @FindBy(id="publicshowmaildivcontent")
    WebElement messageIframe ;

    @FindBy(xpath="//*[@id='publicInboxCtrl']/div[1]/div[4]")
    WebElement mailinatorInbox ;




    public ActivateAccountPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getMailinatorInboxText(){
        new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(mailinatorInbox));
        System.out.println(mailinatorInbox.getText());
        return mailinatorInbox.getText();
    }

    public void clickOnMailinatorInboxMessage(){
        Utilities.explicitWaitToClick(driver,30,mailinatorInboxMessage);
        //new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(mailinatorInboxMessage));
        mailinatorInboxMessage.click();
    }

    public void clickOnGetnadaInboxMessage(){
        Utilities.explicitWaitToClick(driver,30,getnadaInboxMessage);
        getnadaInboxMessage.click();
    }
    public String clickOnLinkInEmail(){
         //linkInEmail.getText();
        String m= linkInEmail.getText();
        System.out.println("m = " + m);
        String s2[]=m.split(": ");
        String newlink = s2[1];
        return newlink;
    }





}
