package com.api.smoke;

import com.config.UrlConfig;
import com.controller.CompanyCreate;
import com.model.ManageHeaders;
import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.SystemDefaultHttpClient;
import org.testng.Assert;
import org.testng.annotations.*;
import java.util.*;
import com.apiUtils.*;

import static io.restassured.config.HttpClientConfig.httpClientConfig;
import static org.aeonbits.owner.ConfigFactory.create;



public class CompanyAPI {

    private ManageHeaders header = new ManageHeaders();
    private MethodManager methodManager = new MethodManager();
    private UrlConfig config = create(UrlConfig.class);
    private LedgerAPI ledgerAPI = new LedgerAPI();
    private GroupAPI groupAPI = new GroupAPI();
    private AccountAPI accountAPI = new AccountAPI();
    private CompanyCreate create = new CompanyCreate();
    private StockGroupAPI stockGroupAPI = new StockGroupAPI();
    private StockAccountAPI stockAccountAPI = new StockAccountAPI();


    @BeforeSuite
    public void initialSetup(){
        System.setProperty("http.maxConnections","200   ");
        RestAssured.config = RestAssuredConfig.newConfig().httpClient(httpClientConfig().reuseHttpClientInstance().httpClientFactory(
                new HttpClientConfig.HttpClientFactory() {
                    @Override
                    public HttpClient createHttpClient() {
                        return new SystemDefaultHttpClient();
                    }
                }
        ));

    }


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
                HelperMethods.setAnsiGreen("Company Create Successfully in Second Iteration");
            }
            else {
                HelperMethods.setAnsiRed("Company Create Functionality Fails in Second Iteration");
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
