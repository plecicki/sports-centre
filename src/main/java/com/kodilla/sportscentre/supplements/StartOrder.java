package com.kodilla.sportscentre.supplements;

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
