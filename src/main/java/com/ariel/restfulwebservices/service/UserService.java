package com.ariel.restfulwebservices.service;

import com.ariel.restfulwebservices.model.User;
import com.ariel.restfulwebservices.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Optional<User> getUserById(long id) {
        return repository.findById(id);
    }

    public void deleteUserById(long id) {
        repository.deleteById(id);
    }
}

