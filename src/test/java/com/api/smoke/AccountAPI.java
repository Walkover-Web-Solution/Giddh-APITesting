package com.api.smoke;


import com.controller.*;
import com.apiUtils.*;
import com.config.UrlConfig;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.config.HttpClientConfig.httpClientConfig;
import static org.aeonbits.owner.ConfigFactory.create;
import static org.testng.Assert.assertEquals;

public class AccountAPI {

    private MethodManager methodManager = new MethodManager();
    private UrlConfig config = create(UrlConfig.class);
    private GroupAPI groupAPI = new GroupAPI();
    private AccountCreate create = new AccountCreate();

    @Test
    public void createAccount() {
        HelperMethods.setAnsiGreen("Started :- Create Account");
        groupAPI.createGroup();

        /**
         * Main test and api call initiated
         */
        SmartResponse response= create.AccountCreate(config.createAccount(),"taccount", "taccount");
        HelperMethods.assertCode("Create Account", response.getStatusCode(), HttpStatus.SC_CREATED, response.getJson());
    }


    @Test(dependsOnMethods={"createAccount"})
    public void getAccount() {
        HelperMethods.setAnsiGreen("Started :- Get Account");

        /**
         * Main test and api call initiated
         */
        SmartResponse response = methodManager.getAPI_with_Assert_Statuscode(null, null,config.getAccount());
        HelperMethods.assertCode("Get Account", response.getStatusCode(), HttpStatus.SC_OK, response.getJson());
    }

    @Test(dependsOnMethods={"createAccount"})
    public void shareAccount() {
        HelperMethods.setAnsiGreen("Started :- Share Account");

        Map<String,String> body = new HashMap<>();
        body.put("user", "tadhall87@gmail.com");
        body.put("role", "edit");

        /**
         * Main test and api call initiated
         */
        SmartResponse response = methodManager.putAPI_with_Assert_Statuscode(null, null,config.shareAccount(), body);
        HelperMethods.assertCode("Share Account", response.getStatusCode(), HttpStatus.SC_OK, response.getJson());
    }


    @Test(dependsOnMethods={"createAccount"})
    public void unShareAccount() {
        HelperMethods.setAnsiGreen("Started :- UnShare Account");

        Map<String,String> body = new HashMap<>();
        body.put("user", "tadhall87@gmail.com");

        /**
         * Main test and api call initiated
         */
        SmartResponse response = methodManager.putAPI_with_Assert_Statuscode(null, null,config.unshareAccount(), body);
        HelperMethods.assertCode("UnShare Account", response.getStatusCode(), HttpStatus.SC_OK, response.getJson());
    }


    @Test(dependsOnMethods={"createAccount"})
    public void updateAccount() {
        HelperMethods.setAnsiGreen("Started :- Update Account");

        Map<String,String> body = new HashMap<>();
        body.put("name", "taccount1");
        body.put("uniqueName", "taccount1");


        /**
         * Main test and api call initiated
         */
        SmartResponse resp = methodManager.putAPI_with_Assert_Statuscode(null, null,config.updateAccount(), body);
        if (resp.getStatusCode() == HttpStatus.SC_OK){
            String json = resp.getJson();
            JsonPath jp = new JsonPath(json);
            assertEquals( jp.get("body.name"), "taccount1");
            HelperMethods.setAnsiGreen("Update Account Functionality Completed Successfully");
        }
        else {
            HelperMethods.setAnsiRed("Update Account Functionality fails with Response Code = " +  resp.getStatusCode());
            HelperMethods.setAnsiRed(resp.getJson());
            Assert.assertEquals(resp.getStatusCode(), HttpStatus.SC_OK);
        }
    }

    public void deleteAccount() {

        /**
         * Main test and api call initiated
         */
        SmartResponse response = methodManager.deleteAPI_with_Assert_Statuscode(null, null,config.deleteAccount());
        HelperMethods.assertCode("Delete Account", response.getStatusCode(), HttpStatus.SC_OK, response.getJson());
    }

    @AfterMethod
    public void  setup(){
        RestAssured.config = RestAssured.config().httpClient(httpClientConfig().reuseHttpClientInstance());
        RestAssured.reset();
    }
}