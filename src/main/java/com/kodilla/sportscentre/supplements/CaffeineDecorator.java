package com.kodilla.sportscentre.supplements;

import java.math.BigDecimal;

public class CaffeineDecorator extends AbstractSupplementsOrderDecorator {

    public CaffeineDecorator(SupplementsOrder supplementsOrder) {
        super(supplementsOrder);
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
