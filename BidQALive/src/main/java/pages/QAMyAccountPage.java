package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageResources.Utilities;

/**
 * Created by nitu on 5/17/2017.
 */
public class QAMyAccountPage {


    private WebDriver driver;

    @FindBy(xpath="//*[contains(@id,'post')]/div/div[5]/div[1]/button[contains(text(),'Start work timer')]")
    WebElement startWorkTimerButton;

    @FindBy(xpath="//div[@class='panel-body']//input[@type='checkbox']")
    WebElement acceptCheckBox ;

    @FindBy(xpath="//*[starts-with(@id,'post')]//div/div[2]/input[2]")
    WebElement OKButton ;

    @FindBy(xpath="//button[@act='stop']")
    WebElement stopWorkTimerButton ;


    @FindBy(xpath="//*[contains(@id,'post')]//a[@class='green_btn']")
    WebElement markDeliveredButton ;

    @FindBy(xpath="//div[@class='mm_inn']")
    WebElement pageHeading;

    @FindBy(xpath="//input[@type='submit'][@name='yes']")
    WebElement confirmDeliveredButton;

    public QAMyAccountPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickOnStartWorkTimerButton(){
        Utilities.explicitWaitToClick(driver,30,startWorkTimerButton);
        startWorkTimerButton.click();
    }

    public void clickOnStopWorkTimerButton(){
        Utilities.explicitWaitToClick(driver,30,stopWorkTimerButton);
        stopWorkTimerButton.click();
    }

    public void clickOnMarkDeliveredButton(){
        Utilities.explicitWaitToClick(driver,30,markDeliveredButton);
        markDeliveredButton.click();
    }

    public void clickOnOKButton(){
        Utilities.explicitWaitToClick(driver,30,OKButton);
        OKButton.click();
    }
    public void clickOnAcceptCheckBox(){
        Utilities.explicitWaitToClick(driver,30,acceptCheckBox);
        acceptCheckBox.click();
    }

    public String getPageHeading(){
        new WebDriverWait(driver,30).until(ExpectedConditions.visibilityOf(pageHeading));
        return pageHeading.getText();
    }
    public void clickOnConfirmDeliveredButton(){
        Utilities.explicitWaitToClick(driver,30,confirmDeliveredButton);
        confirmDeliveredButton.click();
    }





}
