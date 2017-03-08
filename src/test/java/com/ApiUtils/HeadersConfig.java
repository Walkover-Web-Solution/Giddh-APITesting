package com.ApiUtils;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources({"classpath:config/Headers.properties"})
public interface HeadersConfig extends Config{

    @Key("authkey")
    String setAuthKey();

    @Key("contenttype")
    String setType();


}
