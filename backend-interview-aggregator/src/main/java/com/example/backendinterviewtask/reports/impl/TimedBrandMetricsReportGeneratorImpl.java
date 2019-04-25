package com.example.backendinterviewtask.reports.impl;

import com.example.backendinterviewtask.comparator.TimedBrandMetricsReportComparator;
import com.example.backendinterviewtask.reports.TimedBrandMetricsReportGenerator;
import com.example.backendinterviewtask.service.BrandNamesCache;
import com.example.backendinterviewtask.dto.BrandMetrics;
import com.example.backendinterviewtask.dto.Metric;
import com.example.backendinterviewtask.factory.MetricsReportsFactory;
import com.example.backendinterviewtask.model.MetricsReport;
import com.example.backendinterviewtask.model.TimedBrandMetricsReport;
import com.example.backendinterviewtask.service.MetricsCollector;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by GreenNun on 2019-04-22.
 * <p>
 * Total sum of each metric collected, grouped by datetime (rounded to the hour) and brand, sorted by datetime, then brand name, ascending, i.e.
 * <pre>
 * {
 *      "brandId": 65,
 *      "brandName": "Brand A",
 *      "dateTime": "2018-09-11T19:00:00Z",
 *      "metrics": [
 *          {"impression": 10, "click": 1, "interaction": 0}
 *      ]
 * }
 * </pre>
 */
@Component
public class TimedBrandMetricsReportGeneratorImpl extends AbstractTimedReportGenerator
        implements TimedBrandMetricsReportGenerator {

    private final BrandNamesCache brandNamesCache;

    public TimedBrandMetricsReportGeneratorImpl(MetricsCollector collector,
                                                MetricsReportsFactory metricsReportsFactory,
                                                BrandNamesCache brandNamesCache) {
        super(collector, metricsReportsFactory);
        this.brandNamesCache = brandNamesCache;
    }

    @Override
    public List<TimedBrandMetricsReport> generate() {
        return collectGroupedById()
                .entrySet().stream()
                .flatMap(entry -> getGroupedByTimeStream(entry.getKey(), entry.getValue()))
                .sorted(new TimedBrandMetricsReportComparator())
                .collect(Collectors.toList());
    }

    private Stream<? extends TimedBrandMetricsReport> getGroupedByTimeStream(Integer brandId, List<BrandMetrics> brandMetrics) {
        final String brandName = brandNamesCache.getNameByBrandId(brandId);
        final Map<ZonedDateTime, List<BrandMetrics>> groupedByTime =
                collectGroupedByTime(brandMetrics.stream());

        return groupedByTime
                .entrySet().stream()
                .map(entry -> getTimedBrandMetricsReport(brandId, brandName, entry.getKey(), entry.getValue()));
    }

    private TimedBrandMetricsReport getTimedBrandMetricsReport(Integer brandId, String brandName, ZonedDateTime dateTime, List<BrandMetrics> brandMetrics) {
        final Stream<Metric> metricStream = getMetricStream(brandMetrics.stream());
        final MetricsReport metricsReport = getMetricsReport(metricStream);

        return TimedBrandMetricsReport.builder()
                .brandId(brandId)
                .brandName(brandName)
                .dateTime(dateTime.toString())
                .metrics(metricsReport.getMetrics())
                .build();
    }
}
