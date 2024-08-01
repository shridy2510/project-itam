package com.project.controller;

import com.project.dto.StatusDto;
import com.project.repository.entities.StatusEntity;
import com.project.service.StatusService;
import com.project.util.CheckRole;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StatusController {
    @Autowired
    private StatusService statusService;
    @Autowired
    private CheckRole checkRole;
    @GetMapping("/Status/getList")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<List<StatusEntity>> getList(HttpServletRequest request){
      
        return ResponseEntity.ok(statusService.getStatusList());
       

    }

    @GetMapping("/Status/getNameList")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<List<String>> getNameList(HttpServletRequest request){
      
            return ResponseEntity.ok(statusService.getStatusNameList());
       

    }

    @PostMapping("/Status/create")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<String> createStatus(HttpServletRequest request, @RequestBody StatusDto statusDto){
      
            statusService.createStatus(statusDto);
            return ResponseEntity.ok("create status successfully");
       

    }

    @PutMapping("/Status/update")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<String> getList(HttpServletRequest request,@RequestBody StatusDto statusDto){
      
            statusService.updateStatus(statusDto);
            return ResponseEntity.ok("update status successfully");
       

    }

    @DeleteMapping("/Status/delete")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<String> getList(HttpServletRequest request,@RequestParam long id){
      
            statusService.deleteStatus(id);
            return ResponseEntity.ok("delete status successfully");
       

    }
}
