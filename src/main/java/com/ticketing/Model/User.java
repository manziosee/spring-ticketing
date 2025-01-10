package com.ticketing.Model;

import com.ticketing.Model.enums.UserRoleEnum;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRoleEnum role = UserRoleEnum.USER;

    @Column(length = 20, nullable = false)
    private String firstName;

    @Column(length = 20, nullable = false)
    private String lastName;

    @Column(length = 16)
    private Integer nid; // National ID

    @Column(length = 15)
    private String phone;

    @ManyToOne
    @JoinColumn(name = "companyId")
    private Company company;

    @OneToMany(mappedBy = "participant")
    private List<Review> reviews;

    @OneToMany(mappedBy = "owner")
    private List<Company> ownedCompanies;

    @Column(nullable = false)
    private Boolean isActive = true;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}