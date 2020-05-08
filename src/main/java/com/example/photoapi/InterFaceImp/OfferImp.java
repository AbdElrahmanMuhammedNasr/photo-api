package com.example.photoapi.InterFaceImp;


import com.example.photoapi.InterFace.OfferInterface;
import com.example.photoapi.Model.Offer;
import com.example.photoapi.Model.User;
import com.example.photoapi.Repo.OfferRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class OfferImp implements OfferInterface {

    @Autowired private OfferRepo offerRepo;

    @Override
    public List<Offer> getAllUserOffers(User user) {
        return offerRepo.findByUser(user);
    }

    @Override
    public void addNewOffer(Offer offer) {
        offerRepo.save(offer);
    }

    @Override
    public void updateOffer(Offer offer) {
        offerRepo.save(offer);
    }

    @Override
    public Offer getOneOfferUsingId(Long id) {
        return offerRepo.findAllById(id);
    }

    @Override
    public void deleteOffer(Offer offer) {
        offerRepo.delete(offer);
    }
}
