package com.duna.dunaback.service;

import com.duna.dunaback.entities.VehicleManufacturer;
import com.duna.dunaback.entities.VehicleModel;
import com.duna.dunaback.repositories.VehicleManufacturerRepo;
import com.duna.dunaback.repositories.VehicleModelRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VehicleService {
    private VehicleManufacturerRepo manufacturerRepo;
    private VehicleModelRepo modelRepo;


}
