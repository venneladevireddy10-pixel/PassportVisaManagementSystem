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

import com.PVMS.PassportVisaManagementSystem.Models.BAO.Services.StatesService;
import com.PVMS.PassportVisaManagementSystem.Models.Pojos.States;

@RestController
@RequestMapping("/pvms/states")
public class StatesController {

    @Autowired
    private StatesService statesService;
    @PostMapping("/addState")
    public States saveState(@RequestBody States state) {
        return statesService.saveState(state);
    }
    @GetMapping("/getStateById/{stateId}")
    public States getStateById(@PathVariable String stateId) {
        return statesService.getStateById(stateId);
    }
    @GetMapping("/getAllStates")
    public List<States> getAllStates() {
        return statesService.getAllStates();
    }
    @PutMapping("/updateState")
    public States updateState(@RequestBody States state) {
        return statesService.updateState(state);
    }
    @DeleteMapping("/deleteState/{stateId}")
    public void deleteState(@PathVariable String stateId) {
        statesService.deleteState(stateId);
    }
}