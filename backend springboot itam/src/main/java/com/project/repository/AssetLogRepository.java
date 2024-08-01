package com.project.repository;

import com.project.repository.entities.AssetLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetLogRepository extends JpaRepository<AssetLogEntity,Long> {

}
