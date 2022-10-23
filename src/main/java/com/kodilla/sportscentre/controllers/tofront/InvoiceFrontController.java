package com.kodilla.sportscentre.controllers.tofront;

import com.kodilla.sportscentre.domain.Invoice;
import com.kodilla.sportscentre.exceptions.InvoiceNotFoundException;
import com.kodilla.sportscentre.services.InvoiceFrontService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/invoice_status")
@RequiredArgsConstructor
@CrossOrigin("*")
public class InvoiceFrontController {

    private final InvoiceFrontService invoiceFrontService;

    @PutMapping(value = "/paid/{invoiceId}")
    public ResponseEntity<Invoice> setPaymentPaidStatus(@PathVariable Long invoiceId) throws InvoiceNotFoundException {
        return ResponseEntity.ok(invoiceFrontService.setPaymentPaidStatus(invoiceId));
    }

    @PutMapping(value = "/not_paid/{invoiceId}")
    public ResponseEntity<Invoice> setPaymentNotPaidStatus(@PathVariable Long invoiceId) throws InvoiceNotFoundException {
        return ResponseEntity.ok(invoiceFrontService.setPaymentNotPaidStatus(invoiceId));
    }

    @GetMapping(value = "{userId}")
    public ResponseEntity<List<Invoice>> getInvoicesByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(invoiceFrontService.getInvoicesByUserId(userId));
    }
}
