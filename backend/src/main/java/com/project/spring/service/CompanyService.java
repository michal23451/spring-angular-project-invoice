package com.project.spring.service;

import com.project.spring.model.dto.CompanyDTO;
import com.project.spring.model.dto.CreateCompanyRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CompanyService {
    List<CompanyDTO> getCompanyList();
    List<CompanyDTO> getCompanyList(Long userId);
    CompanyDTO addCompany(CreateCompanyRequest request);
}
