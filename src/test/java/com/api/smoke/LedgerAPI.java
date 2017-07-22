package com.api.smoke;


import com.apiUtils.*;
import com.config.UrlConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.model.Ledger;
import com.model.TransactionInput;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.apache.http.HttpStatus;
import org.joda.time.LocalDate;
import org.testng.Assert;
import org.testng.annotations.*;

import java.math.BigDecimal;
import java.util.*;


import static com.api.smoke.TaxAPI.Tax_UniqueName;
import static io.restassured.config.HttpClientConfig.httpClientConfig;
import static org.aeonbits.owner.ConfigFactory.create;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class LedgerAPI {

    private LocalDate localDate = new LocalDate();

    public static String ledger_UniqueName;
    public static String ledger_UniqueName1;
    private int count = 0;

    MethodManager methodManager = new MethodManager();
    UrlConfig config = create(UrlConfig.class);


    @DataProvider
    private Object[][] getLedgerData(){
        Object[][] amountValue = new  Object[2][1];
        amountValue[0][0]= BigDecimal.ONE;
        amountValue[1][0]= new BigDecimal(10.3).setScale(2,BigDecimal.ROUND_HALF_EVEN);
        return amountValue;
    }

    @DataProvider
    private Object[][] getLedgerUniqueName(){
        Object[][] ledgerName = new  Object[2][1];
        ledgerName[0][0]= ledger_UniqueName;
        ledgerName[1][0]= ledger_UniqueName1;
        return ledgerName;
    }

    @Test(dataProvider = "getLedgerData")
    public void createLedger(BigDecimal amount) throws JsonProcessingException {
        HelperMethods.setAnsiGreen("Started :- Create Ledger ");
        assertNotNull(Tax_UniqueName);
        List<String> taxes = new ArrayList<>();
        taxes.add(Tax_UniqueName);

        List<TransactionInput> transactions = new ArrayList<>();
        transactions.add(new TransactionInput(amount, "sales", "debit"));
        Ledger ledger = new Ledger(transactions, localDate.toString("dd-MM-yyyy"), "sales", taxes);
        String body = JsonUtil.toJsonAsString(ledger);

        /**
         * Main test and api call initiated
         */
        SmartResponse response = methodManager.postAPI_with_Assert_Statuscode(null, null, config.createLedger(), body);
        if (response.getStatusCode() == HttpStatus.SC_CREATED){
            String json = response.getJson();
            JsonPath jp = new JsonPath(json);
            count ++;
            assertEquals(jp.get("body[0].entryDate"),localDate.toString("dd-MM-yyyy"));
            if (count == 1){
                ledger_UniqueName = jp.get("body[0].uniqueName");
                HelperMethods.setAnsiGreen("First Ledger uniqueName is " + ledger_UniqueName);
            } else {
                ledger_UniqueName1 = jp.get("body[0].uniqueName");
                HelperMethods.setAnsiGreen("Second Ledger uniqueName is " + ledger_UniqueName1);
            }
            HelperMethods.setAnsiGreen("Create Ledger Functionality Completed Successfully ");
        }
        else {
            HelperMethods.setAnsiRed("Create Ledger Functionality fails with Response Code = " +  response.getStatusCode());
            HelperMethods.setAnsiRed(response.getJson());
            Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_CREATED);
        }
    }

    @Test(dataProvider = "getLedgerUniqueName", dependsOnMethods={"createLedger"})
    public void getLedger(String uniqueName){
        HelperMethods.setAnsiGreen("Started :- Get Ledger ");
        /**
         * Main test and api call initiated
         */
        SmartResponse response = methodManager.getAPI_with_Assert_Statuscode(null, null,config.getLedger()+uniqueName);
        HelperMethods.assertCode("Get Ledger", response.getStatusCode(), HttpStatus.SC_OK, response.getJson());
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
        SmartResponse response = methodManager.putAPI_with_Assert_Statuscode(null, null,config.updateLedger()+ledger_UniqueName, body);
        if (response.getStatusCode() == HttpStatus.SC_OK){
            String json = response.getJson();
            JsonPath jp = new JsonPath(json);
            assertEquals(jp.get("body.entryDate"),localDate.toString("dd-MM-yyyy"));
            HelperMethods.setAnsiGreen("Update Ledger Functionality Completed Successfully ");
        }
        else {
            HelperMethods.setAnsiRed("Update Ledger Functionality fails with Response Code = " +  response.getStatusCode());
            HelperMethods.setAnsiRed(response.getJson());
            Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
        }
    }

    @Test(dependsOnMethods={"createLedger"})
    public void deleteAllLedger() throws Exception{
        /**
         * Main test and api call initiated
         */
        SmartResponse response = methodManager.deleteAPI_with_Assert_Statuscode(null, null,config.deleteLedger());
        HelperMethods.assertCode("Delete All Ledger", response.getStatusCode(), HttpStatus.SC_OK, response.getJson());
    }

    @AfterMethod
    public void  setup(){
        RestAssured.config = RestAssured.config().httpClient(httpClientConfig().reuseHttpClientInstance());
        RestAssured.reset();
    }
}
