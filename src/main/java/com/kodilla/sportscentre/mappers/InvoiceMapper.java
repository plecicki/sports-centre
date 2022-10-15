package com.kodilla.sportscentre.mappers;

import com.kodilla.sportscentre.domain.Invoice;
import com.kodilla.sportscentre.domain.InvoiceCreateDto;
import com.kodilla.sportscentre.domain.InvoiceEditDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvoiceMapper {

    public Invoice mapToInvoiceFromCreate(final InvoiceCreateDto invoiceCreateDto) {
        return new Invoice(
                0L,
                invoiceCreateDto.getPaymentStatus(),
                invoiceCreateDto.getPaymentDeadline(),
                invoiceCreateDto.getSum(),
                invoiceCreateDto.getUser()
        );
    }

    public Invoice mapToInvoiceFromEdit(final InvoiceEditDto invoiceEditDto) {
        return new Invoice(
                invoiceEditDto.getInvoiceId(),
                invoiceEditDto.getPaymentStatus(),
                invoiceEditDto.getPaymentDeadline(),
                invoiceEditDto.getSum(),
                invoiceEditDto.getUser()
        );
    }

    public InvoiceCreateDto mapToInvoiceCreateDto(final Invoice invoice) {
        return new InvoiceCreateDto(
                invoice.getPaymentStatus(),
                invoice.getPaymentDeadline(),
                invoice.getSum(),
                invoice.getUser()
        );
    }

    public InvoiceEditDto mapToInvoiceEditDto(final Invoice invoice) {
        return new InvoiceEditDto(
                invoice.getInvoiceId(),
                invoice.getPaymentStatus(),
                invoice.getPaymentDeadline(),
                invoice.getSum(),
                invoice.getUser()
        );
    }

    public List<InvoiceCreateDto> mapToUserCreateList(final List<Invoice> invoiceList) {
        return invoiceList.stream()
                .map(this::mapToInvoiceCreateDto)
                .collect(Collectors.toList());
    }

    public List<InvoiceEditDto> mapToUserEditList(final List<Invoice> invoiceList) {
        return invoiceList.stream()
                .map(this::mapToInvoiceEditDto)
                .collect(Collectors.toList());
    }
}
