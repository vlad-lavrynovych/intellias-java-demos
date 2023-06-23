package com.demo.rest.webservices.restfulwebservices.helloworld;

import lombok.Data;

@Data
public class HelloWorldBean {

    private final String message;

    public HelloWorldBean(String message) {
        this.message = message;
    }
}
