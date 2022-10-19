package com.kodilla.sportscentre.services;

import com.kodilla.sportscentre.domain.Invoice;
import com.kodilla.sportscentre.domain.InvoiceCreateDto;
import com.kodilla.sportscentre.domain.User;
import com.kodilla.sportscentre.domain.enums.PaymentStatus;
import com.kodilla.sportscentre.exceptions.InvoiceNotFoundException;
import com.kodilla.sportscentre.repositories.InvoiceRepository;
import com.kodilla.sportscentre.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootTest
public class InvoiceFrontServiceTest {

    @Autowired
    private InvoiceFrontService invoiceFrontService;

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    void setPaymentPaidStatusTest() {
        //Given
        User user = new User();
        InvoiceCreateDto invoiceCreateDto = new InvoiceCreateDto(
                PaymentStatus.NOTPAID,
                LocalDate.of(2022, 12, 12),
                BigDecimal.valueOf(150.0),
                user
        );

        userRepository.save(user);
        Invoice createdInvoice = invoiceService.createInvoice(invoiceCreateDto);

        //When
        Invoice editedInvoice = new Invoice();
        try {
            editedInvoice = invoiceFrontService.setPaymentPaidStatus(createdInvoice.getInvoiceId());
        } catch (InvoiceNotFoundException e) {
            System.out.println(e);
        }

        //Then
        Assertions.assertEquals(PaymentStatus.PAID, editedInvoice.getPaymentStatus());

        //CleanUp
        invoiceRepository.delete(createdInvoice);
        userRepository.delete(user);
    }

    @Test
    void setPaymentNotPaidStatusTest() {
        //Given
        User user = new User();
        InvoiceCreateDto invoiceCreateDto = new InvoiceCreateDto(
                PaymentStatus.PAID,
                LocalDate.of(2022, 12, 12),
                BigDecimal.valueOf(150.0),
                user
        );

        userRepository.save(user);
        Invoice createdInvoice = invoiceService.createInvoice(invoiceCreateDto);

        //When
        Invoice editedInvoice = new Invoice();
        try {
            editedInvoice = invoiceFrontService.setPaymentNotPaidStatus(createdInvoice.getInvoiceId());
        } catch (InvoiceNotFoundException e) {
            System.out.println(e);
        }

        //Then
        Assertions.assertEquals(PaymentStatus.NOTPAID, editedInvoice.getPaymentStatus());

        //CleanUp
        invoiceRepository.delete(createdInvoice);
        userRepository.delete(user);
    }
}
