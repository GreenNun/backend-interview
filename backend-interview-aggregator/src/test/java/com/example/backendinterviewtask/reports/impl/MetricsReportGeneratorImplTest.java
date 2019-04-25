package com.example.backendinterviewtask.reports.impl;

import com.example.backendinterviewtask.model.MetricsReport;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

/**
 * Created by GreenNun on 2019-04-22.
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {MetricsReportGeneratorImpl.class})
public class MetricsReportGeneratorImplTest extends AbstractReportGeneratorTest {
    @Autowired
    private MetricsReportGeneratorImpl generator;

    @Test
    public void generate() {
        final MetricsReport report = generator.generate();
        assertEquals("same", getExpectedJson(), new Gson().toJson(report));
    }

    private String getExpectedJson() {
        return "{\"metrics\":{\"a\":6,\"b\":12,\"c\":0}}";
    }
}