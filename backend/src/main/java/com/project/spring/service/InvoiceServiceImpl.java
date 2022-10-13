package com.project.spring.service;

import com.project.spring.model.ApplicationUser;
import com.project.spring.model.Company;
import com.project.spring.model.Invoice;
import com.project.spring.model.dto.CreateInvoiceRequest;
import com.project.spring.model.dto.InvoiceDTO;
import com.project.spring.model.mapper.InvoiceMapper;
import com.project.spring.repository.ApplicationUserRepository;
import com.project.spring.repository.CompanyRepository;
import com.project.spring.repository.InvoiceRepository;
import com.sun.xml.bind.v2.TODO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final InvoiceMapper invoiceMapper;
    private final ApplicationUserRepository applicationUserRepository;
    private final CompanyRepository companyRepository;

    @Override
    public List<InvoiceDTO> getInvoiceList() {
        return invoiceRepository.findAll()
                .stream()
                .map(invoice -> invoiceMapper.mapInvoiceAndCompanyToInvoiceDTO(invoice, invoice.getCompany()))
                .collect(Collectors.toList());
    }

    @Override
    public List<InvoiceDTO> getInvoiceList(Long companyId) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new EntityNotFoundException());

        return invoiceRepository.findAllByCompany(company)
                .stream()
                .map(invoice -> invoiceMapper.mapInvoiceAndCompanyToInvoiceDTO(invoice, company))
                .collect(Collectors.toList());
    }

    @Override
    public List<InvoiceDTO> getUserInvoiceList(Long userId) {
        ApplicationUser applicationUser = applicationUserRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException());

        Set<Company> companies = applicationUser.getCompanies();
        List<Long> companiesId = new ArrayList<>();
        Iterator<Company> iterator = companies.iterator();

        while (iterator.hasNext()) {
            companiesId.add(iterator.next().getId());
        }


        List<Invoice> invoices = invoiceRepository.findAll()
                .stream()
                .filter(invoice -> {
                    for (int i = 0; i < companiesId.size(); i++) {
                        if (invoice.getId().equals(companiesId.get(i))) {
                            return true;
                        }
                    }
                    return false;
                })
                .collect(Collectors.toList());

        return invoices.stream()
                .map(invoice -> {
                    InvoiceDTO invoiceDTO = new InvoiceDTO();
                    while (companies.iterator().hasNext()) {
                        invoiceDTO = invoiceMapper.mapInvoiceAndCompanyToInvoiceDTO(invoice, companies.iterator().next());
                    }
                    return invoiceDTO;
                })
                .collect(Collectors.toList());

    }

    @Override
    public InvoiceDTO addInvoice(CreateInvoiceRequest request) {
        Company company = companyRepository.findById(request.getIdRequest())
                .orElseThrow(() -> new EntityNotFoundException());

        Invoice invoice = invoiceRepository.findFirstByCompanyAndNumberAndBuyer(company, request.getNumberRequest(), request.getBuyerRequest())
                .orElse(null);

        if (invoice == null) {
            invoice = invoiceMapper.mapCreateInvoiceRequestToInvoice(request, company);
        } else {
            throw new EntityExistsException("Istnieje już taka faktura w bazie danych!");
        }

        invoice.setCompany(company);
        invoice = invoiceRepository.save(invoice);

        return invoiceMapper.mapInvoiceAndCompanyToInvoiceDTO(invoice, company);
    }
}
