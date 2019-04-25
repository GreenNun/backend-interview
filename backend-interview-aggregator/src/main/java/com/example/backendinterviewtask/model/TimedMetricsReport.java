package com.example.backendinterviewtask.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

/**
 * Created by GreenNun on 2019-04-22.
 */
@Data
@Builder
public class TimedMetricsReport {
    private String dateTime;
    private Map<String, Long> metrics;
}
