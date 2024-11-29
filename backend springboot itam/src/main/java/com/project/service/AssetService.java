package com.project.service;

import com.project.displayDto.AssetDisplayDto;
import com.project.dto.AssetDto;
import com.project.repository.*;
import com.project.repository.entities.*;
import com.project.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AssetService {
    @Autowired
    private AssetRepository assetRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private StatusRepository statusRepository;
    @Autowired
    private ModelRepository modelRepository;
    @Autowired
    private UserRepository userRepository;

    public List<AssetDisplayDto> getAssetList(){
        List<AssetEntity> assets = assetRepository.findAll();
        return assets.stream()
                .map(Mapper::toAssetDisplayDto)
                .collect(Collectors.toList());
    }
    public List<String> getAssetNameList(){
        assetRepository.findAllNames();
        return assetRepository.findAllNames();
    }
    public void createAsset(AssetDto assetDto){
        Optional<CompanyEntity> optCompanyEntity= companyRepository.findById(assetDto.getCompany_id());
        Optional<StatusEntity> optStatusEntity= statusRepository.findById(assetDto.getStatus_id());
        Optional<ModelEntity> optModelEntity= modelRepository.findById(assetDto.getModel_id());
        AssetEntity assetEntity = new AssetEntity();
        if(assetDto.getAssignedUser_id()!=null){
        Optional<UserEntity> optUserEntity= userRepository.findByUserId(assetDto.getAssignedUser_id());
            if (optUserEntity.isPresent() ) {
                UserEntity userEntity = optUserEntity.get();
                assetEntity.setUserEntity(userEntity);
            }}

        if(optCompanyEntity.isPresent()&&optStatusEntity.isPresent()&&optModelEntity.isPresent()){
            CompanyEntity companyEntity = optCompanyEntity.get();
            StatusEntity statusEntity = optStatusEntity.get();
            ModelEntity modelEntity = optModelEntity.get();

            assetEntity.setName(assetDto.getName());
            assetEntity.setAssetTag(assetDto.getAssetTag());
            assetEntity.setDescription(assetDto.getDescription());
            assetEntity.setSerial(assetDto.getSerial());
            assetEntity.setCompanyEntity(companyEntity);
            assetEntity.setStatusEntity(statusEntity);
            assetEntity.setModelEntity(modelEntity);
            assetEntity.setCost(assetDto.getCost());

            assetEntity.setLastCheckout(assetDto.getLastCheckout());
            assetEntity.setExpectedCheckin(assetDto.getExpectedCheckin());

        }
        assetRepository.save(assetEntity);
        System.out.println("Asset saved");


    }
    public void updateAsset( AssetDto assetDto){
        Optional<AssetEntity> optAssetEntity = assetRepository.findById(assetDto.getId());
        Optional<CompanyEntity> optCompanyEntity= companyRepository.findById(assetDto.getCompany_id());
        Optional<StatusEntity> optStatusEntity= statusRepository.findById(assetDto.getStatus_id());
        Optional<ModelEntity> optModelEntity= modelRepository.findById(assetDto.getModel_id());
        AssetEntity assetEntity = new AssetEntity();
        if(optAssetEntity.isPresent()){
            if(assetDto.getAssignedUser_id()!=null){
                Optional<UserEntity> optUserEntity= userRepository.findByUserId(assetDto.getAssignedUser_id());
                if (optUserEntity.isPresent() ) {
                    UserEntity userEntity = optUserEntity.get();
                    assetEntity.setUserEntity(userEntity);
                    }
            }

            if(optCompanyEntity.isPresent()&&optStatusEntity.isPresent()&&optModelEntity.isPresent()){
                CompanyEntity companyEntity = optCompanyEntity.get();
                StatusEntity statusEntity = optStatusEntity.get();
                ModelEntity modelEntity = optModelEntity.get();

                assetEntity.setName(assetDto.getName());
                assetEntity.setAssetTag(assetDto.getAssetTag());
                assetEntity.setDescription(assetDto.getDescription());
                assetEntity.setCompanyEntity(companyEntity);
                assetEntity.setStatusEntity(statusEntity);
                assetEntity.setModelEntity(modelEntity);
                assetEntity.setSerial(assetDto.getSerial());
                assetEntity.setCost(assetDto.getCost());

                assetEntity.setLastCheckout(assetDto.getLastCheckout());
                assetEntity.setExpectedCheckin(assetDto.getExpectedCheckin());

            }
            assetRepository.save(assetEntity);
            System.out.println("Asset updated");
        }
    }
    public void deleteAsset(long id){
        assetRepository.deleteById(id);
        System.out.println("Asset deleted");

    }
//lấy tổng asset gọi api định kì ở front end
    public long getTotalAssets() {
        return assetRepository.countAssets();
    }
    // trả vè dữ liêu asset by status

    //trả về asset theo id người dùng
    public List<AssetDisplayDto> getAssetById(String user_id){
        List<AssetEntity> assets= assetRepository.findAssetsByUserId(user_id);
        return assets.stream()
                .map(Mapper::toAssetDisplayDto)
                .collect(Collectors.toList());


    }


    public double returnAssetsValue(){
        return 0.120;

    }


}
