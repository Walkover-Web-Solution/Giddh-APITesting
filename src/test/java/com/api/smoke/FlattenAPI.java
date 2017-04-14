package com.api.smoke;

import com.apiUtils.*;
import com.config.UrlConfig;
import io.restassured.path.json.JsonPath;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.*;

import static org.aeonbits.owner.ConfigFactory.create;
import static org.testng.Assert.assertEquals;

public class FlattenAPI {


    MethodManager methodManager = new MethodManager();
    UrlConfig config = create(UrlConfig.class);

    @Test(dataProvider = "getData")
    public void flatten_Group_with_Accounts(){

        HelperMethods.setAnsiGreen("Started :- Get flatten group-with-accounts API");

        /**
         * Main test and api call initiated
         */
        SmartResponse response = methodManager.getAPI_With_Params(null, null, config.get_Flatten_Group_With_Accounts(), null, null, null, true);
        String json = response.getJson();
        JsonPath jp = new JsonPath(json);
        //assertEquals(jp.get("body.results[2].groupUniqueName"), "cash");
        assertEquals(jp.get("body.page"), 1);
        assertEquals(jp.get("body.count"), 5);
        assertEquals(jp.get("body.totalPages"), 2);
        assertEquals(jp.get("body.totalItems"), 7);
    }

    @Test(dependsOnMethods={"flatten_Group_with_Accounts"})
    public void flatten_Group_with_Accounts_with_Cash_Search(){
        HelperMethods.setAnsiGreen("Started :- Get flatten group-with-accounts with_Cash_Search");

        /**
         * Main test and api call initiated
         */
        SmartResponse response = methodManager.getAPI_With_Params(null, null, config.get_Flatten_Group_With_Accounts(), null, null, "cash", true);
        String json = response.getJson();
        JsonPath jp = new JsonPath(json);
        if (response.getStatusCode() == HttpStatus.SC_OK){
            assertEquals(jp.get("body.results[0].groupUniqueName"), "cash");
            assertEquals(jp.get("body.page"), 1);
            assertEquals(jp.get("body.count"), 5);
        }
        else {
            HelperMethods.setAnsiRed(response.getJson());
            HelperMethods.setAnsiRed(response.getJson());
            Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
            HelperMethods.setAnsiRed("Get flatten group-with-accounts with_Cash_Search functionality Fails");
        }
    }


    @DataProvider
    public Object[][] getData(){
        Object[][] data = new Object[3][0];
        return  data;
    }

    // @Test
    public void flatten_With_Accounts(){

    }
}
