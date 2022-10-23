package com.kodilla.sportscentre.services;

import com.kodilla.sportscentre.domain.Order;
import com.kodilla.sportscentre.domain.OrderCreateDto;
import com.kodilla.sportscentre.domain.OrderDecInDto;
import com.kodilla.sportscentre.mappers.OrderMapper;
import com.kodilla.sportscentre.repositories.OrderRepository;
import com.kodilla.sportscentre.suplements.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class SupplementsService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public Order createOrder(OrderDecInDto orderDecInDto) {
        SupplementsOrder supplementsOrder = new StartOrder();
        if (orderDecInDto.getBcaa()) supplementsOrder = new BCAADecorator(supplementsOrder);
        if (orderDecInDto.getCaffeine()) supplementsOrder = new CaffeineDecorator(supplementsOrder);
        if (orderDecInDto.getCitrulline()) supplementsOrder = new CitrullineDecorator(supplementsOrder);
        if (orderDecInDto.getCreatine()) supplementsOrder = new CreatineDecorator(supplementsOrder);
        if (orderDecInDto.getProtein()) supplementsOrder = new ProteinDecorator(supplementsOrder);
        String description = supplementsOrder.getDescription();
        BigDecimal calculatedCost = supplementsOrder.getCost();

        OrderCreateDto orderCreateDto = new OrderCreateDto(
                description,
                calculatedCost,
                orderDecInDto.getUser()
        );
        Order order = orderMapper.mapToOrderFromCreate(orderCreateDto);
        order = orderRepository.save(order);
        return order;
    }
}
