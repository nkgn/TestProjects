package data;

import org.testng.annotations.DataProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Method;

/**
 * Created by nitu on 5/9/2017.
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

        } else if (m.getName().equalsIgnoreCase("registerCityJournalistTest")) {
            GenerateRegData.generateRegistrationData();
            return new Object[][]{
                    {GenerateRegData.email,GenerateRegData.password,GenerateRegData.accountName,
                            GenerateRegData.firstName, GenerateRegData.lastName, GenerateRegData.address,
                            GenerateRegData.phone, GenerateRegData.zipCode, GenerateRegData.country,
                            GenerateRegData.state, GenerateRegData.city, GenerateRegData.paypal}
            };
        }else if (m.getName().equalsIgnoreCase("activateAccountTest")) {
            return new Object[][]{
                    {GenerateRegData.email}
            };
        }else if (m.getName().equalsIgnoreCase("registerUserTest")) {
            GenerateRegData.generateRegistrationData();
            return new Object[][]{
                    {GenerateRegData.email, GenerateRegData.password,GenerateRegData.accountName}
            };

        }else if (m.getName().equalsIgnoreCase("loggingIn")) {
            return new Object[][]{
                    {GenerateRegData.accountName,GenerateRegData.password}
            };

        }else{
            log.info("DataProvider could not find any data .");
            return null;
        }
    }
}

