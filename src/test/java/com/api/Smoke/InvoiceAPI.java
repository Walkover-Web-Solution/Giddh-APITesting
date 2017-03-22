package com.api.Smoke;


import com.ApiUtils.ApiManager;
import com.ApiUtils.HelperMethods;
import com.ApiUtils.JsonUtil;
import com.ApiUtils.SmartResponse;
import com.Config.UrlConfig;
import com.model.Invoice;
import com.model.ManageHeaders;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;

import static org.aeonbits.owner.ConfigFactory.*;

public class InvoiceAPI {

    ApiManager apiManager = new ApiManager();
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
        SmartResponse resp = apiManager.postAPI_with_Assert_Statuscode(config.createInvoice(), body);
        System.out.println(resp.getJson());
        for ( String data : uniqueNames) {
            System.out.println(data);
        }
    }

    @Test
    public void get_All_Invoices() throws Exception{
        HelperMethods.setAnsiGreen("Started :- Get All Invoices");
        SmartResponse resp = apiManager.postAPI_with_Assert_Statuscode1(config.getAllInvoice());
        System.out.println(resp.getJson());
        String json = resp.getJson();
        JsonPath jp = new JsonPath(json);
        Invoice_Number= jp.get("body.results[0].invoiceNumber");
        System.out.println(Invoice_Number + "Invoice number");
    }

    @Test
    public void deleteInvoice() throws Exception{
        HelperMethods.setAnsiGreen("Started :- Delete Invoice ");
        SmartResponse resp = apiManager.deleteAPI_with_Assert_Statuscode(config.deleteInvoice()+ Invoice_Number);
        System.out.println(resp.getJson());
    }
}
