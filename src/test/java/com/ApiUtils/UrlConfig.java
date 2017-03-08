package com.ApiUtils;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources({"classpath:config/URL.properties"})
public interface UrlConfig extends  Config{

    @Key("url")
    String enterUrl();
}
