package com.PVMS.PassportVisaManagementSystem.Models.BAO.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PVMS.PassportVisaManagementSystem.Exceptions.ResourceNotFoundException;
import com.PVMS.PassportVisaManagementSystem.Models.BAO.Services.StatesService;
import com.PVMS.PassportVisaManagementSystem.Models.Pojos.States;
import com.PVMS.PassportVisaManagementSystem.Models.Repositories.StatesRepository;

@Service
public class StatesServiceImpl implements StatesService {
    @Autowired
    private StatesRepository statesRepository;
    @Override
    public States saveState(States state) {
        Optional<States> s = statesRepository.findById(state.getStateId());
        if (s.isEmpty()) {
            return statesRepository.save(state);
        }
        return null;
    }
    @Override
    public States getStateById(String stateId) {
        Optional<States> s = statesRepository.findById(stateId);
        if (s.isPresent()) {
            return s.get();
        }
        throw new ResourceNotFoundException("State not found with id : "+ stateId);
    }
    @Override
    public List<States> getAllStates() {
        return statesRepository.findAll();
    }
    @Override
    public States updateState(States state) {
        Optional<States> s = statesRepository.findById(state.getStateId());
        if (s.isPresent()) {
            return statesRepository.save(state);
        }
        throw new ResourceNotFoundException("State not found with id : "+ state.getStateId());
    }
    @Override
    public void deleteState(String stateId) {
        Optional<States> s = statesRepository.findById(stateId);
        if (s.isPresent()) {
            statesRepository.delete(s.get());
        }
        else {
            throw new ResourceNotFoundException("State not found with id : " + stateId);
        }
    }
}