package com.example.photoapi.Repo;


import com.example.photoapi.Model.Offer;
import com.example.photoapi.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepo extends JpaRepository<Offer, Long> {
    List<Offer> findByUser(User user);
}
