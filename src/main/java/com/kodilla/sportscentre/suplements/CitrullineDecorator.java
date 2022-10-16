package com.kodilla.sportscentre.suplements;

import java.math.BigDecimal;

public class CitrullineDecorator extends AbstractSupplementsOrderDecorator {

    public CitrullineDecorator(SupplementsOrder suplementsOrder) {
        super(suplementsOrder);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " Citrulline";
    }

    @Override
    public BigDecimal getCost() {
        return super.getCost().add(new BigDecimal(43));
    }
}
