package com.model;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
public class TaxDetails {

    private String date;
    private Float taxValue;

    public TaxDetails(String taxDate, float taxRate){
        this.date= taxDate;
        this.taxValue=taxRate;
    }
}
