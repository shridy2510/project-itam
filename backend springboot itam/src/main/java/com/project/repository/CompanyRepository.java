package com.project.repository;

import com.project.repository.entities.CompanyEntity;
import com.project.repository.entities.ManufacturerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyEntity,Long> {
    @Query("SELECT c.name FROM CompanyEntity c")
    List<String> findAllNames();
    void deleteByName(String name);
    CompanyEntity findByName(String name);

}
