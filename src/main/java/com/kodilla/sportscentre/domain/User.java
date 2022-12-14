package com.kodilla.sportscentre.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.kodilla.sportscentre.domain.enums.Goals;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "USERS")
public class User {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "USER_ID", unique = true)
    private Long userId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "SURNAME")
    private String surname;

    @Column(name = "BIRTH_DATE")
    private LocalDate birthDate;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "GOAL")
    private Goals goal;

    @Column(name = "STUDENT")
    private Boolean student;

    @Column(name = "GYM")
    private Boolean gym;

    @Column(name = "SWIMMING_POOL")
    private Boolean swimmingPool;

    @OneToOne
    @JoinColumn(name = "CARD_ID", unique = true)
    @JsonManagedReference
    @NotNull
    private Card card;

    @Column(name = "AUTO_EXTENSION")
    @NotNull
    private Boolean autoExtension;

    @OneToMany
    private List<Invoice> invoices;

    @Column(name = "SUB_VALIDITY")
    private LocalDate subValidity;
}
