package org.example.controller;

import org.example.model.Repository;
import org.example.model.User;

public class UserController {
    private Repository repository;

    public UserController(Repository repository) {
        this.repository = repository;
    }

    public void saveUser(User user) {
        this.repository.createUser(user);
    }
}
