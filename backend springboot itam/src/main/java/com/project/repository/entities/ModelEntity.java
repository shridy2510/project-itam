package com.project.repository.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name="Model")
public class ModelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "Category_id")
    private CategoryEntity categoryEntity;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "Manufacturer_id")
    private ManufacturerEntity manufacturerEntity;
    private String model_number;
    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy="modelEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AssetEntity> assets;

}
