package com.controller;


import com.apiUtils.JsonUtil;
import com.apiUtils.MethodManager;
import com.apiUtils.SmartResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.model.Stock.PurchaseAccountDetails;
import com.model.Stock.SalesAccountDetails;
import com.model.Stock.Stock;
import com.model.Stock.UnitRateInput;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class StockCreate {
    MethodManager methodManager = new MethodManager();
    public SmartResponse StockCreate (String auth , String type, String URL, String salesStockUnitCode, BigDecimal saleValue,String purchaseStockUnitCode,
                                      BigDecimal purchaseValue, String salesAccountUniqueName,
                                      String purchaseAccountUniqueName, String stockName, String stockUniqueCode, BigDecimal openingAmount, BigDecimal openingQty)
            throws JsonProcessingException {

        if (salesAccountUniqueName == null ){
            salesAccountUniqueName = "sales";
        }

        if (purchaseAccountUniqueName == null ){
            purchaseAccountUniqueName = "purchases";
        }

        List<UnitRateInput> salesUnitRateInputs = new ArrayList<>();
        salesUnitRateInputs.add(new UnitRateInput(saleValue,salesStockUnitCode));
        List<UnitRateInput> purchaseUnitRateInputs = new ArrayList<>();
        purchaseUnitRateInputs.add(new UnitRateInput(purchaseValue,purchaseStockUnitCode ));
        SalesAccountDetails salesAccountDetails = new SalesAccountDetails(salesAccountUniqueName, salesUnitRateInputs);
        PurchaseAccountDetails purchaseAccountDetails = new PurchaseAccountDetails(purchaseAccountUniqueName, purchaseUnitRateInputs);
        Stock stock = new Stock(stockName, openingAmount, openingQty, stockUniqueCode, purchaseAccountDetails, salesAccountDetails);
        String body = JsonUtil.toJsonAsString(stock);

        /**
         * Main test and api call initiated
         */
        SmartResponse resp = methodManager.postAPI_with_Assert_Statuscode(auth, type, URL, body);
        return  resp;
    }
}

