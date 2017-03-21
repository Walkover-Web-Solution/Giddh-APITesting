package com.api.Smoke;


import com.ApiUtils.ApiManager;
import com.ApiUtils.HelperMethods;
import com.ApiUtils.JsonUtil;
import com.ApiUtils.SmartResponse;
import com.Config.UrlConfig;
import com.model.Invoice;
import com.model.ManageHeaders;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;

import static org.aeonbits.owner.ConfigFactory.*;

public class InvoiceAPI {


    ManageHeaders header = new ManageHeaders();
    ApiManager apiManager = new ApiManager();
    UrlConfig config = create(UrlConfig.class);

    @Test
    public void createInvoice() throws  Exception{
        HelperMethods.setAnsiGreen("Started :- Create Invoice");
        List<String> uniqueNames = new ArrayList<>();
        uniqueNames.add(LedgerAPI.ledger_UniqueName);
        System.out.println(uniqueNames + "in invoice ");
        Invoice invoice = new Invoice(uniqueNames);
        String body = JsonUtil.toJsonAsString(invoice);
        System.out.println(invoice);
        SmartResponse resp = apiManager.postAPI_with_Assert_Statuscode(config.createInvoice(), body);
        System.out.println(resp.getJson());
        for ( String data : uniqueNames) {
            System.out.println(data);
        }
    }
}
