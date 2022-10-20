package com.kodilla.sportscentre.services;

import com.kodilla.sportscentre.domain.*;
import com.kodilla.sportscentre.domain.enums.Role;
import com.kodilla.sportscentre.exceptions.WrongPasswordException;
import com.kodilla.sportscentre.exceptions.WrongUsernameException;
import com.kodilla.sportscentre.repositories.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public void createAccount(AccountCreateDto accountCreateDto) {
        String username = accountCreateDto.getUsername();
        String passwordSalt = RandomStringUtils.random(32);
        String passwordHash = DigestUtils.sha512Hex(accountCreateDto.getPassword()+passwordSalt);
        Role role = accountCreateDto.getRole();
        User user = accountCreateDto.getUser();

        Account account = new Account(
                0L,
                username,
                passwordSalt,
                passwordHash,
                role,
                user
        );

        accountRepository.save(account);
    }

    public AccountOutDto login(AccountInDto accountInDto) throws WrongUsernameException, WrongPasswordException {

        Account account = accountRepository.findByUsername(accountInDto.getUsername()).orElseThrow(WrongUsernameException::new);

        if (DigestUtils.sha512Hex(accountInDto.getPassword() + account.getPasswordSalt()).equals(account.getPasswordHash())) {
            AccountOutDto accountOutDto = new AccountOutDto(
                    account.getUsername(),
                    account.getRole(),
                    account.getUser()
            );
            return accountOutDto;
        } else {
            throw new WrongPasswordException();
        }
    }
}
