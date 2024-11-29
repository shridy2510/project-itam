package com.project.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AssetLogDto {
    private Long id;
    private Long asset_id;
    private String admin_id;
    private String user_id;
    private String action;
}
