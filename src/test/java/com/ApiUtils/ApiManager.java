package com.ApiUtils;

import com.model.ManageHeaders;
import groovy.util.MapEntry;
import io.restassured.response.Response;

import java.util.Map;
import java.util.Objects;

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


    public SmartResponse postAPI_with_Assert_Statuscode(String URL, Map<String,String> body) {
        header.set_Headers();

        Response resp =
                given()
                        .headers("Auth-Key",header.getAuthKey())
                        .headers("Content-Type",header.getType()).
                        //.contentType("application/json")
                         body(body).
                when().
                        post(URL);
                        HelperMethods.checkStatusIs201(resp);
                        String json = resp.asString();
                        int statusCode = resp.getStatusCode();
                        SmartResponse response = new SmartResponse(statusCode, json);
                        return response;

    }

    public SmartResponse putAPI_with_Assert_Statuscode(String URL, Map<String, String> body){

        Response resp =
                given()
                        .headers("Auth-Key",header.getAuthKey())
                        .headers("Content-Type",header.getType()).
                        //.contentType("application/json")
                        body(body).
                when().
                        put(URL);
                        HelperMethods.checkStatusIs200(resp);
                        String json = resp.asString();
                        int statusCode = resp.getStatusCode();
                        SmartResponse response = new SmartResponse(statusCode, json);
                        return response;
    }


    public SmartResponse deleteAPI_with_Assert_Statuscode(String URL){

        Response resp =
                given()
                        .headers("Auth-Key",header.getAuthKey())
                        .headers("Content-Type",header.getType()).
                        //.contentType("application/json")
                when().
                        delete(URL);
                        HelperMethods.checkStatusIs200(resp);
                        String json = resp.asString();
                        int statusCode = resp.getStatusCode();
                        SmartResponse response = new SmartResponse(statusCode, json);
                        return response;
    }


}
