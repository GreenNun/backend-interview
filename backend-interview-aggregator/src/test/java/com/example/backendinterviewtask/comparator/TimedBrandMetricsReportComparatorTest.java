package com.example.backendinterviewtask.comparator;

import com.example.backendinterviewtask.model.TimedBrandMetricsReport;
import org.junit.Test;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.TreeSet;

import static org.junit.Assert.*;

/**
 * Created by GreenNun on 2019-04-22.
 */
public class TimedBrandMetricsReportComparatorTest {
    private final TimedBrandMetricsReportComparator comparator = new TimedBrandMetricsReportComparator();

    @Test
    public void compare_by_name() {
        final int compare1 = comparator.compare(
                getReport("a", ZonedDateTime.of(2019, 1, 1, 0, 0, 0, 0, ZoneId.of("UTC"))),
                getReport("b", ZonedDateTime.of(2019, 1, 1, 0, 0, 0, 0, ZoneId.of("UTC")))
        );

        assertEquals("less", -1, compare1);

        final int compare2 = comparator.compare(
                getReport("a", ZonedDateTime.of(2019, 1, 1, 0, 0, 0, 0, ZoneId.of("UTC"))),
                getReport("a", ZonedDateTime.of(2019, 1, 1, 0, 0, 0, 0, ZoneId.of("UTC")))
        );

        assertEquals("equals", 0, compare2);

        final int compare3 = comparator.compare(
                getReport("b", ZonedDateTime.of(2019, 1, 1, 0, 0, 0, 0, ZoneId.of("UTC"))),
                getReport("a", ZonedDateTime.of(2019, 1, 1, 0, 0, 0, 0, ZoneId.of("UTC")))
        );

        assertEquals("grate", 1, compare3);
    }

    @Test
    public void compare_by_date() {
        final int compare1 = comparator.compare(
                getReport("", ZonedDateTime.of(2019, 1, 1, 0, 0, 0, 0, ZoneId.of("UTC"))),
                getReport("", ZonedDateTime.of(2019, 1, 2, 0, 0, 0, 0, ZoneId.of("UTC")))
        );

        assertEquals("less", -1, compare1);

        final int compare2 = comparator.compare(
                getReport("", ZonedDateTime.of(2019, 1, 1, 0, 0, 0, 0, ZoneId.of("UTC"))),
                getReport("", ZonedDateTime.of(2019, 1, 1, 0, 0, 0, 0, ZoneId.of("UTC")))
        );

        assertEquals("equals", 0, compare2);

        final int compare3 = comparator.compare(
                getReport("", ZonedDateTime.of(2019, 1, 2, 0, 0, 0, 0, ZoneId.of("UTC"))),
                getReport("", ZonedDateTime.of(2019, 1, 1, 0, 0, 0, 0, ZoneId.of("UTC")))
        );

        assertEquals("grate", 1, compare3);
    }

    private TimedBrandMetricsReport getReport(String brandName, ZonedDateTime dateTime) {
        return TimedBrandMetricsReport.builder()
                .brandName(brandName)
                .dateTime(dateTime.toString())
                .build();
    }
}