package com.api.smoke;


import com.apiUtils.HelperMethods;
import com.apiUtils.MethodManager;
import com.apiUtils.SmartResponse;
import com.config.UrlConfig;
import com.controller.GroupCreate;
import com.controller.StockGroupCreate;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.aeonbits.owner.ConfigFactory.create;

public class StockGroupApi {

    MethodManager methodManager = new MethodManager();
    UrlConfig config = create(UrlConfig.class);
    StockGroupCreate stockGroupCreate = new StockGroupCreate();

    @Test
    public void create_Stock_Group() {
        HelperMethods.setAnsiGreen("Started :- Create Stock Group ");
        /**
         * Main test and api call initiated
         */
        SmartResponse response= stockGroupCreate.StcokGroupCreate(null, null, config.createStcokGroup(),"tgroup", "tgroup", "capital");
        if (response.getStatusCode() != HttpStatus.SC_CREATED){
            System.out.println(response.getStatusCode());
            System.out.println(response.getJson());
        }
        else {
            Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_CREATED);
            HelperMethods.setAnsiGreen("Stock Group Created Successfully ");
        }
    }

}
