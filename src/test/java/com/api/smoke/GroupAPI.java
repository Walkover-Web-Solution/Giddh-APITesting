package com.api.smoke;

import com.api.regression.Company;
import com.controller.GroupCreate;
import com.apiUtils.*;
import com.config.UrlConfig;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.*;
import java.util.*;
import static io.restassured.config.HttpClientConfig.httpClientConfig;
import static org.aeonbits.owner.ConfigFactory.create;
import static org.testng.Assert.assertEquals;

public class GroupAPI {

    MethodManager methodManager = new MethodManager();
    UrlConfig config = create(UrlConfig.class);
    GroupCreate create = new GroupCreate();


    @Test
    public void createGroup() {
        HelperMethods.setAnsiGreen("Started :- Create Group ");
        /**
         * Main test and api call initiated
         */
        SmartResponse response= create.GroupCreate(null, config.createGroup(),"tgroup", "tgroup", "capital");
        HelperMethods.assertCode("Create Group", response.getStatusCode(), HttpStatus.SC_CREATED, response.getJson());
    }

    @Test(dependsOnMethods={"createGroup"})
    public void moveGroup() {
        HelperMethods.setAnsiGreen("Started :- Move Group ");

        Map<String,String> body = new HashMap<>();
        body.put("parentGroupUniqueName", "sundrydebtors");

        /**
         * Main test and api call initiated
         */

        SmartResponse response = methodManager.putAPI_with_Assert_Statuscode(null, config.moveGroup(), body);
        HelperMethods.assertCode("Move Group", response.getStatusCode(), HttpStatus.SC_OK, response.getJson());
    }


    @Test(dependsOnMethods={"createGroup"})
    public void getGroup() {
        HelperMethods.setAnsiGreen("Started :- Get Group ");

        /**
         * Main test and api call initiated
         */
        SmartResponse response = methodManager.getAPI_with_Assert_Statuscode(null, config.getGroup());
        HelperMethods.assertCode("Get Group", response.getStatusCode(), HttpStatus.SC_OK, response.getJson());
    }

    @Test(dependsOnMethods={"createGroup"})
    public void shareGroup() {
        HelperMethods.setAnsiGreen("Started :- Share Group ");

        Map<String,String> body = new HashMap<>();
        body.put("user", "tadhall87@gmail.com");
        body.put("role", "edit");

        /**
         * Main test and api call initiated
         */

        SmartResponse response = methodManager.putAPI_with_Assert_Statuscode(null, config.shareGroup(), body);
        HelperMethods.assertCode("Share Group", response.getStatusCode(), HttpStatus.SC_OK, response.getJson());
    }


    @Test(dependsOnMethods={"createGroup"})
    public void unShareGroup() {
        HelperMethods.setAnsiGreen("Started :- UnShare Group ");

        Map<String,String> body = new HashMap<>();
        body.put("user", "tadhall87@gmail.com");

        /**
         * Main test and api call initiated
         */

        SmartResponse response = methodManager.putAPI_with_Assert_Statuscode(null, config.unshareGroup(), body);
        HelperMethods.assertCode("UnShare Group", response.getStatusCode(), HttpStatus.SC_OK, response.getJson());
    }


    @Test(dependsOnMethods={"createGroup"})
    public void updateGroup() {
        HelperMethods.setAnsiGreen("Started :- Update Group ");

        Map<String,String> body = new HashMap<>();
        body.put("name", "tgroup1");
        body.put("uniqueName", "tgroup1");
        body.put("parentGroupUniqueName", "capital");

        /**
         * Main test and api call initiated
         */

        SmartResponse resp = methodManager.putAPI_with_Assert_Statuscode(null, config.updateGroup(), body);
        if (resp.getStatusCode() == HttpStatus.SC_OK){
            String json = resp.getJson();
            JsonPath jp = new JsonPath(json);
            assertEquals(jp.get("body.name"),"tgroup1" );
            HelperMethods.setAnsiGreen("Update Group Functionality Completed Successfully ");
        }
        else {
            HelperMethods.setAnsiRed("Update Group Functionality fails with Response Code = " +  resp.getStatusCode());
            HelperMethods.setAnsiRed(resp.getJson());
            Assert.assertEquals(resp.getStatusCode(), HttpStatus.SC_OK);
        }
    }


    public void deleteGroup() {
        /**
         * Main test and api call initiated
         */
        SmartResponse response = methodManager.deleteAPI_with_Assert_Statuscode(null, config.deleteGroup());
        HelperMethods.assertCode("Delete Group", response.getStatusCode(), HttpStatus.SC_OK, response.getJson());
    }


    @AfterMethod
    public void  setup(){
        RestAssured.config = RestAssured.config().httpClient(httpClientConfig().reuseHttpClientInstance());
        RestAssured.reset();
    }
}
