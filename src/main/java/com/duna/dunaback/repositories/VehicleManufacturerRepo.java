package com.duna.dunaback.repositories;

import com.duna.dunaback.entities_mini.VehicleManufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VehicleManufacturerRepo extends JpaRepository<VehicleManufacturer, Long> {

    Optional<VehicleManufacturer> findById(Long id);

    List<VehicleManufacturer> findAllByNameContainingIgnoreCaseOrderByNameAsc(String name);

    Optional<VehicleManufacturer> findByName(String name);

}
