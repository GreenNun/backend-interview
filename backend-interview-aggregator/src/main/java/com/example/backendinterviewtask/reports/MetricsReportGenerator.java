package com.example.backendinterviewtask.reports;

import com.example.backendinterviewtask.model.MetricsReport;

/**
 * Created by GreenNun on 2019-04-22.
 */
public interface MetricsReportGenerator extends GenericReportGenerator<MetricsReport> {
    @Override
    MetricsReport generate();
}
