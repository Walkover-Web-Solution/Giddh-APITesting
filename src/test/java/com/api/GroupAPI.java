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

import static io.restassured.RestAssured.given;
import static io.restassured.config.HttpClientConfig.httpClientConfig;
import static org.aeonbits.owner.ConfigFactory.create;
import static org.testng.Assert.assertEquals;

public class GroupAPI {

    ManageHeaders header = new ManageHeaders();
    ApiManager apiManager = new ApiManager();
    ManageURL baseURL = new ManageURL();
    UrlConfig config = create(UrlConfig.class);

    @BeforeClass
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

        SmartResponse resp = apiManager.postAPI_with_Assert_Statuscode(config.createGroup(), body);
//      System.out.println(resp.getStatusCode());
        System.out.println(resp.getJson());
    }

    @Test
    public void moveGroup() {

        Map<String,String> body = new HashMap<>();
        body.put("parentGroupUniqueName", "sundrydebtors");

        /**
         * Main test and api call initiated
         */


        SmartResponse resp = apiManager.putAPI_with_Assert_Statuscode(config.moveGroup(), body);
//      System.out.println(resp.getStatusCode());
        System.out.println(resp.getJson());
    }


    @Test(dependsOnMethods={"createGroup"})
    public void getGroup() {

        /**
         * Main test and api call initiated
         */
        SmartResponse resp = apiManager.getAPI_with_Assert_Statuscode(config.getGroup());
//      System.out.println(resp.getStatusCode());
        System.out.println(resp.getJson() + "This is the Response eof Get Company");

    }

    @Test(dependsOnMethods={"createGroup"})
    public void shareGroup() {

        Map<String,String> body = new HashMap<>();
        body.put("user", "tadhall87@gmail.com");
        body.put("role", "edit");

        /**
         * Main test and api call initiated
         */

        SmartResponse resp = apiManager.putAPI_with_Assert_Statuscode(config.shareGroup(), body);
//      System.out.println(resp.getStatusCode());
        System.out.println(resp.getJson());
    }


    @Test(dependsOnMethods={"createGroup"})
    public void unshareGroup() {

        Map<String,String> body = new HashMap<>();
        body.put("user", "tadhall87@gmail.com");

        /**
         * Main test and api call initiated
         */




        SmartResponse resp = apiManager.putAPI_with_Assert_Statuscode(config.unshareGroup(), body);
//      System.out.println(resp.getStatusCode());
        System.out.println(resp.getJson());
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


        SmartResponse resp = apiManager.putAPI_with_Assert_Statuscode(config.updateGroup(), body);
//      System.out.println(resp.getStatusCode());
        String json = resp.getJson();
        JsonPath jp = new JsonPath(json);
        assertEquals("tgroup1", jp.get("body.name"));
    }

    @Test(dependsOnMethods={"createGroup"})
    public void deleteGroup() {

        /**
         * Main test and api call initiated
         */

        SmartResponse resp = apiManager.deleteAPI_with_Assert_Statuscode(config.deleteGroup());
//      System.out.println(resp.getStatusCode());
        System.out.println(resp.getJson());

    }

}
