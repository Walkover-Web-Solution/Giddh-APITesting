package com.api.regression;


import com.apiUtils.MethodManager;
import com.config.UrlConfig;
import com.api.smoke.AccountAPI;
import com.api.smoke.GroupAPI;
import com.api.smoke.LedgerAPI;
import com.model.ManageHeaders;
import com.model.ManageURL;
import org.testng.annotations.*;

import static org.aeonbits.owner.ConfigFactory.create;

public class Company {
    ManageHeaders header = new ManageHeaders();
    MethodManager methodManager = new MethodManager();
    ManageURL baseURL = new ManageURL();
    UrlConfig config = create(UrlConfig.class);
    LedgerAPI ledgerAPI = new LedgerAPI();
    GroupAPI groupAPI = new GroupAPI();
    AccountAPI accountAPI = new AccountAPI();

    int responseCode;

    @BeforeTest
    public void setHeader(){
        header.set_Headers(null, null);
        baseURL.setURL();
    }

    /**
     * Objective to test Shared User is able to get company or not after share company
     */
    @Test
    public void getCompany_shareUser(){

    }
}
