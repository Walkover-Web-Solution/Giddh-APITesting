package com.api;

import com.ApiUtils.JsonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.model.Ledger;
import com.model.TransactionInput;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class LedgerAPI {


    @Test
    public void createledger() throws JsonProcessingException {
        List<TransactionInput> transactions = new ArrayList<>();
        transactions.add(new TransactionInput(BigDecimal.ONE, "sales", "debit"));
        Ledger ledger = new Ledger(transactions, "01-04-2016", "sales");
        String body = JsonUtil.toJsonAsString(ledger);
        System.out.println(body);
    }
}
