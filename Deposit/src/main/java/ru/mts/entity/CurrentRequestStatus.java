package ru.mts.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "current_request_status")
public class CurrentRequestStatus {
    @Id
    @Column(name = "id_current_request_status")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    @ManyToOne
    @JoinColumn(name = "request_id", referencedColumnName = "id")
    private Request request;

    @ManyToOne
    @JoinColumn(name = "request_status_id", referencedColumnName = "id")
    private RequestStatus requestStatus;

    @Column(name = "change_datetime")
    private LocalDateTime changeDateTime;

    public CurrentRequestStatus(Request request, RequestStatus requestStatus) {
        this.request = request;
        this.requestStatus = requestStatus;
        this.changeDateTime = LocalDateTime.now();
    }
}
