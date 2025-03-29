package com.project.util;

import com.project.displayDto.*;
import com.project.dto.AssetDto;
import com.project.dto.RoleDto;
import com.project.repository.RoleRepository;
import com.project.repository.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Set;
import java.util.stream.Collectors;

public class Mapper {
// chuyển modelEntity sang ModelDisplayDto
    public static ModelDisplayDto toModelDisplayDto (ModelEntity modelEntity){
        ModelDisplayDto modelDisplayDto = new ModelDisplayDto();
        modelDisplayDto.setModel_number(modelEntity.getModel_number());
        modelDisplayDto.setName(modelEntity.getName());
        modelDisplayDto.setId(modelEntity.getId());
        // trả về là name nếu khác null, nếu null trả về là null
        modelDisplayDto.setCategoryName(modelEntity.getCategoryEntity() != null ? modelEntity.getCategoryEntity().getName() : null);
        modelDisplayDto.setManufacturerName(modelEntity.getManufacturerEntity() != null ? modelEntity.getManufacturerEntity().getName() : null);
        modelDisplayDto.setDescription(modelEntity.getDescription());
        return modelDisplayDto;

    }
    public static Long calculateDueDuration(LocalDateTime expectedCheckin) {
        if (expectedCheckin == null) {
            return null;
        }
        long days = ChronoUnit.DAYS.between(expectedCheckin,LocalDateTime.now() );
        return days;
    }

    public static AssetDisplayDto toAssetDisplayDto (AssetEntity assetEntity){
        AssetDisplayDto assetDisplayDto = new AssetDisplayDto();
        assetDisplayDto.setAssetTag(assetEntity.getAssetTag());
        assetDisplayDto.setName(assetEntity.getName());
        assetDisplayDto.setModelName(assetEntity.getModelEntity()!= null ? assetEntity.getModelEntity().getName() : null);
        assetDisplayDto.setCompanyName(assetEntity.getCompanyEntity()!= null ? assetEntity.getCompanyEntity().getName() : null);
        assetDisplayDto.setStatus(assetEntity.getStatusEntity()!= null ? assetEntity.getStatusEntity().getName() : null);
        assetDisplayDto.setAssignedUserName(assetEntity.getUserEntity() != null ? assetEntity.getUserEntity().getUsername() : null);
        assetDisplayDto.setDescription(assetEntity.getDescription());
        assetDisplayDto.setLastCheckout(assetEntity.getLastCheckout());
        assetDisplayDto.setExpectedCheckin(assetEntity.getExpectedCheckin());
        assetDisplayDto.setId(assetEntity.getId());
        assetDisplayDto.setAssignedUserId(assetEntity.getUserEntity() != null ? assetEntity.getUserEntity().getId() : null);
        assetDisplayDto.setSerial(assetEntity.getSerial());
        assetDisplayDto.setDepartment(assetEntity.getDepartment());
        assetDisplayDto.setCost(assetEntity.getCost());
        assetDisplayDto.setLocation(assetEntity.getLocation());
        assetDisplayDto.setPurchaseDate(assetEntity.getPurchaseDate());
        assetDisplayDto.setPurchasedFrom(assetEntity.getPurchasedFrom());
        long days= calculateDueDuration(assetEntity.getExpectedCheckin()!= null?assetEntity.getExpectedCheckin():LocalDateTime.now());
        if(days>0){assetDisplayDto.setDueDuration(days);}
        return assetDisplayDto;
    }
    public static AssetDto toAssetDto (AssetEntity assetEntity){
        AssetDto assetDto = new AssetDto();
        assetDto.setAssetTag(assetEntity.getAssetTag());
        assetDto.setName(assetEntity.getName());
        assetDto.setModel_id(assetEntity.getModelEntity()!= null ? assetEntity.getModelEntity().getId() : null);
        assetDto.setCompany_id(assetEntity.getCompanyEntity()!= null ? assetEntity.getCompanyEntity().getId() : null);
        assetDto.setStatus_id(assetEntity.getStatusEntity()!= null ? assetEntity.getStatusEntity().getId() : null);
        assetDto.setAssignedUser_id(assetEntity.getUserEntity() != null ? assetEntity.getUserEntity().getId() : null);
        assetDto.setDescription(assetEntity.getDescription());
        assetDto.setLastCheckout(assetEntity.getLastCheckout());
        assetDto.setExpectedCheckin(assetEntity.getExpectedCheckin());
        assetDto.setId(assetEntity.getId());
        assetDto.setSerial(assetEntity.getSerial());
        assetDto.setCost(assetEntity.getCost());
        assetDto.setDepartment(assetEntity.getDepartment());
        assetDto.setLocation(assetEntity.getLocation());
        assetDto.setPurchaseDate(assetEntity.getPurchaseDate());
        assetDto.setPurchasedFrom(assetEntity.getPurchasedFrom());
        return assetDto;}
    public static AssetLogDisplayDto toAssetLogDisplayDto (AssetLogEntity assetLogEntity){
        AssetLogDisplayDto assetLogDisplayDto = new AssetLogDisplayDto();
        assetLogDisplayDto.setAssetName(assetLogEntity.getAssetEntity().getName());
        assetLogDisplayDto.setAction(assetLogEntity.getAction());
        assetLogDisplayDto.setAdminName(assetLogEntity.getAdmin().getUsername());
        assetLogDisplayDto.setUserName(assetLogEntity.getUser()!=null ? assetLogEntity.getUser().getUsername() : null);
        assetLogDisplayDto.setCreated_at(assetLogEntity.getCreated_at());
        assetLogDisplayDto.setId(assetLogEntity.getId());
        return assetLogDisplayDto;
    }
    public static UserDisplayDto toUserDisplayDto (UserEntity userEntity){
        UserDisplayDto userDisplayDto = new UserDisplayDto();
        userDisplayDto.setUserId(userEntity.getUserId());
        userDisplayDto.setEmail(userEntity.getEmail());
        userDisplayDto.setFirstName(userEntity.getFirstName());
        userDisplayDto.setLastName(userEntity.getLastName());
        userDisplayDto.setRoles(convertRolesToRoleNames(userEntity.getRoles()));
        userDisplayDto.setUserName(userEntity.getUsername());
        userDisplayDto.setPhoneNumber(userEntity.getPhoneNumber());
        userDisplayDto.setId(userEntity.getId());

        return userDisplayDto;


    }
    public static Set<String> convertRolesToRoleNames(Set<RoleEntity> roleEntities) {
        return roleEntities.stream()
                .map(RoleEntity::getRole) // Chuyển đổi RoleEntity thành tên role
                .collect(Collectors.toSet()); // Thu thập kết quả vào một Set<String>
    }
    public static RoleDto toRoleDto (RoleEntity roleEntity) {
        RoleDto roleDto = new RoleDto();
        roleEntity.setRole(roleEntity.getRole());
        return roleDto;

    }
    public static RequestedAssetDisplayDto toRequestedAssetDisplayDto (RequestedAssetEntity requestedAssetEntity){
        RequestedAssetDisplayDto requestedAssetDisplayDto = new RequestedAssetDisplayDto();
        requestedAssetDisplayDto.setAcceptTime(requestedAssetEntity.getAcceptTime());
        requestedAssetDisplayDto.setActualCheckin(requestedAssetEntity.getActualCheckin());
        requestedAssetDisplayDto.setDeniedTime(requestedAssetEntity.getDeniedTime());
        requestedAssetDisplayDto.setId(requestedAssetEntity.getId());
        requestedAssetDisplayDto.setAdminName(requestedAssetEntity.getAdminEntity()!= null ? requestedAssetEntity.getAdminEntity().getUsername() : null);
        requestedAssetDisplayDto.setAssetTag(requestedAssetEntity.getAssetEntity().getAssetTag());
        requestedAssetDisplayDto.setAssignedUserName(requestedAssetEntity.getUserEntity().getUsername());
        requestedAssetDisplayDto.setStatus(requestedAssetEntity.getStatus());
        requestedAssetDisplayDto.setRequestDate(requestedAssetEntity.getRequestDate());
        requestedAssetDisplayDto.setModelName(requestedAssetEntity.getAssetEntity().getModelEntity()!= null ? requestedAssetEntity.getAssetEntity().getModelEntity().getName() : null);
        requestedAssetDisplayDto.setName(requestedAssetEntity.getAssetEntity().getName());
        requestedAssetDisplayDto.setExpectedCheckout(requestedAssetEntity.getExpectedCheckout());
        requestedAssetDisplayDto.setExpectedCheckin(requestedAssetEntity.getExpectedCheckin());
        requestedAssetDisplayDto.setLocation(requestedAssetEntity.getLocation());
        requestedAssetDisplayDto.setUserId(requestedAssetEntity.getUserEntity().getId());
        requestedAssetDisplayDto.setAssetId(requestedAssetEntity.getAssetEntity().getId());
        requestedAssetDisplayDto.setRequestType(requestedAssetEntity.getRequestType());

        return requestedAssetDisplayDto;

    }
}
