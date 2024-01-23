package com.duna.dunaback.repositories;

import com.duna.dunaback.entities.TechnicType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TechnicTypeRepo extends JpaRepository <TechnicType, Long> {

Optional<TechnicType> findById(Long id);

List<TechnicType> findAllByNameContainingIgnoreCaseOrderByNameAsc(String name);

Optional<TechnicType> findByName(String name);

}
