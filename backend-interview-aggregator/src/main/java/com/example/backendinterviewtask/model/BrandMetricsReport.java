package com.example.backendinterviewtask.model;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

/**
 * Created by GreenNun on 2019-04-22.
 */
@Data
@Builder
public class BrandMetricsReport {
    private int brandId;
    private String brandName;
    private Map<String, Long> metrics;
}
