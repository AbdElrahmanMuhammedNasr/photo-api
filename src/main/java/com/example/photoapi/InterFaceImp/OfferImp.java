package com.example.photoapi.InterFaceImp;


import com.example.photoapi.InterFace.OfferInterface;
import com.example.photoapi.Model.Offer;
import com.example.photoapi.Model.User;
import com.example.photoapi.Repo.OfferRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfferImp implements OfferInterface {

    @Autowired private OfferRepo offerRepo;

    @Override
    public List<Offer> getAllUserOffers(User user) {
        return offerRepo.findByUser(user);
    }
}
