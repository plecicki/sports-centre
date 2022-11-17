package com.kodilla.sportscentre.supplements;

import java.math.BigDecimal;

public class CitrullineDecorator extends AbstractSupplementsOrderDecorator {

    public CitrullineDecorator(SupplementsOrder supplementsOrder) {
        super(supplementsOrder);
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
