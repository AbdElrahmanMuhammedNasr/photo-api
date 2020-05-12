package com.example.photoapi.DTO;

import lombok.Data;

import java.util.Date;

@Data
public class OfferDTO {

    private Long id;
    private String offerName;
    private String details;
    private Date endOffer;
    private String userName;
}
