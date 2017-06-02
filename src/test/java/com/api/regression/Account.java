package com.api.regression;


import com.apiUtils.MethodManager;
import com.config.HeadersConfig;
import com.config.UrlConfig;
import com.controller.CompanyCreate;

import static org.aeonbits.owner.ConfigFactory.create;

public class Account {
    CompanyCreate create = new CompanyCreate();
    MethodManager methodManager = new MethodManager();
    UrlConfig config = create(UrlConfig.class);
    HeadersConfig headersConfig = create(HeadersConfig.class);
}
