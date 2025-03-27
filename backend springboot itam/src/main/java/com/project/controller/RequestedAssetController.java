package com.project.controller;

import com.project.displayDto.AssetDisplayDto;
import com.project.displayDto.RequestedAssetDisplayDto;
import com.project.dto.RequestedAssetDto;
import com.project.service.RequestedAssetService;
import com.project.util.Token;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class RequestedAssetController {
    @Autowired
    private RequestedAssetService requestedAssetService;
    @PostMapping("/request/checkOut")
    @PreAuthorize("hasRole('User')")
    public ResponseEntity<String> create(@RequestBody RequestedAssetDto requestedAssetDto){
        requestedAssetService.create(requestedAssetDto);
        return ResponseEntity.ok("request create successfully");
    }
    @PostMapping("/request/checkIn")
    @PreAuthorize("hasRole('User')")
    public ResponseEntity<String> createCheckIn(@RequestBody RequestedAssetDto requestedAssetDto){
        requestedAssetService.createCheckInRequest(requestedAssetDto);
        return ResponseEntity.ok("request create successfully");
    }
    @PutMapping("/update/request/checkIn")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<String> update(@RequestBody RequestedAssetDto requestedAssetDto){
        requestedAssetService.updateRequestCheckIn(requestedAssetDto);
        return ResponseEntity.ok("Update successfully");
    }
    @PostMapping("/accept")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<String> accept(@RequestBody RequestedAssetDto requestedAssetDto){
        requestedAssetService.accept(requestedAssetDto);
        return ResponseEntity.ok("Accepted successfully");
    }
    @PostMapping("/deny")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<String> deny( @RequestBody RequestedAssetDto requestedAssetDto){
        requestedAssetService.deny(requestedAssetDto);
        return ResponseEntity.ok("Denied successfully");
    }
    @PostMapping("/checkInRequest")
    public ResponseEntity<String> checkIn( @RequestBody RequestedAssetDto requestedAssetDto){
        requestedAssetService.checkIn(requestedAssetDto);
        return ResponseEntity.ok("Check In successfully");
    }
    @GetMapping("/RequestedAssets/getList")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<List<RequestedAssetDisplayDto>> getList(HttpServletRequest request){
        return ResponseEntity.ok(requestedAssetService.getRequestedAssetList());
    }
    @GetMapping("/RequestedAssets/getPendingList")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<List<RequestedAssetDisplayDto>> getPendingList(HttpServletRequest request){
        return ResponseEntity.ok(requestedAssetService.getPendingRequestedAssetList());
    }
    @GetMapping("/RequestedAssets/User/getList")
    @PreAuthorize("hasRole('User')")
    public ResponseEntity<List<RequestedAssetDisplayDto>> getListUserRequest(HttpServletRequest request){
        Token token= new Token();
        String id=token.getUserIdFromToken(request);
        return ResponseEntity.ok(requestedAssetService.getUserRequestedAssetList(id));
    }

    @GetMapping("/RequestedAssets/Count/Pending")
    @PreAuthorize("hasRole('Admin')")
    public BigDecimal getPendingCount(HttpServletRequest request) {
        return requestedAssetService.countPendingRequest();
    }






}
