package org.example.controller;

import org.example.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    // Bellekte veri tutacak geçici liste
    private final List<User> userList = new ArrayList<>();
    private int counter = 1;

    // 1. Kullanıcı Kayıt Olma (POST) - İstediğin Endpoint
    // Kullanıcı username ve password girer, sistem id döner.
    @PostMapping("/register")
    public int registerUser(@RequestBody User newUser) {
        newUser.setId(counter++);
        userList.add(newUser);
        return newUser.getId(); // Yanıt olarak sadece kullanıcının ID'sini döndürür
    }

    // 2. Kullanıcıları Listeleme (GET)
    @GetMapping
    public List<User> getAllUsers() {
        return userList;
    }
}