package com.project.service;

import com.project.dto.RoleDto;
import com.project.dto.UserDto;
import com.project.repository.RoleRepository;
import com.project.repository.UserRepository;
import com.project.repository.entities.RoleEntity;
import com.project.repository.entities.UserEntity;
import com.project.security.KeycloakUtilSecurity;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.RoleRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleService {
    @Autowired
    private KeycloakUtilSecurity keycloakUtilSecurity;
    @Value("${realm}")
    private String realm;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;

    //lấy danh sách role của 1 người dùng với userId từ keycloak
    public List<RoleRepresentation> getRoleList(UserDto userDto) {
        Keycloak keycloak = keycloakUtilSecurity.KeycloakInstance();
        RealmResource realmResource = keycloak.realm(realm);
        UserResource userResource = realmResource.users().get(userDto.getUserId());
        return userResource.roles().realmLevel().listAll();

    }
    //thêm role vào realm cho người dùng với userId vào keycloak

    public void assignRoleToUser(UserDto userDto) {
        Keycloak keycloak = keycloakUtilSecurity.KeycloakInstance();
        UsersResource usersResource = keycloak.realm(realm).users();
        for(RoleEntity roleEntity : roleRepository.findByRoleIn(userDto.getRoles())) {
            RoleRepresentation role = keycloak.realm(realm).roles().get(roleEntity.getRole()).toRepresentation();
            usersResource.get(userDto.getUserId()).roles().realmLevel().add(Collections.singletonList(role));
        }

    }
    //xoá role vào realm với userId ở keycloak

    public void deleteRoleToUser(UserDto userDto) {
        Keycloak keycloak = keycloakUtilSecurity.KeycloakInstance();
        UsersResource usersResource = keycloak.realm(realm).users();
        for(RoleEntity roleEntity : roleRepository.findByRoleIn(userDto.getRoles())) {
            RoleRepresentation role = keycloak.realm(realm).roles().get(roleEntity.getRole()).toRepresentation();
            usersResource.get(userDto.getUserId()).roles().realmLevel().remove(Collections.singletonList(role));
        }
    }
    //lưu role vào db
    public void createRole(RoleDto roleDto) {
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setRole(roleDto.getRole());
        roleRepository.save(roleEntity);
    }
    //xoá role
    public void deleteRole(RoleDto roleDto) {
        RoleEntity roleEntity = roleRepository.findByRole(roleDto.getRole());
        roleRepository.delete(roleEntity);
    }
    public List <String> listRole () {
        List<RoleEntity> roleEntities = roleRepository.findAll();
        return roleEntities.stream().map(RoleEntity::getRole).collect(Collectors.toList());

    }

    public void addRoleToUser(Long userId, String roleName) {
        Optional<UserEntity> optUserEntity = userRepository.findById(userId);
        if(optUserEntity.isPresent()) {
        RoleEntity roleEntity = roleRepository.findByRole(roleName);
        optUserEntity.get().getRoles().add(roleEntity);
        userRepository.save(optUserEntity.get());
        }
    }

    public void removeRoleFromUser(Long userId, String roleName) {
        Optional<UserEntity> optUserEntity = userRepository.findById(userId);
        if (optUserEntity.isPresent()) {
            RoleEntity roleEntity = roleRepository.findByRole(roleName);
            optUserEntity.get().getRoles().remove(roleEntity);
            userRepository.save(optUserEntity.get());
        }
    }



}
