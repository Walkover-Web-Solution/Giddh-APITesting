package com.api.Smoke;

import com.Controller.GroupCreate;
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

import static io.restassured.RestAssured.given;
import static org.aeonbits.owner.ConfigFactory.create;
import static org.testng.Assert.assertEquals;

public class GroupAPI {

    ManageHeaders header = new ManageHeaders();
    MethodManager methodManager = new MethodManager();
    ManageURL baseURL = new ManageURL();
    UrlConfig config = create(UrlConfig.class);
    GroupCreate create = new GroupCreate();

    @BeforeClass
    public void setHeader(){
        header.set_Headers(null, null);
        baseURL.setURL();
    }



    @Test
    public void createGroup() {
        HelperMethods.setAnsiGreen("Started :- Create Group ");
        /**
         * Main test and api call initiated
         */
        SmartResponse response= create.GroupCreate(config.createGroup(),"tgroup", "tgroup", "capital");
        if (response.getStatusCode() != HttpStatus.SC_CREATED){
            System.out.println(response.getStatusCode());
            System.out.println(response.getJson());
        }

        else {
            Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_CREATED);
            HelperMethods.setAnsiGreen("Group Created Successfully ");
        }
    }

    @Test(dependsOnMethods={"createGroup"})
    public void moveGroup() {
        HelperMethods.setAnsiGreen("Started :- Move Group ");

        Map<String,String> body = new HashMap<>();
        body.put("parentGroupUniqueName", "sundrydebtors");

        /**
         * Main test and api call initiated
         */

        SmartResponse resp = methodManager.putAPI_with_Assert_Statuscode(config.moveGroup(), body);
//      System.out.println(resp.getStatusCode());
        System.out.println(resp.getJson());
    }


    @Test(dependsOnMethods={"createGroup"})
    public void getGroup() {

        HelperMethods.setAnsiGreen("Started :- Get Group ");


        /**
         * Main test and api call initiated
         */

        SmartResponse resp = methodManager.getAPI_with_Assert_Statuscode(config.getGroup());
//      System.out.println(resp.getStatusCode());
        System.out.println(resp.getJson() + "This is the Response eof Get Company");

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

        SmartResponse resp = methodManager.putAPI_with_Assert_Statuscode(config.shareGroup(), body);
//      System.out.println(resp.getStatusCode());
        System.out.println(resp.getJson());
    }


    @Test(dependsOnMethods={"createGroup"})
    public void unshareGroup() {

        HelperMethods.setAnsiGreen("Started :- UnShare Group ");

        Map<String,String> body = new HashMap<>();
        body.put("user", "tadhall87@gmail.com");

        /**
         * Main test and api call initiated
         */

        SmartResponse resp = methodManager.putAPI_with_Assert_Statuscode(config.unshareGroup(), body);
//      System.out.println(resp.getStatusCode());
        System.out.println(resp.getJson());
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

        SmartResponse resp = methodManager.putAPI_with_Assert_Statuscode(config.updateGroup(), body);
//      System.out.println(resp.getStatusCode());
        String json = resp.getJson();
        JsonPath jp = new JsonPath(json);
        assertEquals(jp.get("body.name"),"tgroup1" );
    }


    public void deleteGroup() {

        /**
         * Main test and api call initiated
         */

        SmartResponse resp = methodManager.deleteAPI_with_Assert_Statuscode(config.deleteGroup());
//      System.out.println(resp.getStatusCode());
        System.out.println(resp.getJson());

    }

}
