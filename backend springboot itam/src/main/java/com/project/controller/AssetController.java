package com.project.controller;

import com.project.displayDto.AssetDisplayDto;
import com.project.displayDto.BarChartDto;
import com.project.displayDto.PieChartDto;
import com.project.dto.AssetDto;
import com.project.service.AssetService;
import com.project.util.CheckRole;
import com.project.util.Token;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
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
    public ResponseEntity<Long> createAsset(HttpServletRequest request, @RequestBody AssetDto assetDto){
    
            Long id=assetService.createAsset(assetDto);
            return ResponseEntity.ok(id);
    }

    @PutMapping("/Asset/update")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<String> getList(HttpServletRequest request,@RequestBody AssetDto assetDto){
    try{assetService.updateAsset(assetDto);
        return ResponseEntity.ok("update asset successfully");}catch(Exception e){return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());}
    }

    @DeleteMapping("/Asset/delete")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<String> deleteList(HttpServletRequest request, @RequestParam long id){
    
            assetService.deleteAsset(id);
            return ResponseEntity.ok("delete asset successfully");

    }
    @GetMapping("/Asset/View")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<AssetDisplayDto> getAsset(@RequestParam long id) {
        AssetDisplayDto asset = assetService.viewAsset(id);
        if (asset == null) {
            return ResponseEntity.notFound().build(); // 404 Not Found if asset doesn't exist
        }
        return ResponseEntity.ok(asset); // 200 OK with asset data
    }

    @GetMapping("/Asset/Get")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<AssetDto> getAssetById(HttpServletRequest request, @RequestParam long id){
        AssetDto asset = assetService.getAssetById(id);
        if (asset == null) {
            return ResponseEntity.notFound().build(); // 404 Not Found if asset doesn't exist
        }
        return ResponseEntity.ok(asset);
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
        return ResponseEntity.ok(assetService.getAssetByUserId(id));
    }

    @GetMapping("/Asset/total/Available")
    @PreAuthorize("hasRole('Admin')")
    public long getTotalAvailableAssets() {
        return assetService.countTotalAvailableAssets();
    }
    @GetMapping("/Asset/TotalCost")
    @PreAuthorize("hasRole('Admin')")
    public BigDecimal getAssetsCost(HttpServletRequest request) {
        return assetService.countTotalCost();
    }
    @GetMapping("/Asset/Count/Alert")
    @PreAuthorize("hasRole('Admin')")
    public List<BigDecimal> getAlertCount(HttpServletRequest request) {
        return assetService.countAlerts();
    }

    //bar chart
    @GetMapping("/Asset/TotalCostByStatus")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<List<BarChartDto>> getCostByStatus(HttpServletRequest request){
        return ResponseEntity.ok(assetService.costByStatus());
    }
    // pie chart
    @GetMapping("/Asset/TotalByStatus")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<List<PieChartDto>> getTotalByStatus(HttpServletRequest request){
        return ResponseEntity.ok(assetService.totalAssetByStatus());
    }

    @GetMapping("/Asset/Available")
    @PreAuthorize("hasRole('Admin') or hasRole('User')")
    public ResponseEntity<List<AssetDisplayDto>> getListAvailable(HttpServletRequest request){
        return ResponseEntity.ok(assetService.getAssetsAvailable());
    }
    @GetMapping("/Asset/Broken")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<List<AssetDisplayDto>> getListBroken(HttpServletRequest request){
        return ResponseEntity.ok(assetService.getAssetsBroken());
    }
    @GetMapping("/Asset/CheckedOut")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<List<AssetDisplayDto>> getListCheckedOut(HttpServletRequest request){
        return ResponseEntity.ok(assetService.getAssetsCheckedOut());
    }
    @GetMapping("/Asset/Disposed")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<List<AssetDisplayDto>> getListDisposed(HttpServletRequest request){
        return ResponseEntity.ok(assetService.getAssetsDisposed());
    }

    @GetMapping("/Asset/LostMissing")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<List<AssetDisplayDto>> getListLostMissing(HttpServletRequest request){
        return ResponseEntity.ok(assetService.getAssetsLostMissing());
    }
    @GetMapping("/Asset/UnderRepair")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<List<AssetDisplayDto>> getListUnderRepair(HttpServletRequest request){
        return ResponseEntity.ok(assetService.getAssetsUnderRepair());
    }
    @GetMapping("/Asset/DueDate")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<List<AssetDisplayDto>> getListDueDate(HttpServletRequest request){
        return ResponseEntity.ok(assetService.getAssetsDue());
    }
    @PutMapping("/Asset/checkIn")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<String> checkIn(HttpServletRequest request,@RequestBody AssetDto assetDto){
        try{assetService.checkIn(assetDto);
            return ResponseEntity.ok("check in asset successfully");}catch(Exception e){return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());}
    }











}
