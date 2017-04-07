package com.api.Smoke;


import com.ApiUtils.MethodManager;
import com.ApiUtils.HelperMethods;
import com.ApiUtils.JsonUtil;
import com.ApiUtils.SmartResponse;
import com.Config.UrlConfig;
import com.model.Invoice;
import io.restassured.path.json.JsonPath;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;

import static org.aeonbits.owner.ConfigFactory.*;

public class InvoiceAPI {

    MethodManager methodManager = new MethodManager();
    UrlConfig config = create(UrlConfig.class);

    public static String Invoice_Number;


    @Test
    public void createInvoice() throws  Exception{
        HelperMethods.setAnsiGreen("Started :- Create Invoice");
        List<String> uniqueNames = new ArrayList<>();
        uniqueNames.add(LedgerAPI.ledger_UniqueName);
        System.out.println(uniqueNames + "in invoice ");
        Invoice invoice = new Invoice(uniqueNames);
        String body = JsonUtil.toJsonAsString(invoice);
        //System.out.println(invoice);
        SmartResponse response = methodManager.postAPI_with_Assert_Statuscode(null, null, config.createInvoice(), body);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
        System.out.println(response.getJson());
        for ( String data : uniqueNames) {
            System.out.println(data);
        }
    }

    @Test(dependsOnMethods={"createInvoice"})
    public void get_All_Invoices() throws Exception{
        HelperMethods.setAnsiGreen("Started :- Get All Invoices");
        SmartResponse resp = methodManager.postAPI_with_Assert_Statuscode1(null, null,config.getAllInvoice());
        System.out.println(resp.getJson());
        String json = resp.getJson();
        JsonPath jp = new JsonPath(json);
        Invoice_Number= jp.get("body.results[0].invoiceNumber");
        System.out.println(Invoice_Number + "Invoice number");
    }

    @Test(dependsOnMethods={"createInvoice"})
    public void deleteInvoice() throws Exception{
        HelperMethods.setAnsiGreen("Started :- Delete Invoice ");
        SmartResponse resp = methodManager.deleteAPI_with_Assert_Statuscode(null, null,config.deleteInvoice()+ Invoice_Number);
        System.out.println(resp.getJson());
    }
}
