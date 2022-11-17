package com.kodilla.sportscentre.supplements;

import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@RequiredArgsConstructor
public abstract class AbstractSupplementsOrderDecorator implements SupplementsOrder {
    private final SupplementsOrder supplementsOrder;

    @Override
    public String getDescription() {
        return supplementsOrder.getDescription();
    }

    @Override
    public BigDecimal getCost() {
        return supplementsOrder.getCost();
    }
}
