package ru.mts.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.mts.dto.AccountDTO;
import ru.mts.service.AccountService;

import java.math.BigDecimal;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @GetMapping("/{id}")
    public AccountDTO getAccount(@PathVariable("id") int id) {
        return accountService.getAccount(id);
    }

    @GetMapping("/{id}/amount")
    public BigDecimal getAmount(@PathVariable("id") int id) {
        return accountService.getAmount(id);
    }

    @PatchMapping("/{id}/top-up")
    public void topUpAccount(@PathVariable("id") int id, @RequestParam("topUpAmount") BigDecimal topUpAmount) {
        accountService.topUpAccount(id, topUpAmount);
    }

    @PatchMapping("/{id}/withdraw")
    public void withdrawAccount(@PathVariable("id") int id, @RequestParam("withdrawAmount") BigDecimal withdrawAmount) {
        accountService.withdrawAccount(id, withdrawAmount);
    }
}
