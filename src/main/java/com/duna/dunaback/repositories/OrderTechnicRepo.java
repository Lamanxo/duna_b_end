package com.duna.dunaback.repositories;

import com.duna.dunaback.entities.OrderTechnic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderTechnicRepo extends JpaRepository<OrderTechnic, Long> {
    Optional<OrderTechnic> findById(Long id);


}
