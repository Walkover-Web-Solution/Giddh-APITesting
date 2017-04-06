package com.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class Ledger {

    private String description;
    private String tag;
    private String entryDate;
    private String voucherType;
    private Boolean unconfirmedEntry = Boolean.FALSE;
    private Boolean applyApplicableTaxes = Boolean.FALSE;
    private Boolean isInclusiveTax = Boolean.FALSE;
    private Boolean isRecurring = Boolean.FALSE;
    private Boolean isSubscription = Boolean.FALSE;


    //  @JsonIgnore
//  private DurationType durationType;
    private String proratedDate;
    private String recurringFrom = "CREATE_ENTRY";
    private String attachedFile;
    private Set<String> tags;


    private List<TransactionInput> transactions = new ArrayList<>();
    private List<String> taxes;

    //parameters for entry+invoice+paymententry
    private Boolean generateInvoice = Boolean.FALSE;

    public Ledger(List<TransactionInput> transactions, String entryDate, String voucherType, List<String> taxes) {
        this.transactions = transactions;
        this.entryDate = entryDate;
        this.voucherType = voucherType;
        this.taxes= taxes;
    }




//
//  @JsonIgnore
//  public void setDurationType(DurationType durationType) {
//    this.duration = durationType;
//  }
}
