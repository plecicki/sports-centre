package com.kodilla.sportscentre.services;

import com.kodilla.sportscentre.domain.Order;
import com.kodilla.sportscentre.domain.OrderCreateDto;
import com.kodilla.sportscentre.domain.OrderEditDto;
import com.kodilla.sportscentre.exceptions.OrderNotFoundException;
import com.kodilla.sportscentre.mappers.OrderMapper;
import com.kodilla.sportscentre.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public List<Order> getAllOrders() {
        List<Order> orderList = orderRepository.findAll();
        return orderList;
    }

    public Order getOrderById(final Long orderId) throws OrderNotFoundException {
        Order order = orderRepository.findById(orderId).orElseThrow(OrderNotFoundException::new);
        return order;
    }

    public Order createOrder(final OrderCreateDto orderCreateDto) {
        Order order = orderMapper.mapToOrderFromCreate(orderCreateDto);
        return orderRepository.save(order);
    }

    public Order editOrder(final OrderEditDto orderEditDto) {
        Order order = orderMapper.mapToOrderFromEdit(orderEditDto);
        return orderRepository.save(order);
    }

    public void deleteOrder(final Long orderId) throws OrderNotFoundException {
        if(!orderRepository.existsById(orderId)) throw new OrderNotFoundException();
        orderRepository.deleteById(orderId);
    }
}
