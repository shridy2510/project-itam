package com.project.controller;

import com.project.dto.CategoryDto;
import com.project.repository.entities.CategoryEntity;
import com.project.service.CategoryService;
import com.project.util.CheckRole;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CheckRole checkRole;
    @GetMapping("/Category/getList")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<List<CategoryEntity>> getList(HttpServletRequest request){
       
            return ResponseEntity.ok(categoryService.getCategoryList());
       

    }

    @GetMapping("/Category/getNameList")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<List<String>> getNameList(HttpServletRequest request){
       
            return ResponseEntity.ok(categoryService.getCategoryNameList());
       

    }

    @PostMapping("/Category/create")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<String> createCategory(HttpServletRequest request, @RequestBody CategoryDto categoryDto){
       
            categoryService.createCategory(categoryDto);
            return ResponseEntity.ok("create category successfully");
       

    }

    @PutMapping("/Category/update")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<String> getList(HttpServletRequest request,@RequestBody CategoryDto categoryDto){
       
            categoryService.updateCategory(categoryDto);
            return ResponseEntity.ok("update category successfully");
       

    }

    @DeleteMapping("/Category/delete")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<String> getList(HttpServletRequest request,@RequestParam long id){
       
            categoryService.deleteCategory(id);
            return ResponseEntity.ok("delete category successfully");
       

    }
}
