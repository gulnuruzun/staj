package org.example.repository;

import org.example.model.User;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private static final List<User> userList = new ArrayList<>();

    public static List<User> getUserList() {
        return userList;
    }

    public static void addUser(User user) {
        userList.add(user);
    }
}