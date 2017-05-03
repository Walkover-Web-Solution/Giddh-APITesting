package com.api.smoke;


import com.apiUtils.HelperMethods;
import com.apiUtils.MethodManager;
import com.apiUtils.SmartResponse;
import com.config.UrlConfig;
import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.*;


import static io.restassured.config.HttpClientConfig.httpClientConfig;
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
        SmartResponse response = methodManager.getAPI_With_Params(null, null, config.getTrialbalance(), "01-04-2017", "31-03-2018", null, true);
        HelperMethods.assertCode("TrialBalance", response.getStatusCode(), HttpStatus.SC_OK, response.getJson());
    }

    @AfterMethod
    public void  setup(){
        RestAssured.config = RestAssured.config().httpClient(httpClientConfig().reuseHttpClientInstance());
        RestAssured.reset();
    }
}
