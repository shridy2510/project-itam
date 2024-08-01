package com.project.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModelDto {
    private Long id;
    private String name;
    private long categoryID;
    private long manufacturerID;
    private String model_number;
    private String description;
}
