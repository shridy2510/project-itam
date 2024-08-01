package com.project.service;

import com.project.dto.StatusDto;
import com.project.repository.StatusRepository;
import com.project.repository.entities.StatusEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatusService {
    @Autowired
    private StatusRepository statusRepository;
    public List<StatusEntity> getStatusList(){
        return statusRepository.findAll();
    }
    public List<String> getStatusNameList(){
        statusRepository.findAllNames();
        return statusRepository.findAllNames();
    }
    public void createStatus(StatusDto statusDto){
        StatusEntity statusEntity = new StatusEntity();
        statusEntity.setName(statusDto.getType());
        statusEntity.setType(statusDto.getType());
        statusRepository.save(statusEntity);
        System.out.println("Status saved");
    }
    public void updateStatus(StatusDto statusDto){
        Optional<StatusEntity> statusEntity = statusRepository.findById(statusDto.getId());
        if(statusEntity.isPresent()){
        statusEntity.get().setName(statusDto.getName());
        statusEntity.get().setType(statusDto.getType());
        statusRepository.save(statusEntity.get());
        System.out.println("Status updated");
    }
    }
    public void deleteStatus(long id){
        statusRepository.deleteById(id);
        System.out.println("Status deleted");

    }


}
