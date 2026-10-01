package org.example.controller;

import org.example.repository.UserRepository;
import org.springframework.web.bind.annotation.*;
import org.example.model.User;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private int counter = 1;

    @PostMapping("/register")
    public int registerUser(@RequestBody User newUser) {
        newUser.setId(counter++);
        UserRepository.addUser(newUser);
        return Math.toIntExact(newUser.getId());
    }

    @GetMapping
    public List<User> getAllUsers() {
        return UserRepository.getUserList();
    }
}