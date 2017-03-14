package com.api;

import com.model.ManageHeaders;
import com.model.ManageURL;
import org.testng.annotations.*;

import java.util.HashMap;
import java.util.Map;
import com.ApiUtils.*;
import static io.restassured.RestAssured.*;
import static org.aeonbits.owner.ConfigFactory.create;

import io.restassured.response.*;


public class CompanyAPI {

    ManageHeaders header = new ManageHeaders();
    ManageURL baseURL = new ManageURL();
    UrlConfig config = create(UrlConfig.class);

    @BeforeTest
    public void setHeader(){
        header.set_Headers();
        baseURL.setURL();
    }


    @Test
    public void createCompany(){

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
                        post(config.mainURL());
                        HelperMethods.printResponse(resp);
                        HelperMethods.checkStatusIs201(resp);

    }


    @Test(dependsOnMethods={"createCompany"})
    public void getCompany(){
        /**
          * Main test and api call initiated
         */

        Response resp =
                given()
                        .headers("Auth-Key", header.getAuthKey()).
                when().
                        get(config.getCompany());
                        HelperMethods.checkStatusIs200(resp);
    }


    @Test(dependsOnMethods={"createCompany"})
    public void shareCompany(){

        Map<String,String> body = new HashMap<>();
        body.put("user", "walkover78@gmail.com");
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
                        put(config.shareCompany());
                        HelperMethods.printResponse(resp);
                        HelperMethods.checkStatusIs200(resp);

    }


    @Test(dependsOnMethods={"createCompany"})
    public void unShareCompany(){

        Map<String,String> body = new HashMap<>();
        body.put("user", "walkover78@gmail.com");

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
                        put(config.unshareCompany());
                        HelperMethods.printResponse(resp);
                        HelperMethods.checkStatusIs200(resp);

    }


    @AfterSuite
    public void deleteCompany(){
        /**
         * Main test and api call initiated
         */

        Response resp =
                given()
                        .headers("Auth-Key", header.getAuthKey()).
                when().
                        delete(config.deleteCompany());
                       // System.out.println(resp);
                        HelperMethods.checkStatusIs200(resp);

    }

}