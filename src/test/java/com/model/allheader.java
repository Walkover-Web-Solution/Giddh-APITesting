package com.model;

import  com.ApiUtils.Headers;

public class allheader {

    Headers h2 = new Headers();

     public void all_Headers(){
         h2.setAuth_Key("UfHWKDi9mj8MlPpXpJNy5olw3DUPpEa_M6wTd0MYCZHzll3xZCSvkZVkusHzHnbVC9uU7_fuysX7EDqxy8V6z3qCSnd6Kc57J1pvtbbTSZY");
         h2.setType("application/json");
     }

    public String getauth_key(){
        return h2.getAuth_Key();
    }

    public String get_type(){
        return h2.getType();
    }


}
