package com.kodilla.sportscentre.suplements;

import java.math.BigDecimal;

public class StartOrder implements SupplementsOrder {

    @Override
    public String getDescription() {
        return "";
    }

    @Override
    public BigDecimal getCost() {
        return new BigDecimal(0);
    }
}
