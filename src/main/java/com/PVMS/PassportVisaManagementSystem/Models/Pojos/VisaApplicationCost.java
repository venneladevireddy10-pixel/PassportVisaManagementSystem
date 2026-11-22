package com.PVMS.PassportVisaManagementSystem.Models.Pojos;



import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "vennela_visa_application_cost")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VisaApplicationCost {

    @Id
    @NotBlank(message = "Occupation is mandatory")
    private String occupation;
    @NotBlank(message = "Place is mandatory")
    private String place;
    @NotNull(message = "Price is mandatory")
    private Double price;
}

