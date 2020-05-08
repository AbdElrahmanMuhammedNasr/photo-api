package com.example.photoapi.Repo;

import com.example.photoapi.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    List<User> findByUserName(String username);
    User findByEmail(String email);
    void deleteAllByEmail(String email);
}
