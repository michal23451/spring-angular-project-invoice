package com.project.spring.model;

import io.swagger.models.auth.In;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"city", "street", "nip"})})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    private String city;
    private String zipCode;
    private String street;
    @Column(nullable = false)
    private Integer nip;
    private Integer regon;
    @CreationTimestamp
    private LocalDateTime created;
    @UpdateTimestamp
    private LocalDateTime updated;

    //relations
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne
    private ApplicationUser user;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "company")
    private Set<Invoice> invoices;
}
