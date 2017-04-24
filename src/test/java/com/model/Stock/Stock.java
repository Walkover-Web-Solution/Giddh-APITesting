package com.model.Stock;

import java.math.BigDecimal;


public class Stock {
    private String staockName;
    private BigDecimal openingAmount;
    private String stockUnitCode;
    private PurchaseAccountDetails purchaseAccountDetails;
    private SalesAccountDetails salesAccountDetails;


    public Stock(String stockName, BigDecimal openingAmount, String stockUnitCode, PurchaseAccountDetails purchaseAccountDetails,
                 SalesAccountDetails salesAccountDetails ){
        this.staockName = stockName;
        this.openingAmount = openingAmount;
        this.stockUnitCode = stockUnitCode;
        this.purchaseAccountDetails = purchaseAccountDetails;
        this.salesAccountDetails = salesAccountDetails;
    }
}
