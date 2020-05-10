package com.example.photoapi.Model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.List;

@Entity
@Table
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    @Email
    private String email;

    @Column
    private String userName;

    @Column
    private String job;

    @OneToMany(mappedBy = "customer" , cascade = CascadeType.ALL)
    private List<Reserved> reservedList;
}
