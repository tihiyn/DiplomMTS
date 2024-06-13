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
@Table(name = "deposits_types")
public class DepositType {
    @Id
    @Column(name = "id_deposits_types")
    @SequenceGenerator(name = "id_deposits_types_generator", sequenceName = "deposits_types_id_deposits_types_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_deposits_types_generator")
    private int id;

    @Column(name = "deposits_types_name")
    private String depositsTypesName;

    @Column(name = "is_top_up")
    private boolean isTopUp;

    @Column(name = "is_withdraw")
    private boolean idWithdraw;

    @OneToMany(mappedBy = "depositType", cascade = CascadeType.ALL)
    private List<Deposit> deposits;

    public DepositType(String depositsTypesName, boolean isTopUp, boolean idWithdraw) {
        this.depositsTypesName = depositsTypesName;
        this.isTopUp = isTopUp;
        this.idWithdraw = idWithdraw;
    }
}
