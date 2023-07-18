package com.demo.rest.webservices.restfulwebservices.user;

import com.demo.rest.webservices.restfulwebservices.user.post.Post;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "user_details")
public class User {

    @Id
    @GeneratedValue
    private Integer id;

    @Size(min = 2, message = "Name should have at least 2 characters")
//    @JsonProperty("user_name")
    private String name;

    @Past(message = "Birth Date should be in the past")
//    @JsonProperty("birth_date")
    private LocalDate birthDate;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Post> posts;
}
