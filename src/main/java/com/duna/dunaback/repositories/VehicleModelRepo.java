package com.duna.dunaback.repositories;

import com.duna.dunaback.entities.VehicleModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VehicleModelRepo extends JpaRepository<VehicleModel, Long> {
    List<VehicleModel> findAllByManufacturer_Id(Long manufacturerId);

    Optional<VehicleModel> findByName(String name);
}
