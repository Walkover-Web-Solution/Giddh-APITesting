package com.model;

import lombok.*;

@Getter
@Setter
public class TaxInput {

    private String taxNumber;
    private String name;
    private String duration;
    private int taxFileDate;

    public TaxInput(String taxNumber, String name, String duration, int taxFileDate){
        this.taxNumber = taxNumber;
        this.name= name;
        this.duration=duration;
        this.taxFileDate=taxFileDate;
    }

}
