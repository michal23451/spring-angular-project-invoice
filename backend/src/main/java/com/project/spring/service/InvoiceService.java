package com.project.spring.service;

import com.project.spring.model.ApplicationUser;
import com.project.spring.model.dto.CompanyDTO;
import com.project.spring.model.dto.CreateCompanyRequest;
import com.project.spring.model.dto.CreateInvoiceRequest;
import com.project.spring.model.dto.InvoiceDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface InvoiceService {
    List<InvoiceDTO> getInvoiceList();
    List<InvoiceDTO> getInvoiceList(Long companyId);
    List<InvoiceDTO> getUserInvoiceList(Long userId);
    InvoiceDTO addInvoice(CreateInvoiceRequest request);
}
