package com.project.service;

import com.project.displayDto.AssetLogDisplayDto;
import com.project.dto.AssetLogDto;
import com.project.repository.AssetLogRepository;
import com.project.repository.AssetRepository;
import com.project.repository.UserRepository;
import com.project.repository.entities.AssetEntity;
import com.project.repository.entities.AssetLogEntity;
import com.project.repository.entities.UserEntity;
import com.project.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AssetLogService {
    @Autowired
    private AssetLogRepository assetLogRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AssetRepository assetRepository;


    public List<AssetLogDisplayDto> getAssetLogList(){
        List<AssetLogEntity> assetLogs = assetLogRepository.findAll();
        return assetLogs.stream()
                .map(Mapper::toAssetLogDisplayDto)
                .collect(Collectors.toList());
    }
    public void createAssetLog(AssetLogDto assetLogDto){
        AssetLogEntity assetLogEntity = new AssetLogEntity();
        Optional<AssetEntity> assetEntityOptional = assetRepository.findById(assetLogDto.getAsset_id());
        Optional<UserEntity> adminOptional = userRepository.findByUserId(assetLogDto.getAdmin_id());
        if(assetLogDto.getUser_id()!=null){
        Optional<UserEntity> userOptional =userRepository.findByUserId(assetLogDto.getUser_id());
        if(userOptional.isPresent()){
            UserEntity user = userOptional.get();
            assetLogEntity.setUser(user);
            }
        }
        if(adminOptional.isPresent()&&assetEntityOptional.isPresent()){
            UserEntity admin= adminOptional.get();
            AssetEntity assetEntity= assetEntityOptional.get();
            assetLogEntity.setTimestamp(LocalDateTime.now());
            assetLogEntity.setActionType(assetLogDto.getAction());
            assetLogEntity.setAdmin(admin);
            assetLogEntity.setAssetEntity(assetEntity);
            assetLogRepository.save(assetLogEntity);
            System.out.println("AssetLog create");
        }



    }


    public void updateAssetLog(AssetLogDto assetLogDto){
        Optional<AssetLogEntity> assetLogOptional = assetLogRepository.findById(assetLogDto.getId());
        Optional<AssetEntity> assetEntityOptional = assetRepository.findById(assetLogDto.getAsset_id());
        Optional<UserEntity> adminOptional = userRepository.findByUserId(assetLogDto.getAdmin_id());
        if(assetLogOptional.isPresent()){
            AssetLogEntity assetLogEntity = assetLogOptional.get();
            if(assetLogDto.getUser_id()!=null){
                Optional<UserEntity> userOptional =userRepository.findByUserId(assetLogDto.getUser_id());
                if(userOptional.isPresent()){
                    UserEntity user = userOptional.get();
                    assetLogEntity.setUser(user);
                }
            }
            if(adminOptional.isPresent()&&assetEntityOptional.isPresent()){
                UserEntity admin= adminOptional.get();
                AssetEntity assetEntity= assetEntityOptional.get();
                assetLogEntity.setTimestamp(LocalDateTime.now());
                assetLogEntity.setActionType(assetLogDto.getAction());
                assetLogEntity.setAdmin(admin);
                assetLogEntity.setAssetEntity(assetEntity);
                assetLogRepository.save(assetLogEntity);
                System.out.println("AssetLog update");
            }

           }

    }

    public void deleteAssetLog(long id){
        assetLogRepository.deleteById(id);
        System.out.println("AssetLog deleted");

    }
}
