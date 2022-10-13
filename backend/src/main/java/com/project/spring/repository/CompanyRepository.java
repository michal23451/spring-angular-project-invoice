package com.project.spring.repository;

import com.project.spring.model.ApplicationUser;
import com.project.spring.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    List<Company> findAllByUser(ApplicationUser user);

    Optional<Company> findFirstByUserAndCityAndStreetAndNip(ApplicationUser applicationUser, String city, String street, Integer nip); //do sprawdzenia unikalności firmy
}
