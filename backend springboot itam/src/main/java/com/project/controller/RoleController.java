package com.project.controller;

import com.project.dto.RoleDto;
import com.project.dto.UserDto;
import com.project.repository.entities.RoleEntity;
import com.project.service.RoleService;
import com.project.util.CheckRole;
import jakarta.servlet.http.HttpServletRequest;
import org.keycloak.representations.idm.RoleRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RoleController {
    @Autowired
    RoleService roleService;

    private CheckRole checkRole=new CheckRole();


//xoá role của người dùng với userId
    @PostMapping("/keycloak/remove-role")
    @PreAuthorize("hasRole('Admin')")
    //@PreAuthorize("hasRole('User')or hasRole('Admin')")
    public ResponseEntity<String> removeRole(@RequestBody UserDto userDto, HttpServletRequest request) {
      
        roleService.deleteRoleToUser(userDto);
        return ResponseEntity.ok("Role removed successfully");
       

    }
//trả về danh sách role với userId
    @GetMapping("/keycloak/roles")
    @PreAuthorize("hasRole('Admin')")
    //@PreAuthorize("hasRole('User')or hasRole('Admin')")
    public ResponseEntity<List<RoleRepresentation>> getUserRoles(@RequestBody UserDto userDto, HttpServletRequest request) {
      
        List<RoleRepresentation> roles = roleService.getRoleList(userDto);
        return ResponseEntity.ok(roles);
       

    }
    // assign role cho người dùng với UserId
    @PostMapping("/keycloak/assign-role")
    @PreAuthorize("hasRole('Admin')")
    //@PreAuthorize("hasRole('User')or hasRole('Admin')")
    public ResponseEntity<String> assignRole(@RequestBody UserDto userDto, HttpServletRequest request) {

        roleService.assignRoleToUser(userDto);
        return ResponseEntity.ok("Role assign successfully");


    }
    //tạo role trong db
    @PostMapping("/createRole")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<?> createRole(@RequestBody RoleDto roleDto ) {
        roleService.createRole(roleDto);
        return ResponseEntity.ok("Role created successfully");

    }
    //xoá role db
    @DeleteMapping("/deleteRole")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<String> deleteRole(@RequestBody RoleDto roleDto) {
        roleService.deleteRole(roleDto);
        return ResponseEntity.ok("Role deleted successfully");
    }

    // xem role db
    @GetMapping("/listRole")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<List<String>> getRoleList() {
        return ResponseEntity.ok(roleService.listRole());

    }



}
