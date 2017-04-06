package com.ApiUtils;

import com.model.ManageHeaders;
import io.restassured.RestAssured;
import io.restassured.config.ConnectionConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.*;
import static io.restassured.config.HttpClientConfig.httpClientConfig;
import static io.restassured.config.SSLConfig.sslConfig;


public class MethodManager {

    ManageHeaders header = new ManageHeaders();


    public SmartResponse getAPI_with_Assert_Statuscode(String URL) {
        RestAssured.config = RestAssured.config().httpClient(httpClientConfig().reuseHttpClientInstance());
        header.set_Headers();

        Response resp =
                given().config(RestAssured.config().sslConfig(sslConfig().allowAllHostnames()))
                        .headers("Auth-Key",header.getAuthKey())
                        .headers("Content-Type",header.getType()).
                        //.contentType("application/json")
                when().
                        get(URL);
                        String json = resp.asString();
                        int statusCode = resp.getStatusCode();
                        SmartResponse response = new SmartResponse(statusCode, json);
                        RestAssured.config = RestAssuredConfig.config().connectionConfig(new ConnectionConfig().closeIdleConnectionsAfterEachResponseAfter(2, TimeUnit.MILLISECONDS));
                        return response;
    }


    public SmartResponse postAPI_with_Assert_Statuscode(String URL, Object body) {
        RestAssured.config = RestAssured.config().httpClient(httpClientConfig().reuseHttpClientInstance());
        header.set_Headers();

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
                        SmartResponse response = new SmartResponse(statusCode, json);
                        RestAssured.config = RestAssuredConfig.config().connectionConfig(new ConnectionConfig().closeIdleConnectionsAfterEachResponseAfter(2, TimeUnit.MILLISECONDS));
                        return response;

    }

    public SmartResponse postAPI_with_Assert_Statuscode1(String URL) {
        RestAssured.config = RestAssured.config().httpClient(httpClientConfig().reuseHttpClientInstance());
        header.set_Headers();

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
                            SmartResponse response = new SmartResponse(statusCode, json);
                            RestAssured.config = RestAssuredConfig.config().connectionConfig(new ConnectionConfig().closeIdleConnectionsAfterEachResponseAfter(2, TimeUnit.MILLISECONDS));
                            return response;

    }

    public SmartResponse putAPI_with_Assert_Statuscode(String URL, Object body){
        RestAssured.config = RestAssured.config().httpClient(httpClientConfig().reuseHttpClientInstance());
        header.set_Headers();

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
                        SmartResponse response = new SmartResponse(statusCode, json);
                        RestAssured.config = RestAssuredConfig.config().connectionConfig(new ConnectionConfig().closeIdleConnectionsAfterEachResponseAfter(2, TimeUnit.MILLISECONDS));
                        return response;
    }


    public SmartResponse deleteAPI_with_Assert_Statuscode(String URL){
        RestAssured.config = RestAssured.config().httpClient(httpClientConfig().reuseHttpClientInstance());
        header.set_Headers();

        Response resp =
                given().config(RestAssured.config().sslConfig(sslConfig().allowAllHostnames()))
                        .headers("Auth-Key",header.getAuthKey())
                        .headers("Content-Type",header.getType()).
                        //.contentType("application/json")
                when().
                        delete(URL);
                        String json = resp.asString();
                        int statusCode = resp.getStatusCode();
                        SmartResponse response = new SmartResponse(statusCode, json);
                        RestAssured.config = RestAssuredConfig.config().connectionConfig(new ConnectionConfig().closeIdleConnectionsAfterEachResponseAfter(2, TimeUnit.MILLISECONDS));
                        return response;
    }
}
