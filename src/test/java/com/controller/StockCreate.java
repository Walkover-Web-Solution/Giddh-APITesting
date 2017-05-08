package com.controller;


import com.apiUtils.HelperMethods;
import com.apiUtils.JsonUtil;
import com.apiUtils.MethodManager;
import com.apiUtils.SmartResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.model.Stock.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public class StockCreate {
    MethodManager methodManager = new MethodManager();
    public SmartResponse StockCreate (String auth , String type, String URL, String salesStockUnitCode, BigDecimal saleValue,String purchaseStockUnitCode,
                                      BigDecimal purchaseValue, String salesAccountUniqueName, String purchaseAccountUniqueName, String stockName,
                                      String stockUniqueCode, BigDecimal openingAmount, BigDecimal openingQty, BigDecimal manufacturingQuantity,
                                      String manufacturingUnitCode,String stockUniqueName, BigDecimal quantity,String stockUnitCode)
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
        List<LinkedStocks> linkedStocks = new ArrayList<>();
        linkedStocks.add(new LinkedStocks(stockUniqueName, quantity, stockUnitCode));
        ManufacturingDetails manufacturingDetails = new ManufacturingDetails(manufacturingQuantity,manufacturingUnitCode,linkedStocks);
        Stock stock = new Stock(stockName, openingAmount, openingQty, stockUniqueCode, purchaseAccountDetails, salesAccountDetails,manufacturingDetails);
        String body = JsonUtil.toJsonAsString(stock);
        HelperMethods.setAnsiRed(body);

        /**
         * Main test and api call initiated
         */
        SmartResponse resp = methodManager.postAPI_with_Assert_Statuscode(auth, type, URL, body);
        return  resp;
    }
}

