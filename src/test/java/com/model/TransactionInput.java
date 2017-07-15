package com.model;



import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
@NoArgsConstructor
public class TransactionInput {


    private BigDecimal amount;

    private String particular;

    private String type;

    public TransactionInput(BigDecimal amount, String particular, String type) {
        this.amount = amount;
        this.particular = particular;
        this.type = type;
    }
    // private StockTx inventory = new StockTx();

//    public TransactionInput(BigDecimal amount, String particularUniqueName, String type) {
//        this.amount = amount;
//        this.particular = new Particular(particularUniqueName);
//        this.type = type;
//    }
//
//    public TransactionInput(BigDecimal amount, String particularName, String particularUniqueName, BalanceType type) {
//        this.amount = amount;
//        this.particular = new Particular(particularUniqueName, particularName);
//        this.type = type;
//    }
//
//    public TransactionInput(BigDecimal amount, Particular particular, BalanceType type) {
//        this.amount = amount;
//        this.particular = particular;
//        this.type = type;
//    }
//
//    public String getType() {
//        return type == null ? null : type.name();
//    }

//    @JsonIgnore
//    public BalanceType getBalanceType() {
//        return this.type;
//    }
//
//    @JsonProperty(value = "type")
//    public void setType(String type) {
//        this.type = BalanceType.ofName(type);
//    }
//
//    @JsonIgnore
//    public void setType(BalanceType str) {
//        this.type = str;
//    }
}