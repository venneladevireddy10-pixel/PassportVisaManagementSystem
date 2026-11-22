package com.PVMS.PassportVisaManagementSystem.Models.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.PVMS.PassportVisaManagementSystem.Models.Pojos.Cities;

@Repository
public interface CitiesRepository extends JpaRepository<Cities, String> {

}