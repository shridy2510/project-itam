package com.project.controller;

import com.project.dto.RequestedAssetDto;
import com.project.service.RequestedAssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/RequestAsset")
@PreAuthorize("hasRole('Admin')")
public class RequestedAssetController {
    @Autowired
    private RequestedAssetService requestedAssetService;
    @PostMapping("/create")
    public ResponseEntity<String> create(RequestedAssetDto requestedAssetDto){
        requestedAssetService.create(requestedAssetDto);
        return ResponseEntity.ok("Created successfully");
    }
    @PutMapping("/update")
    public ResponseEntity<String> update(RequestedAssetDto requestedAssetDto){
        requestedAssetService.update(requestedAssetDto);
        return ResponseEntity.ok("Update successfully");
    }
    @PostMapping("/accept")
    public ResponseEntity<String> accept(long id){
        requestedAssetService.accept(id);
        return ResponseEntity.ok("Accepted successfully");
    }
    @PostMapping("/deny")
    public ResponseEntity<String> deny(long id){
        requestedAssetService.deny(id);
        return ResponseEntity.ok("Denied successfully");
    }





}
