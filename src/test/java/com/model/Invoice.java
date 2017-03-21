package com.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class Invoice {

    private String uniqueNames[];

    public Invoice(String uniqueNames[]) {
        this.uniqueNames = new String[uniqueNames.length];
        for (int i = 0; i < uniqueNames.length; i++) {
            this.uniqueNames[i] = uniqueNames[i];
        }
    }
}
