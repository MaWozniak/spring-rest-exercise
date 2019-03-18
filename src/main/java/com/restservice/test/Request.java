package com.restservice.test;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Request {

    final private String date;

    public Request(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

}
