package ru.mts.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "codes")
public class Code {
    @Id
    @Column(name = "customers_id")
    private int customerId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "customers_id")
    private Customer customer;

    @Column(name = "code")
    private String code;

    public Code(Customer customer, String code) {
        this.customer = customer;
        this.code = code;
    }
}
