package com.PVMS.PassportVisaManagementSystem.Models.BAO.Services;

import java.util.List;

import com.PVMS.PassportVisaManagementSystem.Models.Pojos.Cities;

public interface CitiesService {
    Cities saveCity(Cities city);
    Cities getCityByPincode(String pincode);
    List<Cities> getAllCities();
    Cities updateCity(Cities city);
    void deleteCity(String pincode);
}