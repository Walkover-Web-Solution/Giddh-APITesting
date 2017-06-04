package com.api.regression;

import com.apiUtils.*;
import com.config.*;
import com.controller.*;
import io.restassured.path.json.JsonPath;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.HashMap;
import java.util.Map;

import static com.api.regression.Company.*;
import static org.aeonbits.owner.ConfigFactory.create;
import static org.testng.Assert.assertEquals;

public class Group {

    MethodManager methodManager = new MethodManager();
    UrlConfig config = create(UrlConfig.class);
    GroupCreate create = new GroupCreate();
    HeadersConfig headersConfig = create(HeadersConfig.class);
    Company company = new Company();
    private String baseURL = mainURL + companyName + "/groups";
    private String apiURL;


    public void createGroup(String auth, int statusCode) {

        /**
         * Main test and api call initiated
         */
        SmartResponse response= create.GroupCreate(auth, baseURL ,"tgroup", "tgroup", "capital");
        HelperMethods.assertCode("Create Group", response.getStatusCode(), statusCode, response.getJson());
    }

    public void getGroup() {

        apiURL = baseURL + "/tgroup";

        /**
         * Main test and api call initiated
         */
        SmartResponse response = methodManager.getAPI_with_Assert_Statuscode(null, apiURL);
        HelperMethods.assertCode("Get Group", response.getStatusCode(), HttpStatus.SC_OK, response.getJson());
    }

    public void updateGroup() {

        Map<String,String> body = new HashMap<>();
        body.put("name", "tgroup1");
        body.put("uniqueName", "tgroup");
        body.put("parentGroupUniqueName", "capital");

        apiURL = baseURL + "/tgroup";

        /**
         * Main test and api call initiated
         */

        SmartResponse resp = methodManager.putAPI_with_Assert_Statuscode(null, apiURL, body);
        if (resp.getStatusCode() == HttpStatus.SC_OK){
            String json = resp.getJson();
            JsonPath jp = new JsonPath(json);
            assertEquals(jp.get("body.name"),"tgroup1" );
            HelperMethods.setAnsiGreen("Update Group Functionality Completed Successfully ");
        }
        else {
            HelperMethods.setAnsiRed(" Update Group Functionality fails with Response Code = " +  resp.getStatusCode());
            HelperMethods.setAnsiRed(resp.getJson());
            Assert.assertEquals(resp.getStatusCode(), HttpStatus.SC_OK);
        }
    }

    public void moveGroup() {

        apiURL = baseURL + "/tgroup" + "/move";

        Map<String,String> body = new HashMap<>();
        body.put("parentGroupUniqueName", "sundrydebtors");

        /**
         * Main test and api call initiated
         */

        SmartResponse response = methodManager.putAPI_with_Assert_Statuscode(null, config.moveGroup(), body);
        HelperMethods.assertCode("Move Group", response.getStatusCode(), HttpStatus.SC_OK, response.getJson());
    }



    public void shareGroup() {

        apiURL = baseURL + "/tgroup" + "/share";

        Map<String,String> body = new HashMap<>();
        body.put("user", "tadhall87@gmail.com");
        body.put("role", "edit");

        /**
         * Main test and api call initiated
         */

        SmartResponse response = methodManager.putAPI_with_Assert_Statuscode(null, config.shareGroup(), body);
        HelperMethods.assertCode("Share Group", response.getStatusCode(), HttpStatus.SC_OK, response.getJson());
    }


    public void unShareGroup() {
        apiURL = baseURL + "/tgroup" + "/unshare";

        Map<String,String> body = new HashMap<>();
        body.put("user", "tadhall87@gmail.com");

        /**
         * Main test and api call initiated
         */

        SmartResponse response = methodManager.putAPI_with_Assert_Statuscode(null, config.unshareGroup(), body);
        HelperMethods.assertCode("UnShare Group", response.getStatusCode(), HttpStatus.SC_OK, response.getJson());
    }


    public void deleteGroup() {

        apiURL = baseURL + "/tgroup";

        /**
         * Main test and api call initiated
         */
        SmartResponse response = methodManager.deleteAPI_with_Assert_Statuscode(null, config.deleteGroup());
        HelperMethods.assertCode("Delete Group", response.getStatusCode(), HttpStatus.SC_OK, response.getJson());
    }

    /** Scenario :-
     * 'A' User share Company with 'B' user with 'Super Admin' permission and
     * then 'A' User Should not Able to Delete Company shared by 'A' User,
     */


    @Test
    public void createGroup_after_shareCompany_for_View(){
        HelperMethods.setAnsiGreen("Started :- Create Group after shareCompany for View");

        /**
         * Main test and api call initiated
         */
        company.getCompany(HttpStatus.SC_OK);
        company.shareCompany("view_only");
        createGroup(headersConfig.getSharedUserAuthKey(), HttpStatus.SC_UNAUTHORIZED);
    }
}
