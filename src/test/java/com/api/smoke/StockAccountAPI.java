package com.api.smoke;

import com.apiUtils.*;
import com.config.UrlConfig;
import com.controller.*;
import com.fasterxml.jackson.core.JsonProcessingException;

import com.model.Stock.*;
import io.restassured.*;
import io.restassured.path.json.JsonPath;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.config.HttpClientConfig.httpClientConfig;
import static org.aeonbits.owner.ConfigFactory.create;


public class StockAccountAPI {

    StockCreate stockCreate = new StockCreate();
    UrlConfig config = create(UrlConfig.class);

    @DataProvider
    public Object[][] stock(){
        Object[][] createStock = new  Object[1][13];
        createStock [0][0]= null;
        createStock [0][1]= null;
        createStock [0][2]= config.createStock();
        createStock [0][3]= "nos";
        createStock [0][4]= BigDecimal.valueOf(50);
        createStock [0][5]= "nos";
        createStock [0][6]= BigDecimal.valueOf(25);
        createStock [0][7]= "sales";
        createStock [0][8]= "purchases";
        createStock [0][9]= "bulksms";
        createStock [0][10]= "nos";
        createStock [0][11]= BigDecimal.ZERO;
        createStock [0][12]= BigDecimal.ZERO;
        return  createStock;
    }

    @Test(dataProvider = "stock")
    public void createStock(String auth, String type, String URL, String salesStockUnitCode, BigDecimal saleValue,String purchaseStockUnitCode,
                            BigDecimal purchaseValue, String salesAccountUniqueName, String purchaseAccountUniqueName, String stockName,
                            String stockUniqueCode, BigDecimal openingAmount, BigDecimal openingQty) throws JsonProcessingException {

        HelperMethods.setAnsiGreen("Started :- Create Stock ");

        /**
         * Main test and api call initiated
         */

        SmartResponse response = stockCreate.StockCreate(auth, type, URL, salesStockUnitCode, saleValue, purchaseStockUnitCode,
                purchaseValue,  salesAccountUniqueName, purchaseAccountUniqueName,  stockName,  stockUniqueCode,  openingAmount,
                openingQty);

        HelperMethods.assertCode("Create Stock", response.getStatusCode(), HttpStatus.SC_CREATED, response.getJson());
    }

    @AfterMethod
    public void setup(){
        RestAssured.config = RestAssured.config().httpClient(httpClientConfig().reuseHttpClientInstance());
        RestAssured.reset();
    }
}
