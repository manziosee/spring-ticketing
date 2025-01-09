package com.ticketing.Model;

import com.ticketing.Model.enums.TicketTypeEnum;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(unique = true)
    private String trackingCode;

    @Column(length = 20, nullable = false)
    private String firstName;

    @Column(length = 20, nullable = false)
    private String lastName;

    @Column(length = 15)
    private String phone;

    private String email;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(255) default 'GENERAL'")
    private TicketTypeEnum ticketType = TicketTypeEnum.GENERAL;

    @ManyToOne
    @JoinColumn(name = "eventId", nullable = false)
    private Event event;

    @ManyToOne
    @JoinColumn(name = "priceId", nullable = false)
    private TicketPrice price;

    @OneToMany(mappedBy = "ticket")
    private List<Payment> payments;

    @Column(columnDefinition = "boolean default false")
    private boolean isChecked = false;

    private LocalDateTime checkedAt;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}