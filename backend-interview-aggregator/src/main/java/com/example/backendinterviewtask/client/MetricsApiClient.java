package com.example.backendinterviewtask.client;

import com.example.backendinterviewtask.dto.MetricResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by GreenNun on 2019-04-22.
 */
@FeignClient(name = "metrics", url = "localhost:8081", path = "/v1")
public interface MetricsApiClient {

    @RequestMapping("metrics")
    MetricResponse getMetrics();

    @RequestMapping("metrics")
    MetricResponse getMetric(@RequestParam(value = "brandId") Integer brandId);

    @RequestMapping("metricTypes")
    List<String> getMetricTypes();
}
