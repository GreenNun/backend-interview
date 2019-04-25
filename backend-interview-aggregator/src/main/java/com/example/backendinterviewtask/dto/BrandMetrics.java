package com.example.backendinterviewtask.dto;

import lombok.Data;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * Created by GreenNun on 2019-04-22.
 */
@Data
public class BrandMetrics {
    private int brandId;
    private ZonedDateTime dateTime;
    private List<Metric> metrics;
}
