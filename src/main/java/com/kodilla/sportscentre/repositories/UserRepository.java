package com.kodilla.sportscentre.repositories;

import com.kodilla.sportscentre.domain.Card;
import com.kodilla.sportscentre.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    @Override
    List<User> findAll();

    List<User> findAllByCard_CardId(Long cardId);

    Optional<User> findByCard_CardId(Long cardId);
}
