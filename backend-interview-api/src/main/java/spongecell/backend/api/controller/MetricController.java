package spongecell.backend.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import spongecell.backend.api.exception.ApiException;
import spongecell.backend.api.model.BrandMetrics;
import spongecell.backend.api.model.Metric;
import spongecell.backend.api.model.MetricResponse;

import java.time.Clock;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.SplittableRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static spongecell.backend.api.controller.BrandController.BRANDS;

@RestController
@RequestMapping("v1")
public class MetricController {

    private static final int MAX_RESPONSES = 30;
    private static final long MAX_SLEEP_MS = 400L;
    private static final long MAX_DAYS = 2L;
    private static final List<Long> MAX_VALUES = Arrays.asList(1_000_001L, 11L, 31L);
    private static final List<String> METRICS = Arrays.asList("impression", "click", "interaction");
    private static final SplittableRandom RAND = new SplittableRandom(System.currentTimeMillis());
    private static final ZonedDateTime START_DATE_TIME = ZonedDateTime.now(Clock.systemUTC()).minusDays(MAX_DAYS);

    @RequestMapping("metrics")
    public MetricResponse getMetrics(@RequestParam("brandId") Optional<Integer> optBrandId) {
        int numResponses = RAND.nextInt(MAX_RESPONSES);

        optBrandId.ifPresent(BrandController::validateBrandId);

        List<BrandMetrics> brandMetrics =
                IntStream.range(0, numResponses)
                        .mapToObj(i -> {
                            int brandId = optBrandId.orElse(BRANDS.get(RAND.nextInt(0, BRANDS.size())).getId());
                            return new BrandMetrics(brandId, getRandomMetrics(), getRandomDateTime());
                        }).collect(Collectors.toList());

        try {
            Thread.sleep(RAND.nextLong(MAX_SLEEP_MS));
        } catch (InterruptedException e) {
            //
        }

        if (RAND.nextInt(100) % 7 == 0) {
            throw new ApiException("Exception retrieving data from backend");
        }

        return new MetricResponse(numResponses, brandMetrics);
    }

    @RequestMapping("metricTypes")
    public List<String> getMetricTypes() {
        return METRICS;
    }

    private static List<Metric> getRandomMetrics() {
        return IntStream.range(0, RAND.nextInt(METRICS.size() + 1))
                .mapToObj(j -> new Metric(METRICS.get(j), RAND.nextLong(0, MAX_VALUES.get(j))))
                .collect(Collectors.toList());
    }

    private static ZonedDateTime getRandomDateTime() {
        return START_DATE_TIME
                .plusDays(RAND.nextLong(MAX_DAYS))
                .plusHours(RAND.nextLong(24))
                .plusMinutes(RAND.nextLong(60))
                .truncatedTo(ChronoUnit.MINUTES);
    }
}
