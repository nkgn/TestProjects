package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by nitu on 5/14/2017.
 */
public class PayPalPage {
    private WebDriver driver ;

    @FindBy(xpath="//*[@id='loginSection']//h1[contains(text(),'Pay with PayPal')]")
    WebElement payPalTitle;

    @FindBy(xpath="//*[@id='email']")
    WebElement payPalEmail;

    @FindBy(xpath="//*[@id='password']")
    WebElement payPalPassword;

    @FindBy(xpath="//*[@id='btnLogin']")
    WebElement payPalLogInButton;

    @FindBy(xpath="//*[@id='confirmButtonTop']")
    WebElement payPalConfirmButton;

    @FindBy(xpath="//*[@id='paid-text']/span[contains(text(),'You paid')]")
    WebElement amountPaidConfirmationText;


    @FindBy(xpath="//*[@id='merchantReturnBtn']")
    WebElement returnToMerchantButton;


    public PayPalPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getPayPalTitle(){
        new WebDriverWait(driver,50).until(ExpectedConditions.visibilityOf(payPalTitle));
        return payPalTitle.getText();
    }

    public void fillPayPalEmail(String email){
        new WebDriverWait(driver,50).until(ExpectedConditions.visibilityOf(payPalEmail));


        payPalEmail.sendKeys(email);
    }
    public void fillPayPalPwd(String pwd){
        new WebDriverWait(driver,50).until(ExpectedConditions.visibilityOf(payPalPassword));
        payPalPassword.sendKeys(pwd);
    }
    public void clickOnPayPalLogInButton(){
        new WebDriverWait(driver,50).until(ExpectedConditions.elementToBeClickable(payPalLogInButton));
        payPalLogInButton.click();
    }

    public void clickOnPayPalConfirmButton(){

        new WebDriverWait(driver,50).until(ExpectedConditions.elementToBeClickable(payPalConfirmButton));
        payPalConfirmButton.click();
    }

    public void clickOnReturnToMerchantButton(){
        new WebDriverWait(driver,50).until(ExpectedConditions.elementToBeClickable(returnToMerchantButton));
        returnToMerchantButton.click();
    }
    public String getAmountPaidConfirmationText(){
        return amountPaidConfirmationText.getText();
    }

}
