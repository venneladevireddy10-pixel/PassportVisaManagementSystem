package com.PVMS.PassportVisaManagementSystem.Models.BAO.Services;

import java.util.List;

import com.PVMS.PassportVisaManagementSystem.Models.Pojos.Visa;

public interface VisaService {

    Visa applyVisa(Visa visa);
    Visa getVisaById(String visaId);
    List<Visa> getAllVisas();
    Visa updateVisa(Visa visa);
    void deleteVisa(String visaId);
    Visa cancelVisa(String visaId);
}
