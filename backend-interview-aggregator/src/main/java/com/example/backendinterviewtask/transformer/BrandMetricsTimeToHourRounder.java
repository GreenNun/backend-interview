package com.example.backendinterviewtask.transformer;

import com.example.backendinterviewtask.dto.BrandMetrics;

import java.time.ZonedDateTime;
import java.util.function.Function;

/**
 * Created by GreenNun on 2019-04-22.
 */
public class BrandMetricsTimeToHourRounder implements Function<BrandMetrics, BrandMetrics> {
    @Override
    public BrandMetrics apply(BrandMetrics brandMetrics) {
        final ZonedDateTime dateTime = roundToHour(brandMetrics.getDateTime());
        brandMetrics.setDateTime(dateTime);
        return brandMetrics;
    }

    private ZonedDateTime roundToHour(ZonedDateTime time) {
        return time
                .withMinute(0)
                .withSecond(0)
                .withNano(0);
    }
}
