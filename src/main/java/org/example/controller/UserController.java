package org.example.controller;

import org.example.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    public static final List<User> userList = new ArrayList<>();
    private int counter = 1;

    @PostMapping("/register")
    public int registerUser(@RequestBody User newUser) {
        newUser.setId(counter++);
        userList.add(newUser);
        return newUser.getId();
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userList;
    }

    public static List<User> getUserList() {
        return userList;
    }
}