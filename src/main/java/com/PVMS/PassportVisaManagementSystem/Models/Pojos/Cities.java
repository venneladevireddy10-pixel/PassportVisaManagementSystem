package com.PVMS.PassportVisaManagementSystem.Models.Pojos;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "vennela_cities")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cities {

    @Id
    @Pattern(regexp = "^[0-9]{6}$", message = "Pincode must contain 6 digits")
    private String pincode;
    @NotBlank(message = "City Name is mandatory")
    private String cityName;
    @NotBlank(message = "State Id is mandatory")
    private String stateId;
}