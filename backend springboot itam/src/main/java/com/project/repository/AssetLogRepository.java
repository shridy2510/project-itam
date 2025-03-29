package com.project.repository;

import com.project.repository.entities.AssetEntity;
import com.project.repository.entities.AssetLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssetLogRepository extends JpaRepository<AssetLogEntity,Long> {

    @Query("SELECT a FROM AssetLogEntity a WHERE a.assetEntity.id= :id")
    List<AssetLogEntity> findAssetLogsByAssetId(@Param("id") Long id);

}
