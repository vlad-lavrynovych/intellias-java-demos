package com.demo.rest.webservices.restfulwebservices.user;

import com.demo.rest.webservices.restfulwebservices.jpa.PostRepository;
import com.demo.rest.webservices.restfulwebservices.jpa.UserRepository;
import com.demo.rest.webservices.restfulwebservices.user.post.Post;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequiredArgsConstructor
public class UserJpaResource {

    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @GetMapping("/jpa/users")
    public List<User> retrieveAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/jpa/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable int id) {

        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        EntityModel<User> entityModel = EntityModel.of(user);

        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        entityModel.add(link.withRel("all-users"));

        return entityModel;
    }

    @DeleteMapping("/jpa/users/{id}")
    public void deleteUser(@PathVariable int id) {
        userRepository.deleteById(id);
    }

    @GetMapping("/jpa/users/{id}/posts")
    public List<Post> retrievePostsForUser(@PathVariable int id) {
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        return user.getPosts();
    }

    @GetMapping("/jpa/users/{id}/posts/{postId}")
    public Post retrievePostById(@PathVariable int postId) {
        return postRepository.findById(postId).orElseThrow(EntityNotFoundException::new);
    }

    @PostMapping("/jpa/users/{id}/posts")
    public ResponseEntity<Object> createPostForUser(@PathVariable int id,
                                                     @Valid @RequestBody Post post) {
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        post.setUser(user);
        Post save = postRepository.save(post);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(save.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/jpa/users/{id}/posts/{postId}")
    public void deletePostById(@PathVariable int postId) {
        postRepository.deleteById(postId);
    }

    @PostMapping("/jpa/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        user = userRepository.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
}
