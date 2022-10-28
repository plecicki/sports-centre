package com.kodilla.sportscentre.services;

import com.kodilla.sportscentre.domain.Order;
import com.kodilla.sportscentre.domain.OrderCreateDto;
import com.kodilla.sportscentre.domain.OrderDecInDto;
import com.kodilla.sportscentre.domain.User;
import com.kodilla.sportscentre.repositories.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
public class SupplementsServiceTest {

    @Autowired
    private SupplementsService supplementsService;

    @Autowired
    private OrderRepository orderRepository;

    @Test
    void createOrderTest() {
        //Given
        OrderDecInDto orderDecInDto = new OrderDecInDto(
                true,
                true,
                true,
                false,
                true,
                null
        );

        //When
        Order order = supplementsService.createOrder(orderDecInDto);

        //Then
        Assertions.assertEquals(" BCAA Caffeine Citrulline Protein", order.getDescription());
        Assertions.assertEquals(new BigDecimal(148.0), order.getSum());

        //CleanUp
        orderRepository.delete(order);
    }
}
