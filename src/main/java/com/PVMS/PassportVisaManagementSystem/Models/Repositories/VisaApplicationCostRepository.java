package com.PVMS.PassportVisaManagementSystem.Models.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.PVMS.PassportVisaManagementSystem.Models.Pojos.VisaApplicationCost;
@Repository
public interface VisaApplicationCostRepository extends JpaRepository<VisaApplicationCost, String> {
	VisaApplicationCost findByOccupationAndPlace(String occupation,String place);


}