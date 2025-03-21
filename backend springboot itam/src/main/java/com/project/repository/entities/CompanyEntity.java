package com.project.repository.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name="Company")
public class CompanyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    @OneToMany(mappedBy="companyEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<AssetEntity> assets;

}
