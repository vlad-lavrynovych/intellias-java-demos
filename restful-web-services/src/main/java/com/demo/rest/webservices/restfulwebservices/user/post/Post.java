package com.demo.rest.webservices.restfulwebservices.user.post;

import com.demo.rest.webservices.restfulwebservices.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Post {

    @Id
    @GeneratedValue
    private Integer id;

    @Size(min = 10)
    private String description;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}
