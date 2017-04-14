package com.api.smoke;

import com.apiUtils.*;
import com.config.UrlConfig;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.*;

import static org.aeonbits.owner.ConfigFactory.create;
import static org.testng.Assert.assertEquals;

public class FlattenAPI {


    MethodManager methodManager = new MethodManager();
    UrlConfig config = create(UrlConfig.class);

    @Test
    public void flatten_Group_with_Accounts(){

        HelperMethods.setAnsiGreen("Started :- Get flatten group-with-accounts API");

        /**
         * Main test and api call initiated
         */

        SmartResponse response = methodManager.getAPI_With_Params(null, null, config.get_Flatten_Group_With_Accounts(), null, null, null, false);
        System.out.println(response.getJson());
        System.out.println(response.getStatusCode());
        String json = response.getJson();
        JsonPath jp = new JsonPath(json);
        assertEquals(jp.get("body.results[2].groupUniqueName"), "cash");
        assertEquals(jp.get("body.page"), 1);
        assertEquals(jp.get("body.count"), 5);
    }

}
