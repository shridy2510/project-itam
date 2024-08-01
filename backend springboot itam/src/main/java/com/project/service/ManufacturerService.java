package com.project.service;

import com.project.dto.ManufacturerDto;
import com.project.repository.ManufacturerRepository;
import com.project.repository.entities.ManufacturerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManufacturerService {
    @Autowired
    private ManufacturerRepository ManufacturerRepository;
    public List<ManufacturerEntity> getManufacturerList(){
        return ManufacturerRepository.findAll();
    }
    public List<String> getManufacturerNameList(){
        ManufacturerRepository.findAllNames();
        return ManufacturerRepository.findAllNames();
    }
    public void createManufacturer(ManufacturerDto manufacturerDto){
        ManufacturerEntity manufacturerEntity = new ManufacturerEntity();
        manufacturerEntity.setName(manufacturerDto.getName());
        manufacturerEntity.setURL(manufacturerDto.getUrl());
        ManufacturerRepository.save(manufacturerEntity);
        System.out.println("Manufacturer create");
        System.out.println(manufacturerEntity.getURL());
    }


    public void updateManufacturer( ManufacturerDto manufacturerDto){
        Optional<ManufacturerEntity> manufacturerEntity = ManufacturerRepository.findById(manufacturerDto.getId());
        if(manufacturerEntity.isPresent()){
            manufacturerEntity.get().setName(manufacturerDto.getName());
            manufacturerEntity.get().setURL(manufacturerDto.getUrl());
            ManufacturerRepository.save(manufacturerEntity.get());
            System.out.println("Manufacturer update");}

    }


    public void deleteManufacturer(long id){
        ManufacturerRepository.deleteById(id);
        System.out.println("Manufacturer deleted");

    }

}
