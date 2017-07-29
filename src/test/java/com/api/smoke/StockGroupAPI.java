package com.api.smoke;

import com.apiUtils.*;
import com.config.UrlConfig;
import com.controller.StockGroupCreate;
import groovy.ui.SystemOutputInterceptor;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.apache.http.HttpStatus;
import org.testng.*;
import org.testng.annotations.*;

import java.math.BigDecimal;
import java.util.*;

import static io.restassured.config.HttpClientConfig.httpClientConfig;
import static org.aeonbits.owner.ConfigFactory.create;
import static org.testng.Assert.assertEquals;

public class StockGroupAPI {

    private MethodManager methodManager = new MethodManager();
    private UrlConfig config = create(UrlConfig.class);
    private StockGroupCreate stockGroupCreate = new StockGroupCreate();

    public static String stock_GroupName1;
    public static String stock_GroupName2;
    int count = 0 ;

    @DataProvider
    private Object[][] getStockGroupData(){
        Object[][] getStockGroupData = new  Object[2][1];
        getStockGroupData[0][0]= "stockgroup1";
        getStockGroupData[1][0]= "stockgroup2";
        return getStockGroupData;
    }

    @DataProvider
    private Object[][] getStockUniqueName(){
        Object[][] stockName = new  Object[2][1];
        stockName[0][0]= stock_GroupName1;
        stockName[1][0]= stock_GroupName1;
        return stockName;
    }

    @Test(dataProvider = "getStockGroupData")
    public void create_Stock_Group(String stockName) {
        HelperMethods.setAnsiGreen("Started :- Create Stock Group ");
        /**
         * Main test and api call initiated
         */
        SmartResponse response= stockGroupCreate.StcokGroupCreate(null, null, config.createStockGroup(),stockName,stockName,"");
        if (response.getStatusCode() == HttpStatus.SC_CREATED){
            String json = response.getJson();
            JsonPath jp = new JsonPath(json);
            count ++ ;
            if (count == 1){
                stock_GroupName1 = jp.get("body.uniqueName");
                HelperMethods.setAnsiGreen("First Stock  uniqueName is " + stock_GroupName1);
            } else {
                stock_GroupName2 = jp.get("body.uniqueName");
                HelperMethods.setAnsiGreen("Second Second uniqueName is " + stock_GroupName2);
            }
        }
        else {
            HelperMethods.setAnsiRed("Create Stock Group Functionality Failed ");
            System.out.println(response.getStatusCode());
            System.out.println(response.getJson());
            Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_CREATED);
        }
    }


    @Test(dataProvider = "getStockUniqueName")
    public void get_Stock_Group(String stockName) {
        HelperMethods.setAnsiGreen("Started :- Get Stock Group");
        /**
         * Main test and api call initiated
         */
        SmartResponse response= methodManager.getAPI_with_Assert_Statuscode(null, null, config.createStockGroup() + stockName);
        HelperMethods.assertCode("Get Stock Group", response.getStatusCode(), HttpStatus.SC_OK, response.getJson());
    }

    @Test
    public void update_Stock_Group() {
        HelperMethods.setAnsiGreen("Started :- Update Stock Group");

        Map<String,String> body = new HashMap<>();
        body.put("name", "Stock Group 2");
        body.put("uniqueName", "stockgroup2");
        body.put("parentStockGroupUniqueName", stock_GroupName1);

        /**
         * Main test and api call initiated
         */
        SmartResponse response= methodManager.putAPI_with_Assert_Statuscode(null, null, config.createStockGroup() + stock_GroupName2, body);
        if (response.getStatusCode() != HttpStatus.SC_OK){
            HelperMethods.setAnsiRed("Update Stock Group Functionality Failed ");
            System.out.println(response.getStatusCode());
            System.out.println(response.getJson());
            Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
        }
        else {
            Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
            String json = response.getJson();
            JsonPath jp = new JsonPath(json);
            stock_GroupName2 = jp.get("body.uniqueName");
            HelperMethods.setAnsiGreen("Stock Group Updated Successfully");
        }
    }

    @Test
    public void get_Hierarchical_Stock_Groups (){
        HelperMethods.setAnsiGreen("Started :- Get Hierarchical Stock Groups");
        /**
         * Main test and api call initiated
         */
        SmartResponse response= methodManager.getAPI_with_Assert_Statuscode(null, null, config.get_All_Hierarchical_Stock_Group());
        if (response.getStatusCode() == HttpStatus.SC_OK){
            String json = response.getJson();
            JsonPath jp = new JsonPath(json);
            assertEquals( jp.get("body.results[0].childStockGroups[0].uniqueName"), stock_GroupName2);
            HelperMethods.setAnsiGreen("Get Hierarchical Stock Groups Completed Successfully");
        }
        else {
            HelperMethods.setAnsiRed("Get Hierarchical Stock Groups Functionality fails with Response Code = " +  response.getStatusCode());
            HelperMethods.setAnsiRed(response.getJson());
            Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
        }
    }

    public void delete_Stock_Group(String stockName){
        HelperMethods.setAnsiGreen("Started :- Delete Stock Group");
        /**
         * Main test and api call initiated
         */
        SmartResponse response= methodManager.deleteAPI_with_Assert_Statuscode(null, null, config.createStockGroup() + stockName);
        HelperMethods.assertCode("Delete Stock Group", response.getStatusCode(), HttpStatus.SC_OK, response.getJson());
    }

    @AfterMethod
    public void  setup(){
        RestAssured.config = RestAssured.config().httpClient(httpClientConfig().reuseHttpClientInstance());
        RestAssured.reset();
    }
}
