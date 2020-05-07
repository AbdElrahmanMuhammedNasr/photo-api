package com.example.photoapi.Model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table
@Data
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column private String offerName;
    @Column private String details;
    @ManyToOne
    private User user;
}
