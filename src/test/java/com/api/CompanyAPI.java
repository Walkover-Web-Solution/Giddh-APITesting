package com.api;

import com.Config.UrlConfig;
import com.model.Ledger;
import com.model.ManageHeaders;
import com.model.ManageURL;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.*;
import java.util.HashMap;
import java.util.Map;
import com.ApiUtils.*;
import static org.aeonbits.owner.ConfigFactory.create;
import static org.testng.Assert.assertEquals;


public class CompanyAPI {

    ManageHeaders header = new ManageHeaders();
    ApiManager apiManager = new ApiManager();
    ManageURL baseURL = new ManageURL();
    UrlConfig config = create(UrlConfig.class);
    LedgerAPI ledgerAPI = new LedgerAPI();
    GroupAPI groupAPI = new GroupAPI();
    AccountAPI accountAPI = new AccountAPI();

    int responseCode;


    @BeforeTest
    public void setHeader(){
        header.set_Headers();
        baseURL.setURL();
    }



    @Test
    public void createCompany() throws Exception{
        HelperMethods.setAnsiGreen("Started :- Create Company ");
        Map<String,String> body = new HashMap<>();
        body.put("name", "audi");
        body.put("uniqueName", "audi");

        /**
         * Main test and api call initiated
         */

        SmartResponse resp = apiManager.postAPI_with_Assert_Statuscode(config.mainURL(), body);
        responseCode = resp.getStatusCode();
        if (responseCode != 201){
            deleteSetup();
            System.out.println("In if situation");
        }
        else {
            System.out.println(responseCode);
            System.out.println(resp.getJson());

            String json = resp.getJson();
            JsonPath jp = new JsonPath(json);
        }
    }


    @Test(dependsOnMethods={"createCompany"})
    public void getCompany(){
        /**
         * Main test and api call initiated
         */

        SmartResponse resp = apiManager.getAPI_with_Assert_Statuscode(config.getCompany());
//      System.out.println(resp.getStatusCode());
        System.out.println(resp.getJson());
    }


    @Test(dependsOnMethods={"createCompany"})
    public void shareCompany(){

        Map<String,String> body = new HashMap<>();
        body.put("user", "walkover78@gmail.com");
        body.put("role", "edit");

        /**
         * Main test and api call initiated
         */

        SmartResponse resp = apiManager.putAPI_with_Assert_Statuscode(config.shareCompany(), body);
        //System.out.println(resp.getStatusCode());
        System.out.println(resp.getJson());
    }


    @Test(dependsOnMethods={"createCompany"})
    public void unShareCompany(){

        Map<String,String> body = new HashMap<>();
        body.put("user", "walkover78@gmail.com");

        /**
         * Main test and api call initiated
         */

        SmartResponse resp = apiManager.putAPI_with_Assert_Statuscode(config.unshareCompany(), body);
        //System.out.println(resp.getStatusCode());
        System.out.println(resp.getJson());
    }


    public void deleteCompany(){

        /**
         * Main test and api call initiated
         */
        SmartResponse resp = apiManager.deleteAPI_with_Assert_Statuscode(config.deleteCompany());
        //System.out.println(resp.getStatusCode());
        System.out.println(resp.getJson());
    }


    @AfterSuite
    public void deleteSetup()throws Exception{
        ledgerAPI.deleteAllLedger();
        accountAPI.deleteAccount();
        groupAPI.deleteGroup();
        deleteCompany();
    }

}
