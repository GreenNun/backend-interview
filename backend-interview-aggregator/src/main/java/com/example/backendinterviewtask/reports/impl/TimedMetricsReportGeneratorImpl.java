package com.example.backendinterviewtask.reports.impl;

import com.example.backendinterviewtask.comparator.TimedMetricsReportComparator;
import com.example.backendinterviewtask.dto.BrandMetrics;
import com.example.backendinterviewtask.dto.Metric;
import com.example.backendinterviewtask.factory.MetricsReportsFactory;
import com.example.backendinterviewtask.model.MetricsReport;
import com.example.backendinterviewtask.model.TimedMetricsReport;
import com.example.backendinterviewtask.reports.TimedMetricsReportGenerator;
import com.example.backendinterviewtask.service.MetricsCollector;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by GreenNun on 2019-04-22.
 * <p>
 * Total sum of the metric counts, grouped by datetime (rounded to the hour), sorted by datetime ascending, i.e.
 * <pre>
 * {
 *      "dateTime": "2018-09-11T19:00:00Z",
 *      "metrics": [
 *          {"impression": 10, "click": 1, "interaction": 0}
 *      ]
 * }
 * </pre>
 */
@Component
public class TimedMetricsReportGeneratorImpl extends AbstractTimedReportGenerator
        implements TimedMetricsReportGenerator {

    public TimedMetricsReportGeneratorImpl(MetricsCollector collector, MetricsReportsFactory metricsReportsFactory) {
        super(collector, metricsReportsFactory);
    }

    @Override
    public List<TimedMetricsReport> generate() {
        final Stream<BrandMetrics> brandMetricsStream = getBrandMetricsStream();

        return collectGroupedByTime(brandMetricsStream)
                .entrySet().stream()
                .map(entry -> getTimedMetricsReport(entry.getKey(), entry.getValue()))
                .sorted(new TimedMetricsReportComparator())
                .collect(Collectors.toList());
    }

    private TimedMetricsReport getTimedMetricsReport(ZonedDateTime dateTime, List<BrandMetrics> brandMetrics) {
        final Stream<Metric> metricStream = getMetricStream(brandMetrics.stream());
        final MetricsReport metricsReport = getMetricsReport(metricStream);

        return TimedMetricsReport.builder()
                .dateTime(dateTime.toString())
                .metrics(metricsReport.getMetrics())
                .build();
    }
}
