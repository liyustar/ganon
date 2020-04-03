package com.lyx.ganon.common;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.lyx.ganon.common.exception.GanonCommonException;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * 用来测试接口调用的压测数值
 * @author liyuxing
 */
public class MetricsInvoker {

    private final int invokeTimes;
    private final int invokeInterval;
    private final TimeUnit timeUnit;
    private final int threadNum;

    public MetricsInvoker(int invokeTimes, int invokeInterval, TimeUnit timeUnit, int threadNum) {
        this.invokeTimes = invokeTimes;
        this.invokeInterval = invokeInterval;
        this.timeUnit = timeUnit;
        this.threadNum = threadNum;
    }

    public static MetricsInvoker.Builder createBuilder() {
        return new Builder();
    }

    public <T> Metrics invoke(Callable<T> callable) throws InterruptedException, ExecutionException {
        return invoke(callable, (t) -> true);
    }

    public <T> Metrics invoke(Callable<T> callable, Predicate<T> predicate) throws InterruptedException, ExecutionException {
        List<Integer> allocateSize = Lists.newArrayListWithCapacity(threadNum);
        int remainTimes = invokeTimes;
        for (int i = 0; i < threadNum; i++) {
            allocateSize.add(Math.min(remainTimes, invokeTimes / threadNum));
            remainTimes -= invokeTimes / threadNum;
        }

        Metrics metrics = new Metrics(0);
        ExecutorService executorService = new ThreadPoolExecutor(
                threadNum,
                threadNum,
                0,
                TimeUnit.SECONDS,
                new SynchronousQueue<>(),
                new ThreadFactoryBuilder().setNameFormat("MetricsInvoker-%d").build());

        metrics.startAt(System.currentTimeMillis());
        List<Future<Metrics>> futureList = allocateSize.stream()
                .map(x -> executorService.submit(() -> invokeInner(x, callable, predicate)))
                .collect(Collectors.toList());

        List<Metrics> metricsList = Lists.newArrayListWithCapacity(threadNum);
        for (int i = 0; i < threadNum; i++) {
            Metrics threadMetrics = futureList.get(i).get();
            metricsList.add(threadMetrics);
        }
        metrics.endAt(System.currentTimeMillis());

        return metricsList.stream().reduce(metrics, Metrics::merge);
    }

    private <T> Metrics invokeInner(int times, Callable<T> callable, Predicate<T> predicate) {
        Metrics metrics = new Metrics(times);
        metrics.startAt(System.currentTimeMillis());

        for (int i = 0; i < times; i++) {
            long start = System.currentTimeMillis();
            try {
                T t = callable.call();
                if (predicate.test(t)) {
                    metrics.addSuccess();
                } else {
                    metrics.addFail();
                }
            } catch (Exception e) {
                metrics.addFail();
            }
            long end = System.currentTimeMillis();
            metrics.addLatency((int) (end - start));
            metrics.addTotal();

            if (invokeInterval != 0) {
                try {
                    timeUnit.sleep(invokeInterval);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new GanonCommonException("interrupted");
                }
            }
        }

        metrics.endAt(System.currentTimeMillis());
        return metrics;
    }

    @Getter
    public static class Metrics {
        private int total;
        private int success;
        private int fail;
        private long start;
        private long end;
        private final ArrayList<Integer> latencyList;

        public Metrics(int n) {
            this.latencyList = Lists.newArrayListWithCapacity(n);
        }

        public Metrics merge(Metrics b) {
            this.total += b.getTotal();
            this.success += b.getSuccess();
            this.fail += b.getFail();
            this.start = Math.min(this.start, b.getStart());
            this.end = Math.max(this.end, b.getEnd());
            this.latencyList.addAll(b.getLatencyList());
            return this;
        }

        public void startAt(long start) {
            this.start = start;
        }

        public void endAt(long end) {
            this.end = end;
        }

        public void addSuccess() {
            success++;
        }

        public void addFail() {
            fail++;
        }

        public void addTotal() {
            total++;
        }

        public void addLatency(int latency) {
            latencyList.add(latency);
        }

        @Override
        public String toString() {
            long period = end - start;
            double tps = total * 1000.0 / period;

            latencyList.sort(Comparator.comparingInt(a -> a));
            int size = latencyList.size();
            int idx90 = (int) Math.floor(0.9 * size);
            int idx95 = (int) Math.floor(0.95 * size);
            int idx99 = (int) Math.floor(0.99 * size);
            double avg= latencyList.stream().collect(Collectors.averagingDouble(x -> x));

            return "Metrics{" +
                    "total=" + total +
                    ", success=" + success +
                    ", fail=" + fail +
                    ", start=" + start +
                    ", end=" + end +
                    ", period=" + period +
                    ", tps=" + tps +
                    ", latency_avg=" + avg +
                    ", latency_90=" + latencyList.get(idx90) +
                    ", latency_95=" + latencyList.get(idx95) +
                    ", latency_99=" + latencyList.get(idx99) +
                    '}';
        }
    }

    public static class Builder {
        private int invokeTimes;
        private int invokeInterval;
        private TimeUnit timeUnit = null;
        private int threadNum;

        public Builder setInvokeTimes(int invokeTimes) {
            this.invokeTimes = invokeTimes;
            return this;
        }

        public Builder setInvokeInterval(int invokeInterval) {
            this.invokeInterval = invokeInterval;
            return this;
        }

        public Builder setInvokeTimeUnit(TimeUnit timeUnit) {
            this.timeUnit = timeUnit;
            return this;
        }

        public Builder setThreadNum(int threadNum) {
            this.threadNum = threadNum;
            return this;
        }

        public MetricsInvoker build() {
            if (invokeTimes == 0) {
                invokeTimes = 1;
            }
            if (timeUnit == null) {
                timeUnit = TimeUnit.MILLISECONDS;
            }
            if (threadNum == 0) {
                threadNum = 1;
            }
            return new MetricsInvoker(invokeTimes, invokeInterval, timeUnit, threadNum);
        }
    }
}
