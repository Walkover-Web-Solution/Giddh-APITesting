package com.model;

import com.apiUtils.Url;
import com.config.UrlConfig;

import static org.aeonbits.owner.ConfigFactory.create;

public class ManageURL {
 Url b1 = new Url();

    UrlConfig config = create(UrlConfig.class);
    public void setURL(){
       b1.setURL(config.baseURL());
    }
}
