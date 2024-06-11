package ru.mts.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mts.dto.AccountDTO;
import ru.mts.entity.Account;
import ru.mts.mapper.AccountToAccountDTOMapper;
import ru.mts.repository.AccountRepository;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final AccountToAccountDTOMapper accountToAccountDTOMapper;

    @Transactional
    public AccountDTO getAccount(int accountId) {
        return accountToAccountDTOMapper.apply(accountRepository.findById(accountId).orElse(null));
    }

    @Transactional
    public BigDecimal getAmount(int accountId) {
        return accountRepository.findById(accountId).orElse(null).getAmount();
    }

    @Transactional
    public void topUpAccount(int accountId, BigDecimal topUpAmount) {
        Account account = accountRepository.findById(accountId).orElse(null);
        BigDecimal oldAmount = account.getAmount();

        account.setAmount(oldAmount.add(topUpAmount));
        accountRepository.save(account);
    }

    @Transactional
    public void withdrawAccount(int accountId, BigDecimal withdrawAmount) {
        Account account = accountRepository.findById(accountId).orElse(null);
        BigDecimal oldAmount = account.getAmount();

        account.setAmount(oldAmount.subtract(withdrawAmount));
        accountRepository.save(account);
    }
}
