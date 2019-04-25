package com.example.backendinterviewtask.reports.impl;

import com.example.backendinterviewtask.comparator.BrandMetricsReportComparator;
import com.example.backendinterviewtask.dto.BrandMetrics;
import com.example.backendinterviewtask.dto.Metric;
import com.example.backendinterviewtask.factory.MetricsReportsFactory;
import com.example.backendinterviewtask.model.BrandMetricsReport;
import com.example.backendinterviewtask.model.MetricsReport;
import com.example.backendinterviewtask.reports.BrandMetricsReportGenerator;
import com.example.backendinterviewtask.service.BrandNamesCache;
import com.example.backendinterviewtask.service.MetricsCollector;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by GreenNun on 2019-04-22.
 * <p>
 * Total sum of each metric collected, grouped by brand, sorted by total impressions descending, i.e.
 * <pre>
 *    {
 *       "brandId": 65,
 *       "brandName": "Brand A",
 *       "metrics": [
 *         {"impression": 10, "click": 1, "interaction": 0}
 *       ]
 *    }
 * </pre>
 */
@Component
public class BrandMetricsReportGeneratorImpl extends AbstractTimedReportGenerator
        implements BrandMetricsReportGenerator {

    private final BrandNamesCache brandNamesCache;
    @Value("${brand-metrics-report-generator.sort-by-metric}")
    private String sortByMetric;

    public BrandMetricsReportGeneratorImpl(MetricsCollector collector,
                                           MetricsReportsFactory metricsReportsFactory,
                                           BrandNamesCache brandNamesCache) {
        super(collector, metricsReportsFactory);
        this.brandNamesCache = brandNamesCache;
    }

    @Override
    public List<BrandMetricsReport> generate() {
        return collectGroupedById().entrySet().stream()
                .map(entry -> getBrandMetricsReport(entry.getKey(), entry.getValue()))
                .sorted(new BrandMetricsReportComparator(sortByMetric))
                .collect(Collectors.toList());
    }

    private BrandMetricsReport getBrandMetricsReport(Integer brandId, List<BrandMetrics> brandMetrics) {
        final String brandName = brandNamesCache.getNameByBrandId(brandId);
        final Stream<Metric> metricStream = getMetricStream(brandMetrics.stream());
        final MetricsReport metricsReport = getMetricsReport(metricStream);

        return BrandMetricsReport.builder()
                .brandId(brandId)
                .brandName(brandName)
                .metrics(metricsReport.getMetrics())
                .build();
    }
}
