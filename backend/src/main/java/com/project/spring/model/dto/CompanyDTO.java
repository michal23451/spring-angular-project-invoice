package com.project.spring.model.dto;

import com.project.spring.model.ApplicationUser;
import com.project.spring.model.Invoice;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDTO {
    private Long idDTO;
    private String nameDTO;
    private String cityDTO;
    private String zipCodeDTO;
    private String streetDTO;
    private Integer nipDTO;
    private Integer regonDTO;
    private LocalDateTime createdDTO;
    private LocalDateTime updatedDTO;

    //relations
    private Long userIdDTO;
    private String usernameDTO;
    private Set<Long> invoiceIdsDTO;
    private Set<String> invoiceNumbersDTO;
}
