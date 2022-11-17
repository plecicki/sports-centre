package com.kodilla.sportscentre.services;

import com.kodilla.sportscentre.domain.Invoice;
import com.kodilla.sportscentre.domain.InvoiceCreateDto;
import com.kodilla.sportscentre.domain.InvoiceEditDto;
import com.kodilla.sportscentre.exceptions.InvoiceNotFoundException;
import com.kodilla.sportscentre.mappers.InvoiceMapper;
import com.kodilla.sportscentre.repositories.InvoiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final InvoiceMapper invoiceMapper;

    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    public Invoice getInvoiceById(final Long invoiceId) throws InvoiceNotFoundException {
        return invoiceRepository.findById(invoiceId).orElseThrow(InvoiceNotFoundException::new);
    }

    public Invoice createInvoice(final InvoiceCreateDto invoiceCreateDto) {
        Invoice invoice = invoiceMapper.mapToInvoiceFromCreate(invoiceCreateDto);
        return invoiceRepository.save(invoice);
    }

    public Invoice editInvoice(final InvoiceEditDto invoiceEditDto) {
        Invoice invoice = invoiceMapper.mapToInvoiceFromEdit(invoiceEditDto);
        return invoiceRepository.save(invoice);
    }

    public void deleteInvoice(final Long invoiceId) throws InvoiceNotFoundException {
        if(!invoiceRepository.existsById(invoiceId)) throw new InvoiceNotFoundException();
        invoiceRepository.deleteById(invoiceId);
    }
}
