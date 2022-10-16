package com.kodilla.sportscentre.suplements;

import java.math.BigDecimal;

public class BCAADecorator extends AbstractSupplementsOrderDecorator {

    public BCAADecorator(SupplementsOrder suplementsOrder) {
        super(suplementsOrder);
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
