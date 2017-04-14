package com.api.smoke;

import com.apiUtils.MethodManager;
import com.apiUtils.HelperMethods;
import com.apiUtils.JsonUtil;
import com.apiUtils.SmartResponse;
import com.config.UrlConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.model.Ledger;
import com.model.TransactionInput;
import io.restassured.path.json.JsonPath;
import org.apache.http.HttpStatus;
import org.joda.time.LocalDate;
import org.testng.Assert;
import org.testng.annotations.*;

import java.math.BigDecimal;
import java.util.*;


import static com.api.smoke.TaxAPI.Tax_UniqueName;
import static org.aeonbits.owner.ConfigFactory.create;
import static org.testng.Assert.assertEquals;

public class LedgerAPI {

    LocalDate localDate = new LocalDate();

    public static String ledger_UniqueName;

    MethodManager methodManager = new MethodManager();
    UrlConfig config = create(UrlConfig.class);


    @Test
    public void createLedger() throws JsonProcessingException {
        HelperMethods.setAnsiGreen("Started :- Create Ledger ");
        Assert.assertNotNull(Tax_UniqueName);
        List<String> taxes = new ArrayList<>();
        taxes.add(Tax_UniqueName);

        List<TransactionInput> transactions = new ArrayList<>();
        transactions.add(new TransactionInput(BigDecimal.ONE, "sales", "debit"));
        Ledger ledger = new Ledger(transactions, localDate.toString("dd-MM-yyyy"), "sales", taxes);
        String body = JsonUtil.toJsonAsString(ledger);

        /**
         * Main test and api call initiated
         */
        SmartResponse response = methodManager.postAPI_with_Assert_Statuscode(null, null, config.createLedger(), body);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_CREATED);
        System.out.println(response.getJson());
        String json = response.getJson();
        JsonPath jp = new JsonPath(json);
        assertEquals(jp.get("body[0].entryDate"),localDate.toString("dd-MM-yyyy"));
        ledger_UniqueName= jp.get("body[0].uniqueName");
        System.out.println(ledger_UniqueName);

    }


    @Test(dependsOnMethods={"createLedger"})
    public void getLedger(){
        HelperMethods.setAnsiGreen("Started :- Get Ledger ");
        /**
         * Main test and api call initiated
         */
        SmartResponse resp = methodManager.getAPI_with_Assert_Statuscode(null, null,config.getLedger()+ledger_UniqueName);
        System.out.println(resp.getJson());
    }

    @Test(dependsOnMethods={"createLedger"})
    public void updateLedger() throws JsonProcessingException {
        HelperMethods.setAnsiGreen("Started :- Update Ledger ");
        List<String> taxes = new ArrayList<>();
        taxes.add(Tax_UniqueName);
        List<TransactionInput> transactions = new ArrayList<>();
        transactions.add(new TransactionInput(BigDecimal.TEN, "sales", "debit"));
        Ledger ledger = new Ledger(transactions, localDate.toString("dd-MM-yyyy"), "sales", taxes);
        String body = JsonUtil.toJsonAsString(ledger);
        /**
         * Main test and api call initiated
         */
        SmartResponse resp = methodManager.putAPI_with_Assert_Statuscode(null, null,config.updateLedger()+ledger_UniqueName, body);
        System.out.println(resp.getJson());
        String json = resp.getJson();
        JsonPath jp = new JsonPath(json);
        assertEquals(jp.get("body.entryDate"),localDate.toString("dd-MM-yyyy"));
    }


    @Test(dependsOnMethods={"createLedger"})
    public void deleteAllLedger() throws Exception{
        /**
         * Main test and api call initiated
         */
        SmartResponse resp = methodManager.deleteAPI_with_Assert_Statuscode(null, null,config.deleteLedger());
        System.out.println(resp.getJson());
    }
}
