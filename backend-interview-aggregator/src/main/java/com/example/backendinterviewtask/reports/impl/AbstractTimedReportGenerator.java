package com.example.backendinterviewtask.reports.impl;

import com.example.backendinterviewtask.transformer.BrandMetricsTimeToHourRounder;
import com.example.backendinterviewtask.dto.BrandMetrics;
import com.example.backendinterviewtask.factory.MetricsReportsFactory;
import com.example.backendinterviewtask.service.MetricsCollector;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by GreenNun on 2019-04-22.
 */
abstract class AbstractTimedReportGenerator extends AbstractReportGenerator {

    AbstractTimedReportGenerator(MetricsCollector collector, MetricsReportsFactory metricsReportsFactory) {
        super(collector, metricsReportsFactory);
    }

    Map<ZonedDateTime, List<BrandMetrics>> collectGroupedByTime(Stream<BrandMetrics> collectRawMetrics) {
        return collectRawMetrics
                .map(new BrandMetricsTimeToHourRounder())
                .collect(Collectors.groupingBy(BrandMetrics::getDateTime));
    }

    Map<Integer, List<BrandMetrics>> collectGroupedById() {
        return getBrandMetricsStream()
                .collect(Collectors.groupingBy(BrandMetrics::getBrandId));
    }
}
