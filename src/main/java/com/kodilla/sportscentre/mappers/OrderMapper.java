package com.kodilla.sportscentre.mappers;

import com.kodilla.sportscentre.domain.Order;
import com.kodilla.sportscentre.domain.OrderCreateDto;
import com.kodilla.sportscentre.domain.OrderEditDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderMapper {

    public Order mapToOrderFromCreate(final OrderCreateDto orderCreateDto) {
        return new Order(
                0L,
                orderCreateDto.getDescription(),
                orderCreateDto.getSum()
        );
    }

    public Order mapToOrderFromEdit(final OrderEditDto orderEditDto) {
        return new Order(
                orderEditDto.getOrderId(),
                orderEditDto.getDescription(),
                orderEditDto.getSum()
        );
    }

    public OrderCreateDto mapToOrderCreateDto(Order order) {
        return new OrderCreateDto(
                order.getDescription(),
                order.getSum()
        );
    }

    public OrderEditDto mapToOrderEditDto(Order order) {
        return new OrderEditDto(
                order.getOrderId(),
                order.getDescription(),
                order.getSum()
        );
    }

    public List<OrderCreateDto> mapToOrderCreateDtoList(final List<Order> orderList) {
        return orderList.stream()
                .map(this::mapToOrderCreateDto)
                .collect(Collectors.toList());
    }

    public List<OrderEditDto> mapToOrderEditDtoList(final List<Order> orderList) {
        return orderList.stream()
                .map(this::mapToOrderEditDto)
                .collect(Collectors.toList());
    }
}
