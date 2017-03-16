package com.ApiUtils;

import com.api.SmartResponse;
import com.model.ManageHeaders;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class ApiManager {

    ManageHeaders header = new ManageHeaders();


    public SmartResponse getAPI_with_Assert_Statuscode(String URL) {

        Response resp =

                given()
                        .headers("Auth-Key",header.getAuthKey() )
                        .headers("Content-Type",header.getType() ).
                        //.contentType("application/json")
                when().
                        get(URL);
                        HelperMethods.checkStatusIs200(resp);
                        String json = resp.asString();
                        JsonPath jp = new JsonPath(json);
                        assertEquals("tgroup1", jp.get("body.name"));
                        int statusCode = resp.getStatusCode();
                        SmartResponse response = new SmartResponse(statusCode, json);
                        return response;
    }
}
