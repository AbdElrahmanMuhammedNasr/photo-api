package com.example.photoapi.InterFaceImp;

import com.example.photoapi.InterFace.UserInterface;
import com.example.photoapi.Model.User;
import com.example.photoapi.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserImp implements UserInterface {

    @Autowired
    private UserRepo userRepo;

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public List<User> getListOfUserWithSameUserName(String username) {
        return userRepo.findByUserName(username);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    @Override
    public void addNewUser(User user) {
        userRepo.save(user);
    }

    @Override
    public void updateUser(User user) {
        userRepo.save(user);
    }

    @Override
    public void deleteUserUsingEmail(String email) {
        userRepo.deleteAllByEmail(email);
    }


}
