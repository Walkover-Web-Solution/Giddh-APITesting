package com.ApiUtils;

import java.util.HashMap;
import java.util.Map;

import static com.jayway.restassured.RestAssured.given;


public class ApiHeaders   {

    private Map<String, String> map = new HashMap<>();

    public Map<String, String> headers() {
        map.put("Auth-Key", "UfHWKDi9mj8MlPpXpJNy5olw3DUPpEa_M6wTd0MYCZHzll3xZCSvkZVkusHzHnbVC9uU7_fuysX7EDqxy8V6z3qCSnd6Kc57J1pvtbbTSZY=");
        map.put("Content-Type", "application/json");
        return map;
    }
}
