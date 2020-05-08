package com.example.photoapi.Model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
@Data
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column private String offerName;
    @Column private String details;
    @Column private Date endOffer;
    @ManyToOne
    private User user;
}
