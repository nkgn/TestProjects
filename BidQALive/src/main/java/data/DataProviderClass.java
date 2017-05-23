package data;

import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class DataProviderClass {


    /*********Using single data provider method with method parameter ****/

    @DataProvider(name="SearchProvider")
    public Object[][] getDataFromDataprovider(Method m) {

        String projectTitle = GenerateData.projectTitle;
        System.out.println(projectTitle);
        if (m.getName().equalsIgnoreCase("bidForProjectTest")) {

            return new Object[][]{
                    {"a1","a1",projectTitle,"1","1"},
                    {"a2","a2",projectTitle,"2","2"},
                    {"a3","a3",projectTitle,"3","3"}

            };

        }else if (m.getName().equalsIgnoreCase("selectWinner")) {

            return new Object[][]{
                    {"a1"},
                    {"a2"},
                    {"a3"}
            };
        }else if (m.getName().equalsIgnoreCase("releasePayment")) {

            return new Object[][]{
                    {"a1",3},
                    {"a2",3},
                    {"a3",3}
            };
        }else if (m.getName().equalsIgnoreCase("makeEscrow")) {

            return new Object[][]{
                    {"a1","1"},
                    {"a2","2"},
                    {"a3","3"}
            };
        }else if (m.getName().equalsIgnoreCase("testCreateProject")) {

            return new Object[][]{
                    //{"n_kgn","kgn",projectTitle,"Market for  QA and Project Owners "}
                    {"UserNamePO","projects",projectTitle,"Market for  QA and Project Owners "}

            }; //
        }else if (m.getName().equalsIgnoreCase("newTeamTest")) {

            return new Object[][]{
                    {"a1",projectTitle,"TeamQA"},
                    {"a2",projectTitle,"TeamQA"},
                    {"a3",projectTitle,"TeamQA"}

            }; //newTeamTest
        }else if (m.getName().equalsIgnoreCase("projectDeliveredTest")) {

            return new Object[][]{
                    {"a1","a1",projectTitle,"TeamQA"},
                    {"a2","a2",projectTitle,"TeamQA"},
                    {"a3","a3",projectTitle,"TeamQA"}

            }; //workOnProjectTest
        }else if (m.getName().equalsIgnoreCase("clearPayments")) {

            return new Object[][]{
                    {"TeamQA",projectTitle},


            }; //clearPayments

        }else {
            //log.info("DataProvider could not find any data .");
            return null;
        }
    }

}

