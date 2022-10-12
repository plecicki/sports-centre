package com.kodilla.sportscentre.repositories;

import com.kodilla.sportscentre.domain.Card;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends CrudRepository<Card, Long> {
    @Override
    List<Card> findAll();
}
