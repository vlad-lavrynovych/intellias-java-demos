package com.demo.rest.webservices.restfulwebservices.filtering;

import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
//@JsonIgnoreProperties({"field1", "field2"})
@JsonFilter("SomeBeanFilter")
public class SomeBean {

    private final String field1;
    private final String field2;
    private final String field3;
}
