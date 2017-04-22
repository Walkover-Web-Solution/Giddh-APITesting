package com.model.Stock;

import java.util.ArrayList;
import java.util.List;

public class SalesAccountDetails {
    private String accountUniqueName;
    private List<UnitRateInput> UnitRateInput = new ArrayList<>();

    public SalesAccountDetails(String accountUniqueName, List<UnitRateInput> UnitRateInput ){
        this.accountUniqueName=accountUniqueName;
        this.UnitRateInput=UnitRateInput;
    }
}
