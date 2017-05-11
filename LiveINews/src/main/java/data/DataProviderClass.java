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

/**********using different data provider methods
/*
    @DataProvider(name="fieldsInputs")
    public static Object[][] getLoginData(){
        GenerateRegistrationTestData loginData = new GenerateRegistrationTestData();

        return new Object[][]{

                {loginData.accountName,loginData.password},

        };

    }
    @DataProvider(name="uploadVideofieldsInputs")
    public static Object[][] uploadVideofieldsInputs(){
        GenerateMyAccountTestData randomData = new GenerateMyAccountTestData();

        return new Object[][]{

                {randomData.videoTitle,randomData.videoDescription, randomData.videoCategory, randomData.videoSubCategory}

        };

    }

*/

/*********Using single data provider method with method parameter ****/

    @DataProvider(name="SearchProvider")
    public Object[][] getDataFromDataprovider(Method m) {
        GenerateRegistrationTestData loginData = new GenerateRegistrationTestData();
        if (m.getName().equalsIgnoreCase("uploadVideo")) {
            GenerateMyAccountTestData randomData = new GenerateMyAccountTestData();
            return new Object[][]{
                    {randomData.videoTitle, randomData.videoDescription,
                            randomData.videoCategory, randomData.videoSubCategory,randomData.videoLocation}
            };

        } else if (m.getName().equalsIgnoreCase("registerCityJournalistTest")) {
            return new Object[][]{
                    {loginData.email,loginData.password,loginData.accountName,
                            loginData.firstName, loginData.lastName, loginData.address,
                            loginData.phone, loginData.zipCode, loginData.country,
                            loginData.state, loginData.city, loginData.paypal}
            };
        }else if (m.getName().equalsIgnoreCase("activateAccountTest")) {
            return new Object[][]{
                    {loginData.email}
            };
        }else if (m.getName().equalsIgnoreCase("registerUserTest")) {
            return new Object[][]{
                    {loginData.email, loginData.password,loginData.accountName}
            };

        }else{
            log.info("DataProvider could not find any data .");
            return null;
        }
    }
}

