package com.api;


import org.testng.Assert;
import org.testng.annotations.Test;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

import static com.jayway.restassured.RestAssured.*;
public class getLedger {

     @Test
    public void Test01(){
         Response resp = when().get("https://www.goo1515gle.co.in/");
         System.out.println(resp.asString());
         Assert.assertEquals(resp.getStatusCode(), 200);
     }
}
