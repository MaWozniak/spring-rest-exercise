package com.restservice.test;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CurrencyName {

    final private String name;

    public CurrencyName(@JsonProperty("currency") String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
