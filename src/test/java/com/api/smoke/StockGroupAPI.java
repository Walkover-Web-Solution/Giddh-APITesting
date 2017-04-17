package com.api.smoke;

import com.apiUtils.HelperMethods;
import com.apiUtils.MethodManager;
import com.apiUtils.SmartResponse;
import com.config.UrlConfig;
import com.controller.StockGroupCreate;
import io.restassured.path.json.JsonPath;
import org.apache.http.HttpStatus;
import org.testng.*;
import org.testng.annotations.*;

import java.util.HashMap;
import java.util.Map;

import static org.aeonbits.owner.ConfigFactory.create;
import static org.testng.Assert.assertEquals;

public class StockGroupAPI {

    MethodManager methodManager = new MethodManager();
    UrlConfig config = create(UrlConfig.class);
    StockGroupCreate stockGroupCreate = new StockGroupCreate();

    public static String stock_GroupName;

    @Test
    public void create_Stock_Group() {
        HelperMethods.setAnsiGreen("Started :- Create Stock Group ");
        /**
         * Main test and api call initiated
         */
        SmartResponse response= stockGroupCreate.StcokGroupCreate(null, null, config.createStockGroup(),"stcokgroup1","stcokgroup1","");
        if (response.getStatusCode() != HttpStatus.SC_CREATED){
            HelperMethods.setAnsiRed("Create Stock Group Functionality Failed ");
            Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_CREATED);
            System.out.println(response.getStatusCode());
            System.out.println(response.getJson());
        }
        else {
            Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_CREATED);
            String json = response.getJson();
            JsonPath jp = new JsonPath(json);
            stock_GroupName = jp.get("body.uniqueName");
            System.out.println(" Stock Group name is  " + stock_GroupName );
            HelperMethods.setAnsiGreen("Stock Group Created Successfully");
        }
    }

    @Test
    public void get_Stock_Group() {
        HelperMethods.setAnsiGreen("Started :- Get Stock Group");
        /**
         * Main test and api call initiated
         */
        SmartResponse response= methodManager.getAPI_with_Assert_Statuscode(null, null, config.createStockGroup() + stock_GroupName);
        if (response.getStatusCode() != HttpStatus.SC_OK){
            HelperMethods.setAnsiRed("Get Stock Group Functionality Failed ");
            Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
            System.out.println(response.getStatusCode());
            System.out.println(response.getJson());
        }
        else {
            Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
            HelperMethods.setAnsiGreen("Get Stock Group Completed Successfully");
        }
    }

    @Test
    public void update_Stock_Group() {
        HelperMethods.setAnsiGreen("Started :- Update Stock Group");

        Map<String,String> body = new HashMap<>();
        body.put("name", "stcokgroup");
        body.put("uniqueName", "stcokgroup");
        body.put("parentStockGroupUniqueName", "");

        /**
         * Main test and api call initiated
         */
        SmartResponse response= methodManager.putAPI_with_Assert_Statuscode(null, null, config.createStockGroup() + stock_GroupName, body);
        if (response.getStatusCode() != HttpStatus.SC_OK){
            HelperMethods.setAnsiRed("Update Stock Group Functionality Failed ");
            Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
            System.out.println(response.getStatusCode());
            System.out.println(response.getJson());
        }
        else {
            Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
            String json = response.getJson();
            JsonPath jp = new JsonPath(json);
            stock_GroupName = jp.get("body.uniqueName");
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
        if (response.getStatusCode() != HttpStatus.SC_OK){
            HelperMethods.setAnsiRed("Get Hierarchical Stock Groups Functionality Failed ");
            Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
            System.out.println(response.getStatusCode());
            System.out.println(response.getJson());
        }
        else {
            Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
            String json = response.getJson();
            JsonPath jp = new JsonPath(json);
            System.out.println(json);
            assertEquals( jp.get("body.results[0].uniqueName"), stock_GroupName);
            HelperMethods.setAnsiGreen("Get Hierarchical Stock Groups Completed Successfully");
        }
    }

    public void delete_Stock_Group(){
        HelperMethods.setAnsiGreen("Started :- Delete Stock Group");
        /**
         * Main test and api call initiated
         */
        SmartResponse response= methodManager.deleteAPI_with_Assert_Statuscode(null, null, config.createStockGroup() + stock_GroupName);
        if (response.getStatusCode() != HttpStatus.SC_OK){
            System.out.println(response.getStatusCode());
            System.out.println(response.getJson());
            HelperMethods.setAnsiRed("Delete Stock Group Functionality Failed ");
            Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
        }
        else {
            Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
            HelperMethods.setAnsiGreen("Stock Group Deleted Successfully");
        }
    }
}