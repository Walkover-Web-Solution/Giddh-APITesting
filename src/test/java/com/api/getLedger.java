package com.api;


import  com.ApiUtils.Headers;
import  com.ApiUtils.Apiheaders;

import com.jayway.restassured.response.Header;

import com.jayway.restassured.response.Response;
import org.testng.annotations.*;
import static com.jayway.restassured.RestAssured.*;

import  org.testng.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class getLedger {

    Map<String, String> headersMap = new Apiheaders().headers();

//    @Test
//    public void Test01(){
//        List<Header> headerList = new ArrayList<>();
//        for(String key : headersMap.keySet()) {
//            String value = headersMap.get(key);
//            Header header = new Header(key, value);
//            headerList.add(header);
//        }
//        Response resp =given().headers(new Headers(headerList)).
//                body("  {\"id\":\"2\","
//                        + " \"titile\":\"dummyTitle\","
//                        + " \"author\":\"Vaibhav\" }  ").
//                when().
//                get("http://apitest.giddh.com/company/inventindore1483703191258019mki/stocks");
//        System.out.println(resp.asString());
//        Assert.assertEquals(resp.getStatusCode(), 200);
//    }


    @Test
    public void Test02(){
        com.ApiUtils.Headers h1 = new com.ApiUtils.Headers();
        h1.setAuth_Key("UfHWKDi9mj8MlPpXpJNy5olw3DUPpEa_M6wTd0MYCZHzll3xZCSvkZVkusHzHnbVC9uU7_fuysX7EDqxy8V6z3qCSnd6Kc57J1pvtbbTSZY");
        h1.setType("application/json");
        Response resp =
                given()
                        .headers("Auth-Key", h1.getAuth_Key()).
                        header("Content-Type", h1.getType()).
                when().
                get("http://apitest.giddh.com/company/inventindore1483703191258019mki/stocks");
        System.out.println(resp.asString());
        Assert.assertEquals(resp.getStatusCode(), 200);
    }
}
