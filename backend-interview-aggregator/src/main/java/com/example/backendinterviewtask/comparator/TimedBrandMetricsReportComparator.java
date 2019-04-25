package com.example.backendinterviewtask.comparator;

import com.example.backendinterviewtask.model.TimedBrandMetricsReport;

import java.util.Comparator;

/**
 * Created by GreenNun on 2019-04-22.
 */
public class TimedBrandMetricsReportComparator implements Comparator<TimedBrandMetricsReport> {
    @Override
    public int compare(TimedBrandMetricsReport o1, TimedBrandMetricsReport o2) {
        // sorted by datetime, then brand name, ascending
        final int byDate = o1.getDateTime().compareTo(o2.getDateTime());

        if (byDate != 0)
            return byDate;

        return o1.getBrandName().compareTo(o2.getBrandName());
    }
}
