package com.kodilla.sportscentre.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderDecInDto {

    private Boolean bcaa;
    private Boolean caffeine;
    private Boolean citrulline;
    private Boolean creatine;
    private Boolean protein;

    private User user;
}
