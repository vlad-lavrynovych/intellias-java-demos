package com.demo.rest.webservices.restfulwebservices.versioning;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Name {

    private final String firstName;
    private final String lastName;
}
