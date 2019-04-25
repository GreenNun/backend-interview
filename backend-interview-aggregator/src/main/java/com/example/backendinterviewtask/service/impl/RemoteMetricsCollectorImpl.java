package com.example.backendinterviewtask.service.impl;

import com.example.backendinterviewtask.dto.MetricResponse;
import com.example.backendinterviewtask.client.MetricsApiClient;
import com.example.backendinterviewtask.service.MetricsCollector;
import io.reactivex.Observable;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by GreenNun on 2019-04-22.
 */
@Service
public class RemoteMetricsCollectorImpl implements MetricsCollector {
    private final static long TIMEOUT_MS = 300;
    private final MetricsApiClient metricsApiClient;

    public RemoteMetricsCollectorImpl(MetricsApiClient metricsApiClient) {
        this.metricsApiClient = metricsApiClient;
    }

    @Override
    public List<MetricResponse> getReports() {
        RxJavaPlugins.setErrorHandler(e -> {});

        return Observable
                .interval(0, TimeUnit.NANOSECONDS)
                .flatMap(i -> Observable.fromCallable(metricsApiClient::getMetrics)
                        .onExceptionResumeNext(Observable.never())
                        .subscribeOn(Schedulers.io()))
                .take(TIMEOUT_MS, TimeUnit.MILLISECONDS)
                .toList()
                .blockingGet();
    }
}
