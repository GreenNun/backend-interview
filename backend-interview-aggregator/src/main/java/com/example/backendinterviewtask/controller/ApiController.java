package com.example.backendinterviewtask.controller;

import com.example.backendinterviewtask.model.BrandMetricsReport;
import com.example.backendinterviewtask.model.MetricsReport;
import com.example.backendinterviewtask.model.TimedBrandMetricsReport;
import com.example.backendinterviewtask.model.TimedMetricsReport;
import com.example.backendinterviewtask.reports.BrandMetricsReportGenerator;
import com.example.backendinterviewtask.reports.MetricsReportGenerator;
import com.example.backendinterviewtask.reports.TimedBrandMetricsReportGenerator;
import com.example.backendinterviewtask.reports.TimedMetricsReportGenerator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by GreenNun on 2019-04-22.
 */
@RestController
@RequestMapping("v1")
public class ApiController {
    private final MetricsReportGenerator metricsReportGenerator;
    private final TimedMetricsReportGenerator timedMetricsReportGenerator;
    private final BrandMetricsReportGenerator brandMetricsReportGenerator;
    private final TimedBrandMetricsReportGenerator timedBrandMetricsReportGenerator;

    public ApiController(MetricsReportGenerator metricsReportGenerator,
                         TimedMetricsReportGenerator timedMetricsReportGenerator,
                         BrandMetricsReportGenerator brandMetricsReportGenerator,
                         TimedBrandMetricsReportGenerator timedBrandMetricsReportGenerator) {

        this.metricsReportGenerator = metricsReportGenerator;
        this.timedMetricsReportGenerator = timedMetricsReportGenerator;
        this.brandMetricsReportGenerator = brandMetricsReportGenerator;
        this.timedBrandMetricsReportGenerator = timedBrandMetricsReportGenerator;
    }

    @GetMapping("/metricsReport")
    public MetricsReport metricsReport() {
        return metricsReportGenerator.generate();
    }

    @GetMapping("/timedMetricsReports")
    public List<TimedMetricsReport> timedMetricsReports() {
        return timedMetricsReportGenerator.generate();
    }

    @GetMapping("/brandMetricsReports")
    public List<BrandMetricsReport> brandMetricsReports() {
        return brandMetricsReportGenerator.generate();
    }

    @GetMapping("/timedBrandMetricsReport")
    public List<TimedBrandMetricsReport> timedBrandMetricsReport() {
        return timedBrandMetricsReportGenerator.generate();
    }
}
