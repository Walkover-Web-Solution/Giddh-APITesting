package com.controller;


import com.apiUtils.MethodManager;
import com.apiUtils.SmartResponse;
import lombok.*;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class AccountCreate {

    MethodManager methodManager = new MethodManager();

    private String name;
    private String uniqueName;

    public SmartResponse AccountCreate(String URL, String name, String uniqueName ){

        Map<String,String> body = new HashMap<>();
        body.put("name", this.name=name);
        body.put("uniqueName", this.uniqueName=uniqueName);

        /**
         * Main test and api call initiated
         */

        SmartResponse resp = methodManager.postAPI_with_Assert_Statuscode(null, URL, body);
        return  resp;
    }
}
