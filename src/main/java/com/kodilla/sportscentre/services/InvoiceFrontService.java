package com.kodilla.sportscentre.services;

import com.kodilla.sportscentre.domain.Invoice;
import com.kodilla.sportscentre.domain.enums.PaymentStatus;
import com.kodilla.sportscentre.exceptions.InvoiceNotFoundException;
import com.kodilla.sportscentre.repositories.InvoiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InvoiceFrontService {

    private final InvoiceRepository invoiceRepository;

    public Invoice setPaymentPaidStatus(final Long invoiceId) throws InvoiceNotFoundException {
        Invoice invoice = invoiceRepository.findById(invoiceId).orElseThrow(InvoiceNotFoundException::new);
        invoice.setPaymentStatus(PaymentStatus.PAID);
        return invoiceRepository.save(invoice);
    }

    public Invoice setPaymentNotPaidStatus(final Long invoiceId) throws InvoiceNotFoundException {
        Invoice invoice = invoiceRepository.findById(invoiceId).orElseThrow(InvoiceNotFoundException::new);
        invoice.setPaymentStatus(PaymentStatus.NOTPAID);
        return invoiceRepository.save(invoice);
    }

    public List<Invoice> getInvoicesByUserId(Long userId) {
        List<Invoice> invoices = invoiceRepository.findAllByUser_UserId(userId);
        return invoices;
    }
}
