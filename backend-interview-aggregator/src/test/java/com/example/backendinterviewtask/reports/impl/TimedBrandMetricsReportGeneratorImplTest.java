package com.example.backendinterviewtask.reports.impl;

import com.example.backendinterviewtask.model.TimedBrandMetricsReport;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by GreenNun on 2019-04-22.
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TimedBrandMetricsReportGeneratorImpl.class})
public class TimedBrandMetricsReportGeneratorImplTest extends AbstractReportGeneratorTest {
    @Autowired
    private TimedBrandMetricsReportGeneratorImpl generator;

    @Test
    public void generate() {
        final List<TimedBrandMetricsReport> reports = generator.generate();
        assertEquals("same", getExpectedJson(), new Gson().toJson(reports));
    }

    private String getExpectedJson() {
        return "[{\"dateTime\":\"2019-01-01T00:00Z[UTC]\",\"brandId\":16,\"brandName\":\"\",\"metrics\":{\"a\":1," +
                "\"b\":2,\"c\":0}},{\"dateTime\":\"2019-01-01T00:00Z[UTC]\",\"brandId\":17,\"brandName\":\"\"," +
                "\"metrics\":{\"a\":1,\"b\":2,\"c\":0}},{\"dateTime\":\"2019-01-01T00:00Z[UTC]\",\"brandId\":18," +
                "\"brandName\":\"\",\"metrics\":{\"a\":1,\"b\":2,\"c\":0}},{\"dateTime\":\"2019-01-01T00:00Z[UTC]\"," +
                "\"brandId\":13,\"brandName\":\"\",\"metrics\":{\"a\":1,\"b\":2,\"c\":0}},{\"dateTime\":" +
                "\"2019-01-01T00:00Z[UTC]\",\"brandId\":14,\"brandName\":\"\",\"metrics\":{\"a\":1,\"b\":2,\"c\":0}}," +
                "{\"dateTime\":\"2019-01-01T00:00Z[UTC]\",\"brandId\":15,\"brandName\":\"\",\"metrics\":{\"a\":1," +
                "\"b\":2,\"c\":0}}]";
    }
}