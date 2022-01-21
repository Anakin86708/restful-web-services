package com.ariel.restfulwebservices.service;

import com.ariel.restfulwebservices.model.Post;
import com.ariel.restfulwebservices.model.User;
import com.ariel.restfulwebservices.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Post getPostById(long id) {
        return repository.getById(id);
    }

    public void deletePostById(long id) {
        repository.deleteById(id);
    }
}
