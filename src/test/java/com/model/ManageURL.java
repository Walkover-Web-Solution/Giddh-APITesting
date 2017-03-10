package com.model;

import com.ApiUtils.Url;
import com.ApiUtils.UrlConfig;

import static org.aeonbits.owner.ConfigFactory.create;

public class ManageURL {
 Url b1 = new Url();

    UrlConfig config = create(UrlConfig.class);
    public void setURL(){
       b1.setURL(config.baseURL());
    }

    public String getURL(){
        return b1.getURL();
    }

}
