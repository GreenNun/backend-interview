package com.example.backendinterviewtask.comparator;

import com.example.backendinterviewtask.model.TimedMetricsReport;

import java.util.Comparator;

/**
 * Created by GreenNun on 2019-04-22.
 */
public class TimedMetricsReportComparator implements Comparator<TimedMetricsReport> {
    @Override
    public int compare(TimedMetricsReport o1, TimedMetricsReport o2) {
        // sorted by datetime ascending
        return o1.getDateTime().compareTo(o2.getDateTime());
    }
}
