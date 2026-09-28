package org.example.controller;

import org.example.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    // Şimdilik verileri geçici olarak bellekte (Listede) tutuyoruz
    private final List<User> userList = new ArrayList<>();
    private long counter = 1;

    // 1. Kullanıcı Listeleme (GET)
    @GetMapping
    public List<User> getAllUsers() {
        return userList;
    }

    // 2. Kullanıcı Kayıt Olma (POST)
    @PostMapping
    public User registerUser(@RequestBody User user) {
        user.setId(counter++);
        userList.add(user);
        return user;
    }
}