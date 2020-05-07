package com.example.photoapi.InterFace;


import com.example.photoapi.Model.Offer;
import com.example.photoapi.Model.User;

import java.util.List;

public interface OfferInterface {
    List<Offer> getAllUserOffers(User user);
}
