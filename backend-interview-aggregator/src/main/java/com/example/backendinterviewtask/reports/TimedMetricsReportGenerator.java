package com.example.backendinterviewtask.reports;

import com.example.backendinterviewtask.model.TimedMetricsReport;

import java.util.List;

/**
 * Created by GreenNun on 2019-04-22.
 */
public interface TimedMetricsReportGenerator extends GenericReportGenerator<List<TimedMetricsReport>> {
    @Override
    List<TimedMetricsReport> generate();
}
