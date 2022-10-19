package com.kodilla.sportscentre.services;

import com.kodilla.sportscentre.domain.Order;
import com.kodilla.sportscentre.domain.OrderCreateDto;
import com.kodilla.sportscentre.domain.OrderEditDto;
import com.kodilla.sportscentre.exceptions.OrderNotFoundException;
import com.kodilla.sportscentre.repositories.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    @Test
    void shouldFetchListWithOrders() {
        //Given
        OrderCreateDto order1 = new OrderCreateDto("Description1", BigDecimal.valueOf(100.0));
        OrderCreateDto order2 = new OrderCreateDto("Description2", BigDecimal.valueOf(200.0));
        OrderCreateDto order3 = new OrderCreateDto("Description3", BigDecimal.valueOf(300.0));

        orderService.createOrder(order1);
        orderService.createOrder(order2);
        orderService.createOrder(order3);

        //When
        List<Order> orders = orderService.getAllOrders();

        //Then
        Assertions.assertEquals("Description1", orders.get(orders.size()-3).getDescription());
        Assertions.assertEquals("Description2", orders.get(orders.size()-2).getDescription());
        Assertions.assertEquals("Description3", orders.get(orders.size()-1).getDescription());

        //CleanUp
        orderRepository.delete(orders.get(orders.size() - 3));
        orderRepository.delete(orders.get(orders.size() - 2));
        orderRepository.delete(orders.get(orders.size() - 1));
    }

    @Test
    void shouldFetchOrder() throws OrderNotFoundException {
        //Given
        OrderCreateDto order1 = new OrderCreateDto("Description1", BigDecimal.valueOf(100.0));
        Order createdOrder = orderService.createOrder(order1);

        //When
        Order order = orderService.getOrderById(createdOrder.getOrderId());

        //Then
        Assertions.assertEquals("Description1", order.getDescription());

        //CleanUp
        orderRepository.delete(order);
    }

    @Test
    void createOrderTest() {
        //Given
        OrderCreateDto orderCreateDto = new OrderCreateDto("Description1", BigDecimal.valueOf(100.0));

        //When
        Order order = orderService.createOrder(orderCreateDto);

        //Then
        Assertions.assertNotNull(order.getOrderId());

        //CleanUp
        orderRepository.delete(order);
    }

    @Test
    void editOrderTest() {
        //Given
        OrderCreateDto orderCreateDto = new OrderCreateDto("Description1", BigDecimal.valueOf(100.0));
        Order order = orderService.createOrder(orderCreateDto);
        OrderEditDto orderEditDto = new OrderEditDto(order.getOrderId(), "Description2", BigDecimal.valueOf(200.0));

        //When
        Order editedOrder = orderService.editOrder(orderEditDto);

        //Then
        Assertions.assertEquals("Description2", editedOrder.getDescription());

        //CleanUp
        orderRepository.delete(editedOrder);
    }

    @Test
    void deleteOrderTest() {
        //Given
        OrderCreateDto orderCreateDto = new OrderCreateDto("Description1", BigDecimal.valueOf(100.0));
        Order order = orderService.createOrder(orderCreateDto);

        //When
        try {
            orderService.deleteOrder(order.getOrderId());
        } catch (OrderNotFoundException e) {
            System.out.println(e);
        }

        //Then
        boolean orderNotFound = false;
        Order orderAfterDelete = new Order();
        try {
            orderAfterDelete = orderService.getOrderById(order.getOrderId());
        } catch (OrderNotFoundException e) {
            orderNotFound = true;
        }
        Assertions.assertNull(orderAfterDelete.getOrderId());
        Assertions.assertTrue(orderNotFound);
    }
}
