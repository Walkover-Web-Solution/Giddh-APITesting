package com.ApiUtils;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources({"classpath:config/URL.${api.environment}.properties"})
public interface UrlConfig extends  Config{

    @Key("url")
    String baseURL();

    @Key("baseURL")
    String mainURL();

    @Key("get.Company")
    String getCompany();

    @Key("share.Company")
    String shareCompany();

    @Key("unshare.Company")
    String unshareCompany();

    @Key("delete.Company")
    String deleteCompany();

    @Key("group.Create")
    String createGroup();

    @Key("group.Get")
    String getGroup();
}
