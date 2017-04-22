package com.model.Stock;


import com.model.TaxDetails;

import java.math.BigDecimal;


public class StockCreate {
    private String staockName;
    private String stockUniqueName;
    private BigDecimal openingAmount;
    private String stockUnitCode;


    public StockCreate (String stockName,String stockUniqueName, BigDecimal openingAmount, String stockUnitCode, String purchaseAccountUniqueName,
                        BigDecimal purchaseRate, String salesAccountUniqueName, BigDecimal salesRate ){
        this.staockName=stockName;
        this.stockUniqueName=stockUniqueName;
        this.openingAmount=openingAmount;
        this.stockUnitCode=stockUnitCode;
    }
}
