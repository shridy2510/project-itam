package com.project.controller;

import com.project.displayDto.ModelDisplayDto;
import com.project.dto.ModelDto;
import com.project.repository.entities.ModelEntity;
import com.project.service.ModelService;
import com.project.util.CheckRole;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Modelcontroller {
    @Autowired
    private ModelService modelService;
    @Autowired
    private CheckRole checkRole;
    @GetMapping("/Model/getList")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<List<ModelDisplayDto>> getList(HttpServletRequest request){
    
            return ResponseEntity.ok(modelService.getModelList());
       

    }

    @GetMapping("/Model/getNameList")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<List<String>> getNameList(HttpServletRequest request){
    
            return ResponseEntity.ok(modelService.getModelNameList());
       

    }

    @PostMapping("/Model/create")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<String> createModel(HttpServletRequest request, @RequestBody ModelDto modelDto){
    
            modelService.createModel(modelDto);
            return ResponseEntity.ok("create model successfully");
       

    }

    @PutMapping("/Model/update")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<String> getList(HttpServletRequest request,@RequestBody ModelDto modelDto){
    
            modelService.updateModel(modelDto);
            return ResponseEntity.ok("update model successfully");
       

    }

    @DeleteMapping("/Model/delete")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<String> getList(HttpServletRequest request,@RequestParam long id){
    
            modelService.deleteModel(id);
            return ResponseEntity.ok("delete model successfully");
       

    }
}
