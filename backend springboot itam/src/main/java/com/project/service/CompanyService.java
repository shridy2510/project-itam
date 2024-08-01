package com.project.service;

import com.project.dto.CompanyDto;
import com.project.dto.CompanyDto;
import com.project.repository.CompanyRepository;
import com.project.repository.entities.CompanyEntity;
import com.project.repository.entities.CompanyEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {
    @Autowired
    private com.project.repository.CompanyRepository CompanyRepository;
    public List<CompanyEntity> getCompanyList(){
        return CompanyRepository.findAll();
    }
    public List<String> getCompanyNameList(){
        CompanyRepository.findAllNames();
        return CompanyRepository.findAllNames();
    }
    public void createCompany(CompanyDto companyDto){
        CompanyEntity companyEntity = new CompanyEntity();
        companyEntity.setName(companyDto.getName());
        companyEntity.setAddress(companyDto.getAddress());
        CompanyRepository.save(companyEntity);

    }


    public void updateCompany(CompanyDto companyDto){
        Optional<CompanyEntity> companyEntity= CompanyRepository.findById(companyDto.getId());
        if(companyEntity.isPresent()){companyEntity.get().setName(companyDto.getName());
            companyEntity.get().setAddress(companyDto.getAddress());
            CompanyRepository.save(companyEntity.get());
            System.out.println("Company update");}

    }


    public void deleteCompany(long id){
        CompanyRepository.deleteById(id);
        System.out.println("Company deleted");

    }
}
