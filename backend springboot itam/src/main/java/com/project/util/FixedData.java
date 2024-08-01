package com.project.util;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Setter
@Getter
@ConfigurationProperties(prefix = "fixed-data")
public class FixedData {
    private List<String> statusType;
    private List<String> assetType;
    private List<String> actionType;






}
