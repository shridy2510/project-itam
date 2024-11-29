package com.project.repository;

import com.project.repository.entities.RequestedAssetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestedAssetRepository extends JpaRepository<RequestedAssetEntity,Long> {


}
