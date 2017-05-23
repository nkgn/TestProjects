package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageResources.Utilities;

/**
 * Created by nitu on 5/17/2017.
 */
public class TeamManagerPage {


    private WebDriver driver;

   /* //click on team manager
    //@FindBy(xpath="/*//*[@id='my-account-admin-menu_seller]/li[8]/a")
    @FindBy(xpath="/*//*[@id='my-account-admin-menu_seller']//a[contains(text(),'Team Manager')]")
    WebElement teamManager ;

    /*//*[@id="my-account-admin-menu_seller"]/li[5]/a
    @FindBy(xpath="/*//*[@id='my-account-admin-menu_seller']//a[contains(text(),'Awaiting Completion')]")
    WebElement awaitingCompletion ;*/

    //*[@id="team-192"]/thead/tr/th[1]/input

    //*[@id="action"]
    @FindBy(id="action")
    WebElement actionButton ;

    //*[contains(@id,'team')]/div[1]/div[3]/div/ul/li[5]/a
    @FindBy(xpath="//*[contains(@id,'team')]/div[1]/div[3]/div/ul/li[5]/a")
    WebElement makeEscrow;

    @FindBy(id="projects-filter")
    WebElement filterProject;

    @FindBy(id="create-new-team")
    WebElement createNewTeamButton ;

    @FindBy(id="select-project")
    WebElement selectProjectList ;

    @FindBy(id="team-title")
    WebElement teamTitle ;

    @FindBy(id="to-team")
    WebElement teamTitleSelect ;

    @FindBy(id="submit-create-team")
    WebElement submitTeamButton ;


    //checkbox to make escrow payments for all QAs
    //@FindBy(xpath="//*[contains(@id,'team')]thead")
    //WebElement awaitingPaymentsCheckBox;


    @FindBy(id="to-team")
    WebElement teamName ;

    @FindBy(id="select-add-users")
    WebElement QAName ;

    @FindBy(id="submit-add-user")
    WebElement addUserSubmitButton;

    @FindBy(id="projects-filter")
    WebElement projectFilterForCompletion;



    public void selectFilterProject(String projectname){
        Select ele = new Select(filterProject);
        ele.selectByVisibleText(projectname);

    }

    public TeamManagerPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void selectMakeEscrow(){
        Utilities.explicitWaitToClick(driver,30,makeEscrow);
        makeEscrow.click();
    }

  /*  public void clickOnTeamManager(){
        Utilities.explicitWaitToClick(driver,30,teamManager);
        teamManager.click();
    }
*/


   // public void clickOnAwaitingPaymentCheckBox(){
     //   Utilities.explicitWaitToClick(driver,30,awaitingPaymentsCheckBox);
     //   awaitingPaymentsCheckBox.click();
   // }

   /* public void clickOnAwaitingCompletion(){
        Utilities.explicitWaitToClick(driver,30,awaitingCompletion);
        awaitingCompletion.click();
    }*/

    public void  clickOnCreateTeamButton(){
        Utilities.explicitWaitToClick(driver,30,createNewTeamButton);
        createNewTeamButton.click();
    }
    public void  clickOnActionButton(){
        Utilities.explicitWaitToClick(driver,30,actionButton);
        actionButton.click();
    }


    public void selectProject(String projectName){
        Select project = new Select(selectProjectList);
        project.selectByVisibleText(projectName);
    }

    public void fillTeamTitle(String teamName){
        teamTitle.sendKeys(teamName);

    }

    public void clickOnSubmitTeamButton(){
        Utilities.explicitWaitToClick(driver,30,submitTeamButton);
        submitTeamButton.click();
    }
    public void clickOnAddUserSubmitButton(){
        Utilities.explicitWaitToClick(driver,30,addUserSubmitButton);
        addUserSubmitButton.click();
    }

    public void selectQAName(String qaNameSt){
    Select ele = new Select(QAName);
    ele.selectByVisibleText(qaNameSt);
    }

    public void selectTeam(String title){
        Select ele = new Select(teamTitleSelect);
        ele.selectByVisibleText(title);
    }

    public void SelectProjectFilterForCompletion(String projname){
        Select ele = new Select(projectFilterForCompletion);
        ele.selectByVisibleText(projname);

    }

    /*public void clickOnAddUserButton(){
        Utilities.explicitWaitToClick(driver,30,addUserButton);
        addUserButton.click();
    }*/





}
