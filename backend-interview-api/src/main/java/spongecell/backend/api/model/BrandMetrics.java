package spongecell.backend.api.model;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BrandMetrics {
    private int brandId;
    private List<Metric> metrics = new ArrayList<>();
    private ZonedDateTime dateTime;

    public BrandMetrics() {}

    public BrandMetrics(int brandId, List<Metric> metrics, ZonedDateTime dateTime) {
        this.brandId = brandId;
        this.metrics = metrics;
        this.dateTime = dateTime;
    }

    public int getBrandId() {
        return brandId;
    }

    public BrandMetrics setBrandId(int brandId) {
        this.brandId = brandId;
        return this;
    }

    public List<Metric> getMetrics() {
        return metrics;
    }

    public BrandMetrics setMetrics(List<Metric> metrics) {
        this.metrics = metrics;
        return this;
    }

    public ZonedDateTime getDateTime() {
        return dateTime;
    }

    public BrandMetrics setDateTime(ZonedDateTime dateTime) {
        this.dateTime = dateTime;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BrandMetrics that = (BrandMetrics) o;
        return brandId == that.brandId &&
                Objects.equals(metrics, that.metrics) &&
                Objects.equals(dateTime, that.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brandId, metrics, dateTime);
    }

    @Override
    public String toString() {
        return "BrandMetrics{" +
                "brandId=" + brandId +
                ", metrics=" + metrics +
                ", dateTime=" + dateTime +
                '}';
    }
}
