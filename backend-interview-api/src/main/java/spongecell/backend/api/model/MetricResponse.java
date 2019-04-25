package spongecell.backend.api.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MetricResponse {
    private int size;
    private List<BrandMetrics> brandMetrics = new ArrayList<>();

    public MetricResponse() {}

    public MetricResponse(int size, List<BrandMetrics> brandMetrics) {
        this.size = size;
        this.brandMetrics = brandMetrics;
    }

    public int getSize() {
        return this.size;
    }

    public MetricResponse setSize(int size) {
        this.size = size;
        return this;
    }

    public List<BrandMetrics> getBrandMetrics() {
        return this.brandMetrics;
    }

    public MetricResponse setBrandMetrics(List<BrandMetrics> brandMetrics) {
        this.brandMetrics = brandMetrics;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MetricResponse that = (MetricResponse) o;
        return size == that.size &&
                Objects.equals(brandMetrics, that.brandMetrics);
    }

    @Override
    public int hashCode() {
        return Objects.hash(size, brandMetrics);
    }

    @Override
    public String toString() {
        return "MetricResponse{" +
                "size=" + size +
                ", brandMetrics=" + brandMetrics +
                '}';
    }
}
