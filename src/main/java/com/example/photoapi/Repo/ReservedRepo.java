package com.example.photoapi.Repo;

import com.example.photoapi.Model.Reserved;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservedRepo extends JpaRepository<Reserved, Long> {
}
