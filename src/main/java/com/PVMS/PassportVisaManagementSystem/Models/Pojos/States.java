package com.PVMS.PassportVisaManagementSystem.Models.Pojos;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "vennela_states")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class States {

    @Id
    @NotBlank(message = "State Id is mandatory")
    private String stateId;
    @NotBlank(message = "State Name is mandatory")
    private String stateName;
}