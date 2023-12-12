package com.duna.dunaback.repositories;

import com.duna.dunaback.entities.FileData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileDataRepo extends JpaRepository<FileData, Long> {
}
