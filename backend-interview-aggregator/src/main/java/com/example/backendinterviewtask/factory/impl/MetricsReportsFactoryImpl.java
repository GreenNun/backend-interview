package com.example.backendinterviewtask.factory.impl;

import com.example.backendinterviewtask.client.MetricsApiClient;
import com.example.backendinterviewtask.factory.MetricsReportsFactory;
import com.example.backendinterviewtask.model.MetricsReport;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by GreenNun on 2019-04-22.
 */
@Component
public class MetricsReportsFactoryImpl implements MetricsReportsFactory {
    private List<String> metricTypes;

    public MetricsReportsFactoryImpl(MetricsApiClient metricsApiClient) {
        this.metricTypes = metricsApiClient.getMetricTypes();
    }

    @Override
    public MetricsReport getInstance() {
        return MetricsReport.builder()
                .metrics(initMetrics())
                .build();
    }

    private Map<String, Long> initMetrics() {
        final Map<String, Long> metrics = getFixedSizeMetricsMap();
        metricTypes.forEach(metricName -> metrics.put(metricName, 0L));
        return metrics;
    }

    private HashMap<String, Long> getFixedSizeMetricsMap() {
        return new HashMap<>(metricTypes.size(), 2);
    }
}
