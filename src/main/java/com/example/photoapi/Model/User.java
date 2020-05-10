package com.example.photoapi.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Data
public class User {
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

    @Column
    private int price;

    @Column
    @Length(min = 11 , max = 11)
    private String phoneNumber;

    @Column
    private String city;

    @OneToMany(mappedBy = "user" , cascade = CascadeType.ALL)
    @JsonIgnore
    @Column private List<WorkPalces> workPalces = new ArrayList<>();

    @OneToMany(mappedBy = "user" , cascade = CascadeType.ALL)
    @JsonIgnore
    @Column private List<Offer> offers;

    @OneToMany(mappedBy = "user" , cascade = CascadeType.ALL)
    @JsonIgnore
    @Column private List<FreeTime> freeTimes;



}
