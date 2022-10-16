package com.kodilla.sportscentre.suplements;

import java.math.BigDecimal;

public class ProteinDecorator extends AbstractSupplementsOrderDecorator{

    public ProteinDecorator(SupplementsOrder suplementsOrder) {
        super(suplementsOrder);
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
