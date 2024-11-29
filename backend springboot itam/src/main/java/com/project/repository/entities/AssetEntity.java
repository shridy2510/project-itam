package com.project.repository.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="Asset")
public class AssetEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String assetTag;
    private String serial;
    private double cost;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "model_id")
    private ModelEntity modelEntity;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "status_id")
    private StatusEntity statusEntity;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="company_id")
    private CompanyEntity companyEntity;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="assigned_to")
    private UserEntity userEntity;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Column(name = "last_checkout")
    private LocalDateTime lastCheckout;
    @Column(name = "expected_checkin")
    private LocalDateTime expectedCheckin;
    @OneToMany(mappedBy ="assetEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AssetLogEntity> assetLogEntities;



}
