package com.kodilla.sportscentre.services;

import com.kodilla.sportscentre.domain.Order;
import com.kodilla.sportscentre.mappers.OrderMapper;
import com.kodilla.sportscentre.repositories.OrderRepository;
import com.kodilla.sportscentre.suplements.StartOrder;
import com.kodilla.sportscentre.suplements.SupplementsOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SupplementsService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public Order startOrder() {

        //TODO Continue work here
        return null;
    }
}
