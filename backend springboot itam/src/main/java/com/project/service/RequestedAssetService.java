package com.project.service;

import com.project.displayDto.AssetDisplayDto;
import com.project.displayDto.RequestedAssetDisplayDto;
import com.project.dto.RequestedAssetDto;
import com.project.repository.AssetRepository;
import com.project.repository.RequestedAssetRepository;
import com.project.repository.UserRepository;
import com.project.repository.entities.*;
import com.project.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
            requestedAssetEntity.setRequestDate(LocalDateTime.now());
            requestedAssetEntity.setStatus("pending");
            if (requestedAssetDto.getExpectedCheckin() != null) {
                requestedAssetEntity.setExpectedCheckin(requestedAssetDto.getExpectedCheckin());
            }
            if (requestedAssetDto.getExpectedCheckout() != null) {
                requestedAssetEntity.setExpectedCheckout(requestedAssetDto.getExpectedCheckout());
            }

            if (requestedAssetDto.getLocation() != null) {
                requestedAssetEntity.setLocation(requestedAssetDto.getLocation());
            }

            if (requestedAssetDto.getRequestType() != null) {
                requestedAssetEntity.setRequestType(requestedAssetDto.getRequestType());
            }

            requestedAssetRepository.save(requestedAssetEntity);
            System.out.println("RequestedAsset created");
        }
    }
    public void createCheckInRequest(RequestedAssetDto requestedAssetDto){
        Optional<UserEntity> OptUserEntity = userRepository.findByUserId(requestedAssetDto.getUserId());
        Optional<AssetEntity> optAssetEntity = assetRepository.findById(requestedAssetDto.getAssetId());
        if(OptUserEntity.isPresent() && optAssetEntity.isPresent()) {
            RequestedAssetEntity requestedAssetEntity = new RequestedAssetEntity();
            requestedAssetEntity.setAssetEntity(optAssetEntity.get());
            requestedAssetEntity.setUserEntity(OptUserEntity.get());
            requestedAssetEntity.setRequestDate(LocalDateTime.now());
            requestedAssetEntity.setStatus("pending");
                requestedAssetEntity.setExpectedCheckin(optAssetEntity.get().getExpectedCheckin());
                requestedAssetEntity.setExpectedCheckout(optAssetEntity.get().getLastCheckout());
                requestedAssetEntity.setLocation(optAssetEntity.get().getLocation());
                requestedAssetEntity.setRequestType(requestedAssetDto.getRequestType());
            requestedAssetRepository.save(requestedAssetEntity);
            System.out.println("RequestedAsset created");
        }
    }
    public void updateRequestCheckIn(RequestedAssetDto requestedAssetDto){
        Optional<RequestedAssetEntity> optRequestedAssetEntity = requestedAssetRepository.findById(requestedAssetDto.getId());
        if(optRequestedAssetEntity.isPresent()) {
        RequestedAssetEntity requestedAssetEntity = optRequestedAssetEntity.get();
        requestedAssetEntity.setActualCheckin(LocalDateTime.now());
        requestedAssetRepository.save(requestedAssetEntity);
        System.out.println("RequestedAsset created");
        }
    }


//    public void update(RequestedAssetDto requestedAssetDto){
//        Optional<UserEntity> OptUserEntity = userRepository.findByUserId(requestedAssetDto.getUserId());
//        Optional<AssetEntity> optAssetEntity = assetRepository.findById(requestedAssetDto.getAssetId());
//        Optional<RequestedAssetEntity> OptRequestedAssetEntity = requestedAssetRepository.findById(requestedAssetDto.getId());
//        if(OptUserEntity.isPresent() && optAssetEntity.isPresent()) {
//            RequestedAssetEntity requestedAssetEntity=OptRequestedAssetEntity.get();
//            requestedAssetEntity.setAssetEntity(optAssetEntity.get());
//            requestedAssetEntity.setUserEntity(OptUserEntity.get());
//            requestedAssetEntity.setUpdateTime(LocalDateTime.now());
//            requestedAssetRepository.save(requestedAssetEntity);
//            System.out.println("RequestedAsset updated");
//        }
//    }
    public void deny(RequestedAssetDto requestedAssetDto){

        Optional<RequestedAssetEntity> OptRequestedAssetEntity = requestedAssetRepository.findById(requestedAssetDto.getId());
        Optional<UserEntity> admin= userRepository.findByUserId(requestedAssetDto.getAdminId());
        if(OptRequestedAssetEntity.isPresent() ) {
            RequestedAssetEntity requestedAssetEntity= OptRequestedAssetEntity.get();
            requestedAssetEntity.setDeniedTime(LocalDateTime.now());
            requestedAssetEntity.setStatus("rejected");
            requestedAssetEntity.setAdminEntity(admin.get());
            requestedAssetRepository.save(requestedAssetEntity);
            System.out.println("RequestedAsset denied");

    }
    }
    public void accept(RequestedAssetDto requestedAssetDto){

        Optional<RequestedAssetEntity> OptRequestedAssetEntity = requestedAssetRepository.findById(requestedAssetDto.getId());
        Optional<UserEntity> admin= userRepository.findByUserId(requestedAssetDto.getAdminId());
        if(OptRequestedAssetEntity.isPresent() ) {
            RequestedAssetEntity requestedAssetEntity= OptRequestedAssetEntity.get();
            requestedAssetEntity.setAcceptTime(LocalDateTime.now());
            requestedAssetEntity.setStatus("accepted");
            requestedAssetEntity.setAdminEntity(admin.get());
            requestedAssetRepository.save(requestedAssetEntity);
            System.out.println("RequestedAsset accepted");
        }
    }
    public void checkIn(RequestedAssetDto requestedAssetDto ){
        Optional<RequestedAssetEntity> optRequestedAssetEntity = requestedAssetRepository.findById(requestedAssetDto.getId());
        if(optRequestedAssetEntity.isPresent()) {
            RequestedAssetEntity requestedAssetEntity= optRequestedAssetEntity.get();
            requestedAssetEntity.setActualCheckin(LocalDateTime.now());
            requestedAssetRepository.save(requestedAssetEntity);
            System.out.println("RequestedAsset checkin");

        }
    }
    public List<RequestedAssetDisplayDto> getRequestedAssetList(){
        List<RequestedAssetEntity> requesteAssets = requestedAssetRepository.findAll();
        return requesteAssets.stream()
                .map(Mapper::toRequestedAssetDisplayDto)
                .collect(Collectors.toList());
    }
    public List<RequestedAssetDisplayDto> getPendingRequestedAssetList(){
        List<RequestedAssetEntity> pendingRequestedAssets = requestedAssetRepository.findPendingAssets();
        return pendingRequestedAssets.stream()
                .map(Mapper::toRequestedAssetDisplayDto)
                .collect(Collectors.toList());
    }
    public List<RequestedAssetDisplayDto> getUserRequestedAssetList(String userId){
        List<RequestedAssetEntity> userRequestedAssets = requestedAssetRepository.findAssetsByUserId(userId);
        return userRequestedAssets.stream()
                .map(Mapper::toRequestedAssetDisplayDto)
                .collect(Collectors.toList());
    }
    public BigDecimal countPendingRequest(){
        BigDecimal count=requestedAssetRepository.countPendingRequest();
        return count!= null? count: BigDecimal.valueOf(0);
    }





}
