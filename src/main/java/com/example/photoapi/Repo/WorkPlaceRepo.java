package com.example.photoapi.Repo;

import com.example.photoapi.Model.User;
import com.example.photoapi.Model.WorkPalces;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkPlaceRepo extends JpaRepository<WorkPalces,Long> {
    List<WorkPalces> findByUser(User user);
    List<WorkPalces> findAllByCity(String city);
}
