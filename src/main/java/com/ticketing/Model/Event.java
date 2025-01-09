package com.ticketing.Model;

import com.ticketing.Model.enums.EventCategoryEnum;
import com.ticketing.Model.enums.EventTypeEnum;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(length = 15, nullable = false)
    private String name;

    private String image;

    @Column(length = 15)
    private String description;

    private int maxTickets;

    @Column(columnDefinition = "int default 0")
    private int availableTickets = 0;

    private LocalDateTime date;

    @Column(length = 15)
    private String location;

    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime startSellingDate;
    private LocalDateTime endSellingDate;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(255) default 'PAID'")
    private EventTypeEnum eventType = EventTypeEnum.PAID;

    @Enumerated(EnumType.STRING)
    private EventCategoryEnum eventCategory;

    @ManyToOne
    @JoinColumn(name = "companyId")
    private Company company;

    @OneToMany(mappedBy = "event")
    private List<Ticket> tickets;

    @OneToMany(mappedBy = "event")
    private List<TicketPrice> ticketPrices;

    @Column(columnDefinition = "boolean default true")
    private boolean active = true;

    @Column(columnDefinition = "boolean default false")
    private boolean isPrivate = false;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}