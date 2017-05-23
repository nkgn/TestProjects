package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import pageResources.Utilities ;
/**
 * Created by nitu on 4/23/2017.
 */
public class LoginPage {

    private WebDriver driver ;

    @FindBy(id="log")
    WebElement loginTextBox;

    @FindBy(id="login_password")
    WebElement passwordTextBox;

    @FindBy(id="submits")
    WebElement signInButton;

    @FindBy(xpath="//i[@class='logout-awsome']")
    WebElement signOutButton;



    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public  void fillLoginTextBox(String username) {
        Utilities.explicitWaitToClick(driver,30,loginTextBox);
        loginTextBox.sendKeys(username);
    }


    public  void fillPasswordTextBox(String password) {
        passwordTextBox.sendKeys(password);
    }


    public void clickOnSignInButton() {
        signInButton.click();
    }

    public void clickOnSignOutButton() {
        Utilities.explicitWaitToClick(driver,20,signOutButton);
        signOutButton.click();

    }

}
