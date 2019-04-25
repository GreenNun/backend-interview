package com.example.backendinterviewtask.comparator;

import com.example.backendinterviewtask.model.BrandMetricsReport;
import org.junit.Test;

import javax.xml.bind.SchemaOutputResolver;
import java.util.HashMap;
import java.util.TreeSet;

import static org.junit.Assert.*;

/**
 * Created by GreenNun on 2019-04-22.
 */
public class BrandMetricsReportComparatorTest {
    private final BrandMetricsReportComparator comparator = new BrandMetricsReportComparator("someMetricName");

    @Test
    public void compare() {
        final int compare1 = comparator.compare(getBrandMetricsReport(0), getBrandMetricsReport(1));
        assertEquals("grate", 1, compare1);

        final int compare2 = comparator.compare(getBrandMetricsReport(1), getBrandMetricsReport(1));
        assertEquals("equals", 0, compare2);

        final int compare3 = comparator.compare(getBrandMetricsReport(2), getBrandMetricsReport(1));
        assertEquals("less", -1, compare3);
    }

    private BrandMetricsReport getBrandMetricsReport(long count) {
        final HashMap<String, Long> metrics = new HashMap<>();
        metrics.put("someMetricName", count);

        return BrandMetricsReport.builder()
                .metrics(metrics)
                .build();
    }
}