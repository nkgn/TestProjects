package pages;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageResources.Utilities ;
public class ProjectPage {

    WebDriver driver;

    @FindBy(name="project_title")
    WebElement projectTitle;

    //@FindBy(name="project_description")
    @FindBy(xpath="//*[@id='content']//div[2]/form/ul/li[4]/p/textarea")
    WebElement newProjectDescription;

    @FindBy(xpath="//*[@id='post_edit_form']/ul//textarea")
    WebElement projectDescription;

    @FindBy(xpath="//ul[@class='post-new']/li[6]/div[@class='multi_cat_placeholder_thing']")
    WebElement categoriesCheckBoxes ;

    @FindBy(xpath="//ul[@class='post-new']/li[9]/div[@class='multi_cat_placeholder_thing']")
    WebElement skillsCheckBoxes ;

    @FindBy(name="budgets")
    WebElement projectBudget;

    @FindBy(id="ending")
    WebElement projectEndingDate;


    @FindBy(name="project_location_cat")
    WebElement projectCountry ;

    @FindBy(id="subloc")
    WebElement projectState ;

    @FindBy(name="project_location_addr")
    WebElement projectLocationAddress ;

    @FindBy(name="project_submit1")
    WebElement projectSubmit1 ;

    @FindBy(name="project_submit2")
    WebElement projectSubmit2 ;

    @FindBy(xpath="//a[contains(text(),'Next Step')]")
    WebElement nextStep ;

    @FindBy(id="cb-payment")
    WebElement termsCheckBox ;

    @FindBy(xpath="//a[contains(text(),'Pay by Credits')]")
    WebElement payByCreditsButton ;

    @FindBy(xpath="//a[contains(text(),'Pay by PayPal')]")
    WebElement payByPayPalButton ;


    @FindBy(id="pay_now")
    WebElement payNowButton ;

    @FindBy(xpath="//a[contains(text(),'your account')]")
    WebElement goToYourAccountLink ;

    /*@FindBy(xpath="//h2[contains(text(),'Category')]/following-sibling::div")
    WebElement skillsCheckBoxes;*/

    @FindBy(name="save-project")
    WebElement saveProjectButton;

    @FindBy(xpath = "//div[@id='content']/div/div/div/div/a/strong")
    WebElement clickHere;

    @FindBy(xpath="//input[@name='project_cat_cat_multi[]'][@value='65']")
    WebElement catAutomationMobile ;

    //project_skill_cat_multi[]
    @FindBy(xpath="//input[@name='project_skill_cat_multi[]'][@value='37']")
    WebElement skillsAutomationMobile ;
    @FindBy(xpath="//div[@id='ui-datepicker-div']/div[1]/a[2]")
    WebElement nextMonth;

    //@FindBy(xpath="//div[@id='ui-datepicker-div']/table/tbody/tr[5]/td[6]/a[contains(text(),'1')]")
    @FindBy(xpath="//div[@id='ui-datepicker-div']/table//a[contains(text(),'10')]")
    WebElement stDate;

    @FindBy(xpath="//div[@id='ui-datepicker-div']/div[3]/button[text()='Done']")
    WebElement doneButton;

    @FindBy(xpath="//div[@class='sonita2']")
    WebElement bkground;

    // used by project owner to read more about project to select QA as winner
    @FindBy(xpath="//*[contains(@id,'post')]/div/div[3]/div[2]/a[1][contains(text(),'Read More')]")
    WebElement readMoreButton ;

    @FindBy(xpath="//div[@id='my_bids']/div[1]/div[5]/a")
    WebElement selectWinnerLink1 ;


    @FindBy(xpath="//div[@id='my_bids']/div[2]/div[5]/a")
    WebElement selectWinnerLink2 ;

    @FindBy(xpath="//div[@id='my_bids']/div[3]/div[5]/a")
    WebElement selectWinnerLink3 ;

    @FindBy(xpath="//div[@id='my_bids']/div[3]/div[1]/a")
    WebElement selectedWinnerName1;

    @FindBy(xpath="//div[@id='my_bids']/div[3]/div[2]/a")
    WebElement selectedWinnerName2;

    @FindBy(xpath="//div[@id='my_bids']/div[3]/div[3]/a")
    WebElement selectedWinnerName3;





    @FindBy(xpath="//*[@id='main']/div/div[1]/div/div/form/input[2]")
    WebElement chooseWinnerButton;

    @FindBy(xpath="//*[@id='right-sidebar']/ul/li[1]/p/span[1]")
    WebElement matchSelectedQAName1;

    public String getSelectedWinnerName1(){
        return selectedWinnerName1.getText();
    }

    public String getSelectedWinnerName2(){
        return selectedWinnerName2.getText();
    }

    public String getSelectedWinnerName3(){
        return selectedWinnerName3.getText();
    }

    public String getMatchSelectedQAName1(){
        return matchSelectedQAName1.getText();
    }

    public void clickOnSelectWinnerLink1(){
        new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(selectWinnerLink1));
        selectWinnerLink1.click();
    }

    public void clickOnSelectWinnerLink2(){
        new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(selectWinnerLink2));
        selectWinnerLink2.click();
    }

    public void clickOnSelectWinnerLink3(){
        new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(selectWinnerLink3));
        selectWinnerLink3.click();
    }


    public void clickOnChooseWinnerButton(){
        new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(chooseWinnerButton));
        chooseWinnerButton.click();
    }



    public ProjectPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public void clickOnSaveProjectButton() {
        saveProjectButton.click();
    }

    public String getProjectdescription(){
        return projectDescription.getText();
    }


    public void clickOnClickHere() {
        Utilities.explicitWaitToClick(driver,10,projectDescription);
        clickHere.click();
    }

    public void fillProjectTitle(String projectname){
        projectTitle.sendKeys(projectname);
    }

    public void fillProjectDescriptionTextArea(String newText) {
        new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(newProjectDescription));
        newProjectDescription.sendKeys("\n"+ newText);
    }

    public void fillProjectDescription(String descrip){
        Actions actions = new Actions(driver);
        actions.moveToElement(projectDescription);
        actions.perform();
        new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(projectDescription));
        projectDescription.click();
        projectDescription.clear();
        projectDescription.sendKeys(descrip);
    }

    public void selectSkillsAndCat(){
        Actions actions = new Actions(driver);
        new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(catAutomationMobile));
        actions.moveToElement(catAutomationMobile);
        actions.perform();
        catAutomationMobile.click();
        new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(skillsAutomationMobile));
        actions.moveToElement(skillsAutomationMobile);
        actions.perform();
        skillsAutomationMobile.click();
    }

    public void fillProjectBudget(int index){
        //Utilities.explicitWaitToClick(driver,4,projectBudget);
        Select budget = new Select(projectBudget);
        budget.selectByIndex(index);
    }

    public void fillProjectEndingDate(){

/*
        DateFormat dateFormat= new SimpleDateFormat("MM/dd/yyyy");// mm:ss");

        // convert date to calendar
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, 20);

        // convert calendar to date
        Date currentDatePlusOne = c.getTime();
        System.out.println(dateFormat.format(currentDatePlusOne));
        String timeStamp = dateFormat.format(currentDatePlusOne) ;
        //new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(projectEndingDate));
        //fillProjectEndingDateNew(timeStamp+ " 00:00");
        projectEndingDate.sendKeys(timeStamp+ " 00:00");
*/

            //Utilities.explicitWaitToClick(driver,7,projectEndingDate);

            projectEndingDate.click();
            bkground.click();
            //JavascriptExecutor js = (JavascriptExecutor) driver;
            //js.executeScript("window.scrollBy(0,50);");

            projectEndingDate.click();
            //Utilities.explicitWaitToClick(driver,7,nextMonth);
            //nextMonth.click();
            Utilities.explicitWaitToClick(driver,10,nextMonth);
            nextMonth.click();
            Utilities.explicitWaitToClick(driver,10,stDate);
            stDate.click();
            doneButton.click();

    }
    public void fillProjectEndingDateNew(String endDate){
        projectEndingDate.click();
        Utilities.explicitWaitToClick(driver,5,nextMonth);
        nextMonth.click();
        Utilities.explicitWaitToClick(driver,4,stDate);
        stDate.click();
        doneButton.click();
    }


    public void fillProjectCountry(int index){
        Select country = new Select(projectCountry);
        country.selectByIndex(index);
    }
    public void fillProjectState(int index){
        Select state = new Select(projectState);
        state.selectByIndex(index);
    }

    public void fillProjectLocationAddress(String addr){
        projectLocationAddress.sendKeys(addr);
    }

    public void clickOnProjectSubmit1(){
        Utilities.explicitWaitToClick(driver,10,projectSubmit1);
        projectSubmit1.click();
    }
    public void clickOnProjectSubmit2(){
        Utilities.explicitWaitToClick(driver,10,projectSubmit2);
        projectSubmit2.click();
    }

    public void clickOnNextStep(){
        Utilities.explicitWaitToClick(driver,10,nextStep);
        nextStep.click();
    }
    public void selectTermsCheckBox(){
        Utilities.explicitWaitToClick(driver,10,termsCheckBox);
        termsCheckBox.click();
    }
    public void clickOnPayByCreditsButton(){
        Utilities.explicitWaitToClick(driver,10,payByCreditsButton);
        payByCreditsButton.click();
    }
    public void clickOnPayNowButton(){
        Utilities.explicitWaitToClick(driver,10,payNowButton);
        payNowButton.click();
    }

    public void clickOnGoToYourAccountLink(){
        Utilities.explicitWaitToClick(driver,10,goToYourAccountLink);
        goToYourAccountLink.click();
    }

    //String projectPath= "//div[@class='post-title']/a[contains(@href, '" + projectTitle +"')]";

    @FindBy(xpath="//div[@id='content']/div[2]/div[1]/div[1]/a")
    WebElement addedProject;

    @FindBy(xpath="//*[@id='wrapper']/div[3]/div/div[1]/div[1]")
    WebElement postedProjectText ;

    // for pay by credits
    public String getAddedProjectText(){
        return addedProject.getText();
    }

    @FindBy(xpath="//div[@id='content']/div[4]/div[2]/p")
    WebElement updatedProjDescription;
    public String getUpdatedProjDescription() {
        return updatedProjDescription.getText();
    }

    // for pay by paypal
    public String getPostedProjectText(){
        new WebDriverWait(driver,50).until(ExpectedConditions.visibilityOf(postedProjectText));
        return postedProjectText.getText();
    }

    public void clickOnPayByPayPalButton(){
        new WebDriverWait(driver,30).until(ExpectedConditions.elementToBeClickable(payByPayPalButton));
        payByPayPalButton.click();
    }

    public void clickOnReadMoreButton(){
        new WebDriverWait(driver,30).until(ExpectedConditions.elementToBeClickable(readMoreButton));
        readMoreButton.click();
    }
}
