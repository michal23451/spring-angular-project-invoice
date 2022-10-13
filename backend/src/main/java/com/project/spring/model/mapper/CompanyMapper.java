package com.project.spring.model.mapper;

import com.project.spring.model.ApplicationUser;
import com.project.spring.model.Company;
import com.project.spring.model.dto.CompanyDTO;
import com.project.spring.model.dto.CreateCompanyRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
    @Mappings(value = {
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "name", source = "request.nameRequest"),
            @Mapping(target = "city", source = "request.cityRequest"),
            @Mapping(target = "zipCode", source = "request.zipCodeRequest"),
            @Mapping(target = "street", source = "request.streetRequest"),
            @Mapping(target = "nip", source = "request.nipRequest"),
            @Mapping(target = "regon", source = "request.regonRequest"),
            @Mapping(target = "created", ignore = true),
            @Mapping(target = "updated", ignore = true),
            @Mapping(target = "user", ignore = true),
            @Mapping(target = "invoices", ignore = true)
    })
    Company mapCreateCompanyRequestToCompany(CreateCompanyRequest request);

    @Mappings(value = {
            @Mapping(target = "idDTO", source = "company.id"),
            @Mapping(target = "nameDTO", source = "company.name"),
            @Mapping(target = "cityDTO", source = "company.city"),
            @Mapping(target = "zipCodeDTO", source = "company.zipCode"),
            @Mapping(target = "streetDTO", source = "company.street"),
            @Mapping(target = "nipDTO", source = "company.nip"),
            @Mapping(target = "regonDTO", source = "company.regon"),
            @Mapping(target = "createdDTO", source = "company.created"),
            @Mapping(target = "updatedDTO", source = "company.updated"),
            @Mapping(target = "userIdDTO", source = "applicationUser.id"),
            @Mapping(target = "usernameDTO", source = "applicationUser.username"),
            @Mapping(target = "invoiceIdsDTO", ignore = true),
            @Mapping(target = "invoiceNumbersDTO", ignore = true)
    })
    CompanyDTO mapUserAndCompanyToCompanyDTO(ApplicationUser applicationUser, Company company);
}
