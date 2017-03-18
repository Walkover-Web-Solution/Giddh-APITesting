package com.ApiUtils;

import com.model.ManageHeaders;
import io.restassured.RestAssured;
import io.restassured.config.ConnectionConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.response.Response;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.*;
import static io.restassured.config.HttpClientConfig.httpClientConfig;


public class ApiManager {

    ManageHeaders header = new ManageHeaders();


    public SmartResponse getAPI_with_Assert_Statuscode(String URL) {

        RestAssured.config = RestAssured.config().httpClient(httpClientConfig().reuseHttpClientInstance());

        header.set_Headers();

        Response resp =
                given()
                        .headers("Auth-Key",header.getAuthKey())
                        .headers("Content-Type",header.getType()).
                        //.contentType("application/json")
                when().
                        get(URL);
                        //HelperMethods.checkStatusIs200(resp);
                        String json = resp.asString();
                        int statusCode = resp.getStatusCode();
                        SmartResponse response = new SmartResponse(statusCode, json);
                        RestAssured.config = RestAssuredConfig.config().connectionConfig(new ConnectionConfig().closeIdleConnectionsAfterEachResponse());
                        return response;
        //  return statusCode;
    }


    public SmartResponse postAPI_with_Assert_Statuscode(String URL, Object body) {

        RestAssured.config = RestAssured.config().httpClient(httpClientConfig().reuseHttpClientInstance());

        header.set_Headers();

        Response resp =
                given()
                        .headers("Auth-Key",header.getAuthKey())
                        .headers("Content-Type",header.getType()).
                        //.contentType("application/json")
                         body(body).
                when().
                        post(URL);
                        //HelperMethods.checkStatusIs201(resp);
                        String json = resp.asString();
                        int statusCode = resp.getStatusCode();
                        SmartResponse response = new SmartResponse(statusCode, json);
                        RestAssured.config = RestAssuredConfig.config().connectionConfig(new ConnectionConfig().closeIdleConnectionsAfterEachResponseAfter(2, TimeUnit.SECONDS));
                        return response;

    }

    public SmartResponse putAPI_with_Assert_Statuscode(String URL, Object body){
        RestAssured.config = RestAssured.config().httpClient(httpClientConfig().reuseHttpClientInstance());

        Response resp =
                given()
                        .headers("Auth-Key",header.getAuthKey())
                        .headers("Content-Type",header.getType()).
                        //.contentType("application/json")
                        body(body).
                when().
                        put(URL);
                        //HelperMethods.checkStatusIs200(resp);
                        String json = resp.asString();
                        int statusCode = resp.getStatusCode();
                        SmartResponse response = new SmartResponse(statusCode, json);
                        RestAssured.config = RestAssuredConfig.config().connectionConfig(new ConnectionConfig().closeIdleConnectionsAfterEachResponse());
                        return response;
    }


    public SmartResponse deleteAPI_with_Assert_Statuscode(String URL){

        RestAssured.config = RestAssured.config().httpClient(httpClientConfig().reuseHttpClientInstance());

        header.set_Headers();


        Response resp =
                given()
                        .headers("Auth-Key",header.getAuthKey())
                        .headers("Content-Type",header.getType()).
                        //.contentType("application/json")
                when().
                        delete(URL);
                       // HelperMethods.checkStatusIs200(resp);
                        String json = resp.asString();
                        int statusCode = resp.getStatusCode();
                        SmartResponse response = new SmartResponse(statusCode, json);
                        RestAssured.config = RestAssuredConfig.config().connectionConfig(new ConnectionConfig().closeIdleConnectionsAfterEachResponse());
                        return response;


    }


}
