
package pages;

        import org.openqa.selenium.JavascriptExecutor;
        import org.openqa.selenium.Keys;
        import org.openqa.selenium.WebDriver;
        import org.openqa.selenium.WebElement;
        import org.openqa.selenium.interactions.Mouse;
        import org.openqa.selenium.support.FindBy;
        import org.openqa.selenium.support.PageFactory;
        import pageresources.Utilities;
        import org.openqa.selenium.support.ui.Select;


public class LoginAndRegistrationPage {

    private WebDriver driver;

    @FindBy(id="i-e-type")
    WebElement accountType ;

    @FindBy(id="reg_email")
    WebElement emailTextBox ;

    @FindBy(id="reg_password")
    WebElement pwdTextBox ;

    @FindBy(id="creg_password")
    WebElement confirmPwdTextBox ;

    @FindBy(id="i-e-name")
    WebElement accountName ;

    @FindBy(id="i-e-address")
    WebElement address ;

    @FindBy(id="i-e-first")
    WebElement firstName ;

    @FindBy(id="i-e-last")
    WebElement lastName ;

    @FindBy(id="i-e-phone")
    WebElement phone ;

    @FindBy(id="i-e-zip")
    WebElement zip ;

    @FindBy(id="countryId")
    WebElement country ;

    @FindBy(id="stateId")
    WebElement state ;

    @FindBy(id="cityId")
    WebElement city ;

    @FindBy(id="i-e-ein")
    WebElement identityNumber ;

    @FindBy(id="i-e-contact")
    WebElement contact ;

    @FindBy(id="i-e-mlc")
    WebElement legalContact ;





    @FindBy(id="i-e-paypal")
    WebElement paypalAcc ;

    //@FindBy(name="register")
    @FindBy(xpath="//*[@id='registration-form']/p[2]/input")
    WebElement registerButton ;

    @FindBy(id="acceptterms")
    WebElement acceptTerms ;

    @FindBy(xpath=".//*[@id='terms-modal']/div/p[2]/span")
    WebElement acceptTermsNext ;

    //@FindBy(xpath="//*[@id='terms-modal']/div/p[contains(text(),'License')]")
    @FindBy(xpath="//*[@id='terms-modal']")
    WebElement acceptTermsDialog ;

    @FindBy(id="i-e-company")
    WebElement companyName ;

    @FindBy(name="log")
    WebElement loginUsername ;

    @FindBy(name="pwd")
    WebElement loginPwd ;

    @FindBy(name="wp-submit")
    WebElement logInButton ;

    @FindBy(xpath="//span[@class='lost_password']/a[contains(@href,'liveinews')]")
    WebElement lostPwd;

    @FindBy(name="rememberme")
    WebElement rememberme ;



    public LoginAndRegistrationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void fillLoginUserName(String username){
        loginUsername.sendKeys(username);
    }

    public void fillLoginPwd(String pwd){
        loginPwd.sendKeys(pwd);
    }

    public void clickOnLogInButton(){
        logInButton.click();
    }

    public void clickOnLostPasswordLink(){
        lostPwd.click();
    }

    public void clickOnRememberMe(){
        rememberme.click();
    }

    public  void selectAccountType(int index) {
        Utilities.explicitWaitToClick(driver,30,accountType);

        Select aType = new Select(accountType);
        aType.selectByIndex(index);
    }

    public void fillEmailTextBox(String emailid){
        emailTextBox.sendKeys(emailid);
    }

    public void fillPwdTextBox(String pwd){
        pwdTextBox.sendKeys(pwd);
    }

    public void fillConfirmPwdTextBox(String pwd){
        confirmPwdTextBox.sendKeys(pwd);
    }
    public void fillAccountName(String name){
        accountName.sendKeys(name);
    }

    public void fillFirstName(String first) {
       // Utilities.explicitWaitToClick(driver,10,firstName);
        firstName.sendKeys(first);
    }

    public void fillCompanyName(String name) {
        // Utilities.explicitWaitToClick(driver,10,lastName);
        companyName.sendKeys(name);
    }


    public void fillLastName(String last) {
        // Utilities.explicitWaitToClick(driver,10,lastName);
        lastName.sendKeys(last);
    }

    public void fillAddress(String addre) {
        // Utilities.explicitWaitToClick(driver,10,address);
        address.sendKeys(addre);
    }


    public void fillPhone(String ph) {
        // Utilities.explicitWaitToClick(driver,10,phone);
        phone.sendKeys(ph);
    }

    public void fillZipCode(String z) {
        // Utilities.explicitWaitToClick(driver,10,zip);
        zip.sendKeys(z);
    }
    public  void selectCountry(int index) {
        Utilities.explicitWaitToClick(driver,30,country);

        Select countryName = new Select(country);
        countryName.selectByIndex(index);
    }
    public  void selectState(int index) {
        Utilities.explicitWaitToClick(driver,30,state);

        Select stateName = new Select(state);
        stateName.selectByIndex(index);
    }

    public  void selectCity(int index) {
        Utilities.explicitWaitToClick(driver,30,city);

        Select cityName = new Select(city);
        cityName.selectByIndex(index);
    }

    public void fillIdentityNumber(String identity) {
        // Utilities.explicitWaitToClick(driver,10,address);
        identityNumber.sendKeys(identity);
    }

    public void fillContact(String data){
        contact.sendKeys(data);
    }

    public void fillLegalContact(String data){
        legalContact.sendKeys(data);
    }

    public void fillPaypal(String paypalID) {
        Utilities.explicitWaitToClick(driver,30,paypalAcc);
        paypalAcc.sendKeys(paypalID);
    }
    public void clickNext(){
        Utilities.explicitWaitToClick(driver,30,registerButton);
        registerButton.click();
    }
    public void scrollAcceptTermsDialog() throws InterruptedException{

        Thread.sleep(2000);
        //String keysPressed =  Keys.chord(Keys.CONTROL, Keys.END);
        acceptTermsDialog.click();
        Thread.sleep(1000);
        boolean st = acceptTermsDialog.isSelected();

        System.out.println(st);
        acceptTermsDialog.sendKeys(Keys.ARROW_DOWN);

        JavascriptExecutor Scrool = (JavascriptExecutor) driver;
        Scrool.executeScript("window.scrollBy(0,100)", "");
        Thread.sleep(1000);

//        keysPressed =  Keys.chord(Keys.ARROW_DOWN);
//        acceptTermsDialog.sendKeys(keysPressed) ;

//        Thread.sleep(1000);
//        keysPressed =  Keys.chord(Keys.CONTROL, Keys.END);
//        acceptTermsDialog.sendKeys(keysPressed) ;
    }
    public void clickOnAcceptTerms(){
        Utilities.explicitWaitToClick(driver,30,acceptTerms);
        acceptTerms.click();
    }
    public void clickOnNext(){
        Utilities.explicitWaitToClick(driver,30,acceptTermsNext);
        acceptTermsNext.click();
    }







}
