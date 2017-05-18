package com.api.regression;


import com.apiUtils.*;
import com.config.UrlConfig;

import com.controller.CompanyCreate;
import com.model.*;
import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.testng.annotations.*;

import static org.aeonbits.owner.ConfigFactory.create;

public class Company {
    ManageHeaders header = new ManageHeaders();
    CompanyCreate create = new CompanyCreate();
    MethodManager methodManager = new MethodManager();
    UrlConfig config = create(UrlConfig.class);

    public static String companyName;
    public static String baseURL;
    public static String mainURL;
    public static String apiURL;

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

    @Test
    public void createCompany(){
        prerequisites();
        HelperMethods.setAnsiRed("Company UniqueName is "  +  companyName);
        SmartResponse response = create.companyCreate( null, mainURL, "AutomationCompany", companyName);
        HelperMethods.assertCode("Create Company ", response.getStatusCode(), HttpStatus.SC_CREATED, response.getJson());

    }


    public void deleteCompany(){
        /**
         * Main test and api call initiated
         */
        SmartResponse response = methodManager.deleteAPI_with_Assert_Statuscode(null, null,mainURL + companyName);
        HelperMethods.assertCode("Delete Company", response.getStatusCode(), HttpStatus.SC_OK, response.getJson());
    }

    @AfterSuite
    public void deleteSetup(){
       deleteCompany();
    }
}
