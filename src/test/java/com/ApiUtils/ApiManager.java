package com.ApiUtils;

import com.model.ManageHeaders;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class ApiManager {

    ManageHeaders header = new ManageHeaders();


    public SmartResponse getAPI_with_Assert_Statuscode(String URL) {
        header.set_Headers();

        Response resp =
                given()
                        .headers("Auth-Key",header.getAuthKey())
                        .headers("Content-Type",header.getType()).
                        //.contentType("application/json")
                when().
                        get(URL);
        HelperMethods.checkStatusIs200(resp);
        String json = resp.asString();
        int statusCode = resp.getStatusCode();
        SmartResponse response = new SmartResponse(statusCode, json);
        return response;
        //  return statusCode;
    }
}
