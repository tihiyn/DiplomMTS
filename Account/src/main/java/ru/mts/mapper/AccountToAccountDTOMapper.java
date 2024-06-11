package ru.mts.mapper;

import org.springframework.stereotype.Service;
import ru.mts.dto.AccountDTO;
import ru.mts.entity.Account;

import java.util.function.Function;

@Service
public class AccountToAccountDTOMapper implements Function<Account, AccountDTO> {
    @Override
    public AccountDTO apply(Account account) {
        return new AccountDTO(
                account.getNumBankAccounts(),
                account.getAmount()
        );
    }
}
