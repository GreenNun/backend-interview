package com.example.backendinterviewtask.reports.impl;

import com.example.backendinterviewtask.model.TimedMetricsReport;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by GreenNun on 2019-04-22.
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TimedMetricsReportGeneratorImpl.class})
public class TimedMetricsReportGeneratorImplTest extends AbstractReportGeneratorTest {
    @Autowired
    private TimedMetricsReportGeneratorImpl generator;

    @Test
    public void generate() {
        final List<TimedMetricsReport> reports = generator.generate();
        assertEquals("same", getExpectedJson(), new Gson().toJson(reports));
    }

    private String getExpectedJson() {
        return "[{\"dateTime\":\"2019-01-01T00:00Z[UTC]\",\"metrics\":{\"a\":6,\"b\":12,\"c\":0}}]";
    }
}