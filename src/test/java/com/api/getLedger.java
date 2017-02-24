package com.api;



import  com.ApiUtils.Apiheaders;

import com.ApiUtils.Headers;
import  com.model.allheader;
import com.jayway.restassured.response.Header;

import com.jayway.restassured.response.Response;
import org.testng.annotations.*;
import static com.jayway.restassured.RestAssured.*;

import  org.testng.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class getLedger {

    allheader ah = new allheader();
    String URL = "http://apitest.giddh.com/company/inventindore1483703191258019mki/";

    Map<String, String> headersMap = new Apiheaders().headers();

//    @Test
//    public void Test01(){
//        List<Header> headerList = new ArrayList<>();
//        for(String key : headersMap.keySet()) {
//            String value = headersMap.get(key);
//            Header header = new Header(key, value);
//            headerList.add(header);
//        }
//        Response resp =given().headers(new Header(headerList)).
//                body("  {\"id\":\"2\","
//                        + " \"titile\":\"dummyTitle\","
//                        + " \"author\":\"Vaibhav\" }  ").
//                when().
//                get("http://apitest.giddh.com/company/inventindore1483703191258019mki/stocks");
//        System.out.println(resp.asString());
//        Assert.assertEquals(resp.getStatusCode(), 200);
//    }

    @BeforeTest
    public void sethead(){
       ah.all_Headers();

    }


    @Test
    public void getStocks(){
      Response resp =
                given()
                        .headers("Auth-Key", ah.getauth_key()).
                        // header("Content-Type", ah.get_type()).
                when().
                       get( URL + "stocks");
                       System.out.println(resp.asString());
                       Assert.assertEquals(resp.getStatusCode(), 200);
    }
}
