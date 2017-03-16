package com.api;

public class SmartResponse {
    private int statusCode;
    private String json;

    public SmartResponse() {
    }

    public SmartResponse(int statusCode, String json) {
        this.statusCode = statusCode;
        this.json = json;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }
}
