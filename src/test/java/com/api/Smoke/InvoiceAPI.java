package com.api.Smoke;


import com.ApiUtils.ApiManager;
import com.ApiUtils.HelperMethods;
import com.ApiUtils.SmartResponse;
import com.Config.UrlConfig;
import com.model.ManageHeaders;
import com.model.ManageURL;

import java.util.ArrayList;

import static org.aeonbits.owner.ConfigFactory.create;

public class InvoiceAPI {


    ManageHeaders header = new ManageHeaders();
    ApiManager apiManager = new ApiManager();
    ManageURL baseURL = new ManageURL();
    UrlConfig config = create(UrlConfig.class);


    public void createInvoice() throws  Exception{
        HelperMethods.setAnsiGreen("Started :- Create Invoice");


        SmartResponse resp = apiManager.postAPI_with_Assert_Statuscode(config.createLedger(), LedgerAPI.ledger_UniqueName);
    }
}
