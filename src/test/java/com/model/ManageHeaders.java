package com.model;

import  com.ApiUtils.Headers;
import com.ApiUtils.HeadersConfig;

import static org.aeonbits.owner.ConfigFactory.create;


public class ManageHeaders {

    Headers headers = new Headers();
    HeadersConfig config = create(HeadersConfig.class);

     public void set_Headers(){
         headers.setAuth_Key(config.setAuthKey());
         headers.setType(config.setType());
     }

//    public void setAuth(){
//        headers.setAuth_Key(config.setAuthKey());
//    }
//
//    public void setType(){
//        headers.setType(config.setType());
//    }

    public String getAuthKey(){
        return headers.getAuth_Key();
    }

    public String getType(){
        return headers.getType();
    }

}
