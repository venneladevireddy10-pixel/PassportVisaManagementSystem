package com.PVMS.PassportVisaManagementSystem.Models.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.PVMS.PassportVisaManagementSystem.Models.Pojos.Visa;
@Repository
public interface VisaRepository extends JpaRepository<Visa, String> {
	 Visa findTopByOrderByVisaIdDesc();

}
