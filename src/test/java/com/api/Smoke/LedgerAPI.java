package com.api.Smoke;

import com.ApiUtils.MethodManager;
import com.ApiUtils.HelperMethods;
import com.ApiUtils.JsonUtil;
import com.ApiUtils.SmartResponse;
import com.Config.UrlConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.model.Ledger;
import com.model.TransactionInput;
import io.restassured.path.json.JsonPath;
import org.apache.http.HttpStatus;
import org.joda.time.LocalDate;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.List;

import static org.aeonbits.owner.ConfigFactory.create;
import static org.testng.Assert.assertEquals;

public class LedgerAPI {

    LocalDate ld = new LocalDate();

    public static String ledger_UniqueName;

    MethodManager methodManager = new MethodManager();
    UrlConfig config = create(UrlConfig.class);


    @Test
    public void createLedger() throws JsonProcessingException {
        LocalDate ld = new LocalDate();
        HelperMethods.setAnsiGreen("Started :- Create Ledger ");
        //System.out.println( ld.toString("dd-MM-yyyy"));

        List<TransactionInput> transactions = new ArrayList<>();
        transactions.add(new TransactionInput(BigDecimal.ONE, "sales", "debit"));
        Ledger ledger = new Ledger(transactions, ld.toString("dd-MM-yyyy"), "sales");
        String body = JsonUtil.toJsonAsString(ledger);
        /**
         * Main test and api call initiated
         */

        SmartResponse response = methodManager.postAPI_with_Assert_Statuscode(config.createLedger(), body);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_CREATED);
        System.out.println(response.getJson());
        String json = response.getJson();
        JsonPath jp = new JsonPath(json);
        assertEquals(jp.get("body[0].entryDate"),ld.toString("dd-MM-yyyy"));
        ledger_UniqueName= jp.get("body[0].uniqueName");
        System.out.println(ledger_UniqueName);

    }


    @Test(dependsOnMethods={"createLedger"})
    public void getLedger(){
        HelperMethods.setAnsiGreen("Started :- Get Ledger ");
        /**
         * Main test and api call initiated
         */
        SmartResponse resp = methodManager.getAPI_with_Assert_Statuscode(config.getLedger()+ledger_UniqueName);
        System.out.println(resp.getJson());
    }

    @Test(dependsOnMethods={"createLedger"})
    public void updateLedger() throws JsonProcessingException {
        HelperMethods.setAnsiGreen("Started :- Update Ledger ");

        List<TransactionInput> transactions = new ArrayList<>();
        transactions.add(new TransactionInput(BigDecimal.TEN, "sales", "debit"));
        Ledger ledger = new Ledger(transactions, ld.toString("dd-MM-yyyy"), "sales");
        String body = JsonUtil.toJsonAsString(ledger);
        /**
         * Main test and api call initiated
         */
        SmartResponse resp = methodManager.putAPI_with_Assert_Statuscode(config.updateLedger()+ledger_UniqueName, body);
        System.out.println(resp.getJson());
        String json = resp.getJson();
        JsonPath jp = new JsonPath(json);
        assertEquals(jp.get("body.entryDate"),ld.toString("dd-MM-yyyy"));
    }


    @Test(dependsOnMethods={"createLedger"})
    public void deleteAllLedger() throws Exception{
        /**
         * Main test and api call initiated
         */
        SmartResponse resp = methodManager.deleteAPI_with_Assert_Statuscode(config.deleteLedger());
        System.out.println(resp.getJson());
    }
}
