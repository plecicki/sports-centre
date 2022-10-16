package com.kodilla.sportscentre.suplements;

import java.math.BigDecimal;

public class CreatineDecorator extends AbstractSupplementsOrderDecorator {

    public CreatineDecorator(SupplementsOrder suplementsOrder) {
        super(suplementsOrder);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " Creatine";
    }

    @Override
    public BigDecimal getCost() {
        return super.getCost().add(new BigDecimal(70));
    }
}
