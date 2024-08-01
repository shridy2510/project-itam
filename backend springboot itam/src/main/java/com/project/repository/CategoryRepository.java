package com.project.repository;

import com.project.repository.entities.CategoryEntity;
import com.project.repository.entities.ManufacturerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    @Query("SELECT c.name FROM CategoryEntity c")
    List<String> findAllNames();
    CategoryEntity findByName(String name);
}
