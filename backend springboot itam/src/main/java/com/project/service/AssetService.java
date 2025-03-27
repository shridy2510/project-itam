package com.project.service;

import com.project.displayDto.AssetDisplayDto;
import com.project.displayDto.BarChartDto;
import com.project.displayDto.PieChartDto;
import com.project.dto.AssetDto;
import com.project.repository.*;
import com.project.repository.entities.*;
import com.project.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.project.util.Mapper.toAssetDisplayDto;
import static com.project.util.Mapper.toAssetDto;

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
    @Autowired
    private RequestedAssetRepository requestedAssetRepository;

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
        AssetEntity assetEntity = new AssetEntity();
// Company
        if (assetDto.getCompany_id() != null) {
            Optional<CompanyEntity> optCompanyEntity = companyRepository.findById(assetDto.getCompany_id());
            if (optCompanyEntity.isPresent()) {
                assetEntity.setCompanyEntity(optCompanyEntity.get());
            }
        }
// Status
        if (assetDto.getStatus_id() != null) {
            Optional<StatusEntity> optStatusEntity = statusRepository.findById(assetDto.getStatus_id());
            if (optStatusEntity.isPresent()) {
                assetEntity.setStatusEntity(optStatusEntity.get());
            }
        }
// Model
        if (assetDto.getModel_id() != null) {
            Optional<ModelEntity> optModelEntity = modelRepository.findById(assetDto.getModel_id());
            if (optModelEntity.isPresent()) {
                assetEntity.setModelEntity(optModelEntity.get());
            }
        }


        if(assetDto.getAssignedUser_id()!=null){
        Optional<UserEntity> optUserEntity= userRepository.findById(assetDto.getAssignedUser_id());
            if (optUserEntity.isPresent() ) {
                UserEntity userEntity = optUserEntity.get();
                assetEntity.setUserEntity(userEntity);
            }}
        assetEntity.setName(assetDto.getName());
        assetEntity.setAssetTag(assetDto.getAssetTag());
        assetEntity.setDescription(assetDto.getDescription());
        assetEntity.setSerial(assetDto.getSerial());
        assetEntity.setCost(assetDto.getCost());
        assetEntity.setLastCheckout(assetDto.getLastCheckout());
        assetEntity.setExpectedCheckin(assetDto.getExpectedCheckin());
        assetEntity.setDepartment(assetDto.getDepartment());
        assetEntity.setLocation(assetDto.getLocation());
        assetRepository.save(assetEntity);
        System.out.println("Asset saved");


    }
    public void updateAsset(AssetDto assetDto) {
        Optional<AssetEntity> optAssetEntity = assetRepository.findById(assetDto.getId());
        if(optAssetEntity.isPresent()){
            AssetEntity assetEntity = optAssetEntity.get();
            if(assetDto.getAssignedUser_id() !=null){ assetEntity.setUserEntity(userRepository.findById(assetDto.getAssignedUser_id()).orElse(null));}
            if(assetDto.getCompany_id() !=null){assetEntity.setCompanyEntity(companyRepository.findById(assetDto.getCompany_id()).orElse(null));}
            if(assetDto.getStatus_id()!=null){  assetEntity.setStatusEntity(statusRepository.findById(assetDto.getStatus_id()).orElse(null));}
            if(assetDto.getModel_id() !=null){assetEntity.setModelEntity(modelRepository.findById(assetDto.getModel_id()).orElse(null));}

            if (assetDto.getName() != null) {
                assetEntity.setName(assetDto.getName());
            }
            if (assetDto.getAssetTag() != null) {
                assetEntity.setAssetTag(assetDto.getAssetTag());
            }
            if (assetDto.getDescription() != null) {
                assetEntity.setDescription(assetDto.getDescription());
            }
            if (assetDto.getSerial() != null) {
                assetEntity.setSerial(assetDto.getSerial());
            }
            if (assetDto.getCost() != null) {
                assetEntity.setCost(assetDto.getCost());
            }
            if (assetDto.getLastCheckout() != null) {
                assetEntity.setLastCheckout(assetDto.getLastCheckout());
            }
            if (assetDto.getExpectedCheckin() != null) {
                assetEntity.setExpectedCheckin(assetDto.getExpectedCheckin());
            }
            if (assetDto.getDepartment() != null) {
                assetEntity.setDepartment(assetDto.getDepartment());
            }
            if (assetDto.getLocation() != null) {
                assetEntity.setLocation(assetDto.getLocation());
            }

            assetRepository.save(assetEntity);
            System.out.println("Asset updated");
        }
    }


    public void deleteAsset(long id){
        assetRepository.deleteById(id);
        System.out.println("Asset deleted");

    }

    public AssetDisplayDto viewAsset(long id){
        Optional<AssetEntity> optAssetEntity = assetRepository.findById(id);
        if(optAssetEntity.isPresent()){
            AssetEntity assetEntity = optAssetEntity.get();
            return toAssetDisplayDto( assetEntity);

        }
        return null;
    }
//lấy tổng asset gọi api định kì ở front end
    public long getTotalAssets() {
        return assetRepository.countAssets();
    }
    // trả vè dữ liêu asset by status

    //trả về thoong tin asset theo id người dùng
    public List<AssetDisplayDto> getAssetByUserId(String user_id){
        List<AssetEntity> assets= assetRepository.findAssetsByUserId(user_id);
        return assets.stream()
                .map(Mapper::toAssetDisplayDto)
                .collect(Collectors.toList());
    }
    public AssetDto getAssetById(long id){
        Optional<AssetEntity> asset= assetRepository.findById(id);
        if(asset.isPresent()){
            return toAssetDto(asset.get());
        }
        else return null;



    }



     public long countTotalAvailableAssets(){
        return assetRepository.countAvailableAssets();

     }
     public BigDecimal countTotalCost(){
        return assetRepository.countVAlueAssets();
     }
     public BigDecimal countPastDueAssets(){
         BigDecimal count=assetRepository.countPassDueAssets();
        return  count!= null? count: BigDecimal.valueOf(0);

     }
    public BigDecimal countUnderRepairAssets(){
        BigDecimal count=assetRepository.countUnderRepairAsset();
        return count!= null? count: BigDecimal.valueOf(0);
    }
    public BigDecimal countBrokenAssets(){
        BigDecimal count=assetRepository.countBrokenAsset();
        return count!= null? count: BigDecimal.valueOf(0);
    }
    public BigDecimal countLostMissingAssets(){
        BigDecimal count=assetRepository.countLostMissingAsset();
        return count!= null? count: BigDecimal.valueOf(0);
    }


    public List<BigDecimal> countAlerts(){
        BigDecimal data1=this.countPastDueAssets();
        BigDecimal data2=this.countUnderRepairAssets();
        BigDecimal data3=this.countBrokenAssets();
        BigDecimal data4=this.countLostMissingAssets();
        BigDecimal data5= requestedAssetRepository.countPendingRequest();
        BigDecimal totalData=data1.add(data2.add(data3).add(data4).add(data5));
        return List.of(data1, data2, data3, data4,totalData,data5);



    }


     //bar chart
     public List<BarChartDto> costByStatus() {
         BigDecimal[] costs = {
                 assetRepository.sumAvailableCost(),
                 assetRepository.sumBrokenCost(),
                 assetRepository.sumCheckedOutCost(),
                 assetRepository.sumDisposedCost(),
                 assetRepository.sumLostMissingCost(),
                 assetRepository.sumUnderRepairCost()
         };

         String[] status = {
                 "Available", "Broken", "Checked Out", "Disposed", "Lost/Missing", "Under Repair"
         };

         List<BarChartDto> listCostByStatus = new ArrayList<>();

         for (int i = 0; i < status.length; i++) {
             listCostByStatus.add(new BarChartDto(status[i], costs[i]));
         }

         return listCostByStatus;
     }

    //pie chart
    public List<PieChartDto> totalAssetByStatus() {
        BigDecimal[] count = {
                assetRepository.countAvailableAsset(),
                assetRepository.countBrokenAsset(),
                assetRepository.countCheckedOutAsset(),
                assetRepository.countDisposedAsset(),
                assetRepository.countLostMissingAsset(),
                assetRepository.countUnderRepairAsset()
        };

        String[] status = {
                "Available", "Broken", "Checked Out", "Disposed", "Lost/Missing", "Under Repair"
        };

        List<PieChartDto> listTotalByStatus = new ArrayList<>();

        for (int i = 0; i < status.length; i++) {
            listTotalByStatus.add(new PieChartDto(status[i], count[i]));
        }

        return listTotalByStatus;
    }

    //return assets by status id
    public List<AssetDisplayDto> getAssetsAvailable(){
        List<AssetEntity> assets= assetRepository.findAssetsByStatusId(1);
        return assets.stream()
                .map(Mapper::toAssetDisplayDto)
                .collect(Collectors.toList());
    }
    public List<AssetDisplayDto> getAssetsBroken(){
        List<AssetEntity> assets= assetRepository.findAssetsByStatusId(2);
        return assets.stream()
                .map(Mapper::toAssetDisplayDto)
                .collect(Collectors.toList());
    }
    public List<AssetDisplayDto> getAssetsCheckedOut(){
        List<AssetEntity> assets= assetRepository.findAssetsByStatusId(3);
        return assets.stream()
                .map(Mapper::toAssetDisplayDto)
                .collect(Collectors.toList());
    }
    public List<AssetDisplayDto> getAssetsDisposed(){
        List<AssetEntity> assets=  assetRepository.findAssetsByStatusId(4);
        return assets.stream()
                .map(Mapper::toAssetDisplayDto)
                .collect(Collectors.toList());
    }
    public List<AssetDisplayDto> getAssetsLostMissing(){
        List<AssetEntity> assets= assetRepository.findAssetsByStatusId(5);
        return assets.stream()
                .map(Mapper::toAssetDisplayDto)
                .collect(Collectors.toList());
    }
    public List<AssetDisplayDto> getAssetsUnderRepair(){
        List<AssetEntity> assets= assetRepository.findAssetsByStatusId(6);
        return assets.stream()
                .map(Mapper::toAssetDisplayDto)
                .collect(Collectors.toList());
    }
    //due date stuff

    public List<AssetDisplayDto> getAssetsDue(){
        List<AssetEntity> assets= assetRepository.findAssetsWithOverdueTime();

        return assets.stream()
                .map(Mapper::toAssetDisplayDto)
                .collect(Collectors.toList());
    }

    public void checkIn(AssetDto assetDto){
        Optional<AssetEntity> optAssetEntity = assetRepository.findById(assetDto.getId());
        if(optAssetEntity.isPresent()){
            AssetEntity assetEntity = optAssetEntity.get();
            if(assetDto.getStatus_id()!=null){  assetEntity.setStatusEntity(statusRepository.findById(assetDto.getStatus_id()).orElse(null));}
            assetEntity.setExpectedCheckin(null);
            assetEntity.setLastCheckout(null);
            assetEntity.setUserEntity(null);
            assetRepository.save(assetEntity);

        }

    }






}
