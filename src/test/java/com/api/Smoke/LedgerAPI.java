package com.api.Smoke;

import com.ApiUtils.ApiManager;
import com.ApiUtils.HelperMethods;
import com.ApiUtils.JsonUtil;
import com.ApiUtils.SmartResponse;
import com.Config.UrlConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.model.Ledger;
import com.model.ManageHeaders;
import com.model.ManageURL;
import com.model.TransactionInput;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.aeonbits.owner.ConfigFactory.create;
import static org.testng.Assert.assertEquals;

public class LedgerAPI {

    public static String ledger_UniqueName;

    ApiManager apiManager = new ApiManager();
    ManageURL baseURL = new ManageURL();
    UrlConfig config = create(UrlConfig.class);


    @Test
    public void createLedger() throws JsonProcessingException {

        HelperMethods.setAnsiGreen("Started :- Create Ledger ");

        List<TransactionInput> transactions = new ArrayList<>();
        transactions.add(new TransactionInput(BigDecimal.ONE, "sales", "debit"));
        Ledger ledger = new Ledger(transactions, "01-04-2016", "sales");
        String body = JsonUtil.toJsonAsString(ledger);
        /**
         * Main test and api call initiated
         */

        SmartResponse resp = apiManager.postAPI_with_Assert_Statuscode(config.createLedger(), body);
        System.out.println(resp.getJson());
        String json = resp.getJson();
        JsonPath jp = new JsonPath(json);
        assertEquals("01-04-2016", jp.get("body[0].entryDate"));
        ledger_UniqueName= jp.get("body[0].uniqueName");
        System.out.println(ledger_UniqueName);

    }


    @Test
    public void getLedger(){
        HelperMethods.setAnsiGreen("Started :- Get Ledger ");
        /**
         * Main test and api call initiated
         */
        SmartResponse resp = apiManager.getAPI_with_Assert_Statuscode(config.getLedger()+ledger_UniqueName);
        System.out.println(resp.getJson());
    }

    @Test
    public void updateLedger() throws JsonProcessingException {
        HelperMethods.setAnsiGreen("Started :- Update Ledger ");

        List<TransactionInput> transactions = new ArrayList<>();
        transactions.add(new TransactionInput(BigDecimal.ONE, "sales", "debit"));
        Ledger ledger = new Ledger(transactions, "02-04-2016", "sales");
        String body = JsonUtil.toJsonAsString(ledger);
        /**
         * Main test and api call initiated
         */
        SmartResponse resp = apiManager.putAPI_with_Assert_Statuscode(config.updateLedger()+ledger_UniqueName, body);
        System.out.println(resp.getJson());
        String json = resp.getJson();
        JsonPath jp = new JsonPath(json);
        assertEquals(jp.get("body.entryDate"),"02-04-2016" );
    }


    @Test
    public void deleteAllLedger() throws Exception{
        /**
         * Main test and api call initiated
         */
        SmartResponse resp = apiManager.deleteAPI_with_Assert_Statuscode(config.deleteLedger());
        System.out.println(resp.getJson());
    }
}