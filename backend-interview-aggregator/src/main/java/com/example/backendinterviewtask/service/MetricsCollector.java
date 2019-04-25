package com.example.backendinterviewtask.service;

import com.example.backendinterviewtask.dto.MetricResponse;

import java.util.List;

/**
 * Created by GreenNun on 2019-04-22.
 */
public interface MetricsCollector {

    List<MetricResponse> getReports();
}
