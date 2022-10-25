package com.kodilla.sportscentre.repositories;

import com.kodilla.sportscentre.domain.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {

    Optional<Account> findByUsername(String username);

    boolean existsAccountByUsername(String username);

    Boolean existsByUsername(String name);
}
