package com.kodilla.sportscentre.domain;

import com.kodilla.sportscentre.domain.enums.Role;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "ACCOUNTS")
public class Account {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "ACCOUNT_ID", unique = true)
    private Long accountId;

    @Column(name = "USERNAME", unique = true)
    private String username;

    @Column(name = "PASSWORD_SALT")
    private String passwordSalt;

    @Column(name = "PASSWORD_HASH")
    private String passwordHash;

    @Column(name = "ROLE")
    private Role role;

    @JoinColumn(name = "USER_ID", unique = true)
    @OneToOne
    private User user;
}
