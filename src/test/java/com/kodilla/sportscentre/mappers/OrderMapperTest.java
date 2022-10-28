package com.kodilla.sportscentre.mappers;

import com.kodilla.sportscentre.domain.Order;
import com.kodilla.sportscentre.domain.OrderCreateDto;
import com.kodilla.sportscentre.domain.OrderEditDto;
import com.kodilla.sportscentre.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
public class OrderMapperTest {

    @Autowired
    private OrderMapper orderMapper;

    @Test
    void mapToOrderFromCreate() {
        //Given
        OrderCreateDto orderCreateDto = new OrderCreateDto(
                "description1",
                BigDecimal.valueOf(100.0),
                new User()
        );

        //When
        Order order = orderMapper.mapToOrderFromCreate(orderCreateDto);

        //Then
        Assertions.assertEquals("description1", order.getDescription());
        Assertions.assertEquals(BigDecimal.valueOf(100.0), order.getSum());
    }

    @Test
    void mapToOrderFromEdit() {
        //Given
        OrderEditDto orderEditDto = new OrderEditDto(
                1L,
                "description1",
                BigDecimal.valueOf(100.0),
                new User()
        );

        //When
        Order order = orderMapper.mapToOrderFromEdit(orderEditDto);

        //Then
        Assertions.assertEquals("description1", order.getDescription());
        Assertions.assertEquals(BigDecimal.valueOf(100.0), order.getSum());
    }

    @Test
    void mapToOrderCreateDto() {
        //Given
        Order order = new Order(
                1L,
                "description1",
                BigDecimal.valueOf(100.0),
                new User()
        );

        //When
        OrderCreateDto orderCreateDto = orderMapper.mapToOrderCreateDto(order);

        //Then
        Assertions.assertEquals("description1", orderCreateDto.getDescription());
        Assertions.assertEquals(BigDecimal.valueOf(100.0), orderCreateDto.getSum());
    }

    @Test
    void mapToOrderEditDto() {
        //Given
        Order order = new Order(
                1L,
                "description1",
                BigDecimal.valueOf(100.0),
                new User()
        );

        //When
        OrderEditDto orderEditDto = orderMapper.mapToOrderEditDto(order);

        //Then
        Assertions.assertEquals("description1", orderEditDto.getDescription());
        Assertions.assertEquals(BigDecimal.valueOf(100.0), orderEditDto.getSum());
    }

    @Test
    void mapToOrderCreateDtoList() {
        //Given
        Order order1 = new Order(
                1L,
                "description1",
                BigDecimal.valueOf(100.0),
                new User()
        );
        Order order2 = new Order(
                2L,
                "description2",
                BigDecimal.valueOf(200.0),
                new User()
        );
        Order order3 = new Order(
                3L,
                "description3",
                BigDecimal.valueOf(300.0),
                new User()
        );
        List<Order> orderList = Arrays.asList(order1, order2, order3);

        //When
        List<OrderCreateDto> orderCreateDtoList = orderMapper.mapToOrderCreateDtoList(orderList);

        //Then
        Assertions.assertEquals("description1", orderCreateDtoList.get(0).getDescription());
        Assertions.assertEquals(BigDecimal.valueOf(100.0), orderCreateDtoList.get(0).getSum());
        Assertions.assertEquals("description2", orderCreateDtoList.get(1).getDescription());
        Assertions.assertEquals(BigDecimal.valueOf(200.0), orderCreateDtoList.get(1).getSum());
        Assertions.assertEquals("description3", orderCreateDtoList.get(2).getDescription());
        Assertions.assertEquals(BigDecimal.valueOf(300.0), orderCreateDtoList.get(2).getSum());
    }

    @Test
    void mapToOrderEditDtoList() {
        //Given
        Order order1 = new Order(
                1L,
                "description1",
                BigDecimal.valueOf(100.0),
                new User()
        );
        Order order2 = new Order(
                2L,
                "description2",
                BigDecimal.valueOf(200.0),
                new User()
        );
        Order order3 = new Order(
                3L,
                "description3",
                BigDecimal.valueOf(300.0),
                new User()
        );
        List<Order> orderList = Arrays.asList(order1, order2, order3);

        //When
        List<OrderEditDto> orderEditDtoList = orderMapper.mapToOrderEditDtoList(orderList);

        //Then
        Assertions.assertEquals("description1", orderEditDtoList.get(0).getDescription());
        Assertions.assertEquals(BigDecimal.valueOf(100.0), orderEditDtoList.get(0).getSum());
        Assertions.assertEquals("description2", orderEditDtoList.get(1).getDescription());
        Assertions.assertEquals(BigDecimal.valueOf(200.0), orderEditDtoList.get(1).getSum());
        Assertions.assertEquals("description3", orderEditDtoList.get(2).getDescription());
        Assertions.assertEquals(BigDecimal.valueOf(300.0), orderEditDtoList.get(2).getSum());
    }
}
