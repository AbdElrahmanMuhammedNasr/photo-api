package com.example.photoapi.Repo;

import com.example.photoapi.Model.WorkPalces;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkPlaceRepo extends JpaRepository<WorkPalces,Long> {
}
