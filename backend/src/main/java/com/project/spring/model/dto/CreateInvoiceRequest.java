package com.project.spring.model.dto;

import com.project.spring.model.Company;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateInvoiceRequest {
    private Long idRequest;
    private String numberRequest;
    private String buyerRequest;
    private LocalDate dateRequest;
    private BigDecimal netValueRequest;
    private BigDecimal grossValueRequest;
    private BigDecimal taxValueRequest;

    //relations
    private Long companyIdRequest;
}
