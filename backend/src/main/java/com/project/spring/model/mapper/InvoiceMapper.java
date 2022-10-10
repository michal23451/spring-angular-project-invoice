package com.project.spring.model.mapper;

import com.project.spring.model.Company;
import com.project.spring.model.Invoice;
import com.project.spring.model.dto.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface InvoiceMapper {
    @Mappings(value = {
            @Mapping(target="id",source = "request.idRequest"),
            @Mapping(target="number",source = "request.numberRequest"),
            @Mapping(target="buyer",source = "request.buyerRequest"),
            @Mapping(target="date",source = "request.dateRequest"),
            @Mapping(target="netValue",source = "request.netValueRequest"),
            @Mapping(target="grossValue",source = "request.grossValueRequest"),
            @Mapping(target="taxValue",source = "request.taxValueRequest"),
            @Mapping(target="company",source = "company")

    })
    Invoice mapCreateInvoiceRequestToInvoice(CreateInvoiceRequest request, Company company);

    @Mappings( value = {
            @Mapping(target = "idDTO", source = "invoice.id"),
            @Mapping(target = "numberDTO", source = "invoice.number"),
            @Mapping(target = "buyerDTO", source = "invoice.buyer"),
            @Mapping(target = "dateDTO", source = "invoice.date"),
            @Mapping(target = "netValueDTO", source = "invoice.netValue"),
            @Mapping(target = "grossValueDTO", source = "invoice.grossValue"),
            @Mapping(target = "taxValueDTO", source = "invoice.taxValue"),
            @Mapping(target = "companyIdDTO", source = "company.id"),
            @Mapping(target = "companyNameDTO", source = "company.name")
    })
    InvoiceDTO mapInvoiceAndCompanyToInvoiceDTO(Invoice invoice, Company company);
}
