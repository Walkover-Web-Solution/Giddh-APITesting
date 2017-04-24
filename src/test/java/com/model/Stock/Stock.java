package com.model.Stock;

import java.math.BigDecimal;


public class Stock {
    private String name;
    private BigDecimal openingAmount;
    private BigDecimal openingQuantity;
    private String stockUnitCode;
    private PurchaseAccountDetails purchaseAccountDetails;
    private SalesAccountDetails salesAccountDetails;


    public Stock(String stockName, BigDecimal openingAmount, BigDecimal openingQuantity, String stockUnitCode, PurchaseAccountDetails purchaseAccountDetails,
                 SalesAccountDetails salesAccountDetails ){
        this.name = stockName;
        this.openingAmount = openingAmount;
        this.openingQuantity = openingQuantity;
        this.stockUnitCode = stockUnitCode;
        this.purchaseAccountDetails = purchaseAccountDetails;
        this.salesAccountDetails = salesAccountDetails;
    }
}
