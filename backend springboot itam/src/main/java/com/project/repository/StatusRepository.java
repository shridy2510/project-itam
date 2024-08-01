package com.project.repository;

import com.project.repository.entities.StatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatusRepository extends JpaRepository<StatusEntity, Long> {
    @Query("SELECT s.name FROM StatusEntity s")
    List<String> findAllNames();
    void deleteByName(String name);
    StatusEntity findByName(String name);


}
