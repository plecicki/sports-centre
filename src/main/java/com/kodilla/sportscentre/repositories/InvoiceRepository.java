package com.kodilla.sportscentre.repositories;

import com.kodilla.sportscentre.domain.Invoice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceRepository extends CrudRepository<Invoice, Long> {

    @Override
    List<Invoice> findAll();
}
