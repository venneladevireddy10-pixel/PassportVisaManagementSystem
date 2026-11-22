package com.PVMS.PassportVisaManagementSystem.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.PVMS.PassportVisaManagementSystem.Models.BAO.Services.VisaApplicationCostService;
import com.PVMS.PassportVisaManagementSystem.Models.Pojos.VisaApplicationCost;

@RestController
@RequestMapping("/pvms/visaapplicationcost")
public class VisaApplicationCostController {

    @Autowired
    private VisaApplicationCostService visaApplicationCostService;
    @PostMapping("/addVisaCost")
    public VisaApplicationCost saveCost(@RequestBody VisaApplicationCost cost) {
        return visaApplicationCostService.saveCost(cost);
    }
    @GetMapping("/getCostByOccupation/{occupation}")
    public VisaApplicationCost getCostByOccupation(@PathVariable String occupation) {
        return visaApplicationCostService.getCostByOccupation(occupation);
    }
    @GetMapping("/getAllCosts")
    public List<VisaApplicationCost> getAllCosts() {
        return visaApplicationCostService.getAllCosts();
    }
    @PutMapping("/updateCost")
    public VisaApplicationCost updateCost(@RequestBody VisaApplicationCost cost) {
        return visaApplicationCostService.updateCost(cost);
    }
    @DeleteMapping("/deleteCost/{occupation}")
    public void deleteCost(@PathVariable String occupation) {
        visaApplicationCostService.deleteCost(occupation);
    }
}