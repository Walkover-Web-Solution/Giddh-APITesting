package com.api.regression;

import  com.apiUtils.*;
import com.model.ManageHeaders;

import io.restassured.response.Response;
import org.testng.annotations.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import  org.testng.Assert;
import java.util.Map;

@Test
public class StocksAPI {

    ManageHeaders header = new ManageHeaders();


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
//        SmartResponse resp =given().headers(new Header(headerList)).
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
        header.set_Headers(null);
    }

//    @BeforeMethod
//    public void setup(){
//        RestAssured.config = RestAssured.config().httpClient(httpClientConfig().reuseHttpClientInstance());
//    }

    //@Test
    public void getStocks(){
        Response resp =
                given()
                        .headers("Auth-Key", header.getAuthKey()).
                when().
                         get( URL + "stocks");
                         System.out.println(resp.asString());
                         Assert.assertEquals(resp.getStatusCode(), 200);
                         HelperMethods.checkStatusIs200(resp);
                         HelperMethods.checkResponseTime(resp, "Get Stock API is ");

    }

    //@Test
    public void getStocksValidater(){
                given()
                       .headers("Auth-Key", header.getAuthKey()).
                when().
                        get( URL + "stocks").
                then().
                assertThat().
                         body(
                              "body.results[0].stockGroup.uniqueName", equalTo("textsms"),
                              "body.results[0].stockGroup.name", equalTo("text sms1")
                         ).
                and().
                          time(lessThan(2000L));

               // body(containsString("sms1"));
        //  System.out.println(resp.asString());
        //  Assert.assertEquals(resp.getStatusCode(), 200);
    }

//    @AfterMethod
//    public void closeConnection(){
//        RestAssured.config = RestAssured.config().httpClient(httpClientConfig().reuseHttpClientInstance());
//    }
}
