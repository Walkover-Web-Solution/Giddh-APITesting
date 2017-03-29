package com.api.Smoke;

import com.Config.UrlConfig;
import com.model.ManageHeaders;
import com.model.ManageURL;
import io.restassured.RestAssured;
import org.testng.annotations.*;
import java.util.HashMap;
import java.util.Map;
import com.ApiUtils.*;

import static io.restassured.config.HttpClientConfig.httpClientConfig;
import static org.aeonbits.owner.ConfigFactory.create;
import static org.testng.Assert.assertEquals;


public class CompanyAPI {

    ManageHeaders header = new ManageHeaders();
    MethodManager methodManager = new MethodManager();
    ManageURL baseURL = new ManageURL();
    UrlConfig config = create(UrlConfig.class);
    LedgerAPI ledgerAPI = new LedgerAPI();
    GroupAPI groupAPI = new GroupAPI();
    AccountAPI accountAPI = new AccountAPI();
    CompanyCreate create = new CompanyCreate();



    @BeforeTest
    public void setHeader(){
        header.set_Headers();
        baseURL.setURL();
    }

    @Test
    public void createCompany() throws Exception{
        HelperMethods.setAnsiGreen("Started :- Create Company ");
        SmartResponse response = create.companyCreate(config.mainURL(), "audi", "audi");

        if (response.getStatusCode() == 409){
            deleteSetup();
            SmartResponse response1 = create.companyCreate(config.mainURL(), "audi", "audi");
            if (response1.getStatusCode() != 201){
                System.out.println(response1.getStatusCode());
                System.out.println(response1.getJson());
            }
            if (response1.getStatusCode() == 201){
                System.out.println(response.getJson());
                HelperMethods.setAnsiGreen("Company Create Successfully");
            }
        }
        if (response.getStatusCode()==201){
            System.out.println(response.getJson());
            HelperMethods.setAnsiGreen("Company Create Successfully");
        }
        else {
            System.out.println(response.getStatusCode());
            System.out.println(response.getJson());
        }
    }


    @Test(dependsOnMethods={"createCompany"})
    public void getCompany(){
        HelperMethods.setAnsiGreen("Started :- Get Company ");

        /**
         * Main test and api call initiated
         */

        SmartResponse resp = methodManager.getAPI_with_Assert_Statuscode(config.getCompany());
//      System.out.println(resp.getStatusCode());
        System.out.println(resp.getJson());
    }


    @Test(dependsOnMethods={"createCompany"})
    public void shareCompany(){
        HelperMethods.setAnsiGreen("Started :- Share Company ");

        Map<String,String> body = new HashMap<>();
        body.put("user", "walkover78@gmail.com");
        body.put("role", "edit");

        /**
         * Main test and api call initiated
         */

        SmartResponse resp = methodManager.putAPI_with_Assert_Statuscode(config.shareCompany(), body);
        //System.out.println(resp.getStatusCode());
        System.out.println(resp.getJson());
    }


    @Test(dependsOnMethods={"createCompany"})
    public void unShareCompany(){

        HelperMethods.setAnsiGreen("Started :- UnShare Company ");

        Map<String,String> body = new HashMap<>();
        body.put("user", "walkover78@gmail.com");

        /**
         * Main test and api call initiated
         */

        SmartResponse resp = methodManager.putAPI_with_Assert_Statuscode(config.unshareCompany(), body);
        //System.out.println(resp.getStatusCode());
        System.out.println(resp.getJson());
    }


    public void deleteCompany(){

        /**
         * Main test and api call initiated
         */
        SmartResponse resp = methodManager.deleteAPI_with_Assert_Statuscode(config.deleteCompany());
        //System.out.println(resp.getStatusCode());
        System.out.println(resp.getJson());
        RestAssured.config = RestAssured.config().httpClient(httpClientConfig().reuseHttpClientInstance());
    }


    @AfterSuite
    public void deleteSetup()throws Exception{
        HelperMethods.setAnsiGreen("Started :- Delete Setup ");
        ledgerAPI.deleteAllLedger();
        accountAPI.deleteAccount();
        groupAPI.deleteGroup();
        deleteCompany();
    }

}
