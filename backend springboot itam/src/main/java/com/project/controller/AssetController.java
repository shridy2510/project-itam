package com.project.controller;

import com.project.displayDto.AssetDisplayDto;
import com.project.dto.AssetDto;
import com.project.service.AssetService;
import com.project.util.CheckRole;
import com.project.util.Token;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AssetController {
    @Autowired
    private AssetService assetService;
    @Autowired
    private CheckRole checkRole;
    @GetMapping("/Asset/getList")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<List<AssetDisplayDto>> getList(HttpServletRequest request){
    
            return ResponseEntity.ok(assetService.getAssetList());
     

    }

    @GetMapping("/Asset/getNameList")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<List<String>> getNameListUser(HttpServletRequest request){
            return ResponseEntity.ok(assetService.getAssetNameList());
     

    }


    @PostMapping("/Asset/create")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<String> createAsset(HttpServletRequest request, @RequestBody AssetDto assetDto){
    
            assetService.createAsset(assetDto);
            return ResponseEntity.ok("create asset successfully");
     

    }

    @PutMapping("/Asset/update")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<String> getList(HttpServletRequest request,@RequestBody AssetDto assetDto){
    
            assetService.updateAsset(assetDto);
            return ResponseEntity.ok("update asset successfully");
     

    }

    @DeleteMapping("/Asset/delete")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<String> getList(HttpServletRequest request,@RequestParam long id){
    
            assetService.deleteAsset(id);
            return ResponseEntity.ok("delete asset successfully");

    }

    @GetMapping("/Asset/total")
    public long getTotalAssets() {
        return assetService.getTotalAssets();
    }

    @GetMapping("/Asset/User/getList")
    @PreAuthorize("hasRole('User')")
    public ResponseEntity<List<AssetDisplayDto>> getListUser(HttpServletRequest request){
        Token token= new Token();
        String id=token.getUserIdFromToken(request);
        return ResponseEntity.ok(assetService.getAssetById(id));


    }

}
