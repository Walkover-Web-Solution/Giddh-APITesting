package com.api.smoke;

import com.config.UrlConfig;
import com.controller.CompanyCreate;
import com.model.ManageHeaders;
import io.restassured.RestAssured;
import org.apache.commons.lang3.RandomStringUtils;
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
    StockGroupAPI stockGroupAPI = new StockGroupAPI();

    public static String companyName;
    public static String baseURL;
    public static String mainURL;

    public String getRandomCompanyName(){
        String chars = "abcdefghijklmnopqrstuvwxyz";
        String randomString = "";
        int length = chars.length();
        for (int i = 0; i < 8; i++) {
            randomString += chars.split("")[(int) (Math.random() * (length - 1))];
        }
        return randomString;
    }


    public void prerequisites(){
        RestAssured.baseURI= config.baseURL();
         baseURL = RestAssured.baseURI;
         mainURL = config.mainURL();
    }

    @Test
    public void createCompany() throws Exception{
        prerequisites();
        long id = Thread.currentThread().getId();
        System.out.println("Create test-class. Thread id is: " + id);
        HelperMethods.setAnsiGreen("Started :- Create Company ");
        header.set_Headers(null, null);
        /**
         * Main test and api call initiated
         */
        companyName = getRandomCompanyName();
        HelperMethods.setAnsiGreen("Company Name is = "+ companyName);

        SmartResponse response = create.companyCreate(config.mainURL(), "automationCompany", companyName);

        if (response.getStatusCode() == HttpStatus.SC_CONFLICT){
            //deleteSetup();
            SmartResponse response1 = create.companyCreate(config.mainURL(), "automationCompany", companyName);

            if (response1.getStatusCode() != HttpStatus.SC_CREATED){
                HelperMethods.setAnsiRed("Company Create Functionality Fails");
                System.out.println(response.getStatusCode());
                System.out.println(response.getJson());
                Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_CREATED);
            }

            if (response1.getStatusCode() == HttpStatus.SC_CREATED){
                HelperMethods.setAnsiGreen("Company Created Successfully");
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

//
//    @Test(dependsOnMethods={"createCompany"})
//    public void getCompany(){
//        HelperMethods.setAnsiGreen("Started :- Get Company ");
//        header.set_Headers(null, null);
//
//        /**
//         * Main test and api call initiated
//         */
//        SmartResponse response = methodManager.getAPI_with_Assert_Statuscode(null, null,config.getCompany());
//        if (response.getStatusCode() == HttpStatus.SC_OK){
//            HelperMethods.setAnsiGreen("Get Company Functionality Completed Successfully");
//        }
//        else {
//            HelperMethods.setAnsiRed("Get Company Functionality fails with Response Code = " +  response.getStatusCode());
//            HelperMethods.setAnsiRed(response.getJson());
//            Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
//        }
//    }
//
//
//    @Test(dependsOnMethods={"createCompany"})
//    public void shareCompany(){
//        HelperMethods.setAnsiGreen("Started :- Share Company ");
//
//        Map<String,String> body = new HashMap<>();
//        body.put("user", "walkover78@gmail.com");
//        body.put("role", "edit");
//
//        /**
//         * Main test and api call initiated
//         */
//        SmartResponse response = methodManager.putAPI_with_Assert_Statuscode(null, null,config.shareCompany(), body);
//        if (response.getStatusCode() == HttpStatus.SC_OK){
//            HelperMethods.setAnsiGreen("Share Company Functionality Completed Successfully");
//        }
//        else {
//            HelperMethods.setAnsiRed("Share Company Functionality fails with Response Code = " +  response.getStatusCode());
//            HelperMethods.setAnsiRed(response.getJson());
//            Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
//        }
//    }
//
//
//    @Test(dependsOnMethods={"createCompany"})
//    public void unShareCompany(){
//        HelperMethods.setAnsiGreen("Started :- UnShare Company ");
//
//        Map<String,String> body = new HashMap<>();
//        body.put("user", "walkover78@gmail.com");
//
//        /**
//         * Main test and api call initiated
//         */
//        SmartResponse response = methodManager.putAPI_with_Assert_Statuscode(null, null,config.unshareCompany(), body);
//        if (response.getStatusCode() == HttpStatus.SC_OK){
//            HelperMethods.setAnsiGreen("UnShare Company Functionality Completed Successfully");
//        }
//        else {
//            HelperMethods.setAnsiRed("UnShare Company Functionality fails with Response Code = " +  response.getStatusCode());
//            HelperMethods.setAnsiRed(response.getJson());
//            Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
//        }
//    }
//

    @Test
    public void deleteCompany(){
        long id = Thread.currentThread().getId();
        System.out.println("Delete  test-class. Thread id is: " + id);
//        /**
//         * Main test and api call initiated
//         */
//        SmartResponse resp = methodManager.deleteAPI_with_Assert_Statuscode(null, null,mainURL + companyName);
//        //System.out.println(resp.getStatusCode());
//        System.out.println(resp.getJson());
//        RestAssured.config = RestAssured.config().httpClient(httpClientConfig().reuseHttpClientInstance());
    }


//    @AfterSuite
//    public void deleteSetup()throws Exception{
//        HelperMethods.setAnsiGreen("Started :- Delete Setup ");
////        RestAssured.reset();
////        ledgerAPI.deleteAllLedger();
////        accountAPI.deleteAccount();
////        groupAPI.deleteGroup();
////        stockGroupAPI.delete_Stock_Group();
//        deleteCompany();
//    }

    @AfterMethod
    public void  setup(){
        RestAssured.config = RestAssured.config().httpClient(httpClientConfig().reuseHttpClientInstance());
//        RestAssured.reset();
    }

}
