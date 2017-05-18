package com.api.regression;


import com.apiUtils.MethodManager;
import com.apiUtils.SmartResponse;
import com.config.UrlConfig;
import com.api.smoke.AccountAPI;
import com.api.smoke.GroupAPI;
import com.api.smoke.LedgerAPI;
import com.controller.CompanyCreate;
import com.model.ManageHeaders;
import com.model.ManageURL;
import org.testng.annotations.*;

import static org.aeonbits.owner.ConfigFactory.create;

public class Company {
    ManageHeaders header = new ManageHeaders();
    MethodManager methodManager = new MethodManager();
    ManageURL baseURL = new ManageURL();
    CompanyCreate create = new CompanyCreate();
    UrlConfig config = create(UrlConfig.class);
    LedgerAPI ledgerAPI = new LedgerAPI();
    GroupAPI groupAPI = new GroupAPI();
    AccountAPI accountAPI = new AccountAPI();

    int responseCode;

    public String getRandomCompanyName(){
        String chars = "abcdefghijklmnopqrstuvwxyz";
        String randomString = "";
        int length = chars.length();
        for (int i = 0; i < 8; i++) {
            randomString += chars.split("")[(int) (Math.random() * (length - 1))];
        }
        return randomString;
    }

    @BeforeTest
    public void setHeader(){
        header.set_Headers(null, null);
        baseURL.setURL();
    }


    @Test
    public void createCompany(){
        SmartResponse response = create.companyCreate( null, config.mainURL(), "AutomationCompany",   "automationcompany");

    }
    /**
     * Objective to test Shared User is able to get company or not after share company
     */
    @Test
    public void getCompany_shareUser(){

    }

    @Test(dataProvider = "getData")
    public void dataProviderTest(String a, String b){
        System.out.println(a + b);
    }

    @DataProvider
    public Object[][] getData(){
        Object[][] data = new Object[2][2];
        data[0][0] = "abcd";
        data[0][1]= "sam";
        data[1][0] = "vsgvhg";
        data[1][1]= "bkbkbki";
        return  data;
    }
}
