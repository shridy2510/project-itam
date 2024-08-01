package com.project.controller;

import com.project.dto.ManufacturerDto;
import com.project.repository.entities.ManufacturerEntity;
import com.project.service.ManufacturerService;
import com.project.util.CheckRole;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ManufacturerController {
    @Autowired
    private ManufacturerService manufacturerService;
    @Autowired
    private CheckRole checkRole;
    @GetMapping("/Manufacturer/getList")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<List<ManufacturerEntity>> getList(HttpServletRequest request){
      
            return ResponseEntity.ok(manufacturerService.getManufacturerList());
       

    }

    @GetMapping("/Manufacturer/getNameList")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<List<String>> getNameList(HttpServletRequest request){
      
            return ResponseEntity.ok(manufacturerService.getManufacturerNameList());
       

    }

    @PostMapping("/Manufacturer/create")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<String> createManufacturer(HttpServletRequest request, @RequestBody ManufacturerDto manufacturerDto){
      
            manufacturerService.createManufacturer(manufacturerDto);
            return ResponseEntity.ok("create manufacturer successfully");
       

    }

    @PutMapping("/Manufacturer/update")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<String> getList(HttpServletRequest request,@RequestBody ManufacturerDto manufacturerDto){
      
            manufacturerService.updateManufacturer(manufacturerDto);
            return ResponseEntity.ok("update manufacturer successfully");
       

    }

    @DeleteMapping("/Manufacturer/delete")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<String> getList(HttpServletRequest request,@RequestParam long id){
      
            manufacturerService.deleteManufacturer(id);
            return ResponseEntity.ok("delete manufacturer successfully");
       

    }
}
