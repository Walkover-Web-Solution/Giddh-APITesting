package com.model;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
public class LedgerTaxInput {

    private String taxes;

    public LedgerTaxInput(String taxes) {
        this.taxes = taxes;
    }
}
