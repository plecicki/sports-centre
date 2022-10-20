package com.kodilla.sportscentre.controllers.tofront;

import com.kodilla.sportscentre.domain.AccountCreateDto;
import com.kodilla.sportscentre.domain.AccountInDto;
import com.kodilla.sportscentre.domain.AccountOutDto;
import com.kodilla.sportscentre.exceptions.WrongPasswordException;
import com.kodilla.sportscentre.exceptions.WrongUsernameException;
import com.kodilla.sportscentre.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/account")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AccountController {

    private final AccountService accountService;

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createAccount(@RequestBody AccountCreateDto accountCreateDto) {
        accountService.createAccount(accountCreateDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountOutDto> login(@RequestBody AccountInDto accountInDto) throws
            WrongUsernameException, WrongPasswordException {
        AccountOutDto accountOutDto = accountService.login(accountInDto);
        return ResponseEntity.ok(accountOutDto);
    }
}
