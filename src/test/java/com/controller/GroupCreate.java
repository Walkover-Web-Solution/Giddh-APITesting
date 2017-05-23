package com.controller;

import com.apiUtils.MethodManager;
import com.apiUtils.SmartResponse;
import lombok.*;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class GroupCreate {

    MethodManager methodManager = new MethodManager();

    private String name;
    private String uniqueName;
    private String parentGroupUniqueName;



    public SmartResponse GroupCreate(String URL, String name, String uniqueName, String parentGroupUniqueName){

        Map<String,String> body = new HashMap<>();
        body.put("name", this.name=name);
        body.put("uniqueName", this.uniqueName=uniqueName);
        body.put("parentGroupUniqueName", this.parentGroupUniqueName=parentGroupUniqueName);

        /**
         * Main test and api call initiated
         */

        SmartResponse resp = methodManager.postAPI_with_Assert_Statuscode(null, URL, body);
        return  resp;
    }

}
