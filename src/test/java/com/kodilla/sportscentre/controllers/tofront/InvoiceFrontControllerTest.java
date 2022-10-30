package com.kodilla.sportscentre.controllers.tofront;

import com.kodilla.sportscentre.domain.Invoice;
import com.kodilla.sportscentre.domain.User;
import com.kodilla.sportscentre.domain.enums.Goals;
import com.kodilla.sportscentre.domain.enums.PaymentStatus;
import com.kodilla.sportscentre.services.InvoiceFrontService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(InvoiceFrontController.class)
public class InvoiceFrontControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InvoiceFrontService invoiceFrontService;

    @Test
    void shouldChangePaymentStatusToPaid() throws Exception {

        //Given
        Invoice invoice = new Invoice(
                1L,
                PaymentStatus.PAID,
                LocalDate.of(2022, 12, 12),
                BigDecimal.valueOf(150.0),
                new User()
        );

        when(invoiceFrontService.setPaymentPaidStatus(1L)).thenReturn(invoice);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/v1/invoice_status/paid/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void shouldChangePaymentStatusToNotPaid() throws Exception {

        //Given
        Invoice invoice = new Invoice(
                1L,
                PaymentStatus.NOTPAID,
                LocalDate.of(2022, 12, 12),
                BigDecimal.valueOf(150.0),
                new User()
        );

        when(invoiceFrontService.setPaymentPaidStatus(1L)).thenReturn(invoice);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/v1/invoice_status/not_paid/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void shouldGetInvoicesByUserId() throws Exception {

        //Given
        User user = new User(1L,
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
        Invoice invoice1 = new Invoice(
                1L,
                PaymentStatus.NOTPAID,
                LocalDate.of(2022, 12, 12),
                BigDecimal.valueOf(150.0),
                user
        );
        Invoice invoice2 = new Invoice(
                2L,
                PaymentStatus.NOTPAID,
                LocalDate.of(2022, 12, 12),
                BigDecimal.valueOf(150.0),
                user
        );
        Invoice invoice3 = new Invoice(
                3L,
                PaymentStatus.NOTPAID,
                LocalDate.of(2022, 12, 12),
                BigDecimal.valueOf(150.0),
                user
        );
        List<Invoice> invoices = Arrays.asList(invoice1, invoice2, invoice3);

        when(invoiceFrontService.getInvoicesByUserId(1L)).thenReturn(invoices);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/invoice_status/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].invoiceId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].invoiceId", Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].invoiceId", Matchers.is(3)));
    }
}
