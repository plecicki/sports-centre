package com.kodilla.sportscentre.controllers.tofront;

import com.kodilla.sportscentre.domain.OrderCreateDto;
import com.kodilla.sportscentre.domain.OrderDecInDto;
import com.kodilla.sportscentre.services.SupplementsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/supplements")
@RequiredArgsConstructor
@CrossOrigin("*")
public class SupplementsController {

    private final SupplementsService supplementsService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderCreateDto> createOrder(@RequestBody OrderDecInDto orderDecInDto) {
        return ResponseEntity.ok(supplementsService.createOrder(orderDecInDto));
    }
}
