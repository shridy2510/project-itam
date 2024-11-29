package com.project.dto;

import lombok.Data;

@Data
public class RequestedAssetDto {
    private long id;
    private String userId;
    private long assetId;
}
