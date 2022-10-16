package com.kodilla.sportscentre.suplements;

import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@RequiredArgsConstructor
public abstract class AbstractSupplementsOrderDecorator implements SupplementsOrder {
    private final SupplementsOrder suplementsOrder;

    @Override
    public String getDescription() {
        return suplementsOrder.getDescription();
    }

    @Override
    public BigDecimal getCost() {
        return suplementsOrder.getCost();
    }
}
