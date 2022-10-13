package com.project.spring.repository;

import com.project.spring.model.Company;
import com.project.spring.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    List<Invoice> findAllByCompany(Company company);
    Optional<Invoice> findFirstByCompanyAndNumberAndBuyer(Company company, String number, String buyer); //do sprawdzenia unikalności faktury
}
