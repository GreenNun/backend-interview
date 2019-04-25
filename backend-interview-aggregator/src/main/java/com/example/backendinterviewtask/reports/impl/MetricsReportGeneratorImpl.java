package com.example.backendinterviewtask.reports.impl;

import com.example.backendinterviewtask.dto.BrandMetrics;
import com.example.backendinterviewtask.dto.Metric;
import com.example.backendinterviewtask.factory.MetricsReportsFactory;
import com.example.backendinterviewtask.model.MetricsReport;
import com.example.backendinterviewtask.reports.MetricsReportGenerator;
import com.example.backendinterviewtask.service.MetricsCollector;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

/**
 * Created by GreenNun on 2019-04-22.
 * <p>
 * Total sum of the metric counts collected, grouped by metric, i.e.
 * <pre>
 *    {
 *       "impression": 65,
 *       "click": 7,
 *       "interaction": 17,
 *    }
 * </pre>
 */
@Component
public class MetricsReportGeneratorImpl extends AbstractReportGenerator implements MetricsReportGenerator {

    public MetricsReportGeneratorImpl(MetricsCollector collector, MetricsReportsFactory metricsReportsFactory) {
        super(collector, metricsReportsFactory);
    }

    @Override
    public MetricsReport generate() {
        final Stream<BrandMetrics> brandMetricsStream = getBrandMetricsStream();
        final Stream<Metric> metricStream = getMetricStream(brandMetricsStream);

        return getMetricsReport(metricStream);
    }
}
