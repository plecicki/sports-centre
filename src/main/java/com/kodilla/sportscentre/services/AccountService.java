package com.kodilla.sportscentre.services;

import com.kodilla.sportscentre.config.CreateAdminKey;
import com.kodilla.sportscentre.domain.*;
import com.kodilla.sportscentre.domain.enums.Role;
import com.kodilla.sportscentre.exceptions.LackOfPermissionToCreateAdminAccount;
import com.kodilla.sportscentre.exceptions.ThisUsernameIsTaken;
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

    private final CreateAdminKey createAdminKey;

    public void createAccount(AccountCreateDto accountCreateDto)
    throws LackOfPermissionToCreateAdminAccount, ThisUsernameIsTaken{
        String username = accountCreateDto.getUsername();
        String passwordSalt = RandomStringUtils.random(32);
        String passwordHash = DigestUtils.sha512Hex(accountCreateDto.getPassword()+passwordSalt);
        Role role = accountCreateDto.getRole();
        User user = accountCreateDto.getUser();
        String adminKey = accountCreateDto.getCreateAdminKey();

        if(role.equals(Role.ADMIN) && !adminKey.equals(createAdminKey.getCreateAdminKey()))
            throw new LackOfPermissionToCreateAdminAccount();

        if(accountRepository.existsAccountByUsername(username))
            throw new ThisUsernameIsTaken();

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

    public Boolean existsUserByUsername(String name) {
        return accountRepository.existsByUsername(name);
    }
}
