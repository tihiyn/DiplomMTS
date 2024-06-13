package ru.mts.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "requests")
public class Request {
    @Id
    @Column(name = "id_request")
    @SequenceGenerator(name = "id_request_generator", sequenceName = "requests_id_request_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_request_generator")
    private int id;

    @Column(name = "customer_id")
    private int customerId;

    @Column(name = "request_date")
    private LocalDate requestDate;

    @OneToOne()
    @JoinColumn(name = "deposit_id", referencedColumnName = "id")
    private Deposit deposit;

    @OneToMany(mappedBy = "request", cascade = CascadeType.ALL)
    private List<CurrentRequestStatus> currentRequestStatuses;

    public Request(int customerId, LocalDate requestDate) {
        this.customerId = customerId;
        this.requestDate = requestDate;
    }
}
