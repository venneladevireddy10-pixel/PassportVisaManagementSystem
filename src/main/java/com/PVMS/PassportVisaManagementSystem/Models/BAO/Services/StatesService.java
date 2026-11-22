package com.PVMS.PassportVisaManagementSystem.Models.BAO.Services;

import java.util.List;

import com.PVMS.PassportVisaManagementSystem.Models.Pojos.States;

public interface StatesService {

    States saveState(States state);
    States getStateById(String stateId);
    List<States> getAllStates();
    States updateState(States state);
    void deleteState(String stateId);
}
