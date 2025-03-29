package com.project.displayDto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class AssetDisplayDto {
    private long id;
    private String assetTag;
    private String name;
    private String serial;
    private String modelName;
    private String companyName;
    private String status;
    private String assignedUserName;
    private Long assignedUserId;
    private String description;
    private LocalDateTime lastCheckout;
    private LocalDateTime expectedCheckin;
    private BigDecimal cost;
    private long dueDuration;
    private String department;
    private String location;
    private String purchasedFrom;
    private LocalDateTime purchaseDate;

}
