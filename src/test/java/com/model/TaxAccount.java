package com.model;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class TaxAccount {

    private String uniqueName;

    public TaxAccount(String uniqueName){
        this.uniqueName=uniqueName;
    }
}
