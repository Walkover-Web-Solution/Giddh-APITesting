package com.model;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
public class TaxDetails {

    private String taxDate;
    private Float value;

    public TaxDetails(String taxDate, float taxRate){
        this.taxDate= taxDate;
        this.value=taxRate;
    }
}
