package com.project.dto;

import com.project.repository.entities.CompanyEntity;
import com.project.repository.entities.ModelEntity;
import com.project.repository.entities.StatusEntity;
import com.project.repository.entities.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class AssetDto {
    private long id;
    private String assetTag;
    private String name;
    private String serial;
    private Long model_id;
    private Long company_id;
    private Long status_id;
    private Long assignedUser_id;
    private String description;
    private LocalDateTime lastCheckout;
    private LocalDateTime expectedCheckin;
    private BigDecimal cost;
    private String department;
    private String location;
    private String purchasedFrom;
    private LocalDateTime purchaseDate;
}



