package com.project.repository;

import com.project.repository.entities.AssetEntity;
import com.project.repository.entities.RequestedAssetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface RequestedAssetRepository extends JpaRepository<RequestedAssetEntity,Long> {
    @Query("SELECT a FROM RequestedAssetEntity a WHERE a.status= 'pending'")
    List<RequestedAssetEntity> findPendingAssets();

    @Query("SELECT a FROM RequestedAssetEntity a WHERE a.userEntity.userId= :userid")
    List<RequestedAssetEntity> findAssetsByUserId(@Param("userid") String userid);
    @Query("SELECT COUNT(a) FROM RequestedAssetEntity a WHERE a.status= 'pending'")
    BigDecimal countPendingRequest();

}
