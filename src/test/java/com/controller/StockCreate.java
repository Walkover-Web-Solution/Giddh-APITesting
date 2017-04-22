package com.controller;


import com.apiUtils.MethodManager;
import com.apiUtils.SmartResponse;
import lombok.*;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class StockCreate {
    MethodManager methodManager = new MethodManager();

    private String name;
    private String uniqueName;
    private String parentStockGroupUniqueName;

    public SmartResponse StockCreate(String auth , String type, String URL, String name, String uniqueName, String parentStockGroupUniqueName){

        if (parentStockGroupUniqueName == null){
            parentStockGroupUniqueName = "";
        }
        Map<String,String> body = new HashMap<>();
        body.put("name", this.name=name);
        body.put("uniqueName", this.uniqueName=uniqueName);
        body.put("parentStockGroupUniqueName", this.parentStockGroupUniqueName=parentStockGroupUniqueName);

        /**
         * Main test and api call initiated
         */
        SmartResponse resp = methodManager.postAPI_with_Assert_Statuscode(auth, type, URL, body);
        return  resp;
    }
}

