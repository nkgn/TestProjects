package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageResources.Utilities ;
/**
 * Created by nitu on 4/23/2017.
 */
public class MyAccountPage {

    private WebDriver driver;

    @FindBy(id="my-top-search-input")
    WebElement searchTextBox;

    @FindBy(id="my-top-submit-input")
    WebElement searchButton;

    @FindBy(xpath="//div[@class='post']/div[1]/div[3]/div[2]//a[contains(text(),'Edit Project')]")
    WebElement editButton;

    @FindBy(xpath="//div[@class='post']/div[1]/div[3]/div[2]//a[contains(text(),'Delete')]")
    WebElement deleteButton;

    @FindBy(xpath="//input[@name='are_you_sure']")
    WebElement confirmDeleteButton;

    @FindBy(xpath="//div[@class='box_content']")
    WebElement projectDeleteStatus;

    @FindBy(xpath="//div[@class='welcome']")
    WebElement textWelcome;

    @FindBy(xpath="//*[@id='my-account-admin-menu_seller']//a[contains(text(),'Team Manager')]")
    WebElement teamManager ;


    @FindBy(xpath="//*[@id='my-account-admin-menu_seller']//a[contains(text(),'Awaiting Completion')]")
    WebElement awaitingCompletion ;


    @FindBy(xpath="//*[@id='my-account-admin-menu_seller']//a[contains(text(),'Completed Payments')]")
    //@FindBy(xpath="//*[@id='my-account-admin-menu_seller']/li[7]/a")
    WebElement completedPayments  ;


    public MyAccountPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public void clickOnTeamManager(){
        Utilities.explicitWaitToClick(driver,30,teamManager);
        teamManager.click();
    }
    public void clickOnCompletedPayments(){
        Utilities.explicitWaitToClick(driver,50,completedPayments);
        completedPayments.click();
    }


    public  void fillSearchTextBox(String searchText) {
        Utilities.explicitWaitToClick(driver,10,searchTextBox);
        searchTextBox.sendKeys(searchText);
    }

    public  void clickSearchButton() {
        Utilities.explicitWaitToClick(driver,10,searchButton);
        searchButton.click();
    }

    public  void clickEditButton() {
        Utilities.explicitWaitToClick(driver,10,editButton);
        editButton.click();
    }


    public  void clickDeleteButton() {
        Utilities.explicitWaitToClick(driver,10,deleteButton);
        deleteButton.click();
    }

    public  String getProjectDeleteStatus() {

        return projectDeleteStatus.getText();

    }

    public void clickOnConfirmDeleteButton(){
        Utilities.explicitWaitToClick(driver,10,confirmDeleteButton);
        confirmDeleteButton.click();
    }

    public void clickOnTextWelcome(){
        textWelcome.click();
    }


}
