package com.api.regression;


import com.apiUtils.*;
import com.config.HeadersConfig;
import com.config.UrlConfig;

import com.controller.CompanyCreate;
import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.testng.annotations.*;

import java.util.HashMap;
import java.util.Map;

import static org.aeonbits.owner.ConfigFactory.create;

public class Company {

    CompanyCreate create = new CompanyCreate();
    MethodManager methodManager = new MethodManager();
    UrlConfig config = create(UrlConfig.class);
    HeadersConfig headersConfig = create(HeadersConfig.class);

    public static String companyName;
    public static String baseURL;
    public static String mainURL;
    public String apiURL;


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
        companyName = getRandomCompanyName();
    }

    //@Test
    public void createCompany(){
        prerequisites();
        HelperMethods.setAnsiRed("Company UniqueName is "  +  companyName);
        SmartResponse response = create.companyCreate( null, mainURL, "AutomationCompany", companyName);
        HelperMethods.assertCode("Create Company", response.getStatusCode(), HttpStatus.SC_CREATED, response.getJson());
    }


   // @Test(dependsOnMethods={"createCompany"})
    public void getCompany(int statusCode){
        HelperMethods.setAnsiGreen("Started :- Get Company ");

        /**
         * Main test and api call initiated
         */
        SmartResponse response = methodManager.getAPI_with_Assert_Statuscode(null, null, mainURL + companyName);
        HelperMethods.assertCode("Get Company", response.getStatusCode(), statusCode, response.getJson());
    }

   // @Test(dependsOnMethods={"createCompany"})
    public void shareCompany(){
        HelperMethods.setAnsiGreen("Started :- Share Company");
        apiURL = mainURL + companyName + "/share";

        Map<String,String> body = new HashMap<>();
        body.put("user", "walkover78@gmail.com");
        body.put("role", "edit");

        /**
         * Main test and api call initiated
         */
        SmartResponse response = methodManager.putAPI_with_Assert_Statuscode(null, null, apiURL, body);
        HelperMethods.assertCode("Share Company", response.getStatusCode(), HttpStatus.SC_OK, response.getJson());
    }

    //@Test(dependsOnMethods={"createCompany"})
    public void getSharedCompany(int statusCode){
        HelperMethods.setAnsiGreen("Started :- Get Company with Shared User");

        /**
         * Main test and api call initiated
         */
        SmartResponse response = methodManager.getAPI_with_Assert_Statuscode(headersConfig.getSharedUserAuthKey(), headersConfig.setType(), mainURL + companyName);
        HelperMethods.assertCode("Get Company with Shared User", response.getStatusCode(), statusCode, response.getJson());
    }

   // @Test(dependsOnMethods={"createCompany"})
    public void unShareCompany(){
        HelperMethods.setAnsiGreen("Started :- UnShare Company ");
        apiURL = mainURL + companyName + "/unshare";

        Map<String,String> body = new HashMap<>();
        body.put("user", "walkover78@gmail.com");

        /**
         * Main test and api call initiated
         */
        SmartResponse response = methodManager.putAPI_with_Assert_Statuscode(null, null, apiURL, body);
        HelperMethods.assertCode("UnShare Company", response.getStatusCode(), HttpStatus.SC_OK, response.getJson());
    }

    public void deleteCompany(){

        /**
         * Main test and api call initiated
         */
        SmartResponse response = methodManager.deleteAPI_with_Assert_Statuscode(null, null,mainURL + companyName);
        HelperMethods.assertCode("Delete Company", response.getStatusCode(), HttpStatus.SC_OK, response.getJson());
    }

    @Test
    public void xyz(){
        createCompany();
        getCompany(HttpStatus.SC_OK);
        shareCompany();
        getSharedCompany(HttpStatus.SC_OK);
        unShareCompany();
        getSharedCompany(HttpStatus.SC_UNAUTHORIZED);
    }

    @AfterSuite
    public void deleteSetup(){
       deleteCompany();
    }
}
