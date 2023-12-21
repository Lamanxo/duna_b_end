package com.duna.dunaback.repositories;

import com.duna.dunaback.entities.VehicleManufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VehicleManufacturerRepo extends JpaRepository<VehicleManufacturer, Long> {

    Optional<VehicleManufacturer> findById(Long id);

    Optional<VehicleManufacturer> findAllByNameContainingIgnoreCaseOrderByNameAsc(String name);

}
