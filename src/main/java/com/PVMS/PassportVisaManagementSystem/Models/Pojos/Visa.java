package com.PVMS.PassportVisaManagementSystem.Models.Pojos;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "vennela_visa")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Visa {

    @Id
    private String visaId;
    @NotBlank(message = "Country is mandatory")
    private String country;
    @NotNull(message = "Date Of Application is mandatory")
    private LocalDate dateOfApplication;
    private LocalDate expiryDate;
    private LocalDate issueDate;
    @NotBlank(message = "Occupation is mandatory")
    private String occupation;
    private Double registrationCost;
    private String status;
    @NotBlank(message = "Passport Id is mandatory")
    private String passportId;
}