package com.example.backendinterviewtask.reports.impl;

import com.example.backendinterviewtask.dto.BrandMetrics;
import com.example.backendinterviewtask.dto.Metric;
import com.example.backendinterviewtask.dto.MetricResponse;
import com.example.backendinterviewtask.factory.MetricsReportsFactory;
import com.example.backendinterviewtask.model.MetricsReport;
import com.example.backendinterviewtask.service.MetricsCollector;
import com.example.backendinterviewtask.transformer.MetricsReportsReducer;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Stream;

/**
 * Created by GreenNun on 2019-04-22.
 */
@Slf4j
abstract class AbstractReportGenerator {
    private final MetricsCollector collector;
    private final MetricsReportsFactory metricsReportsFactory;

    AbstractReportGenerator(MetricsCollector collector, MetricsReportsFactory metricsReportsFactory) {
        this.collector = collector;
        this.metricsReportsFactory = metricsReportsFactory;
    }

    Stream<BrandMetrics> getBrandMetricsStream() {
        final List<MetricResponse> reports = collector.getReports();

        log.info("#### MetricResponses received in 300 ms: {}", reports.size());

        return reports.stream()
                .flatMap(metricResponse -> metricResponse.getBrandMetrics().stream());
    }

    Stream<Metric> getMetricStream(Stream<BrandMetrics> brandMetricsStream) {
        return brandMetricsStream
                .flatMap(brandMetrics -> brandMetrics.getMetrics().stream());
    }

    MetricsReport getMetricsReport(Stream<Metric> metricStream) {
        return metricStream
                .map(metric -> {
                    final MetricsReport report = metricsReportsFactory.getInstance();
                    report.setCount(metric.getMetric(), metric.getCount());
                    return report;
                })
                .reduce(new MetricsReportsReducer()).orElse(metricsReportsFactory.getInstance());
    }
}
