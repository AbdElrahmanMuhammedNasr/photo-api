package com.example.photoapi.Model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table
@Data
public class Reserved {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Customer customer;

    @OneToOne
    private FreeTime freeTime;
}
