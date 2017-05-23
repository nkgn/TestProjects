package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by nitu on 5/15/2017.
 */
public class QAProjectSearchPage {

    private WebDriver driver ;

    @FindBy(xpath="//*[contains(@id,'post')]/div/div[3]/div[2]/a[contains(text(),'Read More')]")
    WebElement readMoreButton ;

    @FindBy(id="submit-proposal-id")
    WebElement submitProposalButton;

    @FindBy(id="bid")
    WebElement bidPrice;

    @FindBy(id="days_done")
    WebElement daysDone;

    @FindBy(id="submits_crt_check")
    WebElement proposalCheckBox;

    @FindBy(id="submits_crt")
    WebElement placeBidButton;

    @FindBy(xpath="//div[@class='bid_panel_ok']/div[contains(text(),'Your bid has been posted')]")
    WebElement proposalSubmittedText;

    //@FindBy(xpath="")
   // WebElement searhedProject;


    public QAProjectSearchPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /*public String searchProjectName(String projName){
        new WebDriverWait(driver,50).until(ExpectedConditions.visibilityOf(searhedProject));
        searhedProject.click();
    }*/

    public void clickOnReadMoreButton(){
        new WebDriverWait(driver,50).until(ExpectedConditions.elementToBeClickable(readMoreButton));
        readMoreButton.click();
    }

    public void clickOnSubmitProposalButton(){
        new WebDriverWait(driver,50).until(ExpectedConditions.elementToBeClickable(submitProposalButton));
        submitProposalButton.click();
    }

    public void clickOnProposalCheckBox(){
        new WebDriverWait(driver,50).until(ExpectedConditions.elementToBeClickable(proposalCheckBox));
        proposalCheckBox.click();
    }

    public void clickOnPlaceBidButton(){
        new WebDriverWait(driver,20).until(ExpectedConditions.elementToBeClickable(placeBidButton));
        placeBidButton.click();
    }

    public void fillBidPrice(String price){
        new WebDriverWait(driver,20).until(ExpectedConditions.visibilityOf(bidPrice));
        bidPrice.clear();
        bidPrice.sendKeys(price);
    }

    public void fillDaysDone(String days){
        new WebDriverWait(driver,20).until(ExpectedConditions.visibilityOf(daysDone));
        daysDone.clear();
        daysDone.sendKeys(days);
    }

    public String proposalStatusText(){
        new WebDriverWait(driver,20).until(ExpectedConditions.visibilityOf(proposalSubmittedText));
        return proposalSubmittedText.getText();
    }




}
