package com.project.displayDto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
public class PieChartDto {
    private String status;
    private BigDecimal count;
    public PieChartDto(String status, BigDecimal count) {
        this.status = status;
        this.count = count;
    }

}
