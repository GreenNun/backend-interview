package com.example.backendinterviewtask.reports.impl;

import com.example.backendinterviewtask.client.BrandsApiClient;
import com.example.backendinterviewtask.client.MetricsApiClient;
import com.example.backendinterviewtask.dto.Brand;
import com.example.backendinterviewtask.dto.BrandMetrics;
import com.example.backendinterviewtask.dto.Metric;
import com.example.backendinterviewtask.dto.MetricResponse;
import com.example.backendinterviewtask.factory.impl.MetricsReportsFactoryImpl;
import com.example.backendinterviewtask.service.impl.BrandNamesCacheImpl;
import com.example.backendinterviewtask.service.impl.RemoteMetricsCollectorImpl;
import org.mockito.stubbing.Answer;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by GreenNun on 2019-04-22.
 */
@ContextConfiguration(classes = {
        AbstractReportGeneratorTest.Config.class,
        BrandNamesCacheImpl.class,
        RemoteMetricsCollectorImpl.class,
        MetricsReportsFactoryImpl.class}
)
public class AbstractReportGeneratorTest {

    @TestConfiguration
    public static class Config {

        private static int counter = 1;

        @Bean
        public BrandsApiClient brandsApiClient() {
            final BrandsApiClient mock = mock(BrandsApiClient.class);

            when(mock.getBrands())
                    .thenReturn(Arrays.asList(getBrand(1, "1"), getBrand(2, "2")));

            when(mock.getBrand(3))
                    .thenReturn(getBrand(3, "3"));

            return mock;
        }

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

            when(mock.getMetricTypes())
                    .thenReturn(Arrays.asList("a", "b", "c"));

            return mock;
        }

        private Brand getBrand(int id, String name) {
            final Brand brand = new Brand();
            brand.setId(id);
            brand.setName(name);
            return brand;
        }

        private MetricResponse getMetricResponse() {
            final MetricResponse response = new MetricResponse();
            response.setSize(2);
            response.setBrandMetrics(Arrays.asList(getBrandMetrics(), getBrandMetrics()));
            return response;
        }

        private BrandMetrics getBrandMetrics() {
            final BrandMetrics brandMetrics0 = new BrandMetrics();
            brandMetrics0.setDateTime(ZonedDateTime.of(2019, 1, 1, 0, 0, 0, 0, ZoneId.of("UTC")));
            brandMetrics0.setBrandId(counter++);

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
