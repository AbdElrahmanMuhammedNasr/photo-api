package com.example.photoapi.InterFace;

import com.example.photoapi.Model.User;

import java.util.List;

public interface UserInterface {
    List<User> getAllUsers();
    List<User> getListOfUserWithSameUserName(String username);
    User getUserByEmail(String email);
    void addNewUser(User user);
    void updateUser(User user);
    void deleteUserUsingEmail(String email);
}
