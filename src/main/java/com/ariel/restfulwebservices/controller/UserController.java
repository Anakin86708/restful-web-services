package com.ariel.restfulwebservices.controller;

import com.ariel.restfulwebservices.exception.UserNotFoundException;
import com.ariel.restfulwebservices.model.Post;
import com.ariel.restfulwebservices.model.User;
import com.ariel.restfulwebservices.service.PostService;
import com.ariel.restfulwebservices.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public EntityModel<User> getUserModelById(@PathVariable long id) {
        User user = getValidUserById(id);
        EntityModel<User> model = EntityModel.of(user);
        WebMvcLinkBuilder linkToUser = linkTo(methodOn(this.getClass()).getAllUsers());
        model.add(linkToUser.withRel("all-users"));
        return model;
    }

    @PostMapping("/users")
    public ResponseEntity<User> addUser(@Valid @RequestBody User user) {
        userService.createUser(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/users/{id}")
    public void deleteUserById(@PathVariable long id) {
        userService.deleteUserById(id);
    }


    ///////////
    // Posts //
    ///////////
    @GetMapping("/users/{userId}/posts")
    public List<Post> getAllPostsFromUser(@PathVariable long userId) {
        return postService.getAllPostsFromUser(getValidUserById(userId));
    }

    @PostMapping("/users/{userId}/posts")
    public ResponseEntity<Post> addPostToUser(@PathVariable long userId, @RequestBody Post post) {
        post.setUser(getValidUserById(userId));
        postService.createPostForUser(post);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(post.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    private User getValidUserById(long id) throws UserNotFoundException {
        Optional<User> user = userService.getUserById(id);

        if (user.isEmpty())
            throw new UserNotFoundException(id);
        return user.get();
    }
}
