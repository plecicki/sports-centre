package com.kodilla.sportscentre.suplements;

import java.math.BigDecimal;

public class CaffeineDecorator extends AbstractSupplementsOrderDecorator {

    public CaffeineDecorator(SupplementsOrder suplementsOrder) {
        super(suplementsOrder);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " Caffeine";
    }

    @Override
    public BigDecimal getCost() {
        return super.getCost().add(new BigDecimal(20));
    }
}
