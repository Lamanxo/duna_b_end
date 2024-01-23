package com.duna.dunaback.service;

import com.duna.dunaback.entities.TechnicType;
import com.duna.dunaback.entities.VehicleManufacturer;
import com.duna.dunaback.entities.VehicleModel;
import com.duna.dunaback.exceptions.VehicleNotFoundException;
import com.duna.dunaback.repositories.TechnicTypeRepo;
import com.duna.dunaback.repositories.VehicleManufacturerRepo;
import com.duna.dunaback.repositories.VehicleModelRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleService {
    private final TechnicTypeRepo technicTypeRepo;
    private final VehicleManufacturerRepo vehicleManufacturerRepo;
    private final VehicleModelRepo vehicleModelRepo;

    public List<VehicleManufacturer> getAllManufacturers(String name) {
        return vehicleManufacturerRepo.findAllByNameContainingIgnoreCaseOrderByNameAsc(name);
    }

    public List<VehicleModel> getAllModelsByManufacturerId(Long manufacturerId) {
        return vehicleModelRepo.findAllByManufacturer_Id(manufacturerId);
    }

    public List<TechnicType> getAllTechnicTypeByLetter(String name) {
        return technicTypeRepo.findAllByNameContainingIgnoreCaseOrderByNameAsc(name);
    }

    public TechnicType findTechnicTypeByName(String name) {
        return technicTypeRepo.findByName(name).orElseThrow(() ->
                new VehicleNotFoundException("Technic type with name: " + name + " not found"));
    }

    public VehicleManufacturer findVehicleManufacturerByName(String name) {
        return vehicleManufacturerRepo.findByName(name).orElseThrow(() ->
                new VehicleNotFoundException("Vehicle manufacturer with name: " + name + " not found"));
    }

    public VehicleModel findVehicleModelByName(String name) {
        return vehicleModelRepo.findByName(name).orElseThrow(() ->
                new VehicleNotFoundException("Vehicle model with name: " + name + " not found"));
    }
}
