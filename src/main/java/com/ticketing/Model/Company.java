package com.ticketing.Model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "companies")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(length = 20, nullable = false)
    private String name;

    @Column(length = 8, nullable = false)
    private String tin; // Tax Identification Number

    @Column(length = 16, nullable = false)
    private Integer nid; // Owner’s National ID

    @ManyToOne
    @JoinColumn(name = "ownerId", nullable = false)
    private User owner;

    @OneToMany(mappedBy = "company")
    private List<Event> events;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}