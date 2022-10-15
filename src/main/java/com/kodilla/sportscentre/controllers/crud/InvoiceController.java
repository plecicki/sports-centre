package com.kodilla.sportscentre.controllers.crud;

import com.kodilla.sportscentre.domain.Invoice;
import com.kodilla.sportscentre.domain.InvoiceCreateDto;
import com.kodilla.sportscentre.domain.InvoiceEditDto;
import com.kodilla.sportscentre.exceptions.InvoiceNotFoundException;
import com.kodilla.sportscentre.services.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/invoice")
@RequiredArgsConstructor
@CrossOrigin("*")
public class InvoiceController {

    private final InvoiceService invoiceService;

    @GetMapping
    public ResponseEntity<List<Invoice>> getInvoices() {
        return ResponseEntity.ok(invoiceService.getAllInvoices());
    }

    @GetMapping(value = "{invoiceId}")
    public ResponseEntity<Invoice> getInvoice(@PathVariable Long invoiceId) throws InvoiceNotFoundException {
        return ResponseEntity.ok(invoiceService.getInvoiceById(invoiceId));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Invoice> createInvoice(@RequestBody InvoiceCreateDto invoiceCreateDto) {
        return ResponseEntity.ok(invoiceService.createInvoice(invoiceCreateDto));
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Invoice> editInvoice(@RequestBody InvoiceEditDto invoiceEditDto) {
        return ResponseEntity.ok(invoiceService.editInvoice(invoiceEditDto));
    }

    @DeleteMapping(value = "{invoiceId}")
    public ResponseEntity<Void> deleteInvoice(@PathVariable Long invoiceId) throws InvoiceNotFoundException {
        invoiceService.deleteInvoice(invoiceId);
        return ResponseEntity.ok().build();
    }
}
