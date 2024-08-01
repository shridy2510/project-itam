package com.project.controller;

import com.project.dto.CompanyDto;
import com.project.repository.entities.CompanyEntity;
import com.project.service.CompanyService;
import com.project.util.CheckRole;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CompanyController {
    @Autowired
    private CompanyService companyService;
    @Autowired
    private CheckRole checkRole;
    @GetMapping("/Company/getList")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<List<CompanyEntity>> getList(HttpServletRequest request){
       
            return ResponseEntity.ok(companyService.getCompanyList());
       

    }

    @GetMapping("/Company/getNameList")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<List<String>> getNameList(HttpServletRequest request){
       
            return ResponseEntity.ok(companyService.getCompanyNameList());
       

    }

    @PostMapping("/Company/create")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<String> createCompany(HttpServletRequest request, @RequestBody CompanyDto companyDto){
       
            companyService.createCompany(companyDto);
            return ResponseEntity.ok("create company successfully");
       

    }

    @PutMapping("/Company/update")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<String> getList(HttpServletRequest request,@RequestBody CompanyDto companyDto){
       
            companyService.updateCompany(companyDto);
            return ResponseEntity.ok("update company successfully");
       

    }

    @DeleteMapping("/Company/delete")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<String> getList(HttpServletRequest request,@RequestParam long id){
       
            companyService.deleteCompany(id);
            return ResponseEntity.ok("delete company successfully");
       

    }
}
