package com.kodilla.sportscentre.supplements;

import java.math.BigDecimal;

public class ProteinDecorator extends AbstractSupplementsOrderDecorator{

    public ProteinDecorator(SupplementsOrder supplementsOrder) {
        super(supplementsOrder);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " Protein";
    }

    @Override
    public BigDecimal getCost() {
        return super.getCost().add(new BigDecimal(50));
    }
}
