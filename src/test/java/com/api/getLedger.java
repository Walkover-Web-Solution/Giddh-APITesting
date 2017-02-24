package com.api;



import  com.ApiUtils.Apiheaders;

import com.model.Manage_Header;


import io.restassured.response.*;
import org.testng.annotations.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.containsString;

import org.hamcrest.Matchers.*;

import  org.testng.Assert;

import java.util.Map;


public class getLedger {

    Manage_Header header = new Manage_Header();
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
    public void setHeader(){
        header.set_Headers();
    }


    @Test
    public void getStocks(){
        Response resp =
                given()
                        .headers("Auth-Key", header.getAuthKey()).
                        // header("Content-Type", ah.get_type()).
                                when().
                        get( URL + "stocks");
        System.out.println(resp.asString());
        Assert.assertEquals(resp.getStatusCode(), 200);
    }

    @Test
    public void getStocksValidater(){
        given()
                .headers("Auth-Key", header.getAuthKey()).
                // header("Content-Type", ah.get_type()).
                        when().
                get( URL + "stocks").
                then().
                body(containsString("success"));
        //  System.out.println(resp.asString());
        //  Assert.assertEquals(resp.getStatusCode(), 200);
    }
}
