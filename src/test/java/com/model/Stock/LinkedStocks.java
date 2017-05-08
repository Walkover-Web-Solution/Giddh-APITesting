package com.model.Stock;

import java.math.BigDecimal;

public class LinkedStocks {
    private String stockUniqueName;
    private BigDecimal quantity;
    private String stockUnitCode;

    public LinkedStocks(String stockUniqueName, BigDecimal quantity, String stockUnitCode){
        this.stockUniqueName = stockUniqueName;
        this.quantity = quantity;
        this.stockUnitCode = stockUnitCode;
    }

}
