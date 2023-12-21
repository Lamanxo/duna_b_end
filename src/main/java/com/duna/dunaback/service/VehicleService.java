package com.duna.dunaback.service;

import com.duna.dunaback.entities.VehicleManufacturer;
import com.duna.dunaback.entities.VehicleModel;
import com.duna.dunaback.repositories.VehicleManufacturerRepo;
import com.duna.dunaback.repositories.VehicleModelRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleService {
    private final VehicleManufacturerRepo manufacturerRepo;
    private final VehicleModelRepo modelRepo;

    public List<VehicleManufacturer> getAllManufacturers(String name) {
        return manufacturerRepo.findAllByNameContainingIgnoreCaseOrderByNameAsc(name);
    }

    public List<VehicleModel> getAllModelsByManufacturerId(Long manufacturerId) {
        return modelRepo.findAllByManufacturer_Id(manufacturerId);
    }
}
