package com.PVMS.PassportVisaManagementSystem.Models.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.PVMS.PassportVisaManagementSystem.Models.Pojos.Passport;
@Repository
public interface PassportRepository extends JpaRepository<Passport, String> {
	 Passport findTopByOrderByPassportIdDesc();
	    Passport findByUserId(String userId);

}
