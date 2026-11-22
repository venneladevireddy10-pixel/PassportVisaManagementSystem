package com.PVMS.PassportVisaManagementSystem.Models.BAO.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PVMS.PassportVisaManagementSystem.Exceptions.ResourceNotFoundException;
import com.PVMS.PassportVisaManagementSystem.Models.BAO.Services.CitiesService;
import com.PVMS.PassportVisaManagementSystem.Models.Pojos.Cities;
import com.PVMS.PassportVisaManagementSystem.Models.Repositories.CitiesRepository;

@Service
public class CitiesServiceImpl implements CitiesService {

    @Autowired
    private CitiesRepository citiesRepository;
    @Override
    public Cities saveCity(Cities city) {
        Optional<Cities> c = citiesRepository.findById(city.getPincode());
        if(c.isEmpty()) {
            return citiesRepository.save(city);
        }
        return null;
    }
    @Override
    public Cities getCityByPincode(String pincode) {
        Optional<Cities> c = citiesRepository.findById(pincode);
        if(c.isPresent()) {
            return c.get();
        }
        throw new ResourceNotFoundException("City not found with pincode : "+ pincode);
    }
    @Override
    public List<Cities> getAllCities() {
        return citiesRepository.findAll();
    }
    @Override
    public Cities updateCity(Cities city) {
        Optional<Cities> c = citiesRepository.findById(city.getPincode());
        if(c.isPresent()) {
            return citiesRepository.save(city);
        }
        throw new ResourceNotFoundException("City not found with pincode : " + city.getPincode());
    }
    @Override
    public void deleteCity(String pincode) {
        Optional<Cities> c = citiesRepository.findById(pincode);
        if(c.isPresent()) {
            citiesRepository.delete(c.get());
        }
        else {
            throw new ResourceNotFoundException("City not found with pincode : " + pincode);
        }
    }
}