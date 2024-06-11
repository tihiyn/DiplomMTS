package ru.mts.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class AccountDTO {
    private String numBankAccounts;
    private BigDecimal amount;
}
