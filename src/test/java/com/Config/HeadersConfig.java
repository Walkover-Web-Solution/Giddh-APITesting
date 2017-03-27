package com.Config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources({"classpath:config/Headers.${api.environment}.properties"})
public interface HeadersConfig extends Config{

    @Key("AuthKey")
    String setAuthKey();

    @Key("contentType")
    String setType();

}
