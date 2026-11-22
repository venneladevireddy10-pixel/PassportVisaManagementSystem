package com.PVMS.PassportVisaManagementSystem.Models.BAO.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PVMS.PassportVisaManagementSystem.Exceptions.ResourceNotFoundException;
import com.PVMS.PassportVisaManagementSystem.Models.BAO.Services.VisaApplicationCostService;
import com.PVMS.PassportVisaManagementSystem.Models.Pojos.VisaApplicationCost;
import com.PVMS.PassportVisaManagementSystem.Models.Repositories.VisaApplicationCostRepository;

@Service
public class VisaApplicationCostServiceIMpl implements VisaApplicationCostService {

    @Autowired
    private VisaApplicationCostRepository repository;
    @Override
    public VisaApplicationCost saveCost(VisaApplicationCost cost) {
        Optional<VisaApplicationCost> c = repository.findById(cost.getOccupation());
        if(c.isEmpty()) {
            return repository.save(cost);
        }
        return null;
    }
    @Override
    public VisaApplicationCost getCostByOccupation(String occupation) {
        Optional<VisaApplicationCost> c = repository.findById(occupation);
        if(c.isPresent()) {
            return c.get();
        }
        throw new ResourceNotFoundException("Occupation not found : " + occupation);
    }
    @Override
    public List<VisaApplicationCost> getAllCosts() {
        return repository.findAll();
    }
    @Override
    public VisaApplicationCost updateCost(VisaApplicationCost cost) {
        Optional<VisaApplicationCost> c = repository.findById(cost.getOccupation());
        if(c.isPresent()) {
            return repository.save(cost);
        }
        throw new ResourceNotFoundException("Occupation not found : "+ cost.getOccupation());
    }
    @Override
    public void deleteCost(String occupation) {
        Optional<VisaApplicationCost> c = repository.findById(occupation);
        if(c.isPresent()) {
            repository.delete(c.get());
        }
        else {
            throw new ResourceNotFoundException("Occupation not found : "+ occupation);
        }
    }
}