package com.example.backendinterviewtask.model;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

/**
 * Created by GreenNun on 2019-04-22.
 */
@Data
@Builder
public class MetricsReport {
    private Map<String, Long> metrics;

    public long getCount(String name) {
        return metrics.get(name);
    }

    public void setCount(String name, long count) {
        metrics.put(name, count);
    }
}
