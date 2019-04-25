package com.example.backendinterviewtask.transformer;

import com.example.backendinterviewtask.model.MetricsReport;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

/**
 * Created by GreenNun on 2019-04-22.
 */
public class MetricsReportsReducerTest {
    private final MetricsReportsReducer reducer = new MetricsReportsReducer();

    @Test
    public void apply() {
        final HashMap<String, Long> metrics0 = new HashMap<>();
        metrics0.put("a", 1L);
        metrics0.put("b", 2L);

        final MetricsReport report0 = MetricsReport.builder()
                .metrics(metrics0)
                .build();

        final HashMap<String, Long> metrics1 = new HashMap<>();
        metrics1.put("a", 0L);
        metrics1.put("b", 1L);

        final MetricsReport report1 = MetricsReport.builder()
                .metrics(metrics1)
                .build();

        final MetricsReport report = reducer.apply(report0, report1);
        assertEquals("a count", 1, report.getCount("a"));
        assertEquals("b count", 3, report.getCount("b"));
    }
}