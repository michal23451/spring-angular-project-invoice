package com.project.spring.model.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCompanyRequest {
    private String nameRequest;
    private String cityRequest;
    private String zipCodeRequest;
    private String streetRequest;
    private Integer nipRequest;
    private Integer regonRequest;

    //relations
    private Long userIdRequest;
}
