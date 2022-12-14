package com.kodilla.sportscentre.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.kodilla.sportscentre.domain.enums.CardStatus;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "CARDS")
public class Card {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "CARD_ID", unique = true)
    private Long cardId;

    @OneToOne
    @JoinColumn(name = "USER_ID", unique = true)
    @JsonBackReference
    private User user;

    @Column(name = "ACCESS_PASS")
    private String accessPass;

    @Column(name = "CARD_STATUS")
    private CardStatus cardStatus;
}
