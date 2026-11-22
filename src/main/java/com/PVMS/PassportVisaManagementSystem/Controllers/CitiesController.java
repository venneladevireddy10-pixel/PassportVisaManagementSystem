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

import com.PVMS.PassportVisaManagementSystem.Models.BAO.Services.CitiesService;
import com.PVMS.PassportVisaManagementSystem.Models.Pojos.Cities;

@RestController
@RequestMapping("/pvms/cities")
public class CitiesController {

    @Autowired
    private CitiesService citiesService;
    @PostMapping("/addCity")
    public Cities saveCity(@RequestBody Cities city) {
        return citiesService.saveCity(city);
    }
    @GetMapping("/getCityByPincode/{pincode}")
    public Cities getCityByPincode(@PathVariable String pincode) {
        return citiesService.getCityByPincode(pincode);
    }
    @GetMapping("/getAllCities")
    public List<Cities> getAllCities() {
        return citiesService.getAllCities();
    }
    @PutMapping("/updateCity")
    public Cities updateCity(@RequestBody Cities city) {
        return citiesService.updateCity(city);
    }
    @DeleteMapping("/deleteCity/{pincode}")
    public void deleteCity(@PathVariable String pincode) {
        citiesService.deleteCity(pincode);
    }
}