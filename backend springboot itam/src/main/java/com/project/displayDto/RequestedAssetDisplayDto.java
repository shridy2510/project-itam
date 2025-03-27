package com.project.displayDto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Getter
@Setter
public class RequestedAssetDisplayDto {
    private long id;
    private String assetTag;
    private String name;
    private String modelName;
    private String status;
    private LocalDateTime deniedTime;
    private String assignedUserName;
    private LocalDateTime expectedCheckin;
    private LocalDateTime expectedCheckout;
    private LocalDateTime actualCheckin;
    private LocalDateTime acceptTime;
    private LocalDateTime requestDate;
    private String adminName;
    private String location;
    private long assetId;
    private long userId;
    private String requestType;
}
