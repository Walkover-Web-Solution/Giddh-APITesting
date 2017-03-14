package com.api;

import com.ApiUtils.HelperMethods;
import com.ApiUtils.UrlConfig;
import com.model.ManageHeaders;
import com.model.ManageURL;
import io.restassured.RestAssured;
import io.restassured.config.ConnectionConfig;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.*;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.config.HttpClientConfig.httpClientConfig;
import static org.aeonbits.owner.ConfigFactory.create;
import static org.testng.Assert.assertEquals;

public class GroupAPI {

    ManageHeaders header = new ManageHeaders();
    ManageURL baseURL = new ManageURL();
    UrlConfig config = create(UrlConfig.class);

    @BeforeTest
    public void setHeader(){
        header.set_Headers();
        baseURL.setURL();
    }


    @Test
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
                        HelperMethods.printResponse(resp);
                        HelperMethods.checkStatusIs201(resp);
    }


    @Test(dependsOnMethods={"createGroup"})
    public void getGroup() {

        /**
         * Main test and api call initiated
         */

        Response resp =

                given()
                        .headers("Auth-Key", header.getAuthKey())
                        .headers("Content-Type", header.getType()).
                        //.contentType("application/json")
                when().
                        get(config.getGroup());
                        HelperMethods.printResponse(resp);
                        HelperMethods.checkStatusIs200(resp);
    }

    @Test(dependsOnMethods={"createGroup"})
    public void shareGroup() {

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
                        put(config.shareGroup());
                        HelperMethods.printResponse(resp);
                        HelperMethods.checkStatusIs200(resp);
    }


    @Test(dependsOnMethods={"createGroup"})
    public void unshareGroup() {

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
                        put(config.unshareGroup());
                        HelperMethods.printResponse(resp);
                        HelperMethods.checkStatusIs200(resp);
    }


    @Test(dependsOnMethods={"createGroup"})
    public void updateGroup() {

        Map<String,String> body = new HashMap<>();
        body.put("name", "tgroup1");
        body.put("uniqueName", "tgroup1");
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
                        put(config.updateGroup());
                        HelperMethods.printResponse(resp);
                        HelperMethods.checkStatusIs200(resp);
                        String json = resp.asString();
                        JsonPath jp = new JsonPath(json);
                        assertEquals("tgroup1", jp.get("body.name"));
    }

    @Test(dependsOnMethods={"createGroup"})
    public void deleteGroup() {

        /**
         * Main test and api call initiated
         */

        Response resp =

                given()
                        .headers("Auth-Key", header.getAuthKey())
                        .headers("Content-Type", header.getType()).
                        //.contentType("application/json")
                when().
                        delete(config.deleteGroup());
                        HelperMethods.printResponse(resp);
                        HelperMethods.checkStatusIs200(resp);
    }


    @AfterMethod
    public void closeConnection(){
        RestAssured.config = RestAssured.config().httpClient(httpClientConfig().reuseHttpClientInstance());
    }


 }
