package com.project.util;

import com.project.displayDto.AssetDisplayDto;
import com.project.displayDto.AssetLogDisplayDto;
import com.project.displayDto.ModelDisplayDto;
import com.project.displayDto.UserDisplayDto;
import com.project.dto.RoleDto;
import com.project.repository.RoleRepository;
import com.project.repository.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

public class Mapper {
// chuyển modelEntity sang ModelDisplayDto
    public static ModelDisplayDto toModelDisplayDto (ModelEntity modelEntity){
        ModelDisplayDto modelDisplayDto = new ModelDisplayDto();
        modelDisplayDto.setModel_number(modelEntity.getModel_number());
        modelDisplayDto.setName(modelEntity.getName());
        // trả về là name nếu khác null, nếu null trả về là null
        modelDisplayDto.setCategoryName(modelEntity.getCategoryEntity() != null ? modelEntity.getCategoryEntity().getName() : null);
        modelDisplayDto.setManufacturerName(modelEntity.getManufacturerEntity() != null ? modelEntity.getManufacturerEntity().getName() : null);
        modelDisplayDto.setDescription(modelEntity.getDescription());
        return modelDisplayDto;

    }
    public static AssetDisplayDto toAssetDisplayDto (AssetEntity assetEntity){
        AssetDisplayDto assetDisplayDto = new AssetDisplayDto();
        assetDisplayDto.setAssetTag(assetEntity.getAssetTag());
        assetDisplayDto.setName(assetEntity.getName());
        assetDisplayDto.setModelName(assetEntity.getModelEntity().getName());
        assetDisplayDto.setCompanyName(assetEntity.getCompanyEntity().getName());
        assetDisplayDto.setStatus(assetEntity.getStatusEntity().getName());
        assetDisplayDto.setAssignedUserName(assetEntity.getUserEntity() != null ? assetEntity.getUserEntity().getUsername() : null);
        assetDisplayDto.setDescription(assetEntity.getDescription());
        assetDisplayDto.setLastCheckout(assetEntity.getLastCheckout());
        assetDisplayDto.setExpectedCheckin(assetEntity.getExpectedCheckin());
        return assetDisplayDto;
    }
    public static AssetLogDisplayDto toAssetLogDisplayDto (AssetLogEntity assetLogEntity){
        AssetLogDisplayDto assetLogDisplayDto = new AssetLogDisplayDto();
        assetLogDisplayDto.setAssetName(assetLogEntity.getAssetEntity().getName());
        assetLogDisplayDto.setAction(assetLogEntity.getActionType());
        assetLogDisplayDto.setAdminName(assetLogEntity.getAdmin().getUsername());
        assetLogDisplayDto.setUserName(assetLogEntity.getUser()!=null ? assetLogEntity.getUser().getUsername() : null);
        assetLogDisplayDto.setTimestamp(assetLogEntity.getTimestamp());
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
}
