package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageResources.* ;

public class HomePage {

    private WebDriver driver ;

    //@FindBy(xpath="//li[starts-with(@id,'menu-item')]/a[@data-hover='Login/Sign Up']")
    @FindBy(xpath="//*[@id='cssmenu']/ul/li[8]/a")
    WebElement loginTab;

    @FindBy(xpath="//*[@id='header']//div[4][contains(text(),'Welcome')]")
    WebElement welcomeText;

    @FindBy(xpath="//img[@id='logo']")
    WebElement bidQaLogo;

    @FindBy(xpath="//i[@class='post-new-awsome']")
    WebElement postNewProjectTab;

    @FindBy(xpath="//div[@class='top-links']/ul[1]//a[contains(text(),'My Account')]")
    WebElement myAccountTab;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public  void clickOnLoginTab() {
        Utilities.explicitWaitToClick(driver,50,loginTab);
        loginTab.click();
    }

    public  void clickOnMyAccountTab() {
        Utilities.explicitWaitToClick(driver,30,myAccountTab);
        myAccountTab.click();
    }

    public  void clickOnPostNewProjectTab() {
        Utilities.explicitWaitToClick(driver,10,postNewProjectTab);
        postNewProjectTab.click();
    }

    public String getwelcomeText(){
        new WebDriverWait(driver,30).until(ExpectedConditions.visibilityOf(welcomeText));
        return welcomeText.getText();
    }

}
