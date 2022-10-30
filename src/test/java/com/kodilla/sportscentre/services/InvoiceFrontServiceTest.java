package com.kodilla.sportscentre.services;

import com.kodilla.sportscentre.domain.Invoice;
import com.kodilla.sportscentre.domain.InvoiceCreateDto;
import com.kodilla.sportscentre.domain.User;
import com.kodilla.sportscentre.domain.enums.Goals;
import com.kodilla.sportscentre.domain.enums.PaymentStatus;
import com.kodilla.sportscentre.exceptions.InvoiceNotFoundException;
import com.kodilla.sportscentre.repositories.InvoiceRepository;
import com.kodilla.sportscentre.repositories.UserRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

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

    @Test
    void shouldGetInvoicesByUserId() throws Exception {

        //Given
        User user = new User(0L,
                "name1", "surname1",
                LocalDate.of(1990, 12, 12),
                "email1",
                "phone1",
                Goals.HEALTH,
                true,
                true,
                true,
                null,
                true,
                new ArrayList<>(),
                LocalDate.of(2022, 11, 18)
        );
        user = userRepository.save(user);

        Invoice invoice1 = new Invoice(
                0L,
                PaymentStatus.NOTPAID,
                LocalDate.of(2022, 12, 12),
                BigDecimal.valueOf(150.0),
                user
        );
        Invoice invoice2 = new Invoice(
                0L,
                PaymentStatus.NOTPAID,
                LocalDate.of(2022, 12, 12),
                BigDecimal.valueOf(150.0),
                user
        );
        Invoice invoice3 = new Invoice(
                0L,
                PaymentStatus.NOTPAID,
                LocalDate.of(2022, 12, 12),
                BigDecimal.valueOf(150.0),
                user
        );

        invoice1 = invoiceRepository.save(invoice1);
        invoice2 = invoiceRepository.save(invoice2);
        invoice3 = invoiceRepository.save(invoice3);

        //When
        List<Invoice> invoices = invoiceFrontService.getInvoicesByUserId(user.getUserId());

        //Then
        Assertions.assertEquals(3, invoices.size());
        Assertions.assertEquals(invoice1.getInvoiceId(), invoices.get(0).getInvoiceId());
        Assertions.assertEquals(invoice2.getInvoiceId(), invoices.get(1).getInvoiceId());
        Assertions.assertEquals(invoice3.getInvoiceId(), invoices.get(2).getInvoiceId());

        //CleanUp
        invoiceRepository.delete(invoice1);
        invoiceRepository.delete(invoice2);
        invoiceRepository.delete(invoice3);
        userRepository.delete(user);
    }
}
