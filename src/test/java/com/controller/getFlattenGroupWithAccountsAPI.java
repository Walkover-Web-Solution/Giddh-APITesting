package com.controller;



import com.apiUtils.*;
import io.restassured.path.json.JsonPath;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import lombok.*;


import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertEquals;

@Getter
@Setter
public class getFlattenGroupWithAccountsAPI {

    MethodManager methodManager = new MethodManager();

    public SmartResponse getFlattenGroupWithAccountsAPI(String auth, String type, String URL, String fromDate, String toDate, String searchValue, Boolean refreshValue ){

        /**
         * Main test and api call initiated
         */
        SmartResponse resp = methodManager.getAPI_With_Params(auth, type, URL, fromDate, toDate, searchValue, refreshValue);
        return  resp;
    }
}
