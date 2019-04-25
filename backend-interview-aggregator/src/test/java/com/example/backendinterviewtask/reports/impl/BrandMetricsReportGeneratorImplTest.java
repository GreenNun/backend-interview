package com.example.backendinterviewtask.reports.impl;

import com.example.backendinterviewtask.model.BrandMetricsReport;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by GreenNun on 2019-04-22.
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {BrandMetricsReportGeneratorImpl.class})
@TestPropertySource(properties = {
        "brand-metrics-report-generator.sort-by-metric=a"
})
public class BrandMetricsReportGeneratorImplTest extends AbstractReportGeneratorTest {
    @Autowired
    private BrandMetricsReportGeneratorImpl generator;

    @Test
    public void generate() {
        final List<BrandMetricsReport> reports = generator.generate();

        assertEquals("same", getExpectedJson(), new Gson().toJson(reports));
    }

    private String getExpectedJson() {
        return "[{\"brandId\":1,\"brandName\":\"1\",\"metrics\":{\"a\":1,\"b\":2,\"c\":0}},{\"brandId\":2," +
                "\"brandName\":\"2\",\"metrics\":{\"a\":1,\"b\":2,\"c\":0}},{\"brandId\":3,\"brandName\":\"3\"," +
                "\"metrics\":{\"a\":1,\"b\":2,\"c\":0}},{\"brandId\":4,\"brandName\":\"\",\"metrics\":" +
                "{\"a\":1,\"b\":2,\"c\":0}},{\"brandId\":5,\"brandName\":\"\",\"metrics\":{\"a\":1,\"b\":2,\"c\":0}}," +
                "{\"brandId\":6,\"brandName\":\"\",\"metrics\":{\"a\":1,\"b\":2,\"c\":0}}]";
    }
}