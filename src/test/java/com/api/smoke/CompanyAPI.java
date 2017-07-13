package com.api.smoke;

import com.config.UrlConfig;
import com.controller.CompanyCreate;
import com.model.ManageHeaders;
import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.*;
import java.util.*;
import com.apiUtils.*;

import static io.restassured.config.HttpClientConfig.httpClientConfig;
import static org.aeonbits.owner.ConfigFactory.create;



public class CompanyAPI {

    ManageHeaders header = new ManageHeaders();
    MethodManager methodManager = new MethodManager();
    UrlConfig config = create(UrlConfig.class);
    LedgerAPI ledgerAPI = new LedgerAPI();
    GroupAPI groupAPI = new GroupAPI();
    AccountAPI accountAPI = new AccountAPI();
    CompanyCreate create = new CompanyCreate();
    StockGroupAPI stockGroupAPI = new StockGroupAPI();
    StockAccountAPI stockAccountAPI = new StockAccountAPI();


    @Test
    public void createCompany() throws Exception{
        HelperMethods.setAnsiGreen("Started :- Create Company ");
        header.set_Headers(null, null);

        /**
         * Main test and api call initiated
         */

        SmartResponse response = create.companyCreate(config.mainURL(), "AutomationCompany",   "automationcompany");

        if (response.getStatusCode() == HttpStatus.SC_CONFLICT){
            deleteSetup();
            SmartResponse response1 = create.companyCreate(config.mainURL(), "AutomationCompany", "automationcompany");

            if (response1.getStatusCode() == HttpStatus.SC_CREATED){
                HelperMethods.setAnsiGreen("Company Create Successfully");
            }
            else {
                HelperMethods.setAnsiRed("Company Create Functionality Fails");
                System.out.println(response.getStatusCode());
                System.out.println(response.getJson());
                Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_CREATED);
            }
        }

        if (response.getStatusCode()==HttpStatus.SC_CREATED){
            HelperMethods.setAnsiGreen("Company Created Successfully");
        }

        else {
            HelperMethods.setAnsiRed("Company Create Functionality Fails with response code = "+ response.getStatusCode());
            HelperMethods.setAnsiRed(response.getJson());
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
        SmartResponse response = methodManager.getAPI_with_Assert_Statuscode(null, null,config.getCompany());
        HelperMethods.assertCode("Get Company", response.getStatusCode(), HttpStatus.SC_OK, response.getJson());
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
        SmartResponse response = methodManager.putAPI_with_Assert_Statuscode(null, null,config.shareCompany(), body);
        HelperMethods.assertCode("Share Company", response.getStatusCode(), HttpStatus.SC_OK, response.getJson());
    }


    @Test(dependsOnMethods={"createCompany"})
    public void unShareCompany(){
        HelperMethods.setAnsiGreen("Started :- UnShare Company ");

        Map<String,String> body = new HashMap<>();
        body.put("user", "walkover78@gmail.com");

        /**
         * Main test and api call initiated
         */
        SmartResponse response = methodManager.putAPI_with_Assert_Statuscode(null, null,config.unshareCompany(), body);
        HelperMethods.assertCode("UnShare Company", response.getStatusCode(), HttpStatus.SC_OK, response.getJson());
    }


    public void deleteCompany(){

        /**
         * Main test and api call initiated
         */
        SmartResponse response = methodManager.deleteAPI_with_Assert_Statuscode(null, null,config.deleteCompany());
        HelperMethods.assertCode("Delete Company", response.getStatusCode(), HttpStatus.SC_OK, response.getJson());
    }


    @AfterSuite
    public void deleteSetup()throws Exception{
        HelperMethods.setAnsiGreen("Started :- Delete Setup ");
        RestAssured.reset();
        ledgerAPI.deleteAllLedger();
        accountAPI.deleteAccount();
        groupAPI.deleteGroup();
        stockAccountAPI.deleteStock();
        stockGroupAPI.delete_Stock_Group();
        deleteCompany();
    }

    @AfterMethod
    public void setup(){
        RestAssured.config = RestAssured.config().httpClient(httpClientConfig().reuseHttpClientInstance());
        RestAssured.reset();
    }
}
