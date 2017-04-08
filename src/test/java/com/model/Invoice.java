package com.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
public class Invoice {

    private List<String> uniqueNames;


    public Invoice(List<String> uniqueNames) {
        this.uniqueNames = uniqueNames;
    }
}
