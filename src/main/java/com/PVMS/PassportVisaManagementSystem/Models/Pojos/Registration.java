package com.PVMS.PassportVisaManagementSystem.Models.Pojos;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "vennela_registration")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Registration {

    @Id
    private String userId;
    @NotBlank(message = "First Name is mandatory")
    @Pattern(regexp = "^[A-Za-z ]+$", message = "First Name should contain only alphabets")
    private String firstName;
    @NotBlank(message = "Surname is mandatory")
    @Pattern(regexp = "^[A-Za-z ]+$", message = "Surname should contain only alphabets")
    private String surname;
    @NotNull(message = "Date Of Birth is mandatory")
    @PastOrPresent(message = "DOB cannot be future date")
    private LocalDate dateOfBirth;
    @NotBlank(message = "Address is mandatory")
    private String address;
    @NotBlank(message = "Contact Number is mandatory")
    @Pattern(regexp = "^[0-9]{10}$",message = "Contact Number must contain 10 digits")
    private String contactNo;
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Invalid Email Format")
    private String email;
    @NotBlank(message = "Qualification is mandatory")
    private String qualification;
    @NotBlank(message = "Gender is mandatory")
    private String gender;
    @NotBlank(message = "Apply Type is mandatory")
    private String applyType;
    @NotBlank(message = "Hint Question is mandatory")
    private String hintQuestion;
    @NotBlank(message = "Hint Answer is mandatory")
    private String hintAnswer;
    private String password;
    private String citizenType;
    private String status;
}