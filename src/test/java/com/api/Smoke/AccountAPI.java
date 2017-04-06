package com.api.Smoke;


import com.api.Controller.AccountCreate;
import com.ApiUtils.MethodManager;
import com.ApiUtils.HelperMethods;
import com.ApiUtils.SmartResponse;
import com.Config.UrlConfig;
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

    ManageHeaders header = new ManageHeaders();
    MethodManager methodManager = new MethodManager();
    ManageURL baseURL = new ManageURL();
    UrlConfig config = create(UrlConfig.class);
    GroupAPI groupAPI = new GroupAPI();
    AccountCreate create = new AccountCreate();

    @BeforeTest
    public void setHeader(){
        header.set_Headers();
        baseURL.setURL();
    }


    @Test
    public void createAccount() {
        HelperMethods.setAnsiGreen("Started :- Create Account");

        groupAPI.createGroup();

        /**
         * Main test and api call initiated
         */
        SmartResponse response= create.AccountCreate(config.createAccount(),"taccount", "taccount");
        if (response.getStatusCode() != HttpStatus.SC_CREATED){
            System.out.println(response.getStatusCode());
            System.out.println(response.getJson());
        }

        else {
            Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_CREATED);
            HelperMethods.setAnsiGreen("Account Created Successfully");
        }
    }


    @Test(dependsOnMethods={"createAccount"})
    public void getAccount() {
        HelperMethods.setAnsiGreen("Started :- Get Account");
        /**
         * Main test and api call initiated
         */

        SmartResponse resp = methodManager.getAPI_with_Assert_Statuscode(config.getAccount());
     //   System.out.println(resp.getStatusCode());
        System.out.println(resp.getJson());
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

        SmartResponse resp = methodManager.putAPI_with_Assert_Statuscode(config.shareAccount(), body);
//      System.out.println(resp.getStatusCode());
        System.out.println(resp.getJson());
    }


    @Test(dependsOnMethods={"createAccount"})
    public void unshareAccount() {

        HelperMethods.setAnsiGreen("Started :- UnShare Account");

        Map<String,String> body = new HashMap<>();
        body.put("user", "tadhall87@gmail.com");

        /**
         * Main test and api call initiated
         */

        SmartResponse resp = methodManager.putAPI_with_Assert_Statuscode(config.unshareAccount(), body);
//      System.out.println(resp.getStatusCode());
        System.out.println(resp.getJson());

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


        SmartResponse resp = methodManager.putAPI_with_Assert_Statuscode(config.updateAccount(), body);
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

        SmartResponse resp = methodManager.deleteAPI_with_Assert_Statuscode(config.deleteAccount());
//      System.out.println(resp.getStatusCode());
        System.out.println(resp.getJson());

    }

}