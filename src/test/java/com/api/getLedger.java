package com.api;



import  com.ApiUtils.Apiheaders;

import com.model.Manage_Header;

import com.jayway.restassured.response.Response;
import org.testng.annotations.*;
import static com.jayway.restassured.RestAssured.*;

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
    public void sethead(){
        header.set_Headers();
    }


    @Test
    public void getStocks(){
      Response resp =
                given()
                        .headers("Auth-Key", header.getauth_key()).
                        // header("Content-Type", ah.get_type()).
                when().
                       get( URL + "stocks");
                       System.out.println(resp.asString());
                       Assert.assertEquals(resp.getStatusCode(), 200);
    }
}
