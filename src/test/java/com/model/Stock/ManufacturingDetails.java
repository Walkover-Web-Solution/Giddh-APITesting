package com.model.Stock;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class ManufacturingDetails {

    private BigDecimal manufacturingQuantity;
    private String manufacturingUnitCode;
    private List<LinkedStocks> linkedStocks = new ArrayList();

    public ManufacturingDetails (BigDecimal manufacturingQuantity, String manufacturingUnitCode, List<LinkedStocks> linkedStocks){
        this.manufacturingQuantity= manufacturingQuantity;
        this.manufacturingUnitCode= manufacturingUnitCode;
        this.linkedStocks=linkedStocks;
    }
}
