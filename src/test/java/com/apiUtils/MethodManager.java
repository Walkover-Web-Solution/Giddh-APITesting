package com.apiUtils;

import com.model.ManageHeaders;
import io.restassured.RestAssured;
import io.restassured.config.ConnectionConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.response.Response;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.*;
import static io.restassured.config.HttpClientConfig.httpClientConfig;
import static io.restassured.config.SSLConfig.sslConfig;


public class MethodManager {

    ManageHeaders header = new ManageHeaders();


    public SmartResponse getAPI_with_Assert_Statuscode(String auth, String type,String URL) {
        RestAssured.config = RestAssured.config().httpClient(httpClientConfig().reuseHttpClientInstance());
        header.set_Headers(auth, type);

        Response resp =
                given().config(RestAssured.config().sslConfig(sslConfig().allowAllHostnames()))
                        .headers("Auth-Key",header.getAuthKey())
                        .headers("Content-Type",header.getType()).
                        //.contentType("application/json")
                when().
                        get(URL);
                        String json = resp.asString();
                        int statusCode = resp.getStatusCode();
        //RestAssured.config = RestAssuredConfig.config().connectionConfig(new ConnectionConfig().closeIdleConnectionsAfterEachResponseAfter(2, TimeUnit.MILLISECONDS));
                        return new SmartResponse(statusCode, json);
    }

    public SmartResponse getAPI_With_Params(String auth, String type, String URL, String from, String to , String search, boolean refresh) {
        RestAssured.config = RestAssured.config().httpClient(httpClientConfig().reuseHttpClientInstance());
        header.set_Headers(auth, type);

        if (from == null){
            from = "";
        }
        if (to == null){
            to = "";
        }
        if (search == null ){
            search = "";
        }
        Response resp =
                given().config(RestAssured.config().sslConfig(sslConfig().allowAllHostnames())).
                        headers("Auth-Key",header.getAuthKey()).
                        headers("Content-Type",header.getType()).
                        param("from",from).
                        param("to", to).
                        param("q", search).
                        param("refresh", refresh).
                        //.contentType("application/json")
                when().
                        get(URL);
                String json = resp.asString();
                int statusCode = resp.getStatusCode();
        //RestAssured.config = RestAssuredConfig.config().connectionConfig(new ConnectionConfig().closeIdleConnectionsAfterEachResponseAfter(2, TimeUnit.MILLISECONDS));
                return new SmartResponse(statusCode, json);
    }


    public SmartResponse postAPI_with_Assert_Statuscode(String auth, String type, String URL, Object body) {
        RestAssured.config = RestAssured.config().httpClient(httpClientConfig().reuseHttpClientInstance());
        header.set_Headers(auth, type);

        Response resp =
                given().config(RestAssured.config().sslConfig(sslConfig().allowAllHostnames()))
                        .headers("Auth-Key",header.getAuthKey())
                        .headers("Content-Type",header.getType()).
                        //.contentType("application/json")
                                body(body).
                when().
                        post(URL);
                        while( true ) {
                            if( resp.getBody() ==  null) {
                                try {
                                    Thread.sleep(3000);
                                }
                                catch (Exception e){}
                                continue;
                            }
                            break;
                        }
                String json = resp.asString();
                int statusCode = resp.getStatusCode();
        //RestAssured.config = RestAssuredConfig.config().connectionConfig(new ConnectionConfig().closeIdleConnectionsAfterEachResponseAfter(2, TimeUnit.MILLISECONDS));
                return new SmartResponse(statusCode, json);
    }

    /** APi mention below is with no body */

    public SmartResponse postAPI_with_Assert_Statuscode1(String auth, String type,String URL) {
        RestAssured.config = RestAssured.config().httpClient(httpClientConfig().reuseHttpClientInstance());
        header.set_Headers(auth, type);

        Response resp =
                given().config(RestAssured.config().sslConfig(sslConfig().allowAllHostnames()))
                        .headers("Auth-Key",header.getAuthKey())
                        .headers("Content-Type",header.getType()).
                        //.contentType("application/json")
                when().
                        post(URL);
                        while( true ) {
                            if( resp.getBody() ==  null) {
                                try {
                                    Thread.sleep(3000);
                                }
                                catch (Exception e){}
                                continue;
                            }
                            break;
                        }
                String json = resp.asString();
                int statusCode = resp.getStatusCode();
        //RestAssured.config = RestAssuredConfig.config().connectionConfig(new ConnectionConfig().closeIdleConnectionsAfterEachResponseAfter(2, TimeUnit.MILLISECONDS));
                return new SmartResponse(statusCode, json);

    }

    public SmartResponse putAPI_with_Assert_Statuscode(String auth, String type,String URL, Object body){
        RestAssured.config = RestAssured.config().httpClient(httpClientConfig().reuseHttpClientInstance());
        header.set_Headers(auth, type);

        Response resp =
                given().config(RestAssured.config().sslConfig(sslConfig().allowAllHostnames()))
                        .headers("Auth-Key",header.getAuthKey())
                        .headers("Content-Type",header.getType()).
                        //.contentType("application/json")
                                body(body).
                when().
                        put(URL);
                        while( true ) {
                            if( resp.getBody() ==  null) {
                                try {
                                    Thread.sleep(3000);
                                }
                                catch (Exception e){}
                                continue;
                            }
                            break;
                        }
                String json = resp.asString();
                int statusCode = resp.getStatusCode();
        //RestAssured.config = RestAssuredConfig.config().connectionConfig(new ConnectionConfig().closeIdleConnectionsAfterEachResponseAfter(2, TimeUnit.MILLISECONDS));
                return new SmartResponse(statusCode, json);
    }


    public SmartResponse deleteAPI_with_Assert_Statuscode(String auth, String type,String URL){
        RestAssured.config = RestAssured.config().httpClient(httpClientConfig().reuseHttpClientInstance());
        header.set_Headers(auth, type);

        Response resp =
                given().config(RestAssured.config().sslConfig(sslConfig().allowAllHostnames()))
                        .headers("Auth-Key",header.getAuthKey())
                        .headers("Content-Type",header.getType()).
                        //.contentType("application/json")
                when().
                        delete(URL);
                String json = resp.asString();
                int statusCode = resp.getStatusCode();
        //RestAssured.config = RestAssuredConfig.config().connectionConfig(new ConnectionConfig().closeIdleConnectionsAfterEachResponseAfter(2, TimeUnit.MILLISECONDS));
                return new SmartResponse(statusCode, json);
    }
}
