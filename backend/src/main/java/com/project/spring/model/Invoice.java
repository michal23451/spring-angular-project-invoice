package com.project.spring.model;

import lombok.*;
import org.mapstruct.Mapping;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String number;
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
