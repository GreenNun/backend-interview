package com.example.backendinterviewtask.reports;

import com.example.backendinterviewtask.model.TimedBrandMetricsReport;

import java.util.List;

/**
 * Created by GreenNun on 2019-04-22.
 */
public interface TimedBrandMetricsReportGenerator extends GenericReportGenerator<List<TimedBrandMetricsReport>> {
    @Override
    List<TimedBrandMetricsReport> generate();
}
