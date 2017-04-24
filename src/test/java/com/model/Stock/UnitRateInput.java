package com.model.Stock;


import java.math.BigDecimal;

public class UnitRateInput {

    private BigDecimal rate;
    private String stockUnitCode;

    public UnitRateInput(BigDecimal rate, String stockUnitCode){
        this.rate=rate;
        this.stockUnitCode=stockUnitCode;
    }
}
