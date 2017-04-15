package com.api.smoke;


import com.apiUtils.HelperMethods;
import com.apiUtils.MethodManager;
import com.apiUtils.SmartResponse;
import com.config.UrlConfig;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.*;


import static org.aeonbits.owner.ConfigFactory.create;

@Test
public class TrialBalanceAPI {

    MethodManager methodManager = new MethodManager();
    UrlConfig config = create(UrlConfig.class);

    public void get_TrialBalance() {
        HelperMethods.setAnsiGreen("Started :- Get Trial Balance ");

        /**
         * Main test and api call initiated
         */
        SmartResponse resp = methodManager.getAPI_With_Params(null, null, config.getTrialbalance(), "01-04-2017", "31-03-2018", null, true);

        if (resp.getStatusCode() != HttpStatus.SC_OK) {
            HelperMethods.setAnsiRed("TrialBalance Functionality fails with Response Code = " + resp.getStatusCode());
            HelperMethods.setAnsiRed(resp.getJson());
            HelperMethods.setAnsiRed("Get Trial Balance Functionality Fails");
        } else {
            Assert.assertEquals(resp.getStatusCode(), HttpStatus.SC_OK);
            HelperMethods.setAnsiGreen("Get Trial Balance Functionality Completed Successfully");
        }
    }
}
