package com.project.repository;

import com.project.repository.entities.ModelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModelRepository extends JpaRepository<ModelEntity, Long> {
    @Query("SELECT a.name FROM ModelEntity a")
    List<String> findAllNames();
}
