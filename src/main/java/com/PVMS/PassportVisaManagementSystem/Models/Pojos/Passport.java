package com.PVMS.PassportVisaManagementSystem.Models.Pojos;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "vennela_passport")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Passport {

    @Id
    private String passportId;
    @NotBlank(message = "User Id is mandatory")
    @Pattern(regexp = "^PASS-[0-9]{4}$",message = "User Id should be in PASS-XXXX format")
    private String userId;
    @NotBlank(message = "Booklet Type is mandatory")
    private String bookletType;
    @NotBlank(message = "City is mandatory")
    private String city;
    @NotBlank(message = "Country is mandatory")
    private String country;
    private LocalDate expiryDate;
    @NotNull(message = "Issue Date is mandatory")
    private LocalDate issueDate;
    @Pattern(regexp = "^[0-9]{6}$",message = "Pincode must be 6 digits")
    private String pincode;
    @NotBlank(message = "State Id is mandatory")
    private String stateId;
    private String status;
    @NotBlank(message = "Type Of Service is mandatory")
    private String typeOfService;
}