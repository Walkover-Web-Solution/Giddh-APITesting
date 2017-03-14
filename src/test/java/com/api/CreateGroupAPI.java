package com.api;

import com.ApiUtils.HelperMethods;
import com.ApiUtils.UrlConfig;
import com.model.ManageHeaders;
import com.model.ManageURL;
import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.aeonbits.owner.ConfigFactory.create;

public class CreateGroupAPI {

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
                        post(config.groupCreate());
                        HelperMethods.printResponse(resp);
                        HelperMethods.checkStatusIs201(resp);


    }


 }
