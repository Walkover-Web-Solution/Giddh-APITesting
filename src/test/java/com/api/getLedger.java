package com.api;


import com.jayway.restassured.response.Response;
import org.testng.annotations.*;
import com.jayway.restassured.*;
import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.testng.Assert;



public class getLedger {


    @BeforeMethod
    public void setUp() {

        given().
                header("Auth-Key", "UfHWKDi9mj8MlPpXpJNy5olw3DUPpEa_M6wTd0MYCZHzll3xZCSvkZVkusHzHnbVC9uU7_fuysX7EDqxy8V6z3qCSnd6Kc57J1pvtbbTSZY=").
                header("Content-Type", "application/json").
                body("  {\"id\":\"2\","
                        + " \"titile\":\"dummyTitle\","
                        + " \"author\":\"Vaibhav\" }  ");
    }

    @Test
    public void Test01(){
         Response resp =
                 when().
                 post("http://apitest.giddh.com/company/tallysindore14848153133160awv0d/sync-enable");
         System.out.println(resp.asString());
         Assert.assertEquals(resp.getStatusCode(), 200);
     }

//
//    @Test
//    public void Test02(){
//        Response resp = given().
//                header("Auth-Key", "UfHWKDi9mj8MlPpXpJNy5olw3DUPpEa_M6wTd0MYCZHzll3xZCSvkZVkusHzHnbVC9uU7_fuysX7EDqxy8V6z3qCSnd6Kc57J1pvtbbTSZY=").
//                header("Content-Type", "application/json").
//                body("  {\"id\":\"2\","
//                        + " \"titile\":\"dummyTitle\","
//                        + " \"author\":\"Vaibhav\" }  ").
//                when().
//                post("http://apitest.giddh.com/company/tallysindore14848153133160awv0d/sync-enable");
//        System.out.println(resp.asString());
//        Assert.assertEquals(resp.getStatusCode(), 200);
//    }




}
