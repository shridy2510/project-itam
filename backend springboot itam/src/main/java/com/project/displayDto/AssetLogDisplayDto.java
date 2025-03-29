package com.project.displayDto;

import com.project.repository.entities.AssetEntity;
import com.project.repository.entities.UserEntity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AssetLogDisplayDto {
    private Long id;
    private String assetName;
    private String adminName;
    private String userName;
    private String action;
    //create at
    private LocalDateTime created_at;




}
