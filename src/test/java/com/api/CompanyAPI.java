package com.api;

import com.Config.UrlConfig;
import com.model.ManageHeaders;
import com.model.ManageURL;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.*;

import java.util.HashMap;
import java.util.Map;
import com.ApiUtils.*;
import static io.restassured.RestAssured.*;
import static io.restassured.config.HttpClientConfig.httpClientConfig;
import static org.aeonbits.owner.ConfigFactory.create;


public class CompanyAPI {

    ManageHeaders header = new ManageHeaders();
    ApiManager apiManager = new ApiManager();
    ManageURL baseURL = new ManageURL();
    UrlConfig config = create(UrlConfig.class);

    @BeforeTest
    public void setHeader(){
        header.set_Headers();
        baseURL.setURL();
    }



    @Test
    public void createCompany(){

        Map<String,String> body = new HashMap<>();
        body.put("name", "audi");
        body.put("uniqueName", "audi");

        /**
         * Main test and api call initiated
         */

        SmartResponse resp = apiManager.postAPI_with_Assert_Statuscode(config.mainURL(), body);
        //System.out.println(resp.getStatusCode());
        System.out.println(resp.getJson());
    }


    @Test(dependsOnMethods={"createCompany"})
    public void getCompany(){
        /**
         * Main test and api call initiated
         */

        SmartResponse resp = apiManager.getAPI_with_Assert_Statuscode(config.getCompany());
//      System.out.println(resp.getStatusCode());
        System.out.println(resp.getJson());
    }


    @Test(dependsOnMethods={"createCompany"})
    public void shareCompany(){

        Map<String,String> body = new HashMap<>();
        body.put("user", "walkover78@gmail.com");
        body.put("role", "edit");

        /**
         * Main test and api call initiated
         */

        SmartResponse resp = apiManager.putAPI_with_Assert_Statuscode(config.shareCompany(), body);
        //System.out.println(resp.getStatusCode());
        System.out.println(resp.getJson() + "This is the Response of create Company");
    }


    @Test(dependsOnMethods={"createCompany"})
    public void unShareCompany(){

        Map<String,String> body = new HashMap<>();
        body.put("user", "walkover78@gmail.com");

        /**
         * Main test and api call initiated
         */

        SmartResponse resp = apiManager.putAPI_with_Assert_Statuscode(config.unshareCompany(), body);
        //System.out.println(resp.getStatusCode());
        System.out.println(resp.getJson() + "This is the Response of create Company");
    }



    @AfterSuite
    public void deleteCompany(){

        /**
         * Main test and api call initiated
         */
        SmartResponse resp = apiManager.deleteAPI_with_Assert_Statuscode(config.deleteCompany());
        //System.out.println(resp.getStatusCode());
        System.out.println(resp.getJson() + "This is the Response of create Company");

    }

}
