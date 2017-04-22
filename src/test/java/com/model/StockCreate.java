package com.model;


import java.math.BigDecimal;

public class StockCreate {
    private String staockName;
    private String stockUniqueName;
    private BigDecimal openingAmount;
    private String openingStockUnitName;
    private String purchaseAccountUniqueName;
    private BigDecimal purchaseRate;
    private String salesAccountUniqueName;
    private BigDecimal salesRate;

    public StockCreate (String stockName,String stockUniqueName, BigDecimal openingAmount, String openingStockUnitName, String purchaseAccountUniqueName,
                        BigDecimal purchaseRate, String salesAccountUniqueName, BigDecimal salesRate ){
        this.staockName=stockName;
        this.stockUniqueName=stockUniqueName;
        this.openingAmount=openingAmount;
        this.openingStockUnitName=openingStockUnitName;
        this.purchaseAccountUniqueName=purchaseAccountUniqueName;
        this.purchaseRate=purchaseRate;
        this.salesAccountUniqueName=salesAccountUniqueName;
        this.salesRate=salesRate;
    }
}
