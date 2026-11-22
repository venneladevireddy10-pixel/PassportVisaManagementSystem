
package com.PVMS.PassportVisaManagementSystem.Models.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.PVMS.PassportVisaManagementSystem.Models.Pojos.Registration;
@Repository
public interface RegistrationRepository extends JpaRepository<Registration, String> {
	Registration findByEmail(String email);
}