package com.project.displayDto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AssetDisplayDto {
    private String assetTag;
    private String name;
    private String serial;
    private String modelName;
    private String companyName;
    private String status;
    private String assignedUserName;
    private String description;
    private LocalDateTime lastCheckout;
    private LocalDateTime expectedCheckin;


}
