package com.ariel.restfulwebservices.service;

import com.ariel.restfulwebservices.model.User;
import com.ariel.restfulwebservices.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> getAllUsers() {
        return repository.findAll();
    }

    public void createUser(User user) {
        repository.save(user);
    }

    public User getUserById(long id) {
        return repository.getById(id);
    }

    public void deleteUserById(long id) {
        repository.deleteById(id);
    }
}

