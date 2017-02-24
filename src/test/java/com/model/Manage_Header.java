package com.model;

import  com.ApiUtils.Headers;

public class Manage_Header {

    Headers headers = new Headers();

     public void set_Headers(){
         headers.setAuth_Key("UfHWKDi9mj8MlPpXpJNy5olw3DUPpEa_M6wTd0MYCZHzll3xZCSvkZVkusHzHnbVC9uU7_fuysX7EDqxy8V6z3qCSnd6Kc57J1pvtbbTSZY");
         headers.setType("application/json");
     }

    public String getAuthKey(){
        return headers.getAuth_Key();
    }

    public String getType(){
        return headers.getType();
    }

}
