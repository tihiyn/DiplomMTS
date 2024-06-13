package ru.mts.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class DepositDTO {
    private String depositType;
    private int depositTerm;
    private BigDecimal depositAmount;
}
