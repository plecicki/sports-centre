package com.kodilla.sportscentre.domain;

import com.kodilla.sportscentre.domain.enums.PaymentStatus;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "INVOICES")
public class Invoice {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "INVOICE_ID", unique = true)
    private Long invoiceId;

    @Column(name = "PAYMENT_STATUS")
    private PaymentStatus paymentStatus;

    @Column(name = "PAYMENT_DEADLINE")
    private LocalDate paymentDeadline;

    @Column(name = "SUM")
    private BigDecimal sum;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;
}
