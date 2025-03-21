package com.project.service;

import com.project.dto.ManufacturerDto;
import com.project.dto.CategoryDto;
import com.project.repository.CategoryRepository;
import com.project.repository.ManufacturerRepository;
import com.project.repository.entities.CategoryEntity;
import com.project.repository.entities.ManufacturerEntity;
import com.project.repository.entities.CategoryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    public List<CategoryEntity> getCategoryList(){
        return categoryRepository.findAll();
    }
    public List<String> getCategoryNameList(){
        categoryRepository.findAllNames();
        return categoryRepository.findAllNames();
    }
    public void createCategory(CategoryDto categoryDto){
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName(categoryDto.getName());
        if(categoryDto.getType()!=null){ categoryEntity.setType(categoryDto.getType());}
        categoryRepository.save(categoryEntity);
        System.out.println("Category saved");
    }
    public void updateCategory(CategoryDto categoryDto){
        Optional<CategoryEntity> categoryEntity = categoryRepository.findById(categoryDto.getId());
        if(categoryEntity.isPresent()){
            categoryEntity.get().setName(categoryDto.getName());
            categoryEntity.get().setType(categoryDto.getType());
            categoryRepository.save(categoryEntity.get());
            System.out.println("Category updated");
        }
    }
    public void deleteCategory(long id){
        categoryRepository.deleteById(id);
        System.out.println("Category deleted");

    }

}
