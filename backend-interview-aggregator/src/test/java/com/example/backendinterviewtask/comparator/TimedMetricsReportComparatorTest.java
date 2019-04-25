package com.example.backendinterviewtask.comparator;

import com.example.backendinterviewtask.model.TimedMetricsReport;
import org.junit.Test;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.junit.Assert.assertEquals;

/**
 * Created by GreenNun on 2019-04-22.
 */
public class TimedMetricsReportComparatorTest {
    private final TimedMetricsReportComparator comparator = new TimedMetricsReportComparator();

    @Test
    public void compare() {
        final int compare1 = comparator.compare(
                getReport(ZonedDateTime.of(2019, 1, 1, 0, 0, 0, 0, ZoneId.of("UTC"))),
                getReport(ZonedDateTime.of(2019, 1, 2, 0, 0, 0, 0, ZoneId.of("UTC")))
        );

        assertEquals("less", -1, compare1);

        final int compare2 = comparator.compare(
                getReport(ZonedDateTime.of(2019, 1, 1, 0, 0, 0, 0, ZoneId.of("UTC"))),
                getReport(ZonedDateTime.of(2019, 1, 1, 0, 0, 0, 0, ZoneId.of("UTC")))
        );

        assertEquals("equals", 0, compare2);

        final int compare3 = comparator.compare(
                getReport(ZonedDateTime.of(2019, 1, 2, 0, 0, 0, 0, ZoneId.of("UTC"))),
                getReport(ZonedDateTime.of(2019, 1, 1, 0, 0, 0, 0, ZoneId.of("UTC")))
        );

        assertEquals("grate", 1, compare3);
    }

    private TimedMetricsReport getReport(ZonedDateTime dateTime) {
        return TimedMetricsReport.builder()
                .dateTime(dateTime.toString())
                .build();
    }
}