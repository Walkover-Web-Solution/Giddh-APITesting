package com.api.smoke;


import com.controller.AccountCreate;
import com.apiUtils.MethodManager;
import com.apiUtils.HelperMethods;
import com.apiUtils.SmartResponse;
import com.config.UrlConfig;
import com.model.ManageHeaders;
import com.model.ManageURL;
import io.restassured.path.json.JsonPath;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.HashMap;
import java.util.Map;

import static org.aeonbits.owner.ConfigFactory.create;
import static org.testng.Assert.assertEquals;

public class AccountAPI {

    MethodManager methodManager = new MethodManager();
    UrlConfig config = create(UrlConfig.class);
    GroupAPI groupAPI = new GroupAPI();
    AccountCreate create = new AccountCreate();



    @Test
    public void createAccount() {
        HelperMethods.setAnsiGreen("Started :- Create Account");
        groupAPI.createGroup();

        /**
         * Main test and api call initiated
         */
        SmartResponse resp= create.AccountCreate(config.createAccount(),"taccount", "taccount");
        if (resp.getStatusCode() == HttpStatus.SC_CREATED){
            HelperMethods.setAnsiGreen("Create Account Functionality Completed Successfully ");
        }
        else {
            HelperMethods.setAnsiRed("Create Account Functionality fails with Response Code = " +  resp.getStatusCode());
            HelperMethods.setAnsiRed(resp.getJson());
            Assert.assertEquals(resp.getStatusCode(), HttpStatus.SC_CREATED);
        }
    }


    @Test(dependsOnMethods={"createAccount"})
    public void getAccount() {
        HelperMethods.setAnsiGreen("Started :- Get Account");

        /**
         * Main test and api call initiated
         */
        SmartResponse resp = methodManager.getAPI_with_Assert_Statuscode(null, null,config.getAccount());
        if (resp.getStatusCode() == HttpStatus.SC_OK){
            HelperMethods.setAnsiGreen("Get Account Functionality Completed Successfully");
        }
        else {
            HelperMethods.setAnsiRed("Get Account Functionality fails with Response Code = " +  resp.getStatusCode());
            HelperMethods.setAnsiRed(resp.getJson());
            Assert.assertEquals(resp.getStatusCode(), HttpStatus.SC_OK);
        }
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
        SmartResponse resp = methodManager.putAPI_with_Assert_Statuscode(null, null,config.shareAccount(), body);
        if (resp.getStatusCode() == HttpStatus.SC_OK){
            HelperMethods.setAnsiGreen("Share Account Functionality Completed Successfully");
        }
        else {
            HelperMethods.setAnsiRed("Share Account Functionality fails with Response Code = " +  resp.getStatusCode());
            HelperMethods.setAnsiRed(resp.getJson());
            Assert.assertEquals(resp.getStatusCode(), HttpStatus.SC_OK);
        }
    }


    @Test(dependsOnMethods={"createAccount"}, timeOut = 5000)
    public void unShareAccount() {
        HelperMethods.setAnsiGreen("Started :- UnShare Account");

        Map<String,String> body = new HashMap<>();
        body.put("user", "tadhall87@gmail.com");

        /**
         * Main test and api call initiated
         */
        SmartResponse resp = methodManager.putAPI_with_Assert_Statuscode(null, null,config.unshareAccount(), body);
        if (resp.getStatusCode() == HttpStatus.SC_OK){
            HelperMethods.setAnsiGreen("UnShare Account Functionality Completed Successfully");
        }
        else {
            HelperMethods.setAnsiRed("UnShare Account Functionality fails with Response Code = " +  resp.getStatusCode());
            HelperMethods.setAnsiRed(resp.getJson());
            Assert.assertEquals(resp.getStatusCode(), HttpStatus.SC_OK);
        }
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
        SmartResponse resp = methodManager.deleteAPI_with_Assert_Statuscode(null, null,config.deleteAccount());
        System.out.println(resp.getJson());
    }
}