package com.duna.dunaback.controllers;

import com.duna.dunaback.entities.VehicleManufacturer;
import com.duna.dunaback.entities.VehicleModel;
import com.duna.dunaback.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class VehicleController {

    private final VehicleService vehicleService;

    @GetMapping("/manufacturer/{name}")
    public List<VehicleManufacturer> downloadImageFromFileSystem(@PathVariable String name) {

        return vehicleService.getAllManufacturers(name);
    }

    @GetMapping("/manufacturer/vehicle/{id}")
    public List<VehicleModel> getAllModelsByManufacturerId(@PathVariable Long id) {
        return vehicleService.getAllModelsByManufacturerId(id);
    }


}
