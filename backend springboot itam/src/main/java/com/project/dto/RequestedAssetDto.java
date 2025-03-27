package com.project.dto;

import com.project.repository.entities.AssetEntity;
import com.project.repository.entities.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RequestedAssetDto {
    private long id;
    private String userId;
    private long assetId;
    private String status;
    private LocalDateTime expectedCheckin;
    private LocalDateTime expectedCheckout;
    private LocalDateTime actualCheckin;
    private String adminId;
    private String location;
    private String requestType;
}
