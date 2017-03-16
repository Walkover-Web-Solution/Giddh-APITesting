package com.api;


import com.ApiUtils.ApiManager;
import com.ApiUtils.HelperMethods;
import com.ApiUtils.SmartResponse;
import com.Config.UrlConfig;
import com.model.ManageHeaders;
import com.model.ManageURL;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.*;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static io.restassured.config.HttpClientConfig.httpClientConfig;
import static org.aeonbits.owner.ConfigFactory.create;
import static org.testng.Assert.assertEquals;

public class AccountAPI {

    ManageHeaders header = new ManageHeaders();
    ApiManager apiManager = new ApiManager();
    ManageURL baseURL = new ManageURL();
    UrlConfig config = create(UrlConfig.class);

    @BeforeTest
    public void setHeader(){
        header.set_Headers();
        baseURL.setURL();
    }

    public void createGroup() {

        Map<String,String> body = new HashMap<>();
        body.put("name", "tgroup");
        body.put("uniqueName", "tgroup");
        body.put("parentGroupUniqueName", "capital");

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
                        post(config.createGroup());
                        HelperMethods.checkStatusIs201(resp);
            }


    @Test
    public void createAccount() {

        createGroup();
        RestAssured.config = RestAssured.config().httpClient(httpClientConfig().reuseHttpClientInstance());

        Map<String,String> body = new HashMap<>();
        body.put("name", "taccount");
        body.put("uniqueName", "taccount");

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
                        post(config.createAccount());
                        HelperMethods.printResponse(resp);
                        HelperMethods.checkStatusIs201(resp);
    }


    @Test(dependsOnMethods={"createAccount"})
    public void getAccount() {

        /**
         * Main test and api call initiated
         */

        SmartResponse resp = apiManager.getAPI_with_Assert_Statuscode(config.getAccount());
//      System.out.println(resp.getStatusCode());
        System.out.println(resp.getJson() + "This is the Response eof Get Company");
    }

    @Test(dependsOnMethods={"createAccount"})
    public void shareAccount() {

        Map<String,String> body = new HashMap<>();
        body.put("user", "tadhall87@gmail.com");
        body.put("role", "edit");

        /**
         * Main test and api call initiated
         */

        Response resp =

                given()
                        .headers("Auth-Key", header.getAuthKey()).
                        headers("Content-Type", header.getType()).
                        //.contentType("application/json")
                                body(body).
                when().
                        put(config.shareAccount());
                        HelperMethods.printResponse(resp);
                        HelperMethods.checkStatusIs200(resp);
    }


    @Test(dependsOnMethods={"createAccount"})
    public void unshareAccount() {

        Map<String,String> body = new HashMap<>();
        body.put("user", "tadhall87@gmail.com");

        /**
         * Main test and api call initiated
         */

        Response resp =

                given()
                        .headers("Auth-Key", header.getAuthKey())
                        .headers("Content-Type", header.getType()).
                        //.contentType("application/json")
                        body(body).
                when().
                        put(config.unshareAccount());
                        HelperMethods.printResponse(resp);
                        HelperMethods.checkStatusIs200(resp);
    }


    @Test(dependsOnMethods={"createAccount"})
    public void updateAccount() {

        Map<String,String> body = new HashMap<>();
        body.put("name", "taccount1");
        body.put("uniqueName", "taccount1");


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
                        put(config.updateAccount());
                        HelperMethods.printResponse(resp);
                        HelperMethods.checkStatusIs200(resp);
                        String json = resp.asString();
                        JsonPath jp = new JsonPath(json);
                        assertEquals("taccount1", jp.get("body.name"));
    }

    @Test(dependsOnMethods={"createAccount"})
    public void deleteAccount() {

        /**
         * Main test and api call initiated
         */

        Response resp =

                given()
                        .headers("Auth-Key", header.getAuthKey())
                        .headers("Content-Type", header.getType()).
                        //.contentType("application/json")
                when().
                        delete(config.deleteAccount());
                        HelperMethods.printResponse(resp);
                        HelperMethods.checkStatusIs200(resp);
    }


    @AfterMethod
    public void closeConnection(){
        RestAssured.config = RestAssured.config().httpClient(httpClientConfig().reuseHttpClientInstance());
    }

}