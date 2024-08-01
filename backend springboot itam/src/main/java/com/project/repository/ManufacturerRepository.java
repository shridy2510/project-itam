package com.project.repository;

import com.project.repository.entities.ManufacturerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ManufacturerRepository extends JpaRepository<ManufacturerEntity, Long> {
    @Query("SELECT m.name FROM ManufacturerEntity m")
    List<String> findAllNames();
    void deleteByName(String manufacturerName);
    ManufacturerEntity findByName(String name);
}
