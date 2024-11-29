package com.project.repository.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name="Requested_assets")
@Data
public class RequestedAssetEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "User_id")
    private UserEntity userEntity;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "Asset_id")
    private AssetEntity assetEntity;
    @Column(name="Created_at")
    private LocalDateTime createTime;
    @Column(name="Accepted_at")
    private LocalDateTime acceptTime;
    @Column(name="Denied_at")
    private LocalDateTime deniedTime;
    @Column(name="Updated_at")
    private LocalDateTime updateTime;




}
