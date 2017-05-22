package com.model;

import  com.apiUtils.Headers;
import com.config.HeadersConfig;

import static org.aeonbits.owner.ConfigFactory.create;


public class ManageHeaders {

    Headers headers = new Headers();
    HeadersConfig config = create(HeadersConfig.class);

     public void set_Headers(String auth , String type){
         if (auth == null && type == null ){
             headers.setAuth_Key(config.setAuthKey());
             headers.setType(config.setType());
         }
         else {
             headers.setAuth_Key(auth);
             headers.setType(config.setType());
         }
     }

    public String getAuthKey(){
        return headers.getAuth_Key();
    }

    public String getType(){
        return headers.getType();
    }

}
