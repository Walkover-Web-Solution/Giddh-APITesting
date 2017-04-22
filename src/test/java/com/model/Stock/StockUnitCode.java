package com.model.Stock;


import java.math.BigDecimal;

public class StockUnitCode {

    private BigDecimal rate;
    private String stockUnitCode;

    public StockUnitCode (BigDecimal rate, String stockUnitCode){
        this.rate=rate;
        this.stockUnitCode=stockUnitCode;
    }
}
