package com.api.smoke;

import com.config.UrlConfig;
import com.controller.CompanyCreate;
import com.model.ManageHeaders;
import com.model.ManageURL;
import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.*;
import java.util.HashMap;
import java.util.Map;
import com.apiUtils.*;

import static io.restassured.config.HttpClientConfig.httpClientConfig;
import static org.aeonbits.owner.ConfigFactory.create;
import static org.testng.Assert.assertEquals;


public class CompanyAPI {

    ManageHeaders header = new ManageHeaders();
    MethodManager methodManager = new MethodManager();
    UrlConfig config = create(UrlConfig.class);
    LedgerAPI ledgerAPI = new LedgerAPI();
    GroupAPI groupAPI = new GroupAPI();
    AccountAPI accountAPI = new AccountAPI();
    CompanyCreate create = new CompanyCreate();
    StockGroupApi stockGroupApi = new StockGroupApi();


    @Test
    public void createCompany() throws Exception{
        HelperMethods.setAnsiGreen("Started :- Create Company ");
        header.set_Headers(null, null);

        SmartResponse response = create.companyCreate(config.mainURL(), "audi", "audi");

        if (response.getStatusCode() == HttpStatus.SC_CONFLICT){
            deleteSetup();
            SmartResponse response1 = create.companyCreate(config.mainURL(), "audi", "audi");

            if (response1.getStatusCode() != HttpStatus.SC_CREATED){
                HelperMethods.setAnsiRed("Company Create Functionality Fails");
                System.out.println(response.getStatusCode());
                System.out.println(response.getJson());
                Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_CREATED);
            }

            if (response1.getStatusCode() == HttpStatus.SC_CREATED){
                System.out.println(response.getJson());
                HelperMethods.setAnsiGreen("Company Create Successfully");
            }
        }

        if (response.getStatusCode()==HttpStatus.SC_CREATED){
            System.out.println(response.getJson());
            HelperMethods.setAnsiGreen("Company Created Successfully");
        }

        else {
            HelperMethods.setAnsiRed("Company Create Functionality Fails");
            System.out.println(response.getStatusCode());
            System.out.println(response.getJson());
            Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_CREATED);
        }
    }


    @Test(dependsOnMethods={"createCompany"})
    public void getCompany(){
        HelperMethods.setAnsiGreen("Started :- Get Company ");
        header.set_Headers(null, null);
        /**
         * Main test and api call initiated
         */

        SmartResponse resp = methodManager.getAPI_with_Assert_Statuscode(null, null,config.getCompany());
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

        SmartResponse resp = methodManager.putAPI_with_Assert_Statuscode(null, null,config.shareCompany(), body);
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

        SmartResponse resp = methodManager.putAPI_with_Assert_Statuscode(null, null,config.unshareCompany(), body);
        //System.out.println(resp.getStatusCode());
        System.out.println(resp.getJson());
    }

    public void deleteCompany(){

        /**
         * Main test and api call initiated
         */
        SmartResponse resp = methodManager.deleteAPI_with_Assert_Statuscode(null, null,config.deleteCompany());
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
        stockGroupApi.delete_Stock_Group();
        deleteCompany();
    }

}
