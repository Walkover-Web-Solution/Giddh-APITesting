package com.controller;

import com.apiUtils.*;
import lombok.*;
import java.util.HashMap;
import java.util.Map;


@Getter
@Setter
public class CompanyCreate {

    MethodManager methodManager = new MethodManager();

    int responseCode;
    private String name;
    private String uniqueName;


    public SmartResponse companyCreate(String authKey, String URL, String name, String uniqueName){

        Map<String,String> body = new HashMap<>();
        body.put("name", this.name= name);
        body.put("uniqueName", this.uniqueName=uniqueName);

        /**
         * Main test and api call initiated
         */
        SmartResponse resp = methodManager.postAPI_with_Assert_Statuscode(authKey, URL, body);
        return resp;
    }
}
