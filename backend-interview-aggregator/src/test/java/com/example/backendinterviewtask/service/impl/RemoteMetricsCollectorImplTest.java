package com.example.backendinterviewtask.service.impl;

import com.example.backendinterviewtask.client.MetricsApiClient;
import com.example.backendinterviewtask.dto.BrandMetrics;
import com.example.backendinterviewtask.dto.Metric;
import com.example.backendinterviewtask.dto.MetricResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by GreenNun on 2019-04-22.
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {RemoteMetricsCollectorImplTest.Config.class, RemoteMetricsCollectorImpl.class})
public class RemoteMetricsCollectorImplTest {
    @Autowired
    private RemoteMetricsCollectorImpl collector;

    @Test
    public void getReports() {
        final List<MetricResponse> reports = collector.getReports();
        assertEquals("size", 3, reports.size());
    }

    @TestConfiguration
    public static class Config {

        @Bean
        public MetricsApiClient metricsApiClient() {
            final MetricsApiClient mock = mock(MetricsApiClient.class);
            when(mock.getMetrics())
                    .thenReturn(getMetricResponse())
                    .thenThrow(new RuntimeException())
                    .then((Answer<MetricResponse>) invocation -> {
                        TimeUnit.MILLISECONDS.sleep(100);
                        return getMetricResponse();
                    })
                    .then((Answer<MetricResponse>) invocation -> {
                        TimeUnit.MILLISECONDS.sleep(200);
                        return getMetricResponse();
                    })
                    .then((Answer<MetricResponse>) invocation -> {
                        TimeUnit.MILLISECONDS.sleep(500);
                        return getMetricResponse();
                    });

            return mock;
        }

        private MetricResponse getMetricResponse() {
            final MetricResponse response = new MetricResponse();
            response.setSize(2);
            response.setBrandMetrics(Arrays.asList(getBrandMetrics(), getBrandMetrics()));
            return response;
        }

        private static BrandMetrics getBrandMetrics() {
            final BrandMetrics brandMetrics0 = new BrandMetrics();

            final Metric metric00 = new Metric();
            metric00.setMetric("a");
            metric00.setCount(1L);

            final Metric metric01 = new Metric();
            metric01.setMetric("b");
            metric01.setCount(2L);

            brandMetrics0.setMetrics(Arrays.asList(metric00, metric01));
            return brandMetrics0;
        }
    }
}