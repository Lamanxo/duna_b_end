package com.duna.dunaback.repositories;

import com.duna.dunaback.entities.VehicleModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VehicleModelRepo extends JpaRepository<VehicleModel, Long> {
    Optional<VehicleModel> findByManufacturer(Long manufacturerId);
}
