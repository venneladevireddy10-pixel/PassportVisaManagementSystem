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

import com.PVMS.PassportVisaManagementSystem.Models.BAO.Services.VisaService;
import com.PVMS.PassportVisaManagementSystem.Models.Pojos.Visa;

@RestController
@RequestMapping("/pvms/visas")
public class VisaController {

    @Autowired
    private VisaService visaService;
    @PostMapping("/applyVisa")
    public Visa applyVisa(@RequestBody Visa visa) {
        return visaService.applyVisa(visa);
    }
    @GetMapping("/getVisaById/{visaId}")
    public Visa getVisaById(@PathVariable String visaId) {
        return visaService.getVisaById(visaId);
    }
    @GetMapping("/getAllVisas")
    public List<Visa> getAllVisas() {
        return visaService.getAllVisas();
    }
    @PutMapping("/updateVisa")
    public Visa updateVisa(@RequestBody Visa visa) {
        return visaService.updateVisa(visa);
    }
    @DeleteMapping("/deleteVisa/{visaId}")
    public void deleteVisa(@PathVariable String visaId) {
        visaService.deleteVisa(visaId);
    }    
    @PutMapping("/cancelVisa/{visaId}")
    public Visa cancelVisa(@PathVariable String visaId) {
        return visaService.cancelVisa(visaId);
    }
}