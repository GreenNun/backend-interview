package com.example.backendinterviewtask.reports;

import com.example.backendinterviewtask.model.BrandMetricsReport;

import java.util.List;

/**
 * Created by GreenNun on 2019-04-22.
 */
public interface BrandMetricsReportGenerator extends GenericReportGenerator<List<BrandMetricsReport>> {
    @Override
    List<BrandMetricsReport> generate();
}
