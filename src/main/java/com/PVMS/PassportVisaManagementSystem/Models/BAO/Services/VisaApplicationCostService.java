package com.PVMS.PassportVisaManagementSystem.Models.BAO.Services;

import java.util.List;

import com.PVMS.PassportVisaManagementSystem.Models.Pojos.VisaApplicationCost;

public interface VisaApplicationCostService {

    VisaApplicationCost saveCost(VisaApplicationCost cost);
    VisaApplicationCost getCostByOccupation(String occupation);
    List<VisaApplicationCost> getAllCosts();
    VisaApplicationCost updateCost(VisaApplicationCost cost);
    void deleteCost(String occupation);
}

