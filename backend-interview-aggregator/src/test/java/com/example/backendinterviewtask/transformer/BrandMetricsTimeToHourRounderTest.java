package com.example.backendinterviewtask.transformer;

import com.example.backendinterviewtask.dto.BrandMetrics;
import org.junit.Test;

import java.time.ZonedDateTime;

import static org.junit.Assert.*;

/**
 * Created by GreenNun on 2019-04-22.
 */
public class BrandMetricsTimeToHourRounderTest {
    private final BrandMetricsTimeToHourRounder rounder = new BrandMetricsTimeToHourRounder();

    @Test
    public void apply() {
        final ZonedDateTime now = ZonedDateTime.now();
        final BrandMetrics brandMetrics = new BrandMetrics();
        brandMetrics.setDateTime(now);

        final ZonedDateTime result = rounder.apply(brandMetrics).getDateTime();
        assertEquals("year", now.getYear(), result.getYear());
        assertEquals("day", now.getDayOfYear(), result.getDayOfYear());
        assertEquals("hours", now.getHour(), result.getHour());
        assertEquals("minutes", 0, result.getMinute());
        assertEquals("seconds", 0, result.getSecond());
        assertEquals("nanoseconds", 0, result.getNano());
    }
}