package com.example.photoapi.Model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;


@Entity
@Table
@Data
public class FreeTime {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private Date day;
    @Column
    private String fromTime;
    @Column
    private String toTime;
    @Column
    private char isReserved ;

    @ManyToOne
    private User user;
}
