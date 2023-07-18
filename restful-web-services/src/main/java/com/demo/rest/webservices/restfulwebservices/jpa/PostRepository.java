package com.demo.rest.webservices.restfulwebservices.jpa;

import com.demo.rest.webservices.restfulwebservices.user.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
}
