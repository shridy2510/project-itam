package com.project.displayDto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class BarChartDto {
    private String status;
    private BigDecimal cost;

    public BarChartDto(String status, BigDecimal cost) {
        this.status = status;
        this.cost = cost;
    }
}
