package spongecell.backend.api.model;

public class Metric {
    private String metric;
    private long count;

    public Metric() {}

    public Metric(String metric, long count) {
        this.metric = metric;
        this.count = count;
    }

    public String getMetric() {
        return this.metric;
    }

    public Metric setMetric(String metric) {
        this.metric = metric;
        return this;
    }

    public long getCount() {
        return this.count;
    }

    public Metric setCount(long count) {
        this.count = count;
        return this;
    }
}
