package com.api.smoke;


import com.apiUtils.MethodManager;
import com.apiUtils.SmartResponse;
import com.config.UrlConfig;
import org.testng.annotations.*;


import static org.aeonbits.owner.ConfigFactory.create;

@Test
public class TrialBalanceAPI {

    MethodManager methodManager = new MethodManager();
    UrlConfig config = create(UrlConfig.class);

    public void  get_TrialBalance() {

        /**
         * Main test and api call initiated
         */

        SmartResponse resp = methodManager.getAPI_With_Params(null, null, config.getTrialbalance(), 01 - 04 - 2018, 31 - 03 - 2019, null, true);
        // System.out.println(resp.getStatusCode());
        System.out.println(resp.getJson());
    }

}
