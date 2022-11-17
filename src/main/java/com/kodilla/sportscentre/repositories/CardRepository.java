package com.kodilla.sportscentre.repositories;

import com.kodilla.sportscentre.domain.Card;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CardRepository extends CrudRepository<Card, Long> {
    @Override
    List<Card> findAll();

    List<Card> findAllByUser_UserId(Long userId);

    Optional<Card> findByUser_UserId(Long userId);

    Optional<Card> findByCardId(Long cardId);
}
