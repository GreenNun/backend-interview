package com.example.backendinterviewtask.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GreenNun on 2019-04-22.
 */
@Data
public class MetricResponse {
    private int size;
    private List<BrandMetrics> brandMetrics = new ArrayList<>();
}
