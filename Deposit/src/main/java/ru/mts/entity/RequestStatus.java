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
@Table(name = "request_statuses")
public class RequestStatus {
    @Id
    @Column(name = "id_request_status")
    @SequenceGenerator(name = "id_request_status_generator", sequenceName = "request_statuses_id_request_status_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_request_status_generator")
    private int id;

    @Column(name = "request_status_name")
    private String requestStatusName;

    @OneToMany(mappedBy = "requestStatus", cascade = CascadeType.ALL)
    private List<CurrentRequestStatus> currentRequestStatuses;

    public RequestStatus(String requestStatusName) {
        this.requestStatusName = requestStatusName;
    }
}
