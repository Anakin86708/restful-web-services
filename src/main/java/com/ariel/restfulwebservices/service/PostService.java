package com.ariel.restfulwebservices.service;

import com.ariel.restfulwebservices.model.Post;
import com.ariel.restfulwebservices.model.User;
import com.ariel.restfulwebservices.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    private PostRepository repository;

    public List<Post> getAllPostsFromUser(User user) {
        return repository.findAllByUserId(user.getId());
    }

    public void createPostForUser(Post post) {
        repository.save(post);
    }

    public Optional<Post> getPostById(long id) {
        return repository.findById(id);
    }

    public void deletePostById(long id) {
        repository.deleteById(id);
    }
}
