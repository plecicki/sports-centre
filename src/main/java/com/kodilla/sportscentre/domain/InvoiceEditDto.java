package com.kodilla.sportscentre.domain;

import com.kodilla.sportscentre.domain.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class InvoiceEditDto {
    private Long invoiceId;
    private PaymentStatus paymentStatus;
    private LocalDate paymentDeadline;
    private Double sum;
    private User user;
}