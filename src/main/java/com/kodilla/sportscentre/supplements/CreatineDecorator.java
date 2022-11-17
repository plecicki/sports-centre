package com.kodilla.sportscentre.supplements;

import java.math.BigDecimal;

public class CreatineDecorator extends AbstractSupplementsOrderDecorator {

    public CreatineDecorator(SupplementsOrder supplementsOrder) {
        super(supplementsOrder);
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
