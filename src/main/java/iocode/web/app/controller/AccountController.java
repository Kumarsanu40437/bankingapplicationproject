package iocode.web.app.controller;

import iocode.web.app.dto.AccountDto;
import iocode.web.app.dto.ConvertDto;
import iocode.web.app.dto.TransferDto;
import iocode.web.app.entity.Account;
import iocode.web.app.entity.Transaction;
import iocode.web.app.entity.User;
import iocode.web.app.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody AccountDto accountDto, Authentication authentication) throws Exception {
        var user = (User) authentication.getPrincipal();
        return ResponseEntity.ok(accountService.createAccount(accountDto, user));
    }

    @GetMapping
    public ResponseEntity<List<Account>> getUserAccounts(Authentication authentication) {
        var user = (User) authentication.getPrincipal();
        return ResponseEntity.ok(accountService.getUserAccounts(user.getUid()));
    }

    @PostMapping("/transfer")
    public ResponseEntity<Transaction> transferFunds(@RequestBody TransferDto transferDto, Authentication authentication) throws Exception {
        var user = (User) authentication.getPrincipal();
        return ResponseEntity.ok(accountService.transferFunds(transferDto, user));
    }

    @GetMapping("/rates")
    public ResponseEntity<Map<String, Double>> getExchangeRate() {
        return ResponseEntity.ok(accountService.getExchangeRate());
    }

//    @PostMapping("/find")
//    public ResponseEntity<Account> findAccount(@RequestBody TransferDto dto) {
//        return ResponseEntity.ok(accountService.findAccount(dto.getCode(), dto.getRecipientAccountNumber()));
//    }

    @PostMapping("/convert")
    public ResponseEntity<Transaction> convertCurrency(@RequestBody ConvertDto convertDto, Authentication authentication) throws Exception {
        var user = (User) authentication.getPrincipal();
        return ResponseEntity.ok(accountService.convertCurrency(convertDto, user));
    }
}

