package com.kodilla.sportscentre.controller.crud;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kodilla.sportscentre.controller.serializer.LocalDateSerializer;
import com.kodilla.sportscentre.controllers.crud.InvoiceController;
import com.kodilla.sportscentre.domain.Invoice;
import com.kodilla.sportscentre.domain.InvoiceCreateDto;
import com.kodilla.sportscentre.domain.InvoiceEditDto;
import com.kodilla.sportscentre.domain.User;
import com.kodilla.sportscentre.domain.enums.Goals;
import com.kodilla.sportscentre.domain.enums.PaymentStatus;
import com.kodilla.sportscentre.services.InvoiceService;
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
@WebMvcTest(InvoiceController.class)
public class InvoiceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InvoiceService invoiceService;

    @Test
    void shouldFetchListWithInvoices() throws Exception {
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
                LocalDate.now().plusMonths(1)
        );
        Invoice invoice1 = new Invoice(
                1L,
                PaymentStatus.PAID,
                LocalDate.of(2024, 12, 12),
                BigDecimal.valueOf(150.0),
                user
        );
        Invoice invoice2 = new Invoice(
                2L,
                PaymentStatus.PAID,
                LocalDate.of(2024, 12, 12),
                BigDecimal.valueOf(150.0),
                user
        );
        Invoice invoice3 = new Invoice(
                3L,
                PaymentStatus.PAID,
                LocalDate.of(2024, 12, 12),
                BigDecimal.valueOf(150.0),
                user
        );
        List<Invoice> invoiceList = Arrays.asList(invoice1, invoice2, invoice3);

        when(invoiceService.getAllInvoices()).thenReturn(invoiceList);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/invoice")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(3)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].invoiceId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].paymentStatus", Matchers.is("PAID")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].paymentDeadline", Matchers.is("2024-12-12")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].sum", Matchers.is(150.0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].user.userId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].invoiceId", Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].paymentStatus", Matchers.is("PAID")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].paymentDeadline", Matchers.is("2024-12-12")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].sum", Matchers.is(150.0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].user.userId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].invoiceId", Matchers.is(3)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].paymentStatus", Matchers.is("PAID")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].paymentDeadline", Matchers.is("2024-12-12")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].sum", Matchers.is(150.0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].user.userId", Matchers.is(1)));
    }

    @Test
    void shouldFetchInvoice() throws Exception {
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
                LocalDate.now().plusMonths(1)
        );
        Invoice invoice = new Invoice(
                1L,
                PaymentStatus.PAID,
                LocalDate.of(2024, 12, 12),
                BigDecimal.valueOf(150.0),
                user
        );

        when(invoiceService.getInvoiceById(1L)).thenReturn(invoice);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/invoice/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.invoiceId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.paymentStatus", Matchers.is("PAID")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.paymentDeadline", Matchers.is("2024-12-12")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.sum", Matchers.is(150.0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.user.userId", Matchers.is(1)));
    }

    @Test
    void shouldCreateInvoice() throws Exception {
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
                LocalDate.now().plusMonths(1)
        );
        Invoice invoice = new Invoice(
                1L,
                PaymentStatus.PAID,
                LocalDate.of(2024, 12, 12),
                BigDecimal.valueOf(150.0),
                user
        );
        InvoiceCreateDto invoiceCreateDto = new InvoiceCreateDto(
                PaymentStatus.PAID,
                LocalDate.of(2024, 12, 12),
                BigDecimal.valueOf(150.0),
                user
        );

        when(invoiceService.createInvoice(invoiceCreateDto)).thenReturn(invoice);

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new LocalDateSerializer())
                .create();
        String jsonContent = gson.toJson(invoiceCreateDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/v1/invoice")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void shouldUpdateInvoice() throws Exception {
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
                LocalDate.now().plusMonths(1)
        );
        Invoice invoice = new Invoice(
                1L,
                PaymentStatus.PAID,
                LocalDate.of(2024, 12, 12),
                BigDecimal.valueOf(150.0),
                user
        );
        InvoiceEditDto invoiceEditDto = new InvoiceEditDto(
                1L,
                PaymentStatus.PAID,
                LocalDate.of(2024, 12, 12),
                BigDecimal.valueOf(150.0),
                user
        );

        when(invoiceService.editInvoice(invoiceEditDto)).thenReturn(invoice);

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new LocalDateSerializer())
                .create();
        String jsonContent = gson.toJson(invoiceEditDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/v1/invoice")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void shouldDeleteInvoice() throws Exception {
        //Given & When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/v1/invoice/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }
}
