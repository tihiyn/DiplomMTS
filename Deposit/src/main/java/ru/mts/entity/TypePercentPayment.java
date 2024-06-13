package ru.mts.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "insert_options")
public class TypePercentPayment {
    @Id
    @Column(name = "id_insert_options")
    @SequenceGenerator(name = "id_insert_options_generator",
            sequenceName = "insert_options_id_insert_options_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_insert_options_generator")
    private int id;

    @Column(name = "name")
    private String typePercentPaymentPeriod;

    @OneToMany(mappedBy = "typePercentPayment", cascade = CascadeType.ALL)
    private List<Deposit> deposits;

    public TypePercentPayment(String typePercentPaymentPeriod) {
        this.typePercentPaymentPeriod = typePercentPaymentPeriod;
    }
}
