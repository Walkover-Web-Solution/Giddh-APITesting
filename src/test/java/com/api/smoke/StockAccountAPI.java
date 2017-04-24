package com.api.smoke;

import com.apiUtils.*;
import com.controller.*;
import com.fasterxml.jackson.core.JsonProcessingException;

import com.model.Stock.*;
import io.restassured.*;
import org.testng.annotations.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;



public class StockAccountAPI {

    StockCreate stockCreate = new StockCreate();

    @BeforeMethod
    public void prerequiste(){
        //RestAssured.baseURI= "http://"+ co
    }

    @Test
    public void CreateStock() throws JsonProcessingException {
        List<UnitRateInput> salesUnitRateInputs = new ArrayList<>();
        salesUnitRateInputs.add(new UnitRateInput(BigDecimal.valueOf(50),"nos"));
        List<UnitRateInput> purchaseUnitRateInputs = new ArrayList<>();
        purchaseUnitRateInputs.add(new UnitRateInput(BigDecimal.valueOf(25),"nos" ));
        SalesAccountDetails salesAccountDetails = new SalesAccountDetails("sales", salesUnitRateInputs);
        PurchaseAccountDetails purchaseAccountDetails = new PurchaseAccountDetails("purchases", purchaseUnitRateInputs);
        Stock stock = new Stock("sms", BigDecimal.ZERO, "nos", purchaseAccountDetails, salesAccountDetails);
        String body = JsonUtil.toJsonAsString(stock);
        SmartResponse response = stockCreate.StockCreate(null, null,  "gygfyufgu", body);
    }
}
