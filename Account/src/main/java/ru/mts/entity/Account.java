package ru.mts.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "bank_accounts")
public class Account {
    @Id
    @Column(name = "id_bank_accounts")
    @SequenceGenerator(name = "id_generator", sequenceName = "bank_accounts_id_bank_accounts_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_generator")
    private int id;

    @Column(name = "num_bank_accounts")
    private String numBankAccounts;

    @Column(name = "amount", columnDefinition = "money")
    private BigDecimal amount;

    public Account(String numBankAccounts, BigDecimal amount) {
        this.numBankAccounts = numBankAccounts;
        this.amount = amount;
    }
}
