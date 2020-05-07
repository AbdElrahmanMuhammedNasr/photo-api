package com.example.photoapi.Repo;

import com.example.photoapi.Model.FreeTime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;

public interface FreeTimeRepo extends JpaRepository<FreeTime, Long> {
    List<FreeTime> findAllByDay(Date date);
}
