package com.kodilla.sportscentre.services;

import com.kodilla.sportscentre.domain.Invoice;
import com.kodilla.sportscentre.domain.InvoiceCreateDto;
import com.kodilla.sportscentre.domain.InvoiceEditDto;
import com.kodilla.sportscentre.domain.User;
import com.kodilla.sportscentre.domain.enums.Goals;
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
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class InvoiceServiceTest {

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    void shouldFetchListWithInvoices() {
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
                LocalDate.now().plusMonths(1)
        );

        User savedUser = userRepository.save(user);

        Invoice invoice1 = new Invoice(
                0L,
                PaymentStatus.PAID,
                LocalDate.of(2024, 12, 12),
                BigDecimal.valueOf(150.0),
                savedUser
        );
        Invoice invoice2 = new Invoice(
                0L,
                PaymentStatus.PAID,
                LocalDate.of(2024, 12, 12),
                BigDecimal.valueOf(150.0),
                savedUser
        );
        Invoice invoice3 = new Invoice(
                0L,
                PaymentStatus.PAID,
                LocalDate.of(2024, 12, 12),
                BigDecimal.valueOf(150.0),
                savedUser
        );

        Invoice savedInvoice1 = invoiceRepository.save(invoice1);
        Invoice savedInvoice2 = invoiceRepository.save(invoice2);
        Invoice savedInvoice3 = invoiceRepository.save(invoice3);

        //When
        List<Invoice> invoiceList = invoiceService.getAllInvoices();

        //Then
        Assertions.assertEquals(savedInvoice1.getInvoiceId(), invoiceList.get(invoiceList.size()-3).getInvoiceId());
        Assertions.assertEquals(PaymentStatus.PAID, invoiceList.get(invoiceList.size()-3).getPaymentStatus());
        Assertions.assertEquals(LocalDate.of(2024, 12, 12), invoiceList.get(invoiceList.size()-3).getPaymentDeadline());
        Assertions.assertEquals(BigDecimal.valueOf(150.0), invoiceList.get(invoiceList.size()-3).getSum());
        Assertions.assertEquals(savedInvoice2.getInvoiceId(), invoiceList.get(invoiceList.size()-2).getInvoiceId());
        Assertions.assertEquals(PaymentStatus.PAID, invoiceList.get(invoiceList.size()-2).getPaymentStatus());
        Assertions.assertEquals(LocalDate.of(2024, 12, 12), invoiceList.get(invoiceList.size()-2).getPaymentDeadline());
        Assertions.assertEquals(BigDecimal.valueOf(150.0), invoiceList.get(invoiceList.size()-2).getSum());
        Assertions.assertEquals(savedInvoice3.getInvoiceId(), invoiceList.get(invoiceList.size()-1).getInvoiceId());
        Assertions.assertEquals(PaymentStatus.PAID, invoiceList.get(invoiceList.size()-1).getPaymentStatus());
        Assertions.assertEquals(LocalDate.of(2024, 12, 12), invoiceList.get(invoiceList.size()-1).getPaymentDeadline());
        Assertions.assertEquals(BigDecimal.valueOf(150.0), invoiceList.get(invoiceList.size()-1).getSum());

        //CleanUp
        invoiceRepository.delete(savedInvoice1);
        invoiceRepository.delete(savedInvoice2);
        invoiceRepository.delete(savedInvoice3);
        userRepository.delete(savedUser);
    }

    @Test
    void shouldFetchInvoice() throws InvoiceNotFoundException {
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
                LocalDate.now().plusMonths(1)
        );

        User savedUser = userRepository.save(user);

        Invoice invoice1 = new Invoice(
                0L,
                PaymentStatus.PAID,
                LocalDate.of(2024, 12, 12),
                BigDecimal.valueOf(150.0),
                savedUser
        );

        Invoice savedInvoice1 = invoiceRepository.save(invoice1);

        //When
        Invoice invoiceFromDB = invoiceService.getInvoiceById(savedInvoice1.getInvoiceId());

        //Then
        Assertions.assertEquals(savedInvoice1.getInvoiceId(), invoiceFromDB.getInvoiceId());
        Assertions.assertEquals(PaymentStatus.PAID, invoiceFromDB.getPaymentStatus());
        Assertions.assertEquals(LocalDate.of(2024, 12, 12), invoiceFromDB.getPaymentDeadline());
        Assertions.assertEquals(BigDecimal.valueOf(150.0), invoiceFromDB.getSum());

        //CleanUp
        invoiceRepository.delete(savedInvoice1);
        userRepository.delete(savedUser);
    }

    @Test
    void shouldCreateInvoice() {
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
                LocalDate.now().plusMonths(1)
        );

        User savedUser = userRepository.save(user);

        InvoiceCreateDto invoiceCreateDto = new InvoiceCreateDto(
                PaymentStatus.PAID,
                LocalDate.of(2024, 12, 12),
                BigDecimal.valueOf(150.0),
                savedUser
        );

        //When
        Invoice createdInvoice = invoiceService.createInvoice(invoiceCreateDto);

        //Then
        Assertions.assertNotNull(createdInvoice.getInvoiceId());

        //CleanUp
        invoiceRepository.delete(createdInvoice);
        userRepository.delete(savedUser);
    }

    @Test
    void shouldEditInvoice() {
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
                LocalDate.now().plusMonths(1)
        );

        User savedUser = userRepository.save(user);

        InvoiceCreateDto invoiceCreateDto = new InvoiceCreateDto(
                PaymentStatus.PAID,
                LocalDate.of(2024, 12, 12),
                BigDecimal.valueOf(150.0),
                savedUser
        );

        Invoice createdInvoice = invoiceService.createInvoice(invoiceCreateDto);

        InvoiceEditDto invoiceEditDto = new InvoiceEditDto(
                createdInvoice.getInvoiceId(),
                PaymentStatus.NOTPAID,
                LocalDate.of(2024, 12, 12),
                BigDecimal.valueOf(200.0),
                savedUser
        );

        //When
        Invoice editedInvoice = invoiceService.editInvoice(invoiceEditDto);

        //Then
        Assertions.assertEquals(createdInvoice.getInvoiceId(), editedInvoice.getInvoiceId());
        Assertions.assertEquals(PaymentStatus.NOTPAID, editedInvoice.getPaymentStatus());
        Assertions.assertEquals(LocalDate.of(2024, 12, 12), editedInvoice.getPaymentDeadline());
        Assertions.assertEquals(BigDecimal.valueOf(200.0), editedInvoice.getSum());
    }

    @Test
    void deleteInvoiceTest() {
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
                LocalDate.now().plusMonths(1)
        );

        User savedUser = userRepository.save(user);

        InvoiceCreateDto invoiceCreateDto = new InvoiceCreateDto(
                PaymentStatus.PAID,
                LocalDate.of(2024, 12, 12),
                BigDecimal.valueOf(150.0),
                savedUser
        );

        Invoice createdInvoice = invoiceService.createInvoice(invoiceCreateDto);

        //When
        try {
            invoiceService.deleteInvoice(createdInvoice.getInvoiceId());
        } catch (InvoiceNotFoundException e) {
            System.out.println(e);
        }

        //Then
        boolean invoiceNotFound = false;
        Invoice invoiceAfterDelete = new Invoice();
        try {
            invoiceAfterDelete = invoiceService.getInvoiceById(createdInvoice.getInvoiceId());
        } catch (InvoiceNotFoundException e) {
            invoiceNotFound = true;
        }
        Assertions.assertNull(invoiceAfterDelete.getInvoiceId());
        Assertions.assertTrue(invoiceNotFound);
    }
}
