package com.example.backendinterviewtask.comparator;

import com.example.backendinterviewtask.model.BrandMetricsReport;

import java.util.Comparator;

/**
 * Created by GreenNun on 2019-04-22.
 */
public class BrandMetricsReportComparator implements Comparator<BrandMetricsReport> {
    private final String metric;

    public BrandMetricsReportComparator(String metric) {
        this.metric = metric;
    }

    @Override
    public int compare(BrandMetricsReport o1, BrandMetricsReport o2) {
        // metric count descending
        return Long.compare(o2.getMetrics().get(metric), o1.getMetrics().get(metric));
    }
}
