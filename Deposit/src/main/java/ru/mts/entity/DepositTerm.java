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
@Table(name = "deposit_terms")
public class DepositTerm {
    @Id
    @Column(name = "id_deposit_terms")
    @SequenceGenerator(name = "id_deposit_terms_generator",
            sequenceName = "deposit_terms_id_deposit_terms_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_deposit_terms_generator")
    private int id;

    @Column(name = "term_length")
    private int termLength;

    @OneToMany(mappedBy = "depositTerm", cascade = CascadeType.ALL)
    private List<Deposit> deposits;

    public DepositTerm(int termLength) {
        this.termLength = termLength;
    }
}
