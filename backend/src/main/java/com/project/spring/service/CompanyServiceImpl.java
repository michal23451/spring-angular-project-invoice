package com.project.spring.service;

import com.project.spring.model.ApplicationUser;
import com.project.spring.model.Company;
import com.project.spring.model.dto.CompanyDTO;
import com.project.spring.model.dto.CreateCompanyRequest;
import com.project.spring.model.mapper.CompanyMapper;
import com.project.spring.repository.ApplicationUserRepository;
import com.project.spring.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;
    private final ApplicationUserRepository applicationUserRepository;

    @Override
    public List<CompanyDTO> getCompanyList() {
        return companyRepository.findAll()
                .stream()
                .map(company -> companyMapper.mapUserAndCompanyToCompanyDTO(company.getUser(), company))
                .collect(Collectors.toList());
    }

    @Override
    public List<CompanyDTO> getCompanyList(Long userId) {
        ApplicationUser applicationUser = applicationUserRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException());

        return companyRepository.findAllByUser(applicationUser)
                .stream()
                .map(company -> companyMapper.mapUserAndCompanyToCompanyDTO(company.getUser(), company))
                .collect(Collectors.toList());
    }

    @Override
    public CompanyDTO addCompany(CreateCompanyRequest request) {
        ApplicationUser applicationUser = applicationUserRepository.findById(request.getUserIdRequest())
                .orElseThrow(() -> new EntityNotFoundException());

        Company company = companyRepository.findFirstByUserAndCityAndStreetAndNip(applicationUser, request.getCityRequest(), request.getNameRequest(), request.getNipRequest())
                .orElse(null);

        if (company == null) {
            company = companyMapper.mapCreateCompanyRequestToCompany(request);
        } else {
            throw new EntityExistsException("Istnieje już taka firma w bazie danych!");
        }

        company.setUser(applicationUser);
        company = companyRepository.save(company);

        return companyMapper.mapUserAndCompanyToCompanyDTO(company.getUser(), company);
    }
}
