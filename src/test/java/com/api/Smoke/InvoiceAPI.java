package com.api.Smoke;


import com.ApiUtils.ApiManager;
import com.ApiUtils.HelperMethods;
import com.ApiUtils.SmartResponse;
import com.Config.UrlConfig;
import com.model.Invoice;
import com.model.ManageHeaders;
import com.model.ManageURL;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static org.aeonbits.owner.ConfigFactory.create;

public class InvoiceAPI {


    ManageHeaders header = new ManageHeaders();
    ApiManager apiManager = new ApiManager();
    ManageURL baseURL = new ManageURL();
    UrlConfig config = create(UrlConfig.class);

    @Test
    public void createInvoice() throws  Exception{
        HelperMethods.setAnsiGreen("Started :- Create Invoice");
        String uniqueName[]={LedgerAPI.ledger_UniqueName};
        //System.out.println(uniqueName + "in invoice ");
        Invoice invoice = new Invoice(uniqueName);
        System.out.println(invoice);
        SmartResponse resp = apiManager.postAPI_with_Assert_Statuscode(config.createInvoice(), invoice);
        System.out.println(resp.getJson());
    }
}
