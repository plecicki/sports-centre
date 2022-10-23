package com.kodilla.sportscentre.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "ORDERS")
public class Order {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "ORDER_ID", unique = true)
    private Long orderId;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "SUM")
    private BigDecimal sum;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;
}
