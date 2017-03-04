package com.api;

import com.model.ManageHeaders;
import com.model.ManageURL;
import org.testng.Assert;
import org.testng.annotations.*;


import java.util.HashMap;
import java.util.Map;
import com.ApiUtils.*;
import static io.restassured.RestAssured.*;
import io.restassured.response.*;


public class CreateCompanyAPI {

    ManageHeaders header = new ManageHeaders();
    ManageURL baseURL = new ManageURL();


    @BeforeTest
    public void setHeader(){
        header.set_Headers();
        baseURL.setURL();
        String URL = baseURL.getURL() + "company/";
    }


    @Test
    public void createCompany(){

        baseURL.setURL();
        String URL = baseURL.getURL() + "company/";
        // System.out.println(URL);

        Map<String,String> body = new HashMap<>();
        body.put("name", "audi");
        body.put("uniqueName", "audi");

        /**
         * Main test and api call initiated
         */
        Response resp =
                given()
                        .headers("Auth-Key", header.getAuthKey())
                        .contentType("application/json")
                        .body(body).
                when().
                        post(URL);
                        System.out.println(resp.asString());
                        HelperMethods.checkStatusIs201(resp);
    }


    @Test
    public void deleteCompany(){
        baseURL.setURL();
        String URL = baseURL.getURL() + "company/";

        /**
         * Main test and api call initiated
         */

        Response resp =
                given()
                        .headers("Auth-Key", header.getAuthKey()).
                when().
                        delete(URL + "audi");
                        HelperMethods.checkStatusIs200(resp);
    }

}
