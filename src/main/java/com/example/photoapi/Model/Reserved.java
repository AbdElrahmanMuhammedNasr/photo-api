package com.example.photoapi.Model;

import lombok.Data;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;

@Entity
@Table
@Data
public class Reserved {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Customer customer;

    @OneToOne
    private FreeTime freeTime;
}
