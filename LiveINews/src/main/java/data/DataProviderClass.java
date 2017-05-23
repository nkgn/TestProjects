package data;

import org.testng.annotations.DataProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Method;

/**
 * DataProvider class  to create object of  randomly created data and to pass it to various tests based on specific method names
 */
public class DataProviderClass {

    private static final Logger log = LogManager.getLogger(DataProviderClass.class.getName());

/*********Using single data provider method with method parameter ****/

    @DataProvider(name="SearchProvider")
    public Object[][] getDataFromDataprovider(Method m) {

        if (m.getName().equalsIgnoreCase("uploadVideo")) {
            GenerateMyAccData.generateMyAccountData();
            return new Object[][]{
                    {GenerateMyAccData.videoTitle, GenerateMyAccData.videoDescription,
                            GenerateMyAccData.videoCategory, GenerateMyAccData.videoSubCategory,GenerateMyAccData.videoLocation}
            };
        /*this object is returned if register city jounalist test is called */
        } else if (m.getName().equalsIgnoreCase("registerCityJournalistTest")) {
            GenerateRegData.generateRegistrationData();
            return new Object[][]{
                    {GenerateRegData.email,GenerateRegData.password,GenerateRegData.accountName,
                            GenerateRegData.firstName, GenerateRegData.lastName, GenerateRegData.address,
                            GenerateRegData.phone, GenerateRegData.zipCode, GenerateRegData.country,
                            GenerateRegData.state, GenerateRegData.city, GenerateRegData.paypal}
            };
        /*this object is returned if activation of any user test is called */
        }else if (m.getName().equalsIgnoreCase("activateAccountTest")) {
            return new Object[][]{
                    {GenerateRegData.email}
            };
        /*this object is returned if register user test is called */
        }else if (m.getName().equalsIgnoreCase("registerUserTest")) {
            GenerateRegData.generateRegistrationData();
            return new Object[][]{
                    {GenerateRegData.email, GenerateRegData.password,GenerateRegData.accountName}
            };

        }else if (m.getName().equalsIgnoreCase("loggingIn")) {
            return new Object[][]{
                    {GenerateRegData.accountName, GenerateRegData.password}
            };
        /*this object is returned if register media test is called */
        }else if (m.getName().equalsIgnoreCase("registerMediaTest")) {
                GenerateRegData.generateRegistrationData();
                return new Object[][]{
                        {GenerateRegData.email,GenerateRegData.password,GenerateRegData.accountName,
                                GenerateRegData.companyName, GenerateRegData.zipCode,GenerateRegData.address ,
                                GenerateRegData.phone,GenerateRegData.country,
                                GenerateRegData.state, GenerateRegData.city, GenerateRegData.identity,
                                GenerateRegData.contact,GenerateRegData.legalContact}
                };
        /*this object is returned if register editor test is called */
        }else if (m.getName().equalsIgnoreCase("registerEditorTest")) {
            GenerateRegData.generateRegistrationData();
            return new Object[][]{
                    {GenerateRegData.email,GenerateRegData.password,GenerateRegData.accountName,
                            GenerateRegData.firstName,GenerateRegData.lastName, GenerateRegData.address ,
                            GenerateRegData.phone,GenerateRegData.zipCode,GenerateRegData.country,
                            GenerateRegData.state, GenerateRegData.city}
            };

        }else{
            log.info("DataProvider could not find any data .");
            return null;
        }
    }
}

