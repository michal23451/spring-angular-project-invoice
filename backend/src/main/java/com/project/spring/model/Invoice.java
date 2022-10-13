package com.project.spring.model;

import lombok.*;
import org.mapstruct.Mapping;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"number", "buyer"})})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //@Column(nullable = false, unique = true)
    @Column(nullable = false)
    private String number;
    @Column(nullable = false)
    private String buyer;
    private LocalDate date;
    private BigDecimal netValue;
    private BigDecimal grossValue;
    private BigDecimal taxValue;

    //relations
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne
    private Company company;
}
