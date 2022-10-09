package com.project.spring.model.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class InvoiceDTO {
    private Long idDTO;
    private String numberDTO;
    private String buyerDTO;
    private LocalDate dateDTO;
    private BigDecimal netValueDTO;
    private BigDecimal grossValueDTO;
    private BigDecimal taxValueDTO;

    //relations
    private String companyIdDTO;
    private String companyNameDTO;
}
