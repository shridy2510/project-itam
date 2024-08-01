package com.project.controller;

import com.project.repository.UserRepository;
import com.project.service.FixedDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class FixedDataController {
    @Autowired
    private FixedDataService fixDataService;

    //kiểm tra data fixed
    @GetMapping("/getAssetType")
    public ResponseEntity<List<String>> getAssetType() {
        return ResponseEntity.ok(fixDataService.getAssetType());
    }
    @GetMapping("/getStatusType")
    public ResponseEntity<List<String>> getStatusType() {
        return ResponseEntity.ok(fixDataService.getStatusType());
    }
    @GetMapping("/getActionType")
    public ResponseEntity<List<String>> getActionType() {
        return ResponseEntity.ok(fixDataService.getActionType());
    }

}

