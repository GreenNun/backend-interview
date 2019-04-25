package com.example.backendinterviewtask.transformer;

import com.example.backendinterviewtask.model.MetricsReport;

import java.util.function.BinaryOperator;

/**
 * Created by GreenNun on 2019-04-18.
 */
public class MetricsReportsReducer implements BinaryOperator<MetricsReport> {
    @Override
    public MetricsReport apply(MetricsReport metricsReport1, MetricsReport metricsReport2) {
        // sum counts for each metric
        metricsReport2.getMetrics().forEach((metric, count) -> {
            metricsReport1.getMetrics().computeIfPresent(metric, (key, val) -> val + count);
        });

        return metricsReport1;
    }
}
