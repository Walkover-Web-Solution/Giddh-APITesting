package com.api.Regression;


import com.ApiUtils.ApiManager;
import com.Config.UrlConfig;
import com.api.Smoke.AccountAPI;
import com.api.Smoke.GroupAPI;
import com.api.Smoke.LedgerAPI;
import com.model.ManageHeaders;
import com.model.ManageURL;
import org.testng.annotations.*;

import static org.aeonbits.owner.ConfigFactory.create;

public class Company {
    ManageHeaders header = new ManageHeaders();
    ApiManager apiManager = new ApiManager();
    ManageURL baseURL = new ManageURL();
    UrlConfig config = create(UrlConfig.class);
    LedgerAPI ledgerAPI = new LedgerAPI();
    GroupAPI groupAPI = new GroupAPI();
    AccountAPI accountAPI = new AccountAPI();

    int responseCode;

    @BeforeTest
    public void setHeader(){
        header.set_Headers();
        baseURL.setURL();
    }

    /**
     * Objective to test Shared User is able to get company or not after share company
     */
    @Test
    public void getCompany_shareUser(){

    }
}
