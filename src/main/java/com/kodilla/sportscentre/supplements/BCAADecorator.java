package com.kodilla.sportscentre.supplements;

import java.math.BigDecimal;

public class BCAADecorator extends AbstractSupplementsOrderDecorator {

    public BCAADecorator(SupplementsOrder supplementsOrder) {
        super(supplementsOrder);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " BCAA";
    }

    @Override
    public BigDecimal getCost() {
        return super.getCost().add(new BigDecimal(35));
    }
}
