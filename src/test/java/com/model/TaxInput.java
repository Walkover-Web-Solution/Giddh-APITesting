package com.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class TaxInput {

    private String taxNumber;
    private String name;
    private String duration;
    private int taxFileDate;

    private List<TaxDetails> taxDetail = new ArrayList<>();
    private TaxAccount account;


    public TaxInput(List<TaxDetails> taxDetail, String taxNumber, String name, String duration, int taxFileDate, TaxAccount account){
        this.taxNumber = taxNumber;
        this.name= name;
        this.duration=duration;
        this.taxFileDate=taxFileDate;
        this.taxDetail= taxDetail;
        this.account=account;
    }

}
