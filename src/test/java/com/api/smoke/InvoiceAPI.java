package com.api.smoke;


import com.apiUtils.*;
import com.config.UrlConfig;
import com.model.Invoice;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.apache.http.HttpStatus;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;

import static com.api.smoke.LedgerAPI.ledger_UniqueName;
import static com.api.smoke.LedgerAPI.ledger_UniqueName1;
import static io.restassured.config.HttpClientConfig.httpClientConfig;
import static org.aeonbits.owner.ConfigFactory.*;
import static org.testng.Assert.assertNotNull;

public class InvoiceAPI {

    private MethodManager methodManager = new MethodManager();
    private UrlConfig config = create(UrlConfig.class);

    private static String Invoice_Number;

    @Test
    public void createInvoice() throws  Exception{
        HelperMethods.setAnsiGreen("Started :- Create Invoice");
        assertNotNull(ledger_UniqueName);
        List<String> uniqueNames = new ArrayList<>();
        uniqueNames.add(ledger_UniqueName);
        uniqueNames.add(ledger_UniqueName1);
        Invoice invoice = new Invoice(uniqueNames);
        String body = JsonUtil.toJsonAsString(invoice);
        SmartResponse response = methodManager.postAPI_with_Assert_Statuscode(null, null, config.createInvoice(), body);
        for ( String data : uniqueNames) {
            System.out.println(data);
        }
        HelperMethods.assertCode("Create Invoice", response.getStatusCode(), HttpStatus.SC_CREATED, response.getJson());
    }

    @Test(dependsOnMethods={"createInvoice"})
    public void get_All_Invoices() throws Exception{
        HelperMethods.setAnsiGreen("Started :- Get All Invoices");
        SmartResponse response = methodManager.postAPI_with_Assert_Statuscode1(null, null,config.getAllInvoice());
        if (response.getStatusCode() == HttpStatus.SC_OK){
            String json = response.getJson();
            JsonPath jp = new JsonPath(json);
            Invoice_Number= jp.get("body.results[0].invoiceNumber");
            HelperMethods.setAnsiGreen(Invoice_Number + "Invoice number");
            HelperMethods.setAnsiGreen("Get All Invoice Functionality Completed Successfully ");
        }
        else {
            HelperMethods.setAnsiRed("Get All Invoice Functionality fails with Response Code = " +  response.getStatusCode());
            HelperMethods.setAnsiRed(response.getJson());
            Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
        }
    }

    @Test(dependsOnMethods={"createInvoice"})
    public void deleteInvoice() throws Exception{
        HelperMethods.setAnsiGreen("Started :- Delete Invoice ");
        SmartResponse response = methodManager.deleteAPI_with_Assert_Statuscode(null, null,config.deleteInvoice()+ Invoice_Number);
        HelperMethods.assertCode("Delete Invoice", response.getStatusCode(), HttpStatus.SC_OK, response.getJson());
    }

    @AfterMethod
    public void  setup(){
        RestAssured.config = RestAssured.config().httpClient(httpClientConfig().reuseHttpClientInstance());
        RestAssured.reset();
    }
}
