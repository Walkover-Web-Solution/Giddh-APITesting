package com.controller;



import com.apiUtils.*;
import com.config.UrlConfig;
import io.restassured.path.json.JsonPath;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import lombok.*;


import java.util.HashMap;
import java.util.Map;

import static org.aeonbits.owner.ConfigFactory.create;
import static org.testng.Assert.assertEquals;

@Getter
@Setter
public class getFlattenGroupWithAccountsAPI {

    MethodManager methodManager = new MethodManager();
    UrlConfig config = create(UrlConfig.class);

    public SmartResponse getFlattenGroupWithAccountsAPI(String auth, String searchValue, Boolean refreshValue ){

        if (searchValue == null && refreshValue == null){
            searchValue = "";
            refreshValue= false;
        }

        /**
         * Main test and api call initiated
         */
        SmartResponse resp = methodManager.getAPI_With_Params(auth, config.get_Flatten_Group_With_Accounts(), "", "", searchValue, refreshValue);
        return  resp;
    }
}
