package ru.mts.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "deposits")
public class Deposit {
    @Id
    @Column(name = "id_deposits")
    @SequenceGenerator(name = "id_deposits_generator", sequenceName = "deposits_id_deposit_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_deposits_generator")
    private int id;

    @Column(name = "num_bank_account")
    private String numBankAccount;

    @ManyToOne
    @JoinColumn(name = "deposits_types_id", referencedColumnName = "id")
    private DepositType depositType;

    @ManyToOne
    @JoinColumn(name = "deposit_terms_id", referencedColumnName = "id")
    private DepositTerm depositTerm;

    @ManyToOne
    @JoinColumn(name = "insert_options_id", referencedColumnName = "id")
    private TypePercentPayment typePercentPayment;

    @Column(name = "deposit_amount")
    private BigDecimal depositAmount;

    @Column(name = "deposit_rate")
    private BigDecimal depositRate;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "capitalization")
    private boolean capitalization;

//    @OneToOne(mappedBy = "deposit")
//    private Request request;

    public Deposit(String numBankAccount, DepositType depositType, DepositTerm depositTerm,
                   TypePercentPayment typePercentPayment, BigDecimal depositAmount, LocalDate startDate,
                   LocalDate endDate, boolean capitalization) {
        this.numBankAccount = numBankAccount;
        this.depositType = depositType;
        this.depositTerm = depositTerm;
        this.typePercentPayment = typePercentPayment;
        this.depositAmount = depositAmount;
        this.startDate = startDate;
        this.endDate = endDate;
        this.capitalization = capitalization;
    }
}
