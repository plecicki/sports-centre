package com.kodilla.sportscentre.mappers;

import com.kodilla.sportscentre.domain.Invoice;
import com.kodilla.sportscentre.domain.InvoiceCreateDto;
import com.kodilla.sportscentre.domain.InvoiceEditDto;
import com.kodilla.sportscentre.domain.enums.PaymentStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class InvoiceMapperTest {

    @Autowired
    private InvoiceMapper invoiceMapper;

    @Test
    void mapToInvoiceFromCreateTest() {
        //Given
        InvoiceCreateDto invoiceCreateDto = new InvoiceCreateDto(
                PaymentStatus.PAID,
                LocalDate.of(2024, 12, 12),
                BigDecimal.valueOf(150.0),
                null
        );

        //When
        Invoice invoice = invoiceMapper.mapToInvoiceFromCreate(invoiceCreateDto);

        //Then
        Assertions.assertEquals(PaymentStatus.PAID, invoice.getPaymentStatus());
        Assertions.assertEquals(LocalDate.of(2024, 12, 12), invoice.getPaymentDeadline());
        Assertions.assertEquals(BigDecimal.valueOf(150.0), invoice.getSum());
        Assertions.assertEquals(null, invoice.getUser());
    }

    @Test
    void mapToInvoiceFromEditTest() {
        //Given
        InvoiceEditDto invoiceEditDto = new InvoiceEditDto(
                1L,
                PaymentStatus.PAID,
                LocalDate.of(2024, 12, 12),
                BigDecimal.valueOf(150.0),
                null
        );

        //When
        Invoice invoice = invoiceMapper.mapToInvoiceFromEdit(invoiceEditDto);

        //Then
        Assertions.assertEquals(PaymentStatus.PAID, invoice.getPaymentStatus());
        Assertions.assertEquals(LocalDate.of(2024, 12, 12), invoice.getPaymentDeadline());
        Assertions.assertEquals(BigDecimal.valueOf(150.0), invoice.getSum());
        Assertions.assertEquals(null, invoice.getUser());
    }

    @Test
    void mapToInvoiceCreateDtoTest() {
        //Given
        Invoice invoice = new Invoice(
                1L,
                PaymentStatus.PAID,
                LocalDate.of(2024, 12, 12),
                BigDecimal.valueOf(150.0),
                null
        );

        //When
        InvoiceCreateDto invoiceCreateDto = invoiceMapper.mapToInvoiceCreateDto(invoice);

        //Then
        Assertions.assertEquals(PaymentStatus.PAID, invoiceCreateDto.getPaymentStatus());
        Assertions.assertEquals(LocalDate.of(2024, 12, 12), invoiceCreateDto.getPaymentDeadline());
        Assertions.assertEquals(BigDecimal.valueOf(150.0), invoiceCreateDto.getSum());
        Assertions.assertEquals(null, invoiceCreateDto.getUser());
    }

    @Test
    void mapToInvoiceEditDtoTest() {
        //Given
        Invoice invoice = new Invoice(
                1L,
                PaymentStatus.PAID,
                LocalDate.of(2024, 12, 12),
                BigDecimal.valueOf(150.0),
                null
        );

        //When
        InvoiceEditDto invoiceEditDto = invoiceMapper.mapToInvoiceEditDto(invoice);

        //Then
        Assertions.assertEquals(PaymentStatus.PAID, invoiceEditDto.getPaymentStatus());
        Assertions.assertEquals(LocalDate.of(2024, 12, 12), invoiceEditDto.getPaymentDeadline());
        Assertions.assertEquals(BigDecimal.valueOf(150.0), invoiceEditDto.getSum());
        Assertions.assertEquals(null, invoiceEditDto.getUser());
    }

    @Test
    void mapToUserCreateListTest() {
        //Given
        Invoice invoice1 = new Invoice(
                1L,
                PaymentStatus.PAID,
                LocalDate.of(2024, 12, 12),
                BigDecimal.valueOf(150.0),
                null
        );
        Invoice invoice2 = new Invoice(
                2L,
                PaymentStatus.PAID,
                LocalDate.of(2024, 12, 12),
                BigDecimal.valueOf(250.0),
                null
        );
        Invoice invoice3 = new Invoice(
                3L,
                PaymentStatus.PAID,
                LocalDate.of(2024, 12, 12),
                BigDecimal.valueOf(350.0),
                null
        );
        List<Invoice> invoiceList = Arrays.asList(invoice1, invoice2, invoice3);

        //When
        List<InvoiceCreateDto> invoiceCreateDtoList = invoiceMapper.mapToUserCreateList(invoiceList);

        //Then
        Assertions.assertEquals(PaymentStatus.PAID, invoiceCreateDtoList.get(0).getPaymentStatus());
        Assertions.assertEquals(LocalDate.of(2024, 12, 12), invoiceCreateDtoList.get(0).getPaymentDeadline());
        Assertions.assertEquals(BigDecimal.valueOf(150.0), invoiceCreateDtoList.get(0).getSum());
        Assertions.assertEquals(null, invoiceCreateDtoList.get(0).getUser());
        Assertions.assertEquals(PaymentStatus.PAID, invoiceCreateDtoList.get(1).getPaymentStatus());
        Assertions.assertEquals(LocalDate.of(2024, 12, 12), invoiceCreateDtoList.get(1).getPaymentDeadline());
        Assertions.assertEquals(BigDecimal.valueOf(250.0), invoiceCreateDtoList.get(1).getSum());
        Assertions.assertEquals(null, invoiceCreateDtoList.get(1).getUser());
        Assertions.assertEquals(PaymentStatus.PAID, invoiceCreateDtoList.get(2).getPaymentStatus());
        Assertions.assertEquals(LocalDate.of(2024, 12, 12), invoiceCreateDtoList.get(2).getPaymentDeadline());
        Assertions.assertEquals(BigDecimal.valueOf(350.0), invoiceCreateDtoList.get(2).getSum());
        Assertions.assertEquals(null, invoiceCreateDtoList.get(2).getUser());
    }

    @Test
    void mapToUserEditListTest() {
        //Given
        Invoice invoice1 = new Invoice(
                1L,
                PaymentStatus.PAID,
                LocalDate.of(2024, 12, 12),
                BigDecimal.valueOf(150.0),
                null
        );
        Invoice invoice2 = new Invoice(
                2L,
                PaymentStatus.PAID,
                LocalDate.of(2024, 12, 12),
                BigDecimal.valueOf(250.0),
                null
        );
        Invoice invoice3 = new Invoice(
                3L,
                PaymentStatus.PAID,
                LocalDate.of(2024, 12, 12),
                BigDecimal.valueOf(350.0),
                null
        );
        List<Invoice> invoiceList = Arrays.asList(invoice1, invoice2, invoice3);

        //When
        List<InvoiceEditDto> invoiceEditDtoList = invoiceMapper.mapToUserEditList(invoiceList);

        //Then
        Assertions.assertEquals(PaymentStatus.PAID, invoiceEditDtoList.get(0).getPaymentStatus());
        Assertions.assertEquals(LocalDate.of(2024, 12, 12), invoiceEditDtoList.get(0).getPaymentDeadline());
        Assertions.assertEquals(BigDecimal.valueOf(150.0), invoiceEditDtoList.get(0).getSum());
        Assertions.assertEquals(null, invoiceEditDtoList.get(0).getUser());
        Assertions.assertEquals(PaymentStatus.PAID, invoiceEditDtoList.get(1).getPaymentStatus());
        Assertions.assertEquals(LocalDate.of(2024, 12, 12), invoiceEditDtoList.get(1).getPaymentDeadline());
        Assertions.assertEquals(BigDecimal.valueOf(250.0), invoiceEditDtoList.get(1).getSum());
        Assertions.assertEquals(null, invoiceEditDtoList.get(1).getUser());
        Assertions.assertEquals(PaymentStatus.PAID, invoiceEditDtoList.get(2).getPaymentStatus());
        Assertions.assertEquals(LocalDate.of(2024, 12, 12), invoiceEditDtoList.get(2).getPaymentDeadline());
        Assertions.assertEquals(BigDecimal.valueOf(350.0), invoiceEditDtoList.get(2).getSum());
        Assertions.assertEquals(null, invoiceEditDtoList.get(2).getUser());
    }
}
