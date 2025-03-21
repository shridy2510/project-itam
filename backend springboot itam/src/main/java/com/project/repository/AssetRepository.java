package com.project.repository;

import com.project.repository.entities.AssetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface AssetRepository extends JpaRepository<AssetEntity,Long> {
    @Query("SELECT a.name FROM AssetEntity a")
    List<String> findAllNames();

    @Query("SELECT COUNT(a) FROM AssetEntity a")
    long countAssets();

    @Query("SELECT a FROM AssetEntity a WHERE a.userEntity.userId= :userid")
    List<AssetEntity> findAssetsByUserId(@Param("userid") String userid);


@Query("SELECT COUNT(a) FROM AssetEntity a WHERE a.statusEntity.id= 1")
long countAvailableAssets();
    @Query("SELECT SUM(a.cost) FROM AssetEntity a ")
    BigDecimal countVAlueAssets();

    //table stuff

    //bar chart

    @Query("SELECT SUM(a.cost) FROM AssetEntity a WHERE a.statusEntity.id= 1")
    BigDecimal sumAvailableCost();
    @Query("SELECT SUM(a.cost) FROM AssetEntity a WHERE a.statusEntity.id= 2")
    BigDecimal sumBrokenCost();
    @Query("SELECT SUM(a.cost) FROM AssetEntity a WHERE a.statusEntity.id= 3")
    BigDecimal sumCheckedOutCost();
    @Query("SELECT SUM(a.cost) FROM AssetEntity a WHERE a.statusEntity.id= 4")
    BigDecimal sumDisposedCost();
    @Query("SELECT SUM(a.cost) FROM AssetEntity a WHERE a.statusEntity.id= 5")
    BigDecimal sumLostMissingCost();
    @Query("SELECT SUM(a.cost) FROM AssetEntity a WHERE a.statusEntity.id= 6")
    BigDecimal sumUnderRepairCost();

    //pie chart
    @Query("SELECT COUNT (a) FROM AssetEntity a WHERE a.statusEntity.id= 1")
    BigDecimal countAvailableAsset();
    @Query("SELECT COUNT(a) FROM AssetEntity a WHERE a.statusEntity.id= 2")
    BigDecimal countBrokenAsset();
    @Query("SELECT COUNT(a) FROM AssetEntity a WHERE a.statusEntity.id= 3")
    BigDecimal countCheckedOutAsset();
    @Query("SELECT COUNT(a) FROM AssetEntity a WHERE a.statusEntity.id= 4")
    BigDecimal countDisposedAsset();
    @Query("SELECT COUNT(a) FROM AssetEntity a WHERE a.statusEntity.id= 5")
    BigDecimal countLostMissingAsset();
    @Query("SELECT COUNT(a) FROM AssetEntity a WHERE a.statusEntity.id= 6")
    BigDecimal countUnderRepairAsset();
//get assets by status id
    @Query("SELECT a FROM AssetEntity a WHERE a.statusEntity.id=:statusId ")
    List<AssetEntity> findAssetsByStatusId(@Param("statusId") int userid);
    @Query("SELECT a FROM AssetEntity a WHERE CURRENT_TIMESTAMP > a.expectedCheckin")
    List<AssetEntity> findAssetsWithOverdueTime();
    @Query("SELECT COUNT(a) FROM AssetEntity a WHERE CURRENT_TIMESTAMP > a.expectedCheckin")
    BigDecimal countPassDueAssets();



}
