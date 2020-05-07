package com.example.photoapi.Model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table
@Data
public class WorkPalces {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String city;

    @ManyToOne
    private User user;
}
