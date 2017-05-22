package com.apiUtils;


public class Headers {

    private String Auth_Key;
    private  String Auth_Key1;
    private String Type;

    public String getAuth_Key() {
        return Auth_Key;
    }

    public String getSharedUserAuth_key(){
        return Auth_Key1;
    }

    public void setAuth_Key(String auth_Key) {
        Auth_Key = auth_Key;
      }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }
}
