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
    int count = 0;

    MethodManager methodManager = new MethodManager();
    UrlConfig config = create(UrlConfig.class);

    @DataProvider
    private Object[][] getLedgerData(){
        Object[][] amountValue = new  Object[1][2];
        amountValue[0][0]= BigDecimal.ONE;
        amountValue[1][0]= new BigDecimal(10.3);
        return amountValue;
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
            count = count + 1;
            assertEquals(jp.get("body[0].entryDate"),localDate.toString("dd-MM-yyyy"));
            if (count == 1){

                ledger_UniqueName = jp.get("body[0].uniqueName");
            }else {
                HelperMethods.setAnsiRed( "Count value is  " + count);
                ledger_UniqueName1 = jp.get("body[0].uniqueName");
            }


            HelperMethods.setAnsiGreen("Ledger uniqueName is " + ledger_UniqueName);
            HelperMethods.setAnsiGreen("Create Ledger Functionality Completed Successfully ");
        }
        else {
            HelperMethods.setAnsiRed("Create Ledger Functionality fails with Response Code = " +  response.getStatusCode());
            HelperMethods.setAnsiRed(response.getJson());
            Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_CREATED);
        }
    }



//    @Test
//    public void createLedger() throws JsonProcessingException {
//        HelperMethods.setAnsiGreen("Started :- Create Ledger ");
//        assertNotNull(Tax_UniqueName);
//        List<String> taxes = new ArrayList<>();
//        taxes.add(Tax_UniqueName);
//
//        List<TransactionInput> transactions = new ArrayList<>();
//        transactions.add(new TransactionInput(BigDecimal.ONE, "sales", "debit"));
//        Ledger ledger = new Ledger(transactions, localDate.toString("dd-MM-yyyy"), "sales", taxes);
//        String body = JsonUtil.toJsonAsString(ledger);
//
//        /**
//         * Main test and api call initiated
//         */
//        SmartResponse response = methodManager.postAPI_with_Assert_Statuscode(null, null, config.createLedger(), body);
//        if (response.getStatusCode() == HttpStatus.SC_CREATED){
//            String json = response.getJson();
//            JsonPath jp = new JsonPath(json);
//            assertEquals(jp.get("body[0].entryDate"),localDate.toString("dd-MM-yyyy"));
//            ledger_UniqueName= jp.get("body[0].uniqueName");
//            HelperMethods.setAnsiGreen("Ledger uniqueName is " + ledger_UniqueName);
//            HelperMethods.setAnsiGreen("Create Ledger Functionality Completed Successfully ");
//        }
//        else {
//            HelperMethods.setAnsiRed("Create Ledger Functionality fails with Response Code = " +  response.getStatusCode());
//            HelperMethods.setAnsiRed(response.getJson());
//            Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_CREATED);
//        }
//    }

    @Test
    public void createLedgerWithDecimal() throws JsonProcessingException {
        HelperMethods.setAnsiGreen("Started :- Create Ledger With Decimal ");
        assertNotNull(Tax_UniqueName);
        List<String> taxes = new ArrayList<>();
        taxes.add(Tax_UniqueName);
        List<TransactionInput> transactions = new ArrayList<>();
        transactions.add(new TransactionInput(new BigDecimal(10.3), "sales", "debit"));
        Ledger ledger = new Ledger(transactions, localDate.toString("dd-MM-yyyy"), "sales", taxes);
        String body = JsonUtil.toJsonAsString(ledger);

        /**
         * Main test and api call initiated
         */
        SmartResponse response = methodManager.postAPI_with_Assert_Statuscode(null, null, config.createLedger(), body);
        if (response.getStatusCode() == HttpStatus.SC_CREATED){
            String json = response.getJson();
            JsonPath jp = new JsonPath(json);
            assertEquals(jp.get("body[0].entryDate"),localDate.toString("dd-MM-yyyy"));
            ledger_UniqueName1 = jp.get("body[0].uniqueName");
            HelperMethods.setAnsiGreen("Ledger uniqueName is " + ledger_UniqueName);
            HelperMethods.setAnsiGreen("Create Ledger With Decimal Functionality Completed Successfully ");
        }
        else {
            HelperMethods.setAnsiRed("Create Ledger Functionality  with Decimal fails with Response Code = " +  response.getStatusCode());
            HelperMethods.setAnsiRed(response.getJson());
            Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_CREATED);
        }
    }

    @Test(dependsOnMethods={"createLedger"})
    public void getLedger(){
        HelperMethods.setAnsiGreen("Started :- Get Ledger ");
        /**
         * Main test and api call initiated
         */
        SmartResponse response = methodManager.getAPI_with_Assert_Statuscode(null, null,config.getLedger()+ledger_UniqueName);
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
