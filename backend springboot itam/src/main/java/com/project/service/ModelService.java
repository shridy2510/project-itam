package com.project.service;

import com.project.displayDto.ModelDisplayDto;
import com.project.dto.ModelDto;
import com.project.repository.CategoryRepository;
import com.project.repository.ManufacturerRepository;
import com.project.repository.ModelRepository;
import com.project.repository.entities.CategoryEntity;
import com.project.repository.entities.ManufacturerEntity;
import com.project.repository.entities.ModelEntity;
import com.project.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ModelService {
    @Autowired
    private ModelRepository modelRepository;
    @Autowired
    private ManufacturerRepository manufacturerRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    public List<ModelDisplayDto> getModelList(){
        List<ModelEntity> models = modelRepository.findAll();
        return models.stream()
                .map(Mapper::toModelDisplayDto)
                .collect(Collectors.toList());


    }
    public List<String> getModelNameList(){
        modelRepository.findAllNames();
        return modelRepository.findAllNames();
    }
    public void createModel(ModelDto modelDto){
        Optional<CategoryEntity> OptCategoryEntity = categoryRepository.findById(modelDto.getCategoryID());
        Optional<ManufacturerEntity> optManufacturerEntity = manufacturerRepository.findById(modelDto.getManufacturerID());
        if(OptCategoryEntity.isPresent()&&optManufacturerEntity.isPresent()){
            ModelEntity modelEntity = new ModelEntity();
            modelEntity.setName(modelDto.getName());
            modelEntity.setModel_number(modelDto.getModel_number());
            modelEntity.setDescription(modelDto.getDescription());

            CategoryEntity categoryEntity = OptCategoryEntity.get();
            ManufacturerEntity manufacturerEntity = optManufacturerEntity.get();
            modelEntity.setCategoryEntity(categoryEntity);
            modelEntity.setManufacturerEntity(manufacturerEntity);

            modelRepository.save(modelEntity);
            System.out.println("Model saved");
        }
    }
    public void updateModel( ModelDto modelDto){
        Optional<ModelEntity> optmodelEntity = modelRepository.findById(modelDto.getId());
        Optional<CategoryEntity> OptCategoryEntity = categoryRepository.findById(modelDto.getCategoryID());
        Optional<ManufacturerEntity> optManufacturerEntity = manufacturerRepository.findById(modelDto.getManufacturerID());
        if(optmodelEntity.isPresent()&& OptCategoryEntity.isPresent()&&optManufacturerEntity.isPresent()){
            ModelEntity modelEntity = optmodelEntity.get();
            modelEntity.setName(modelDto.getName());
            modelEntity.setName(modelDto.getName());
            modelEntity.setModel_number(modelDto.getModel_number());
            modelEntity.setDescription(modelDto.getDescription());

            CategoryEntity categoryEntity = OptCategoryEntity.get();
            ManufacturerEntity manufacturerEntity = optManufacturerEntity.get();
            modelEntity.setCategoryEntity(categoryEntity);
            modelEntity.setManufacturerEntity(manufacturerEntity);

            modelRepository.save(modelEntity);
            System.out.println("Model updated");
        }
    }
    public void deleteModel(long id){
        modelRepository.deleteById(id);
        System.out.println("Model deleted");

    }
}
