package com.example.backendinterviewtask.factory.impl;

import com.example.backendinterviewtask.client.BrandsApiClient;
import com.example.backendinterviewtask.client.MetricsApiClient;
import com.example.backendinterviewtask.dto.BrandMetrics;
import com.example.backendinterviewtask.dto.Metric;
import com.example.backendinterviewtask.dto.MetricResponse;
import com.example.backendinterviewtask.model.MetricsReport;
import com.example.backendinterviewtask.service.impl.BrandNamesCacheImpl;
import com.example.backendinterviewtask.service.impl.BrandNamesCacheImplTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by GreenNun on 2019-04-22.
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {MetricsReportsFactoryImplTest.Config.class, MetricsReportsFactoryImpl.class})
public class MetricsReportsFactoryImplTest {
    @Autowired
    private MetricsReportsFactoryImpl factory;
    @Autowired
    private MetricsApiClient metricsApiClient;

    @Test
    public void getInstance() {
        verify(metricsApiClient, times(1)).getMetricTypes(); // init in post construct method

        final MetricsReport result = factory.getInstance();
        assertEquals("metrics size", 3, result.getMetrics().size());
        assertEquals("a count", 0, (long) result.getMetrics().get("a"));
        assertEquals("b count", 0, (long) result.getMetrics().get("b"));
        assertEquals("c count", 0, (long) result.getMetrics().get("c"));
        assertNull("not contains d", result.getMetrics().get("d"));
    }

    @TestConfiguration
    public static class Config {

        @Bean
        public MetricsApiClient metricsApiClient() {
            final MetricsApiClient mock = mock(MetricsApiClient.class);
            when(mock.getMetricTypes())
                    .thenReturn(Arrays.asList("a", "b", "c"));

            return mock;
        }
    }
}