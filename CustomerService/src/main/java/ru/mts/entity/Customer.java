package ru.mts.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @Column(name = "id_customers")
    @SequenceGenerator(name = "id_customers_generator", sequenceName = "customers_id_customers_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_customers_generator")
    private int id;

    @Column(name = "num_bank_accounts")
    private String numBankAccount;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    private Code code;

    public Customer(String numBankAccount, String phoneNumber, String password) {
        this.numBankAccount = numBankAccount;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.role = Role.USER;
    }
}
