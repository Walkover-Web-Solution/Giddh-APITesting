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
                        .headers("Content-Type", header.getType())
                        //.contentType("application/json")
                        .body(body).
                when().
                        post(URL);
                        HelperMethods.printResponse(resp);
                        HelperMethods.checkStatusIs201(resp);
    }

    @Test(dependsOnMethods={"createCompany"})
    public void getCompany(){
        String URL = baseURL.getURL() + "company/";
        /**
         * Main test and api call initiated
         */

        Response resp =
                given()
                        .headers("Auth-Key", header.getAuthKey()).
                when().
                        get(URL + "audi");
                        HelperMethods.checkStatusIs200(resp);
    }

    @Test(dependsOnMethods={"createCompany"})
    public void shareCompany(){

        String URL = baseURL.getURL() + "company/audi/share";

        Map<String,String> body = new HashMap<>();
        body.put("user", "chirag@walkover.in");
        body.put("role", "edit");

        /**
         * Main test and api call initiated
         */
        Response resp =
                given()
                        .headers("Auth-Key", header.getAuthKey())
                        .headers("Content-Type", header.getType())
                        //.contentType("application/json")
                        .body(body).
                when().
                        put(URL);
                        HelperMethods.printResponse(resp);
                        HelperMethods.checkStatusIs200(resp);

    }

    @Test(dependsOnMethods={"createCompany"})
    public void unshareCompany(){

        String URL = baseURL.getURL() + "company/audi/unshare";

        Map<String,String> body = new HashMap<>();
        body.put("user", "chirag@walkover.in");

        /**
         * Main test and api call initiated
         */
        Response resp =
                given()
                        .headers("Auth-Key", header.getAuthKey())
                        .headers("Content-Type", header.getType())
                        //.contentType("application/json")
                        .body(body).
                        when().
                        put(URL);
        HelperMethods.printResponse(resp);
        HelperMethods.checkStatusIs200(resp);

    }





    @Test(dependsOnMethods={"getCompany"})
    public void deleteCompany(){

        String URL = baseURL.getURL() + "company/audi";

        /**
         * Main test and api call initiated
         */

        Response resp =
                given()
                        .headers("Auth-Key", header.getAuthKey()).
                when().
                        delete(URL);
                        HelperMethods.checkStatusIs200(resp);
    }

}
