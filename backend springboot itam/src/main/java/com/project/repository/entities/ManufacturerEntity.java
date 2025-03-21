package com.project.repository.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name="Manufacturer")
@Getter
@Setter
public class ManufacturerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String URL;
    @OneToMany(mappedBy ="manufacturerEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<ModelEntity> modelEntities;


}
