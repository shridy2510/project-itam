package com.project.controller;

import com.project.displayDto.AssetLogDisplayDto;
import com.project.dto.AssetLogDto;
import com.project.repository.entities.AssetLogEntity;
import com.project.service.AssetLogService;
import com.project.util.CheckRole;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AssetLogController {
    @Autowired
    private AssetLogService assetLogService;
    @Autowired
    private CheckRole checkRole;
    @GetMapping("/AssetLog/getList")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<List<AssetLogDisplayDto>> getList(HttpServletRequest request){
//        okif(checkRole.checkRoleAdmin(request)) {
            return ResponseEntity.ok(assetLogService.getAssetLogList());
//        ok}
////        else{
////            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
////        }

    }



    @PostMapping("/AssetLog/create")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<String> createAssetLog(HttpServletRequest request, @RequestBody AssetLogDto assetLogDto){
       
            assetLogService.createAssetLog(assetLogDto);
            return ResponseEntity.ok("create assetLog successfully");
     

    }

    @PutMapping("/AssetLog/update")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<String> getList(HttpServletRequest request,@RequestBody AssetLogDto assetLogDto){
       
            assetLogService.updateAssetLog(assetLogDto);
            return ResponseEntity.ok("update assetLog successfully");
     

    }

    @DeleteMapping("/AssetLog/delete")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<String> getList(HttpServletRequest request,@RequestParam long id){
       
            assetLogService. deleteAssetLog(id);
            return ResponseEntity.ok("delete assetLog successfully");
     

    }
}
