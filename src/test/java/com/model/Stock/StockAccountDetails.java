package com.model.Stock;


import java.util.ArrayList;
import java.util.List;

public class StockAccountDetails {

    private String accountUniqueName;
    private List<UnitRateInput> UnitRateInput = new ArrayList<>();

    public StockAccountDetails(String accountUniqueName, List<UnitRateInput> UnitRateInput ){
        this.accountUniqueName=accountUniqueName;
        this.UnitRateInput=UnitRateInput;
    }

}
