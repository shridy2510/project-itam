package com.project.service;

import com.project.dto.RequestedAssetDto;
import com.project.repository.AssetRepository;
import com.project.repository.RequestedAssetRepository;
import com.project.repository.UserRepository;
import com.project.repository.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class RequestedAssetService {
    @Autowired
    private RequestedAssetRepository requestedAssetRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AssetRepository assetRepository;

    public void create(RequestedAssetDto requestedAssetDto){
        Optional<UserEntity> OptUserEntity = userRepository.findByUserId(requestedAssetDto.getUserId());
        Optional<AssetEntity> optAssetEntity = assetRepository.findById(requestedAssetDto.getAssetId());
        if(OptUserEntity.isPresent() && optAssetEntity.isPresent()) {
            RequestedAssetEntity requestedAssetEntity = new RequestedAssetEntity();
            requestedAssetEntity.setAssetEntity(optAssetEntity.get());
            requestedAssetEntity.setUserEntity(OptUserEntity.get());
            requestedAssetEntity.setCreateTime(LocalDateTime.now());
            requestedAssetRepository.save(requestedAssetEntity);
            System.out.println("RequestedAsset created");
        }
    }
    public void update(RequestedAssetDto requestedAssetDto){
        Optional<UserEntity> OptUserEntity = userRepository.findByUserId(requestedAssetDto.getUserId());
        Optional<AssetEntity> optAssetEntity = assetRepository.findById(requestedAssetDto.getAssetId());
        Optional<RequestedAssetEntity> OptRequestedAssetEntity = requestedAssetRepository.findById(requestedAssetDto.getId());
        if(OptUserEntity.isPresent() && optAssetEntity.isPresent()) {
            RequestedAssetEntity requestedAssetEntity=OptRequestedAssetEntity.get();
            requestedAssetEntity.setAssetEntity(optAssetEntity.get());
            requestedAssetEntity.setUserEntity(OptUserEntity.get());
            requestedAssetEntity.setUpdateTime(LocalDateTime.now());
            requestedAssetRepository.save(requestedAssetEntity);
            System.out.println("RequestedAsset updated");
        }
    }
    public void deny(long id){

        Optional<RequestedAssetEntity> OptRequestedAssetEntity = requestedAssetRepository.findById(id);
        if(OptRequestedAssetEntity.isPresent() ) {
            RequestedAssetEntity requestedAssetEntity= OptRequestedAssetEntity.get();
            requestedAssetEntity.setDeniedTime(LocalDateTime.now());
            requestedAssetRepository.save(requestedAssetEntity);
            System.out.println("RequestedAsset denied");

    }
    }
    public void accept(long id){

        Optional<RequestedAssetEntity> OptRequestedAssetEntity = requestedAssetRepository.findById(id);
        if(OptRequestedAssetEntity.isPresent() ) {
            RequestedAssetEntity requestedAssetEntity= OptRequestedAssetEntity.get();
            requestedAssetEntity.setDeniedTime(LocalDateTime.now());
            requestedAssetRepository.save(requestedAssetEntity);
            System.out.println("RequestedAsset accepted");

        }
    }



}
