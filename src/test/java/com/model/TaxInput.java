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
    private String taxType;

    private List<TaxDetails> taxDetail = new ArrayList<>();
    private List<TaxAccount> accounts = new ArrayList<>();


    public TaxInput(List<TaxDetails> taxDetail, String taxNumber, String name, String taxType, String duration, int taxFileDate, List<TaxAccount> accounts){
        this.taxNumber = taxNumber;
        this.name= name;
        this.taxType= taxType;
        this.duration=duration;
        this.taxFileDate=taxFileDate;
        this.taxDetail= taxDetail;
        this.accounts=accounts;
    }

}
