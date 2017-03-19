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
    GroupAPI groupAPI = new GroupAPI();

    @BeforeTest
    public void setHeader(){
        header.set_Headers();
        baseURL.setURL();
    }


    @Test
    public void createAccount() {
        HelperMethods.setAnsiGreen("Started :- Create Account ");

        Map<String,String> body = new HashMap<>();
        body.put("name", "taccount");
        body.put("uniqueName", "taccount");

        /**
         * Main test and api call initiated
         */

        SmartResponse resp = apiManager.postAPI_with_Assert_Statuscode(config.createAccount(), body);
//      System.out.println(resp.getStatusCode());
        System.out.println(resp.getJson());

    }


    @Test(dependsOnMethods={"createAccount"})
    public void getAccount() {

        HelperMethods.setAnsiGreen("Started :- Get Account ");


        /**
         * Main test and api call initiated
         */

        SmartResponse resp = apiManager.getAPI_with_Assert_Statuscode(config.getAccount());
//      System.out.println(resp.getStatusCode());
        System.out.println(resp.getJson());
    }

    @Test(dependsOnMethods={"createAccount"})
    public void shareAccount() {

        HelperMethods.setAnsiGreen("Started :- Share Account ");

        Map<String,String> body = new HashMap<>();
        body.put("user", "tadhall87@gmail.com");
        body.put("role", "edit");

        /**
         * Main test and api call initiated
         */

        SmartResponse resp = apiManager.putAPI_with_Assert_Statuscode(config.shareAccount(), body);
//      System.out.println(resp.getStatusCode());
        System.out.println(resp.getJson());
    }


    @Test(dependsOnMethods={"createAccount"})
    public void unshareAccount() {

        HelperMethods.setAnsiGreen("Started :- UnShare Account ");

        Map<String,String> body = new HashMap<>();
        body.put("user", "tadhall87@gmail.com");

        /**
         * Main test and api call initiated
         */

        SmartResponse resp = apiManager.putAPI_with_Assert_Statuscode(config.unshareAccount(), body);
//      System.out.println(resp.getStatusCode());
        System.out.println(resp.getJson());

    }


    @Test(dependsOnMethods={"createAccount"})
    public void updateAccount() {

        HelperMethods.setAnsiGreen("Started :- Update Account ");

        Map<String,String> body = new HashMap<>();
        body.put("name", "taccount1");
        body.put("uniqueName", "taccount1");


        /**
         * Main test and api call initiated
         */


        SmartResponse resp = apiManager.putAPI_with_Assert_Statuscode(config.updateAccount(), body);
//      System.out.println(resp.getStatusCode());
        System.out.println(resp.getJson());

        String json = resp.getJson();
        JsonPath jp = new JsonPath(json);
        assertEquals( jp.get("body.name"), "taccount1");
    }


    public void deleteAccount() {

        /**
         * Main test and api call initiated
         */

        SmartResponse resp = apiManager.deleteAPI_with_Assert_Statuscode(config.deleteAccount());
//      System.out.println(resp.getStatusCode());
        System.out.println(resp.getJson());

    }

}